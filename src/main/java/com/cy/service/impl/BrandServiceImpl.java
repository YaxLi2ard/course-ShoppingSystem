package com.cy.service.impl;

import com.cy.mapper.BrandMapper;
import com.cy.mapper.UserMapper;
import com.cy.pojo.Brand;
import com.cy.pojo.Order;
import com.cy.service.BrandService;
import com.cy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    //1.创建SqlSessionFactory工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<Brand> selectByCondition(Brand brand) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        List<Brand> brands = mapper.selectByCondition(brand);
        //5.释放资源
        sqlSession.close();
        //6.返回
        return brands;
    }

    @Override
    public Brand selectById(int id) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        Brand brand = mapper.selectById(id);
        //5.释放资源
        sqlSession.close();
        //6.返回
        return brand;
    }

    @Override
    public void add(Brand brand) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.add(brand);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public void deleteByIds(int[] ids) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.deleteByIds(ids);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public void updateById(Brand brand) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.updateById(brand);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public void deleteById(int id) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.deleteById(id);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public List<Order> selectOrder(int userId) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        List<Order> orders = mapper.selectOrders(userId);
        //5.释放资源
        sqlSession.close();
        //6.返回
        return orders;
    }
}
