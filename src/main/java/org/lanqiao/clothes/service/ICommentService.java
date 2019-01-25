package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Comment;
import org.lanqiao.clothes.pojo.Condition;

import java.util.List;

public interface ICommentService {
    //获取该店铺下的所有评论
    public List<Comment> getCommentList(Condition condition);

    //获取评论数量
    public int getCommentCount(Condition condition);

    //通过评论id获取评论详情
    public Comment getCommentById(int storeId,int commentId);

    //修改评论回复语
    public void modifyCommentById(Comment comment);

    public void modifyCommentState(Comment comment);

    //    ================前台操作================
    //根据商品id查询所有的评价
    public List<Comment> getAllCommentByGoodsId(Condition condition);
    //根据商品id查询评论数量
    public int getAllCountCommentGoodsId(Condition condition);
    //根据用户id查询所有评论
    public List<Comment> getAllCommentByCustomerId(Condition condition);
    //根据用户id查询商品数量
    public int getAllCountCommentCustomerId(Condition condition);

 }
