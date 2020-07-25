package com.itheima.dao;

import com.itheima.model.domain.Category;
import com.itheima.model.domain.Comment;
import com.itheima.model.domain.Statistic;
import com.itheima.model.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface  CategoryMapper {

    // 分类查询
    @Select("select * from t_category where categorycode =#{categoryCode}")
    List<Category> selectCategoryByCode(String categoryCode);

    // 分类查询
    @Select("select * from t_category where categorycode =#{categoryCode} and parentId = #{parentid}")
    List< Category> selectCategoryByCodeAndParent(String categoryCode,String parentid);

    // 后台分类信息
    @Select("SELECT c1.`categoryName` parentName,c2.* FROM t_category c1,t_category c2 WHERE c1.id = c2.parentId ORDER BY c2.id DESC")
    public List<Category> selectNewCategory();

    // 统计分类使用次数
    @Select("SELECT CATEGORY_LEVEL1, CATEGORY_LEVEL2,COUNT(*) T_ARTICLE FROM T_ARTICLE GROUP BY CATEGORY_LEVEL1,CATEGORY_LEVEL2")
    public long getTotalVisit();

    // 创建文章时修改对应的category的引用值+1
    @Update("UPDATE t_category SET useNum = useNum+1 WHERE id=#{id}")
    public void updateUseNumWithId(int id);

    // 删除分类信息
    @Delete("DELETE FROM t_category WHERE id=#{aid}")
    public void deleteCategoryWithId(int id);

    //创建分类
    @Insert("INSERT INTO t_category (categoryName,parentId,creationTime)" +
            " VALUES (#{categoryName}, #{id},#{creationTime})")
    public void  categoryMapper(Category category);
}