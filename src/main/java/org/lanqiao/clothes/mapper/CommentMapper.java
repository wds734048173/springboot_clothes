package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Comment;
import org.lanqiao.clothes.pojo.Condition;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {
    //获取该店铺的所有评论信息
    public List<Comment> selectCommentList(Condition condition);
    //获取该店铺的所有评论信息数量
    public int selectCommentCount(Condition condition);
    //通过评论id获取评论详情
    public Comment selectCommentById(int storeId,int commentId);
    //通过评论id修改评论信息
    public void updateCommentById(Comment comment);
    //修改评论状态
    public void updateCommentState(Comment comment);

    //=================前台操作=============
    //根据商品id查询所有的评价
    public List<Comment> selectAllCommentByGoodsId(Condition condition);
    //根据商品id查询评论数量
    public int selectAllCountCommentGoodsId(Condition condition);
    //根据评论id查询某条评论
//    public Comment selectCommentByCommentId(int commentId);
}
