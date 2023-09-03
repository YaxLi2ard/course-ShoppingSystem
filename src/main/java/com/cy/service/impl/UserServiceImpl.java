package com.cy.service.impl;

import com.cy.mapper.ShoppingCartMapper;
import com.cy.mapper.UserMapper;
import com.cy.pojo.User;
import com.cy.service.UserService;
import com.cy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    //1.创建SqlSessionFactory工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public User selectByEmail(String email){
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        User user = mapper.selectByEmail(email);
        //5.释放资源
        sqlSession.close();
        //6.返回
        return user;
    }

    @Override
    public User selectUser(String email, String password) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        User user = mapper.selectUser(email, password);
        //5.释放资源
        sqlSession.close();
        //6.返回
        return user;
    }

    @Override
    public List<User> selectByCondition(User user) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        List<User> users = mapper.selectByCondition(user);
        //5.释放资源
        sqlSession.close();
        //6.返回
        return users;
    }

    @Override
    public void add(User user){
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        mapper.add(user);
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
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        mapper.deleteByIds(ids);
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
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        mapper.deleteById(id);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public void updateTotalById(int id, int add) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        mapper.updateTotalById(id, add);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public void updatePassword(int id, String password) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        mapper.updatePassword(id, password);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public void updateLevel(int id, String level) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        mapper.updateLevel(id, level);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public void addErrorCnt(String email) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        mapper.addErrorCnt(email);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public void clearErrorCnt(String email) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        mapper.clearErrorCnt(email);
        //增删改需要提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
        //6.返回
    }

    @Override
    public Integer checkErrorCnt(String email) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        Integer count = mapper.checkErrorCnt(email);
        //5.释放资源
        sqlSession.close();
        //6.返回
        return count;
    }

    @Override
    public Integer selectByEmailAndUsername(String email, String username) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        Integer id = mapper.selectByEmailAndUsername(email, username);
        //5.释放资源
        sqlSession.close();
        //6.返回
        return id;
    }
}
