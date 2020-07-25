package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.UserMapper;
import com.itheima.model.domain.Comment;
import com.itheima.model.domain.User;
import com.itheima.service.ISiteService;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    // 后台获取可操作性的用户表
    public PageInfo<User> getUsers(int page, int count,Integer id){
        PageHelper.startPage(page,count);
        List<User> userList = userMapper.selectUsers(id);
        PageInfo<User> userInfo = new PageInfo<>(userList);
        return userInfo;
    }

    //根据username获取user信息
    public User getUserByName(String username){
        // 删除评论
        return userMapper.selectUserByName(username);
    }

    //修改用户权限信息
    public void updateUserWithId(int userId, int authorityId){
        userMapper.updateUserAuthorityWithId(userId,authorityId);
    }

    //删除用户
    public void deleteUserWithId(int id){
        userMapper.deleteUserById(id);
        userMapper.deleteAuthorityById(id);
    }
}
