package com.example.auto_warehouse.mapper;

import com.example.auto_warehouse.bean.User;
import com.example.auto_warehouse.bean.order;
import com.example.auto_warehouse.bean.orderPlus;
import com.example.auto_warehouse.bean.transportion;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {
//    三个功能都要写1.调动数据库里所有item的id
//    2.写入item
//    3。写入order

    List<Integer> getId();
    void createItem(int id,String name,int quantity,double weight);
    void createOrder(int id,int consignorId,String destinationAddress,String departAddress,String status,
                     Date fromdate,double cost,String currentLocation,
                     String reciverName,String reciverPhone);

    List<orderPlus> ordersearch(int consignorId);

    List<transportion> transportsearch(int consignorId,int id);
}
