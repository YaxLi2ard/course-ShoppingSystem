package com.cy.service.impl;

import com.cy.mapper.BrandMapper;
import com.cy.mapper.ShoppingCartMapper;
import com.cy.mapper.UserMapper;
import com.cy.pojo.Brand;
import com.cy.service.BrandService;
import com.cy.service.ShoppingCartService;
import com.cy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    //1.创建SqlSessionFactory工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public Integer selectCountById(int userId, int brandId) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);
        //4.调用方法
        Integer count = mapper.selectCountById(userId, brandId);
        //5.释放资源
        sqlSession.close();
        //6.返回
        return count;
    }

    @Override
    public List<Integer> selectBrandIds(int id) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);
        //4.调用方法
        List<Integer> brandIds = mapper.selectBrandIds(id);
        //5.释放资源
        sqlSession.close();
        //6.返回
        return brandIds;
    }

    @Override
    public List<Integer> selectCounts(int id) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);
        //4.调用方法
        List<Integer> counts = mapper.selectCounts(id);
        //5.释放资源
        sqlSession.close();
        //6.返回
        return counts;
    }

    @Override
    public void addCart(int userId, int brandId, int count) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);
        //4.调用方法
        mapper.addCart(userId, brandId, count);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public void updateCart(int userId, int brandId, int count) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);
        //4.调用方法
        mapper.updateCart(userId, brandId, count);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public void removeCart(int userId, int brandId) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);
        //4.调用方法
        mapper.removeCart(userId, brandId);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public void addOrder(int userId, int brandId, int count, int total, String date) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);
        //4.调用方法
        mapper.addOrder(userId, brandId, count, total, date);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public void deleteByUserId(int userId) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);
        //4.调用方法
        mapper.deleteByUserId(userId);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public void deleteByBrandId(int brandId) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);
        //4.调用方法
        mapper.deleteByBrandId(brandId);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }


}
