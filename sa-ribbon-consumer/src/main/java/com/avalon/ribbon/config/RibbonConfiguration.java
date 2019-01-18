package com.avalon.ribbon.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @author jwwang on 2019/1/16
 */
public class RibbonConfiguration {
    @Autowired
    IClientConfig ribbonClientConfig;
    @Bean
    public IPing ribbonPing(IClientConfig config){
        return new PingUrl();
    }
    public IRule ribbonRule(IClientConfig config){
        return new AvailabilityFilteringRule();
    }
}
