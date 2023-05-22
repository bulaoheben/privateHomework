package com.example.auto_warehouse.controller;

import com.example.auto_warehouse.bean.JwtUtility;
import com.example.auto_warehouse.bean.order;
import com.example.auto_warehouse.bean.orderPlus;
import com.example.auto_warehouse.bean.transportion;
import com.example.auto_warehouse.service.OrderService;
import com.example.auto_warehouse.util.JsonResult;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    private JwtUtility jwtutility=new JwtUtility();

    //登录部分及相关函数，还需要根据用户type分别再写个函数/或者加个参数type判断（未完成）
    @CrossOrigin(origins = "*")
    @PostMapping("/create")
    public JsonResult<Integer> ordercreate(@RequestHeader Map<String,String> Headermap, @RequestBody Map<String,String> map) throws ParseException {
        String token = Headermap.get("token");
        if (token.startsWith("\"") && token.endsWith("\"")) {
            token = token.substring(1, token.length() - 1);
        }
        //是否过期
        try{
            jwtutility.isTokenExpired(token);
        }catch (ExpiredJwtException e){
            return new JsonResult<>("2","登录已过期，请重新登陆");
        }


     /*       Claims claim = jwtutility.getAllClaimsFromToken(token);//claim声明
            String username = jwtutility.getUsernameFromToken(token);//用户名

            System.out.println("claim"+claim);*/

        int consignorId = Integer.parseInt(map.get("consignorId"));
        String destinationAddress = map.get("destinationAddress");
        String departAddress = map.get("departAddress");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fromdate = dateFormat.parse(map.get("fromdate"));

        double cost = Double.parseDouble(map.get("cost"));
        double weight = Double.parseDouble(map.get("weight"));
        String name = map.get("name");
        int quantity = Integer.parseInt( map.get("quantity"));
        String reciverName = map.get("reciverName");
        String reciverPhone = map.get("reciverPhone");

        System.out.println("进到这里");
        return orderService.ordercreate(consignorId,destinationAddress,departAddress,fromdate,cost,weight,name,quantity,reciverName,reciverPhone);
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/search")
    public JsonResult<List<orderPlus>> ordersearch(@RequestHeader Map<String,String> Headermap,@RequestBody Map<String,String> map) throws ParseException {

        String token = Headermap.get("token");
        if (token.startsWith("\"") && token.endsWith("\"")) {
            token = token.substring(1, token.length() - 1);
        }

        //是否过期
        try{
            jwtutility.isTokenExpired(token);
        }catch (ExpiredJwtException e){
            return new JsonResult<>("2","登录已过期，请重新登陆");
        }

        int consignorId = Integer.parseInt(map.get("consignorId"));
        return orderService.ordersearch(consignorId);

    }


    @CrossOrigin(origins = "*")
    @PostMapping("/transport")
    public JsonResult<List<transportion>> transportsearch(@RequestHeader Map<String,String> Headermap, @RequestBody Map<String,String> map) throws ParseException {

        String token = Headermap.get("token");
        if (token.startsWith("\"") && token.endsWith("\"")) {
            token = token.substring(1, token.length() - 1);
        }

        //是否过期
        try{
            jwtutility.isTokenExpired(token);
        }catch (ExpiredJwtException e){
            return new JsonResult<>("2","登录已过期，请重新登陆");
        }


        int consignorId = Integer.parseInt(map.get("consignorId"));
        int id = Integer.parseInt(map.get("id"));
        return orderService.transportsearch(consignorId,id);

    }
}
