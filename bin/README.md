jersey_mongodb_test
===================

使用jersey的restfull风格架构，搭建后台接口开发的框架，使用mysql和mongodb作为db存储，采用http协议发送和接收请求，发送给客户端Json的数据格式。

1、高效、简洁、灵活、可扩展的框架模型。

2、采用的技术DB层使用mysql和mongodb两种数据源，可以采用任意一种，mysql数据源采用了ibatis作为持久层处理，CRUD操作使用ibator
生成所需要的bean，dao，sqlmap；service和resource层需要按照业务封装一下就可以。

3、接口采用了jersey的注解方式，可以把service层和resource层分离，这样在以后想要扩展成为WEB项目时候，只需要添加web层显示，如spring mvc 的Controller层就可以提供web实现了，service层可以在不改变原来代码格式的情况下，提供需要的接口服务，resource层也可以正常提供给第三方提供接口服务。

4、工程提供了常用的工具类Json、XML等工具类，方便转换为客户端需要的数据格式。
