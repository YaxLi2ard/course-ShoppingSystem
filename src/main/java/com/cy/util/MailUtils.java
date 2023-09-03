package com.cy.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 * 发邮件工具类
 */
public class MailUtils {
    private static final String USER = "XXX"; // 发件人称号，同邮箱地址※
    private static final String PASSWORD = "XXX"; // 授权码，开启SMTP时显示※

    /**
     *
     * @param to 收件人邮箱
     * @param text 邮件正文
     * @param title 标题
     */
    /* 发送验证信息的邮件 */
    public static boolean sendMail(String to, String text, String title){
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
//            注意发送邮件的方法中，发送给谁的，发送给对应的app，※
//            要改成对应的app。扣扣的改成qq的，网易的要改成网易的。※
//            props.put("mail.smtp.host", "smtp.qq.com");
            props.put("mail.smtp.host", "smtp.163.com");

            // 发件人的账号
            props.put("mail.user", USER);
            //发件人的密码
            props.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(title);

            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 随机生成验证码
     * @param n 验证码位数
     * @return 验证码
     */
    public static String creatCode(int n) {
        //1、定义一个字符串变量记录生成的随机字符
        String code = "";
        Random r = new Random();
        //2、定义一个for循环，循环n次，依次生成随机字符
        for (int i = 0; i < n; i++) {
            //i=0 1 2
            //3、生成一个随机字符，英文大、小写 数字（0 1 2 ）
            int type = r.nextInt(3);//0 1 2
            switch (type) {
                case 0:
                    //大写字符（A 65-Z 65+25）
                    char ch = (char) (r.nextInt(26) + 65);
                    code += ch;
                    break;
                case 1:
                    //小写字符（a 97-z 97+25）
                    char ch1 = (char) (r.nextInt(26) + 97);
                    code += ch1;
                    break;
                case 2:
                    //数字字符
                    code += r.nextInt(10);//0-9
                    break;
            }
        }
        return code;
    }

    /**
     * 随机生成密码,包含特殊字符，数字和大小写字母
     * @return 密码
     */
    public static String creatPassword() {
        int n = 0;
        //1、定义一个字符串变量记录生成的随机字符
        String code = "";
        String specilaChar = "!@^,._=|$*()-<>?";
        Random r = new Random();
        while(n < 8){ // 随机一个长度,范围为8~30
            n = r.nextInt(31);
        }
        code += (char) (r.nextInt(26) + 97);
        code += (char) specilaChar.charAt(r.nextInt(16));
        code += r.nextInt(10);
        code += (char) (r.nextInt(26) + 65);
        //2、定义一个for循环，循环n次，依次生成随机字符
        for (int i = 0; i < n-4; i++) {
            //i=0 1 2 3
            //3、生成一个随机字符，英文大、小写 数字 特殊字符（0 1 2 ）
            int type = r.nextInt(4);//0 1 2
            switch (type) {
                case 0:
                    //大写字符（A 65-Z 65+25）
                    char ch = (char) (r.nextInt(26) + 65);
                    code += ch;
                    break;
                case 1:
                    //小写字符（a 97-z 97+25）
                    char ch1 = (char) (r.nextInt(26) + 97);
                    code += ch1;
                    break;
                case 2:
                    //数字字符
                    code += r.nextInt(10);//0-9
                    break;
                case 3:
                    //特殊符号
                    code += specilaChar.charAt(r.nextInt(16));
                    break;
            }
        }
        return code;
    }

    public static void main(String[] args) throws Exception { // 做测试用
//        MailUtils.sendMail("1310516892@qq.com","你好，这是一封测试邮件，无需回复。","测试邮件");//填写接收邮箱※
//        System.out.println("发送成功");
        System.out.println(creatPassword());
    }

}
