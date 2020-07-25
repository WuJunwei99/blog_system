package com.itheima.web.admin;

import com.github.pagehelper.PageInfo;
import com.itheima.model.ResponseData.ArticleResponseData;
import com.itheima.model.ResponseData.StaticticsBo;
import com.itheima.model.domain.Article;
import com.itheima.model.domain.Category;
import com.itheima.model.domain.Comment;
import com.itheima.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @Classname AdminController
 * @Description 后台管理模块
 * @Date 2019-3-14 10:39
 * @Created by CrazyStone
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ISiteService siteServiceImpl;
    @Autowired
    private IArticleService articleServiceImpl;
    @Autowired
    private ICommentService iCommentServicelImpl;
    @Autowired
    private IUserService userServiceImpl;
    @Autowired
    private ICategoryService categoryServiceImpl;

    // 管理中心起始页
    @GetMapping(value = {"", "/index"})
    public String index(HttpServletRequest request) {
        // 获取最新的5篇博客、评论以及统计数据
        List<Article> articles = siteServiceImpl.recentArticles(5);
        List<Comment> comments = siteServiceImpl.recentComments(5);
        StaticticsBo staticticsBo = siteServiceImpl.getStatistics();
        // 向Request域中存储数据
        request.setAttribute("comments", comments);
        request.setAttribute("articles", articles);
        request.setAttribute("statistics", staticticsBo);
        return "back/index";
    }

    // 向文章发表页面跳转
    @GetMapping(value = "/article/toEditPage")
    public String newArticle( ) {

        return "back/article_edit";
    }
    // 发表文章
    @PostMapping(value = "/article/publish")
    @ResponseBody
    public ArticleResponseData publishArticle(Article article) {
        try {
            System.out.println(article.getCategorical2().getId());
            categoryServiceImpl.updateUseNumWithId(article.getCategorical2().getId());
            articleServiceImpl.publish(article);
            logger.info("文章发布成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("文章发布失败，错误信息: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }
    // 跳转到后台文章列表页面
    @GetMapping(value = "/article")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "count", defaultValue = "10") int count,
                        HttpServletRequest request) {
        PageInfo<Article> pageInfo = articleServiceImpl.selectArticleWithPage(page, count);
        request.setAttribute("articles", pageInfo);
        System.out.println(pageInfo);
        return "back/article_list";
    }


    // 向文章修改页面跳转
    @GetMapping(value = "/article/{id}")
    public String editArticle(@PathVariable("id") String id, HttpServletRequest request) {
        Article article = articleServiceImpl.selectArticleWithId(Integer.parseInt(id));
        request.setAttribute("contents", article);
        request.setAttribute("categories", article.getCategories());
        return "back/article_edit";
    }

    // 文章修改处理
    @PostMapping(value = "/article/modify")
    @ResponseBody
    public ArticleResponseData modifyArticle(Article article) {
        try {
            articleServiceImpl.updateArticleWithId(article);
            logger.info("文章更新成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("文章更新失败，错误信息: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    // 文章删除
    @PostMapping(value = "/article/delete")
    @ResponseBody
    public ArticleResponseData delete(@RequestParam int id) {
        try {
            articleServiceImpl.deleteArticleWithId(id);
            logger.info("文章删除成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("文章删除失败，错误信息: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    // 跳转到后台文章列表页面
    @GetMapping(value = "/comments")
    public String comment_index(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "count", defaultValue = "10") int count,
                                HttpServletRequest request) {
        PageInfo<Comment> pageInfo = iCommentServicelImpl.getComments(page, count);
        request.setAttribute("comments", pageInfo);
        return "back/comments_list";
    }

    // 评论删除
    @PostMapping(value = "/comment/delete")
    @ResponseBody
    public ArticleResponseData delete_comment(@RequestParam int id) {
        try {
            iCommentServicelImpl.deleteCommentWithId(id);
            logger.info("文章删除成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("文章删除失败，错误信息: " + e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    //修改评论
    @PostMapping(value = "/comment/update")
    @ResponseBody
    public ArticleResponseData update_comment(@RequestParam int id,@RequestParam String status) {
        try {
            System.out.println(status);
            iCommentServicelImpl.updateCommentWithId(id,status);
            logger.info("文章更新成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("文章更新失败，错误信息: " + e.getMessage());
            return ArticleResponseData.fail();
        }
    }


    // 跳转到用户列表页面
    @GetMapping(value = "/users")
    public String users_index(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "count", defaultValue = "10") int count,
                                HttpServletRequest request) {
        // 获取当前登录用户
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.itheima.model.domain.User user0 = userServiceImpl.getUserByName(user.getUsername());
        System.out.println(user.getUsername());
        PageInfo<com.itheima.model.domain.User> pageInfo = userServiceImpl.getUsers(page, count,user0.getAuthorityId());
        request.setAttribute("users", pageInfo);
        request.setAttribute("authority",user0.getAuthorityId());
        return "back/user_list";
    }
    //修改用户权限
    @PostMapping(value = "/user/update")
    @ResponseBody
    public ArticleResponseData update_user(@RequestParam int user_id,@RequestParam int authority) {
        try {
            System.out.println(authority);
            userServiceImpl.updateUserWithId(user_id,authority);
            logger.info("文章更新成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("文章更新失败，错误信息: " + e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    // 用户删除
    @PostMapping(value = "/user/delete")
    @ResponseBody
    public ArticleResponseData deleteUser(@RequestParam int id) {
        try {
            userServiceImpl.deleteUserWithId(id);
            logger.info("用户删除成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("用户删除失败，错误信息: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    // 分类列表
    @GetMapping(value = "/category")
    @ResponseBody
    public List<Category> getCategory(String level, String parent){
        if (parent != null && parent.length() > 0){
            return categoryServiceImpl.getCategoryByCategoryCodeAndParent(level,parent);
        }
        return categoryServiceImpl.getCategoryByCategoryCode(level);
    }

    // 跳转到后台分类列表页面
    @GetMapping(value = "/categorys")
    public String category_index(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "count", defaultValue = "10") int count,
                                HttpServletRequest request) {
        PageInfo<Category> pageInfo = categoryServiceImpl.selectNewCategory(page, count);
        request.setAttribute("categorys", pageInfo);
        return "back/category_list";
    }

    // 分类删除
    @PostMapping(value = "/category/delete")
    @ResponseBody
    public ArticleResponseData deleteCategory(@RequestParam int id) {
        try {
            categoryServiceImpl.deleteCategoryWithId(id);
            logger.info("分类删除成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("分类删除失败，错误信息: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }

    // 向创建分类页面跳转
    @GetMapping(value = "/category/toEditPage")
    public String newCategory( ) {

        return "back/category_edit";
    }

    // 新建分类
    @GetMapping(value = "/category/publish")
    @ResponseBody
    public ArticleResponseData publishCategory(Category category) {
        System.out.println(category);
        try {
            categoryServiceImpl.creatCategory(category);

            logger.info("分类创建成功");
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("分类创建失败，错误信息: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }
}

