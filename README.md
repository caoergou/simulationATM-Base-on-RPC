# 基于RPC的ATM模拟器

2020 分布式计算课程实践项目-重案组    
手写RPC框架，简单实现了**多线程**、**负载均衡**、**AOP**，制作了Java的GUi界面，使用MySQL进行数据管理          

### 理解本项目所需的前置知识点 ：            

- 反射
- 注入
- 动态代理
- AOP 即面向切面编程
- RPC

### 如何运行？

1. 在本地运用`bank MySQL5.6.sql`中的sql语句建立MySQL数据库
2. 在`server/ServerConfig.java`文件中，配置数据库连接、账号密码等
3. 首先运行服务器`test/TestServer.java`或者`test/TestServer2.java`
4. 运行客户端`test/TestClient.java`
5. `bank MySQL5.6.sql`自带测试数据
   - 用户1 
     - 账户：`123456`
     - 密码：`123456`
   - 用户2
     - 账户：`123457`
     - 密码：`123456` 



## 项目说明

开发编辑器：`IntelliJ IDEA`/`VS Code`   
开发语言：`Java`   
数据库：`MySQL 5.6`    
版本管理功能：`Git`    
代码托管平台：`Gitee.com` /`github.com`    

## 工作分工

CFG：项目小组长，负责分布式计算部分，管理Gitee项目  
LWT：负责应用前端界面设计   
DLY：负责后端功能实现    
CSZ：负责前期代码的整合与调试    
WJH：负责数据库实现与访问         

## 当前任务进度

- [x] 完成前端界面设计（LWT）
- [x] 完成后端功能实现（DLY）
- [x] 完成数据库设计（WJH）
- [x] 实现前端代码、后端代码、数据库代码整合调试（CSZ）
- [x] 手写RPC框架的客户端与服务器端，并实现多线程（CFG）
- [x] 运用AOP，实现简单的负载均衡(CFG)
- [x] 实现面向切面编程，加入登录次数访问(CFG)





## 截止时间

DDL：***2020-04-24***



## 心得

- 我真的是一个菜鸡
- **Think Twice，Code Once**
- 团队协作时，事前明确接口、代码风格、注释要求、协作方式很重要