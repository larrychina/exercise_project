# exercise_project
owner exercise_project

this project is for exercising seckill_project.

  最近发现网上有这样一个学习的小项目，感觉非常值得学习，在学习的过程中顺便复习一下小的知识点，学习是一个漫长的过程，最重要的就是要坚持下去。

Restful接口设计

  1，什么是Restful？
      一种优雅的url的表达方式，
      资源的状态和状态转移


java 问题

    1，redirect 和forward问题

        forward 可以把问号和表单中的参数传到forward目标页面，而redirect不行

    2，jsp动态包含和静态包含

        静态导入<%@ include file="..jsp" %> 是将被导入的页面与其全部融入，两个页面融合成一个servlet进行编译，被导入的页面不需要一个完整的页面。
        动态导入<jsp:include page="..jsp"/> 动态导入是两个servlet,两个文件先独立编译再融合

js问题

    1，jquery one方法，on 方法区别？

        on方法只能对当前存在的元素绑定事件
        $(document).ready(function () {

            // Sets up click behavior on all button elements with the alert class
            // that exist in the DOM when the instruction was executed
            $("button.alert").on("click", function () {
                console.log("A button with the alert class was clicked!");
            });

            // Now create a new button element with the alert class. This button
            // was created after the click listeners were applied above, so it
            // will not have the same click behavior as its peers
            $("<button class='alert'>Alert!</button>").appendTo(document.body);
        });
        one方法适用于只需要执行一次的handler

        参考文档：http://www.cnblogs.com/mengdd/p/4384232.html

    2，clone方法作用?

        clone方法生成被选元素的副本，复制该元素以及其子元素文本和属性
        $(selector).clone(isIncludeEvent)



高并发优化

    1，静态资源部署到CDN上，

    2，缓存redis,


Redis使用过程中的坑？

    Redis protected-mode 是3.2 之后加入的新特性，在Redis.conf的注释中，我们可以了解到，他的具体作用和启用条件
    链接redis 时只能通过本地localhost (127.0.0.1）这个来链接，而不能用网络ip(192.168..)这个链接，问题然如果用网络ip 链接会报以下的错误：

    (error) DENIED Redis is running in protected mode because protected mode is enabled, no bind address was specified, no authentication password is requested to clients. In this mode connections are only accepted from the lookback interface. If you want to connect from external computers to Redis you may adopt one of the following solutions: 1) Just disable protected mode sending the command 'CONFIG SET protected-mode no' from the loopback interface by connecting to Redis from the same host the server is running, however MAKE SURE Redis is not publicly accessible from internet if you do so. Use CONFIG REWRITE to make this change permanent. 2) Alternatively you can just disable the protected mode by editing the Redis configuration file, and setting the protected mode option to 'no', and then restarting the server. 3) If you started the server manually just for testing, restart it with the --portected-mode no option. 4) Setup a bind address or an authentication password. NOTE: You only need to do one of the above things in order for the server to start accepting connections from the outside.
    是说处于保护模式，只能本地链接，我们需要修改配置文件../redis.conf
    1)打开配置文件把下面对应的注释掉

    # bind 127.0.0.1
    2)Redis默认不是以守护进程的方式运行，可以通过该配置项修改，使用yes启用守护进程，设置为no

    daemonize no
    3)保护模式

    protected-mode no
    4)最后关键的是：
    没反应应该是你启动服务端的时候没有带上配置文件。你可以./redis-server redis.conf
    你配置好了，但要重新启动redis,如果还是报一样的错误，很可能是没有启动到配置文件，所以需要真正的和配置文件启动需要：
    在redis.conf文件的当前目录下：

    $ redis-server redis.conf
    如果还是所某个端口已在使用，那么可能是有 后台程序在占用该端口，需要kill 掉该程序，重新带上配置文件。./redis-server redis.conf启动。
    将含有”redis”关键词的进程杀死:

    $ ps -ef | grep redis | awk ‘{print $2}’ | xargs kill -9

