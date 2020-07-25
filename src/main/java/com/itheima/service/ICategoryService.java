package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.model.domain.Category;
import com.itheima.model.domain.Comment;

import java.util.List;

/**
 * @Classname ICommentService
 * @Description 文章评论业务处理接口
 * @Date 2019-3-14 10:13
 * @Created by CrazyStone
 */
public interface ICategoryService {

    public List<Category> getCategoryByCategoryCode(String categoryCode);

    public List<Category> getCategoryByCategoryCodeAndParent(String categoryCode, String parent);

    public PageInfo<Category> selectNewCategory(int page, int count);

    public void updateUseNumWithId(int id);

    public void deleteCategoryWithId(int id);

    public void creatCategory(Category category);
}

