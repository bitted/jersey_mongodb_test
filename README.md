jersey_mongodb_test
===================

使用jersey的restfull风格架构，搭建后台接口开发的框架，使用mysql和mongodb作为db存储，采用http协议发送和接收请求。

1、采用了项目中使用的框架原型，高效、简洁、灵活、高并发的框架模型。

2、采用的技术DB层使用mysql和mongodb两种数据源，可以采用任意一种，mysql数据源采用了ibatis作为持久层处理，CRUD操作使用ibator

生成所需要的dao，sqlmap和service，

3、接口采用了jersey的注解方式，可以把service层和resource层分离，这样在以后想要扩展成为WEB项目时候，只需要添加web层显示，如spring mvc 的Controller层就可以提供web实现了，service层可以在不改变

原来代码格式的情况下，提供需要的接口服务，resource层也可以正常提供给第三方提供接口服务。

