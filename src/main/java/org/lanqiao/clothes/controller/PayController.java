package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Customer;
import org.lanqiao.clothes.pojo.Order;
import org.lanqiao.clothes.pojo.OrderInfo;
import org.lanqiao.clothes.pojo.ShopingCarItem;
import org.lanqiao.clothes.service.IOrderService;
import org.lanqiao.clothes.service.impl.ShopCarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PayController {
    @Autowired
    IOrderService orderService;

    @Autowired
    ShopCarServiceImpl shopCarService;

    @RequestMapping("/sale/pay")
    public String addToOrder(HttpServletRequest req, HttpServletResponse resp, Model model){
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String[] carIds=req.getParameter("carIds").split(",");
        int totalPrice = Integer.parseInt(req.getParameter("totalPrice"));
        List<ShopingCarItem> items =shopCarService.selectAllToList(customer.getId());
        List<ShopingCarItem> payItems =new ArrayList<>();
        List<Integer> orderId=new ArrayList<>();
        //将需要支付的商品筛出
        for (int i=0;i<carIds.length;i++){
            for (ShopingCarItem item:items){
                if (item.getId() == Integer.parseInt(carIds[i])){
                    payItems.add(item);
                }
            }
        }

        //按店铺分订单
        Map<Integer,List<ShopingCarItem>> storeMap = new HashMap<>();
        for (ShopingCarItem item:payItems){
            int storeId=item.getStoreId();
            if (storeMap.containsKey(storeId)){
                storeMap.get(storeId).add(item);
            }else {
                List<ShopingCarItem> orderItems=new ArrayList<>();
                orderItems.add(item);
                storeMap.put(storeId,orderItems);
            }
        }

        //如果只有一个店铺
        if (storeMap.size()==1){
            int storeId=payItems.get(0).getStoreId();
            //生成新订单
            Order order = new Order();
            order.setPayMoney(totalPrice+10);
            order.setTotalMoney(totalPrice);
            order.setAddressId(1);
            order.setStoreId(storeId);
            order.setCustomerId(customer.getId());
            orderService.addOrder(order);
            int oId =order.getId();
            orderId.add(oId);
            //订单详情添加物品
            for (ShopingCarItem item:payItems){
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
            for(Map.Entry<Integer,List<ShopingCarItem>> entry:storeMap.entrySet()){
                int sId=entry.getKey();
                List<ShopingCarItem> orderItem=entry.getValue();
                int totalPay=0;
                for (ShopingCarItem item:orderItem){
                    totalPay+=item.getPrice();
                }
                //生成新订单
                Order order = new Order();
                order.setPayMoney(totalPay+10);
                order.setTotalMoney(totalPay);
                order.setAddressId(1);//需要修改
                order.setStoreId(sId);
                order.setCustomerId(customer.getId());
                orderService.addOrder(order);
                int oId =order.getId();
                orderId.add(oId);
                //订单详情添加物品
                for (ShopingCarItem item:orderItem){
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
        for (int i=0;i<carIds.length;i++){
            shopCarService.deleteShop(Integer.parseInt(carIds[i]));
        }
        req.setAttribute("totalMoney",totalMoney);
        return "/sale/shoppingcar";
    }


}
