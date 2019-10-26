# JavaWeb 分页查询示例
[可以查看CSDN博主「loetca」的原创文章](https://blog.csdn.net/adsl624153/article/details/72512459 "JavaWeb 分页查询的实现")



## 声明

**首先非常感谢各位朋友的支持！**

当时写了一个笔记式的文章，没有想到会这么受欢迎，有很多朋友找我要源码，为了方便你们继续学习，所以我重新敲出来了这个项目



## 注意事项

1. **记得修改c3p0配置文件里的数据库用户名和密码！**
2. 由于这是Java Web项目，所以我是专门通过Eclipse创建的动态Web项目。如果你熟悉Maven的话，note/pom.xml我已经给出了，相信你转换过来是没有问题的
3. Jar包我就不上传了，不浪费空间，如果你继续使用动态Web项目，那参考下面依赖的jar包，并添加到Build Path里
4. sql/SQL.txt保存了项目需要的SQL语句



## 运行环境

JDK8
Apache Tomcat 7



依赖Jar包（详细可以见note/POM依赖）
----------
com.mchange:c3p0:0.9.5.4
com.mchange:mchange-commons-java:0.2.15
commons-dbutils:commons-dbutils:1.7
javax.servlet:jstl:1.2
mysql:mysql-connector-java:5.1.48

