package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Comment;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Customer;
import org.lanqiao.clothes.pojo.User;
import org.lanqiao.clothes.service.ICommentService;
import org.lanqiao.clothes.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class CommentController {
    @Autowired
    ICommentService commentService;
    @RequestMapping("/manager/commentList")
    public String commentList(HttpServletRequest req, HttpServletResponse resp, Model model){
        int pageNum = 1;
        if(req.getParameter("currentPage") != null){
            pageNum = Integer.valueOf(req.getParameter("currentPage"));
        }
        int pageSize = 5;
        if(req.getParameter("pageSize") != null){
            pageSize = Integer.valueOf(req.getParameter("pageSize"));
        }

        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        //查询条件
        String searchGoodsName = "";
        if(req.getParameter("searchGoodsName") != null){
            searchGoodsName = req.getParameter("searchGoodsName");
        }
        String searchCommentGrade = "-1";
        if(req.getParameter("searchCommentGrade") != null){
            searchCommentGrade = req.getParameter("searchCommentGrade");
        }
        String searchCommentState = "-1";
        if(req.getParameter("searchCommentState") != null){
            searchCommentState = req.getParameter("searchCommentState");
        }
        Condition condition = new Condition();
        condition.setName(searchGoodsName);
        condition.setState(searchCommentState);
        condition.setGrade(searchCommentGrade);
        condition.setStoreId(storeId);
        int totalRecords = commentService.getCommentCount(condition);
        //不同操作，不同的当前页设置
        PageModel pm = new PageModel(pageNum,totalRecords,pageSize);
        String method = req.getParameter("method");
        if("add".equals(method)){
            pageNum = pm.getEndPage();
        }else{
            //如果当前页大于总页数，但是排除查询不到数据的情况。当前页等于最大页
            if(pageNum > pm.getTotalPageNum() && pm.getTotalPageNum() != 0){
                pageNum = pm.getTotalPageNum();
            }
        }
        PageModel pageModel = new PageModel(pageNum,totalRecords,pageSize);
        //分页条件封装
        condition.setCurrentPage(pageModel.getStartIndex());
        condition.setPageSize(pageModel.getPageSize());
        List<Comment> commentList = commentService.getCommentList(condition);
        model.addAttribute("commentList",commentList);
        model.addAttribute("pm",pageModel);
        model.addAttribute("condition",condition);
        model.addAttribute("currentPage",pageNum);
        return "/manager/commentList";
    }

    //通过commentId查询评论信息
    @RequestMapping("/manager/selectComment")
    @ResponseBody
    public Comment selectCommentById(HttpServletRequest req, HttpServletResponse resp){
        int id = Integer.valueOf(req.getParameter("commentId"));
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        Comment comment = commentService.getCommentById(storeId,id);
        return comment;
    }

    @RequestMapping("/manager/updateCommentById")
    public String updateCommentById(HttpServletRequest req, HttpServletResponse resp,Model model){
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        int commentId = Integer.valueOf(req.getParameter("commentId"));
        int state = Integer.valueOf(req.getParameter("state"));
        String reply = req.getParameter("reply");
        Comment comment = Comment.builder().build();
        comment.setId(commentId);
        comment.setReply(reply);
        comment.setStoreId(storeId);
        comment.setState(state);
        commentService.modifyCommentById(comment);
        return commentList(req, resp, model);
    }

    @RequestMapping("/manager/updateCommentState")
    public String updateCommentState(HttpServletRequest req, HttpServletResponse resp,Model model){
        String method = req.getParameter("method");
        int state = 0;
        if("hidden".equals(method)){
            state = 1;
        }
        int commentId = Integer.valueOf(req.getParameter("commentId"));
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        Comment comment = Comment.builder().build();
        comment.setState(state);
        comment.setId(commentId);
        comment.setStoreId(storeId);
        commentService.modifyCommentState(comment);
        return commentList(req, resp, model);
    }
    //获取个人评价管理
    @RequestMapping("/sale/personal")
    public String myComment(HttpServletRequest req, HttpServletResponse resp, Model model){
        //       获取用户id
        HttpSession session = req.getSession();
        Customer customer =(Customer) session.getAttribute("customer");
//        int customerId =customer.getId();
        int customerId = 1;

        //根据评价等级查询评价
        String searchCommentGrade="-1";
        if (req.getParameter("searchCommentGrade")!=null){
            searchCommentGrade = req.getParameter("searchCommentGrade");
        }
        //获取商品的评论信息
        Condition condition = new Condition();
        condition.setCustomerId(customerId);
        condition.setGrade(searchCommentGrade);
        int totalRecords = commentService.getAllCountCommentCustomerId(condition);
        List<Comment> commentListByCustomerId=commentService.getAllCommentByCustomerId(condition);
        model.addAttribute("commentListByCustomerId",commentListByCustomerId);
        model.addAttribute("condition",condition);
        model.addAttribute("count",totalRecords);
        return "/sale/personal";
    }
    @RequestMapping("/sale/myComment")
    public String getComment(HttpServletRequest req, HttpServletResponse resp, Model model){
        //       获取用户id
        HttpSession session = req.getSession();
        Customer customer =(Customer) session.getAttribute("customer");
//        int customerId =customer.getId();
        int customerId = 1;

        //根据评价等级查询评价
        String searchCommentGrade="-1";
        if (req.getParameter("searchCommentGrade")!=null){
            searchCommentGrade = req.getParameter("searchCommentGrade");
        }
        //获取商品的评论信息
        Condition condition = new Condition();
        condition.setCustomerId(customerId);
        condition.setGrade(searchCommentGrade);
        int totalRecords = commentService.getAllCountCommentCustomerId(condition);
        List<Comment> commentListByCustomerId=commentService.getAllCommentByCustomerId(condition);
        model.addAttribute("commentListByCustomerId",commentListByCustomerId);
        model.addAttribute("condition",condition);
        model.addAttribute("count",totalRecords);
        return "/sale/myComment";
    }

}
