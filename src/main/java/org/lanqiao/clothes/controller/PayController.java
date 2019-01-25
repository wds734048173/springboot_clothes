package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Customer;
import org.lanqiao.clothes.pojo.Order;
import org.lanqiao.clothes.pojo.OrderInfo;
import org.lanqiao.clothes.pojo.ShopingCar;
import org.lanqiao.clothes.service.IOrderService;
import org.lanqiao.clothes.service.impl.ShopCarServiceImpl;
import org.lanqiao.clothes.utils.OrderNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class PayController {
    @Autowired
    IOrderService orderService;

    @Autowired
    ShopCarServiceImpl shopCarService;

    @RequestMapping("/sale/pay")
    @Transactional
    public String addToOrder(HttpServletRequest req, HttpServletResponse resp, Model model){
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String[] carIds=req.getParameter("carIds").split(",");
        int totalPrice = Integer.parseInt(req.getParameter("totalPrice"));
        List<ShopingCar> items =shopCarService.selectAllToList(customer.getId());
        List<ShopingCar> payItems =new ArrayList<>();
        List<Integer> orderId=new ArrayList<>();
        //将需要支付的商品筛出
        for (int i=0;i<carIds.length;i++){
            for (ShopingCar item:items){
                if (item.getId() == Integer.parseInt(carIds[i])){
                    payItems.add(item);
                }
            }
        }//为什么不直接查询需要的购物车商品，在这里比对？

        //按店铺分订单
        Map<Integer,List<ShopingCar>> storeMap = new HashMap<>();
        for (ShopingCar item:payItems){
            int storeId=item.getStoreId();
            if (storeMap.containsKey(storeId)){
                storeMap.get(storeId).add(item);
            }else {
                List<ShopingCar> orderItems=new ArrayList<>();
                orderItems.add(item);
                storeMap.put(storeId,orderItems);
            }
        }

        //如果只有一个店铺
        if (storeMap.size()==1){
            int storeId=payItems.get(0).getStoreId();
            //生成新订单
            Order order = Order.builder().build();
            order.setPayMoney(totalPrice+10);
            order.setTotalMoney(totalPrice);
            //获取地址id===============
            order.setAddressId(1);
            order.setStoreId(storeId);
            order.setCustomerId(customer.getId());
            order.setNo(OrderNo.getOrderNo(2));
            orderService.addOrder(order);
            int oId =order.getId();
            orderId.add(oId);
            //订单详情添加物品
            for (ShopingCar item:payItems){
                OrderInfo info = new OrderInfo();
                info.setGoodsId(item.getGoodsId());
                info.setOId(oId);
                info.setNum(item.getNum());
                info.setPrice(item.getPrice());
                info.setSkuId(item.getSkuId());
                orderService.addOrderInfo(info);
            }
        }else {
            //根据店铺生成新订单
            for(Map.Entry<Integer,List<ShopingCar>> entry:storeMap.entrySet()){
                int sId=entry.getKey();
                List<ShopingCar> orderItem=entry.getValue();
                int totalPay=0;
                for (ShopingCar item:orderItem){
                    totalPay+=item.getPrice();
                }
                //生成新订单
                Order order = Order.builder().build();
                order.setPayMoney(totalPay+10);
                order.setTotalMoney(totalPay);
                order.setAddressId(1);//需要修改
                order.setStoreId(sId);
                order.setCustomerId(customer.getId());
                order.setNo(OrderNo.getOrderNo(2));
                orderService.addOrder(order);
                int oId =order.getId();
                orderId.add(oId);
                //订单详情添加物品
                for (ShopingCar item:orderItem){
                    OrderInfo info = new OrderInfo();
                    info.setGoodsId(item.getGoodsId());
                    info.setOId(oId);
                    info.setNum(item.getNum());
                    info.setPrice(item.getPrice());
                    info.setSkuId(item.getSkuId());
                    orderService.addOrderInfo(info);
                }
            }
        }
        //下单成功后删除购物车选中的商品
        List<Integer> ids = new ArrayList<>();
        for(String str : carIds){
            ids.add(Integer.valueOf(str));
        }
        shopCarService.deleteShopByIds(ids);
        req.setAttribute("orderMap",storeMap);
        req.setAttribute("carIds",carIds);
        req.setAttribute("totalPrice",totalPrice+10);
        req.setAttribute("orderId",orderId);
        return "/sale/order";
    }

    @RequestMapping("/sale/order")
    public String toOrder(){
        return "/sale/order";
    }

    //支付订单并跳转页面
    @RequestMapping("/sale/payOrder")
    public String payOrder(HttpServletRequest req, HttpServletResponse resp, Model model){
        int totalMoney=Integer.parseInt(req.getParameter("totalPay"));
        String[] orderIds=req.getParameter("orderId").split(",");
        String[] carIds=req.getParameter("carIds").split(",");
        //修改对应的订单状态
        for (int i =0;i<orderIds.length;i++){
            orderService.payOrder(Integer.parseInt(orderIds[i]));
        }
        //删除购物车物品
        /*for (int i=0;i<carIds.length;i++){
            shopCarService.deleteShop(Integer.parseInt(carIds[i]));
        }*/
        req.setAttribute("totalMoney",totalMoney);
        return "/sale/shoppingcar";
    }


}
