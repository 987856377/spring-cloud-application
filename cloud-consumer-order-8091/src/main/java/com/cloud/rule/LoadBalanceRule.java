package com.cloud.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package com.cloud.loadBalance.rule
 * @Author xuzhenkui
 * @Date 2020/4/29 18:59
 */
@Configuration
public class LoadBalanceRule {

    /*
        RoundRobinRule              轮询策略
        RandomRule                  随机策略
        RetryRule                   重试策略
        WeightedResponseTimeRule    权重策略
        BestAvailableRule           最佳策略
        AvailableFilteringRule      先过滤掉故障实例,再选择并发最小策略
        ZoneAvoidanceRule           默认规则,符合判断server所在区域的性能和server的可用性选择服务器
    */

    @Bean
    public IRule rule(){
        return new RandomRule();
    }
}
