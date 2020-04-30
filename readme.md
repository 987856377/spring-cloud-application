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