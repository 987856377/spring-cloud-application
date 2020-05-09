### 通用组件
~~~
cloud-api-common
~~~

###使用 eureka 做服务注册中心
~~~
cloud-eureka-server-5001
cloud-eureka-server-5002
cloud-provider-payment-8081
cloud-provider-payment-8082
cloud-consumer-order-8091(使用Ribbon+RestTemplate做软负载均衡服务调用)
cloud-consumer-order-8092(使用Feign做服务调用)
~~~
###使用 zookeeper 做服务注册中心
~~~
cloud-provider-payment-8084
cloud-consumer-order-8094
~~~
###使用 hystrix dashboard 做服务监控
~~~
cloud-provider-payment-8086     (被监控者)  
cloud-hystrix-dashboard-7001    (监控者) 监控地址: http://localhost:8086/hystrix.stream
~~~
###使用 hystrix 做服务熔断, eureka 做服务注册中心, feign 做服务调用
~~~
cloud-provider-payment-8086
cloud-consumer-order-8096
~~~
###使用 gateway 做服务网关
~~~
cloud-gateway-service-9527
~~~
###使用 config 做配置中心
~~~
cloud-config-center-7021    访问地址: http://config-7021.com:7021/master/config-dev.yml
cloud-config-client-7031    手动刷新: curl -X POST "http://localhost:7031/actrator/refresh"
~~~
###使用 zipkin 做服务链路监控
~~~
启动jar包:     java -jar .\zipkin-server-2.12.9-exec.jar
访问监控:      http://localhost:9411/zipkin/
cloud-provider-payment-8086
cloud-consumer-order-8096
~~~
###使用 spring-cloud-alibaba nacos 做服务注册中心, 配置中心
~~~
cloud-provider-payment-8087
cloud-provider-payment-8088

cloud-consumer-order-8097

cloud-nacos-config-client-7131
~~~
###使用 spring-cloud-alibaba sentinel 做服务降级,熔断,限流 , nacos 做服务注册中心, 配置中心
~~~
cloud-sentinel-serivce-8401
~~~
###使用 spring-cloud-alibaba sentinel 做服务降级,熔断,限流 , nacos 做服务注册中心, 配置中心, ribbon做负载均衡, openfeign做服务调用, seata做分布式事务控制
~~~
cloud-provider-payment-9003
cloud-provider-payment-9004

cloud-consumer-order-8098
~~~











### sentinel 控制台
~~~
资源名: 唯一名称,默认请求路径
针对来源: Sentinel 可以针对调用者进行限流, 填写微服务名, 默认default(不区分来源)
阈值类型/单机阈值:
    QPS(每秒请求数量): 当调用该api的QPS达到阈值的时候, 进行限流
    线程数: 当调用该api的线程数达到阈值的时候, 进行限流
是否集群: 不需要集群
流控模式:
    直接: api达到限流条件时, 直接限流
    关联: 当关联的资源达到阈值时, 就限流自己
    链路: 只记录指定链路上的流量(指定资源从入口资源进来的流量, 如果达到阈值, 就进行限流) [api级别的针对来源]
流控效果:
    快速失败: 直接失败, 抛异常
    Warm Up: 根据codeFactor(冷加载银子, 默认3)的值, 从阈值/codeFactor, 经过预热时长, 才达到设置的QPS阈值
    排队等待: 控制请求通过的时间间隔, 即让请求以均匀的速度通过, 对应的是漏桶算法
~~~
