course-ShoppingSystem
实训课JAVA线上购物系统 
------------------------------------------------------------------
1.
src/main/resources/mybatis-config.xml 文件中
16行为连接数据库的名称
17行对应连接数据库的账户和密码
不填写会导致项目大部分功能不可用
2.
src/main/java/com/cy/util/MailUtils.java 文件中
13行更换为发送邮件的邮箱地址
14行更换为发送邮件的邮箱授权码
不填写会导致发送邮件功能不可用
3.
先使用"sql建表"文件中的sql语句建立项目所需的表，再根据上述提示更换为自己的信息
-------------------------------------------------------------------

项目结构及说明
course-ShoppingSystem:
│  pom.xml  // maven 的配置文件，用以描述项目的各种信息，主要为项目的依赖配置。
│  README.txt
│  
├─.idea   // IDEA项目的配置信息
│      ...
│                          
├─sql建表   // 项目所需表的建表sql语句
│      ...
│      
└─src   // 项目的资源
    └─main
        ├─java
        │  └─com
        │      └─cy
        │          ├─mapper  // Dao层(mapper层)，存放mapper类，对数据库进行数据持久化操作。
        │          │      BrandMapper.java  // 商品相关的数据库操作
        │          │      ShoppingCartMapper.java  // 购物车相关的数据库操作
        │          │      UserMapper.java  // 用户相关的数据库操作
        │          │      
        │          ├─pojo  // Entity层，存放实体类
        │          │      Brand.java  // 商品实体类
        │          │      Cart.java  // 购物车商品实体类
        │          │      Order.java  // 订单实体类
        │          │      OrderBean.java  // 封装商品类和订单类
        │          │      User.java  // 用户实体类
        │          │      
        │          ├─service  // Service层，业务逻辑层，处理逻辑上的业务，而不去考虑具体的实现。
        │          │  │  BrandService.java  // 商品相关的操作
        │          │  │  ShoppingCartService.java  // 购物车相关的操作
        │          │  │  UserService.java  // 用户相关的操作
        │          │  │  
        │          │  └─impl  // 存放service接口的实现类
        │          │          BrandServiceImpl.java
        │          │          ShoppingCartServiceImpl.java
        │          │          UserServiceImpl.java
        │          │          
        │          ├─util  // Utils层，用于存放工具类
        │          │      MailUtils.java  // 发送邮件、随机创建验证码和密码
        │          │      PasswordEncryptionUtils.java  // 密码哈希加密
        │          │      SqlSessionFactoryUtils.java  // 获取SqlSessionFactory对象
        │          │      ValidatorUtils.java  // 正则表达式验证，验证邮箱地址是否合法以及密码是否符合要求
        │          │      
        │          └─web
        │              └─servlet  // Servlet层，用Java编写的服务器端程序，主要功能在于交互式地浏览和修改数据，生成动态Web内容。
        │                      BaseServlet.java  // 替换HttpServlet，根据请求的最后一段路径进行方法分发
        │                      BrandServlet.java  // 处理商品相关的请求
        │                      UserServlet.java  // 处理用户信息相关的请求
        │                      
        ├─resources
        │  │  mybatis-config.xml  // mybatis的配置文件
        │  │  
        │  └─com
        │      └─cy
        │          └─mapper  // 存放与src/main/java/com/cy/mapper中的mapper类对应的mapper映射配置文件
        │                  BrandMapper.xml
        │                  ShoppingCartMapper.xml
        │                  UserMapper.xml
        │                  
        └─webapp  // 存放前端的资源文件，包括html、css、js等文件
                 ...
                    
