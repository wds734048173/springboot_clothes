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
}
