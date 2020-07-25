package com.itheima.dao;

import com.itheima.model.domain.Comment;
import com.itheima.model.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    // 后台查询可操作的用户
    @Select("SELECT * FROM t_user_authority a,t_user u WHERE a.user_id=u.id and authority_id>#{authorityId} ORDER BY created DESC")
    public List<User> selectUsers(Integer authorityId);

    //根据username获取user信息
    @Select("SELECT * FROM t_user_authority a,t_user u WHERE a.user_id=u.id and u.username=#{username}")
    public User selectUserByName(String username);

    //修改用户的权限信息
    @Update("UPDATE t_user_authority set authority_id=#{authorityId} where user_id=#{userId}")
    public void updateUserAuthorityWithId(int userId,int authorityId);

    //删除用户信息
    @Delete("DELETE FROM t_user WHERE id=#{id}")
    public void deleteUserById(int id);

    //删除权限信息
    @Delete("DELETE FROM t_user_authority WHERE user_id=#{id}")
    public void deleteAuthorityById(int id);
}
