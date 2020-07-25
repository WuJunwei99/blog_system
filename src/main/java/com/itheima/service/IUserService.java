package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.model.domain.Comment;
import com.itheima.model.domain.User;

public interface IUserService {

    // 后台获取可操作性的用户表
    public PageInfo<User> getUsers(int page, int count,Integer id);

    //根据username获取user信息
    public User getUserByName(String username);

    //修改用户权限信息
    public void updateUserWithId(int userId, int authorityId);

    //删除用户
    public void deleteUserWithId(int id);
}
