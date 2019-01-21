package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Comment;
import org.lanqiao.clothes.pojo.Condition;

import java.util.List;

public interface ICommentService {
    //获取该店铺下的所有评论
    public List<Comment> getCommentList(Condition condition);

    //获取评论数量
    public int getCommentCount(Condition condition);

 }
