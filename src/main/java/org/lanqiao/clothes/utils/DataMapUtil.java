package org.lanqiao.clothes.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: WDS
 * @Date: 2019/1/11 14:55
 * @Description:
 */
public class DataMapUtil {
    private static Map sexMap;
    private static Map stateMap;
    private static Map yearMap;
    private static Map preOrderStateMap;
    private static Map orderStateMap;
    private static Map gradeMap;

    //前端销售订单状态
    public static Map getPreOrderStateMap(){
        preOrderStateMap = new HashMap<Integer,String>();
        preOrderStateMap.put(0,"待付款");
        preOrderStateMap.put(1,"待发货");
        preOrderStateMap.put(2,"待收货");
        preOrderStateMap.put(3,"待评价");
        preOrderStateMap.put(4,"已完成");
        preOrderStateMap.put(4,"已取消");
        return preOrderStateMap;
    }


    //后销售订单状态
    public static Map getOrderStateMap(){
        orderStateMap = new HashMap<Integer,String>();
        orderStateMap.put(0,"已下单");
        orderStateMap.put(1,"已支付");
        orderStateMap.put(2,"已发货");
        orderStateMap.put(3,"已收货");
        orderStateMap.put(4,"已完成");
        orderStateMap.put(5,"已取消");
        return orderStateMap;
    }

    //普通的状态
    public static Map getStateMap(){
        stateMap = new HashMap<Integer,String>();
        stateMap.put(0,"启用");
        stateMap.put(1,"停用");
        return stateMap;
    }

    //性别
    public static Map getSexMap(){
        sexMap = new HashMap<Integer,String>();
        sexMap.put(0,"男");
        sexMap.put(1,"女");
        return sexMap;
    }

    //上新年份
    public static Map getYearMap(){
        yearMap = new HashMap<Integer,String>();
        yearMap.put(2016,"2016年");
        yearMap.put(2017,"2017年");
        yearMap.put(2018,"2018年");
        yearMap.put(2019,"2019年");
        yearMap.put(2020,"2020年");
        return yearMap;
    }

    //评论等级
    public static Map getGradeMap(){
        gradeMap = new HashMap<Integer,String>();
        gradeMap.put(0,"好评");
        gradeMap.put(1,"中评");
        gradeMap.put(2,"差评");
        return gradeMap;
    }

}
