package com.cy.mapper;

import com.cy.pojo.Brand;
import com.cy.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
//    User selectAll(@Param("username") String username,@Param("password") String password);

    /**
     * 根据email查询用户
     * @param email
     * @return
     */
    User selectByEmail(@Param("email") String email);

    /**
     * 添加新用户
     * @param user
     */
    void add(User user);

    /**
     * 根据邮箱和密码查找用户，登陆时使用
     * @param email
     * @param password
     * @return
     */
    User selectUser(@Param("email") String email, @Param("password") String password);

    /**
     * 根据条件查询用户
     * @return
     */
    List<User> selectByCondition(User user);

    /**
     * 根据ids数组批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(@Param("id") int id);

    /**
     * 根据id修改用户购物金额,消费金额加上add
     * @param id
     */
    void updateTotalById(@Param("id") int id, @Param("add") int add);

    /**
     * 用户修改密码
     * @param id
     * @param password
     */
    void updatePassword(@Param("id") int id, @Param("password") String password);

    /**
     * 更新用户等级
     * @param id
     * @param level
     */
    void updateLevel(@Param("id") int id, @Param("level") String level);

    /**
     * 用户密码输入错误时增加其输入错误的次数
     * @param email
     */
    void addErrorCnt(@Param("email") String email);

    /**
     * 用户成功登录则置零其输入错误的次数
     * @param email
     */
    void clearErrorCnt(@Param("email") String email);

    /**
     * 查询用户连续输入错误的次数,同时可以检查表中是否存在该邮箱注册的账号
     * @param email
     * @return
     */
    Integer checkErrorCnt(@Param("email") String email);

    /**
     * 根据邮箱和用户名查询用户id,重置密码时判断输入的用户名和邮箱是否正确且匹配
     * @param email
     * @param username
     * @return
     */
    Integer selectByEmailAndUsername(@Param("email") String email, @Param("username") String username);
}