package com.cy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.cy.pojo.Brand;
import com.cy.pojo.User;
import com.cy.service.ShoppingCartService;
import com.cy.service.UserService;
import com.cy.service.impl.ShoppingCartServiceImpl;
import com.cy.service.impl.UserServiceImpl;
import com.cy.util.MailUtils;
import com.cy.util.PasswordEncryptionUtils;
import com.cy.util.ValidatorUtils;
import com.cy.web.servlet.BaseServlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    private ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
    private HashMap<String,String> mp = new HashMap<>();

    public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入邮件发送服务");
        // 接收数据
        String email = request.getParameter("email");
        System.out.println("邮箱为"+email);
        // 判断邮箱格式是否正确
        if(!ValidatorUtils.validate(email, 1)){
            response.getWriter().write("failed1");
            System.out.println("格式不正确");
            return;
        }
        // 查询邮箱是否已注册
        User checkUser = userService.selectByEmail(email);
        if(checkUser != null){
            System.out.println("该邮箱已注册!");
            response.getWriter().write("failed2");
            return;
        }
        String verCode = MailUtils.creatCode(6);
        mp.put(email, verCode);
        MailUtils.sendMail(email, "以下为您的验证码:"+verCode, "验证码");
        System.out.println("发送成功！");
        response.getWriter().write("success");
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入注册服务");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //1.接收数据
        String verCode = request.getParameter("verCode");
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //2.将JSON转为Brand对象
        User user = JSON.parseObject(params, User.class);
        System.out.println(user);
        // 判断验证码是否正确
        System.out.println(mp.get(user.getEmail()));
        System.out.println(verCode);
        if(!verCode.equals(mp.get(user.getEmail()))){
            System.out.println("验证码不正确!");
            response.getWriter().write("验证码不正确!");
            return;
        }
        // 判断密码是否符合规则
        if(!ValidatorUtils.validate(user.getPassword(), 2)){
            System.out.println("密码不符合规则!");
            response.getWriter().write("密码需包含大小写字母、数字及特殊符号");
            return;
        }
        // 定义注册时间
        // 获取当前时间
        Date currentDate = new Date();
        // 定义时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 将当前时间格式化为字符串并设置到对象中
        user.setRegisterTime(dateFormat.format(currentDate));
        System.out.println("时间设置成功!");

        // 密码加密并替换为哈希密码
        user.setPassword(PasswordEncryptionUtils.hashPassword(user.getPassword()));

        // 已经检查完成，可以添加新用户
        System.out.println("开始添加新用户!");
        userService.add(user);
        System.out.println("新用户注册成功!");
        response.getWriter().write("注册成功!正在跳转,请稍等...");
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入登录服务");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        //1.接收数据
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("email:"+email+" password:"+password);
        // 密码替换为哈希密码
        password = PasswordEncryptionUtils.hashPassword(password);
        // 2.查询用户连续输入错误的次数,同时可以判断邮箱输入是否正确
        Integer cnt = userService.checkErrorCnt(email);
        if(cnt == null){ //邮箱输入错误,不存在该邮箱
            response.getWriter().write("不存在该邮箱注册的账户!");
        }
        else{ //邮箱输入正确
            if(cnt == 6){ //已连续输入密码错误5次,账户被锁定
                response.getWriter().write("账户被锁定,请联系管理员解封,或点击忘记密码进行重置密码!");
            }
            else{
                // 检查密码是否输入正确
                User checkUser = userService.selectUser(email, password);
                if(checkUser == null){ //密码输入错误,则增加输入错误次数
                    userService.addErrorCnt(email);
                    if(cnt<5){ //连续错误次数不到5次
                        response.getWriter().write("密码输入错误!您还有"+(5-cnt)+"次机会");
                    }
                    else{ //连续错误次数已到5次,提示账户被锁定
                        response.getWriter().write("密码输入错误!账户被锁定!");
                    }
                }
                else{ //密码输入正确，响应登录成功并清空连续输入错误的次数
                    userService.clearErrorCnt(email);
                    System.out.println("登陆成功");
                    response.getWriter().write("登录成功!正在跳转,请稍等...");
                }
            }
        }
    }

    public void selectByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入查询服务...");
        //1.接收数据
        //获取查询条件
        BufferedReader br = request.getReader();
        String _user = br.readLine();
        User user = JSON.parseObject(_user,User.class);
        System.out.println(user);
        //2.将PageBean对象转为JSON
        List<User> users = userService.selectByCondition(user);
        String jsonString = JSON.toJSONString(users);

        //3.写入数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
        System.out.println("查询完成!");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("deleteByIds...");
        //1.接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //2.将JSON转为Brand对象
        int[] ids = JSON.parseObject(params, int[].class);

        //3.调用service删除
        userService.deleteByIds(ids);
        // 删除这些用户的购物车的记录
        for(int i=0; i<ids.length ; i++){
            shoppingCartService.deleteByUserId(ids[i]);
        }
        //响应成功标记
        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("deleteById...");
        //1.接收数据
        int id = Integer.parseInt(request.getParameter("id"));
        //2.调用service删除
        userService.deleteById(id);
        // 删除该用户购物车的记录
        shoppingCartService.deleteByUserId(id);
        //响应成功标记
        response.getWriter().write("success");
        System.out.println("更新数据成功!");
    }

    public void selectUserByEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收数据
        //获取查询条件
        String email = request.getParameter("email");
        //2.查询
        User user = userService.selectByEmail(email);
        String jsonString = JSON.toJSONString(user);
        //3.写入数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("updatePassword...");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //1.接收数据
        Integer id = Integer.parseInt(request.getParameter("id"));
        String password = request.getParameter("password");
        String oldPassword = request.getParameter("oldPassword");
        String encryption = request.getParameter("encryption");
        System.out.println("旧密码"+oldPassword);
        System.out.println("加密"+encryption);
        System.out.println("新密码"+password);
        //2.查询
        // 判断输入的旧密码是否正确
        if(!PasswordEncryptionUtils.hashPassword(oldPassword).equals(encryption)){
            System.out.println("旧密码输入错误!");
            response.getWriter().write("旧密码输入错误!");
            return;
        }
        // 判断密码是否符合规则
        if(!ValidatorUtils.validate(password, 2)){
            System.out.println("密码不符合规则!");
            response.getWriter().write("密码需包含大小写字母、数字及特殊符号!");
            return;
        }
        // 密码加密并替换为哈希密码
        password = PasswordEncryptionUtils.hashPassword(password);
        //3.调用service修改
        userService.updatePassword(id, password);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("success");
        System.out.println("修改密码成功!");
    }

    public void updateLevel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("updateLevel...");
        //1.接收数据
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer total = Integer.parseInt(request.getParameter("total"));
        //2.判断是否需要修改
        if(total >= 100000){
            userService.updateLevel(id, "金牌");
        }
        else if(total >= 10000){
            userService.updateLevel(id, "银牌");
        }
        System.out.println("修改等级成功!");
    }

    public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("resetPassword...");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //1.接收数据
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        //2.查询,判断输入的邮箱和用户名是否正确
        Integer id = userService.selectByEmailAndUsername(email, username);
        if(id == null){ //输入的邮箱和用户名不正确或不匹配
            response.getWriter().write("输入的邮箱和用户名不正确或不匹配");
        }
        else{ //用户输入正确
            //工具类方法随机生成密码
            String newPassword = MailUtils.creatPassword();
            //工具类方法发送新密码至邮箱
            MailUtils.sendMail(email, "您已通过忘记密码操作重置了您的密码，重置的新密码为"+newPassword, "您的密码已被重置");
            // 密码加密并替换为哈希密码
            newPassword = PasswordEncryptionUtils.hashPassword(newPassword);
            //调用service修改密码为新密码
            userService.updatePassword(id, newPassword);
            //调用service清空用户连续输入错误的次数
            userService.clearErrorCnt(email);
            //响应成功标志
            response.getWriter().write("密码重置成功,新密码已发送至邮箱!");
        }
        System.out.println("重置密码结束!");
    }

    public void AdminResetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("resetPassword...");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //1.接收数据
        String email = request.getParameter("email");
        Integer id = Integer.parseInt(request.getParameter("id"));
        //2.重置用户密码

        //工具类方法随机生成密码
        String newPassword = MailUtils.creatPassword();
        //工具类方法发送新密码至邮箱
        MailUtils.sendMail(email, "管理员重置了您的密码，重置的新密码为"+newPassword, "您的密码已被重置");
        // 密码加密并替换为哈希密码
        newPassword = PasswordEncryptionUtils.hashPassword(newPassword);
        //调用service修改密码为新密码
        userService.updatePassword(id, newPassword);
        //调用service清空用户连续输入错误的次数
        userService.clearErrorCnt(email);
        //响应成功标志
        response.getWriter().write("success");

        System.out.println("重置密码结束!");
    }
}
