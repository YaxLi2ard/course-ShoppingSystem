package com.cy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.cy.pojo.*;
import com.cy.service.BrandService;
import com.cy.service.ShoppingCartService;
import com.cy.service.UserService;
import com.cy.service.impl.BrandServiceImpl;
import com.cy.service.impl.ShoppingCartServiceImpl;
import com.cy.service.impl.UserServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();
    private ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
    private UserService userService = new UserServiceImpl();

    public void selectByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入查询服务...");
        //1.接收数据
        //获取查询条件
        BufferedReader br = request.getReader();
        String _brand = br.readLine();
        Brand brand = JSON.parseObject(_brand,Brand.class);
        System.out.println(brand);
        //2.将PageBean对象转为JSON
        List<Brand> brands = brandService.selectByCondition(brand);
        String jsonString = JSON.toJSONString(brands);

        //3.写入数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
        System.out.println("查询完成!");
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("add...");
        //1.接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //2.将JSON转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        // 截取日期一部分
        brand.setDate(brand.getDate().substring(0,10));
        System.out.println(brand);
        //3.调用service添加品牌
        brandService.add(brand);
        //响应成功标记
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("deleteByIds...");
        //1.接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //2.将JSON转为Brand对象
        int[] ids = JSON.parseObject(params, int[].class);

        //3.调用service删除
        brandService.deleteByIds(ids);
        // 删除这些商品对应购物车的记录
        for(int i=0; i<ids.length ; i++){
            shoppingCartService.deleteByBrandId(ids[i]);
        }
        //响应成功标记
        response.getWriter().write("success");
    }

    public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("updateById...");
        //1.接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //2.将JSON转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        // 截取日期一部分
        brand.setDate(brand.getDate().substring(0,10));
        System.out.println(brand);
        //3.调用service更新
        brandService.updateById(brand);
        //响应成功标记
        response.getWriter().write("success");
        System.out.println("更新数据成功!");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("deleteById...");
        //1.接收数据
        int id = Integer.parseInt(request.getParameter("id"));
        //2.调用service删除
        brandService.deleteById(id);
        // 删除该商品对应购物车的记录
        shoppingCartService.deleteByBrandId(id);
        //响应成功标记
        response.getWriter().write("success");
        System.out.println("更新数据成功!");
    }

    /**
     *根据用户id查询其购物车商品
     */
    public void selectCartById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入购物车查询服务...");
        //1.接收数据
        //获取查询条件
        int userId = Integer.parseInt(request.getParameter("userId"));
        //2.查询并转为JSON
        List<Cart> cart = new ArrayList<>();
        List<Integer> brandIds = shoppingCartService.selectBrandIds(userId);
        List<Integer> counts = shoppingCartService.selectCounts(userId);
        for(int i=0;i<brandIds.size(); i++) {
            Brand brand = brandService.selectById(brandIds.get(i));
            cart.add(new Cart(brand, counts.get(i)));
        }
        String jsonString = JSON.toJSONString(cart);

        //3.写入数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
        System.out.println("购物车查询完成!");
    }

    public void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("addCart...");
        //1.接收数据
        int userId = Integer.parseInt(request.getParameter("userId"));
        int brandId = Integer.parseInt(request.getParameter("brandId"));
        int count = Integer.parseInt(request.getParameter("count"));

        // 查询该商品是否已存在购物车中
        Integer checkFlag = shoppingCartService.selectCountById(userId, brandId);
        if(checkFlag == null){
            //2.不存在,调用service添加
            shoppingCartService.addCart(userId, brandId, count);
            //响应成功标记
            response.getWriter().write("success");
            System.out.println("添加购物车成功!");
        }
        else{
            //2.存在,不执行插入操作
            response.getWriter().write("alreadyIn");
            System.out.println("商品已存在于购物车，需查看用户反应!");
        }
    }

    public void updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("updateCart...");
        //1.接收数据
        int userId = Integer.parseInt(request.getParameter("userId"));
        int brandId = Integer.parseInt(request.getParameter("brandId"));
        int count = Integer.parseInt(request.getParameter("count"));

        //2.调用service修改
        shoppingCartService.updateCart(userId, brandId, count);
        //响应成功标记
        response.getWriter().write("success");
        System.out.println("修改购物车数量成功!");
    }

    public void removeCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("removeCart...");
        //1.接收数据
        int userId = Integer.parseInt(request.getParameter("userId"));
        int brandId = Integer.parseInt(request.getParameter("brandId"));

        //2.调用service修改
        shoppingCartService.removeCart(userId, brandId);
        //响应成功标记
        response.getWriter().write("success");
        System.out.println("移除购物车成功!");
    }

    public void purchase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入购买服务...");
        //1.接收数据
        int userId = Integer.parseInt(request.getParameter("userId"));
        int brandId = Integer.parseInt(request.getParameter("brandId"));
        int count = Integer.parseInt(request.getParameter("count"));
        // 查询购买商品
        Brand brand = brandService.selectById(brandId);
        if(count > brand.getCount()){
            // 购买数量大于剩余数量
            System.out.println("购买数量大于剩余数量!");
            response.getWriter().write("failed");
            return;
        }
        // 修改商品数量
        brand.setCount(brand.getCount() - count);
        brandService.updateById(brand);
        // 修改用户消费金额
        int add = brand.getRetailPrice() * count; // 订单的消费金额
        userService.updateTotalById(userId, add);
        // 添加到历史订单
        // 获取当前时间并转为字符串
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String date = currentTime.format(formatter);
        shoppingCartService.addOrder(userId, brandId, count, add, date);

        System.out.println("商品购买成功!");
        response.getWriter().write("success");
    }

    /**
     *根据用户id查询其历史订单
     */
    public void selectOrdersById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入历史订单查询服务...");
        //1.接收数据
        //获取查询条件
        int userId = Integer.parseInt(request.getParameter("userId"));
        //2.查询并转为JSON
        List<OrderBean> historicalOrders = new ArrayList<>();
        List<Order> orders = brandService.selectOrder(userId);
        for(int i=0;i<orders.size(); i++) {
            Brand brand = brandService.selectById(orders.get(i).getBrandId());
            historicalOrders.add(new OrderBean(brand, orders.get(i)));
        }
        String jsonString = JSON.toJSONString(historicalOrders);

        //3.写入数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
        System.out.println("历史订单查询完成!");
    }
}
