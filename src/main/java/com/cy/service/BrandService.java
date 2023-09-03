package com.cy.service;
import com.cy.pojo.Brand;
import com.cy.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.*;

public interface BrandService {
    /**
     * 查询所有商品
     * @return
     */
    List<Brand> selectByCondition(Brand brand);

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    Brand selectById(int id);

    /**
     * 添加新商品
     * @param brand
     */
    void add(Brand brand);

    /**
     * 根据ids数组批量删除
     * @param ids
     */
    void deleteByIds(int[] ids);

    /**
     * 根据id和brand修改数据
     * @param brand
     */
    void updateById(Brand brand);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(@Param("id") int id);

    /**
     * 根据用户id查询历史订单
     * @param userId
     * @return
     */
    List<Order> selectOrder(int userId);

}
