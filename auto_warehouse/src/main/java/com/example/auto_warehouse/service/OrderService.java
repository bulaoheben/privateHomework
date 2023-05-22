package com.example.auto_warehouse.service;
import com.example.auto_warehouse.bean.JwtUtility;
import com.example.auto_warehouse.bean.order;
import com.example.auto_warehouse.bean.orderPlus;
import com.example.auto_warehouse.bean.transportion;
import com.example.auto_warehouse.mapper.OrderMapper;
import com.example.auto_warehouse.util.JsonResult;
import jakarta.persistence.criteria.Order;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;



    public List<Integer> getId(){
        return orderMapper.getId();
    }
    public void createItem(int id,String name,int quantity,double weight){
        orderMapper.createItem(id,name,quantity,weight);
    }

    public void createOrder(int id,int consignorId,String destinationAddress,String departAddress,
                            String status,Date fromdate,double cost,String currentLocation,
                            String reciverName,String reciverPhone){
        orderMapper.createOrder(id,consignorId,destinationAddress,departAddress,status,
                fromdate,cost,currentLocation,reciverName,reciverPhone);
    }

    public int generateId(List<Integer> list){
        // 新建随机数生成器对象
        Random rand = new Random();
        int randomInt;
        do {
            // 循环生成随机数，直到生成一个不在 List 范围内的整数
            randomInt = rand.nextInt();
        } while (list.contains(randomInt));
        return randomInt;
    }

    public JsonResult<Integer> ordercreate(int consignorId, String  destinationAddress,
                                         String departAddress, Date fromdate, double cost,
                                           double weight, String name, int quantity,
                                           String reciverName,String reciverPhone){
        List<Integer> idlist= getId();
        int newId = generateId(idlist);
        createItem(newId,name,quantity,weight);
        createOrder(newId,consignorId,destinationAddress,departAddress,"processing",
                fromdate,cost,departAddress,reciverName,reciverPhone);
        return new JsonResult<>("1","订单创建成功!");
    }

    public JsonResult<List<orderPlus>> ordersearch(int consignorId){
        List<orderPlus> orders = orderMapper.ordersearch(consignorId);
        return new JsonResult<>(orders,"订单创建成功!");
    }

    public JsonResult<List<transportion>> transportsearch(int consignorId, int id){
        List<transportion> transportions = orderMapper.transportsearch(consignorId,id);
        return new JsonResult<>(transportions,"订单详情查询成功");
    }

}
