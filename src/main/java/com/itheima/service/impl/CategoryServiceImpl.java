package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.ArticleMapper;
import com.itheima.dao.CategoryMapper;
import com.itheima.dao.CommentMapper;
import com.itheima.dao.StatisticMapper;
import com.itheima.model.domain.Article;
import com.itheima.model.domain.Category;
import com.itheima.model.domain.Comment;
import com.itheima.model.domain.Statistic;
import com.itheima.service.IArticleService;
import com.itheima.service.ICategoryService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname ArticleServiceImpl
 * @Description TODO
 * @Date 2019-3-14 9:47
 * @Created by CrazyStone
 */
@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private com.itheima.dao.CategoryMapper categoryMapper;

    public List<Category> getCategoryByCategoryCode(String categoryCode) {
        return categoryMapper.selectCategoryByCode(categoryCode);
    }

    public List<Category> getCategoryByCategoryCodeAndParent(String categoryCode, String parent) {
        return categoryMapper.selectCategoryByCodeAndParent(categoryCode,parent);
    }

    // 根据文章id分页查询评论
    @Override
    public PageInfo<Category> selectNewCategory(int page, int count) {
        PageHelper.startPage(page,count);
        List<Category> categoryList = categoryMapper.selectNewCategory();
        PageInfo<Category> categoryInfo = new PageInfo<>(categoryList);
        return categoryInfo;
    }

    //新增分类使用数
    public void updateUseNumWithId(int id){
        categoryMapper.updateUseNumWithId(id);
    }

    //删除分类
    public void deleteCategoryWithId(int id){
        categoryMapper.deleteCategoryWithId(id);
    }

    //创建分类
    public void creatCategory(Category category){
        category.setCreationTime(new Date());
        categoryMapper.categoryMapper(category);
    }
}

