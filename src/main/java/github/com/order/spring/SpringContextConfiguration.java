package github.com.order.spring;

import org.springframework.context.annotation.Bean;

/**
 * @author : hongqiangren.
 * @since: 2018/11/17 17:06
 */
public class SpringContextConfiguration {
    public SpringContextConfiguration() {

    }

    @Bean
    public SpringContextListener listener() {
        return new SpringContextListener();
    }
}
