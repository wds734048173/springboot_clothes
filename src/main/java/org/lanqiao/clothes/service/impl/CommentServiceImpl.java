package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.*;
import org.lanqiao.clothes.pojo.*;
import org.lanqiao.clothes.service.ICommentService;
import org.lanqiao.clothes.utils.DataMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Service("commentService")
public class CommentServiceImpl implements ICommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ColorMapper colorMapper;
    @Autowired
    SizeMapper sizeMapper;

    @Override
    public List<Comment> getCommentList(Condition condition) {
        int storeId = condition.getStoreId();
        List<Comment> commentList =  commentMapper.selectCommentList(condition);
        //获取商品ids
        List<Integer> goodsIds = new ArrayList<>();
        Map<Integer,String> goodsMap = new HashMap<>();
        //获取skuid
        List<Integer> skuIds = new ArrayList<>();
        Map<Integer,GoodsSKU> skuMap = new HashMap<>();
        Map<Integer,String> colorMap = new HashMap<>();
        Map<Integer,String> sizeMap = new HashMap<>();
        //客户ids
        List<Integer> customerIds = new ArrayList<>();
        Map<Integer,String> customerMap = new HashMap<>();

        Map<Integer,String> gradeMap = DataMapUtil.getGradeMap();
        for(Comment comment : commentList){
            int goodsId = comment.getGoodsId();
            goodsIds.add(goodsId);
            int customerId = comment.getCustomerId();
            customerIds.add(customerId);
            int skuId = comment.getSkuId();
            skuIds.add(skuId);
            int grade = comment.getGrade();
            if(gradeMap.containsKey(grade)){
                comment.setGradeStr(gradeMap.get(grade));
            }
            int state = comment.getState();
            if(state == 0){
                comment.setStateStr("显示");
            }else if(state == 1){
                comment.setStateStr("隐藏");
            }
        }
        //获取商品信息
        if(goodsIds.size()>0){
            List<Goods> goodsList = goodsMapper.selectGoodsByIds(goodsIds);
            for(Goods goods : goodsList){
                goodsMap.put(goods.getId(),goods.getName());
            }
        }

        if(customerIds.size()>0){
            List<Customer> customerList = customerMapper.selectCustomerByIds(customerIds);
            for(Customer customer : customerList){
                customerMap.put(customer.getId(),customer.getUsername());
            }
        }

        if(skuIds.size()>0){
            List<GoodsSKU> goodsSKUList = goodsMapper.selectSKUByIds(skuIds);
            for(GoodsSKU goodsSKU : goodsSKUList){
                skuMap.put(goodsSKU.getId(),goodsSKU);
            }
            //获取颜色并做成map
            List<Color> colorList = colorMapper.selectColorSelectedList(storeId);
            if(colorList.size()>0){
                for(Color color : colorList){
                    colorMap.put(color.getId(),color.getName());
                }
            }
            //获取尺码，并做成map
            List<Size> sizeList = sizeMapper.selectSizeSelectedList(storeId);
            if(sizeList.size()>0){
                for(Size size : sizeList){
                    sizeMap.put(size.getId(),size.getName());
                }
            }
        }

        //配置查询到的数据
        for(Comment comment : commentList){
            int goodsId = comment.getGoodsId();
            if(goodsMap.containsKey(goodsId)){
                comment.setGoodsName(goodsMap.get(goodsId));
            }
            int customerId = comment.getCustomerId();
            if(customerMap.containsKey(customerId)){
                comment.setCustomerName(customerMap.get(customerId));
            }
            int skuId = comment.getSkuId();
            if(skuMap.containsKey(skuId)){
                comment.setColorId(skuMap.get(skuId).getColorId());
                if(colorMap.containsKey(skuMap.get(skuId).getColorId())){
                    comment.setColorName(colorMap.get(skuMap.get(skuId).getColorId()));
                }
                comment.setSizeId(skuMap.get(skuId).getSizeId());
                if(sizeMap.containsKey(skuMap.get(skuId).getSizeId())){
                    comment.setSizeName(sizeMap.get(skuMap.get(skuId).getSizeId()));
                }
            }
        }
        return commentList;
    }

    @Override
    public int getCommentCount(Condition condition) {
        return commentMapper.selectCommentCount(condition);
    }

    @Override
    public Comment getCommentById(int storeId, int commentId) {
        return commentMapper.selectCommentById(storeId, commentId);
    }

    @Override
    public void modifyCommentById(Comment comment) {
        commentMapper.updateCommentById(comment);
    }

    @Override
    public void modifyCommentState(Comment comment) {
        commentMapper.updateCommentState(comment);
    }
}
