package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.model.domain.Article;
import com.itheima.model.domain.Comment;
/**
 * @Classname ICommentService
 * @Description 文章评论业务处理接口
 * @Date 2019-3-14 10:13
 * @Created by CrazyStone
 */
public interface ICommentService {
    // 根据文章id获取文章下的评论
    public PageInfo<Comment> getComments(Integer aid, int page, int count);

    // 后台获取文章下的评论
    public PageInfo<Comment> getComments(int page, int count);

    // 用户发表评论
    public void pushComment(Comment comment);

    // 删除评论
    public void deleteCommentWithId(int id);

    //修改评论
    public void updateCommentWithId(int id,String status);
}
