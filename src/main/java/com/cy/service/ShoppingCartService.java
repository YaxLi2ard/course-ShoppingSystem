package com.cy.service;
import org.apache.ibatis.annotations.Param;

import java.util.*;

public interface ShoppingCartService {
    /**
     * 根据id查询购物车数据,用于判断某一数据是否存在
     * @param userId
     * @param brandId
     * @return
     */
    Integer selectCountById(int userId, int brandId);

    /**
     * 根据用户id查询购物车中的商品id列表
     * @param id 用户id
     * @return
     */
    List<Integer> selectBrandIds(int id);

    /**
     * 根据用户id查询购物车中的商品的数量列表
     * @param id
     * @return
     */
    List<Integer> selectCounts(@Param("id") int id);

    /**
     * 添加数据到购物车
     * @param userId
     * @param brandId
     * @param count
     */
    void addCart(int userId, int brandId, int count);

    /**
     * 修改购物车数量
     * @param userId
     * @param brandId
     * @param count
     */
    void updateCart(int userId, int brandId, int count);

    /**
     * 移除购物车某一商品
     * @param userId
     * @param brandId
     */
    void removeCart(@Param("userId") int userId, @Param("brandId") int brandId);

    /**
     * 添加到历史订单
     * @param userId
     * @param brandId
     * @param count
     * @param total
     * @param date
     */
    void addOrder(@Param("userId") int userId, @Param("brandId") int brandId, @Param("count") int count, @Param("total") int total, @Param("date") String date);

    /**
     * 删除用户时将其购物车记录删除
     * @param userId
     */
    void deleteByUserId(@Param("userId") int userId);

    /**
     * 删除商品时将对应购物车记录删除
     * @param brandId
     */
    void deleteByBrandId(@Param("brandId") int brandId);
}
