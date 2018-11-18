package github.com.order.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author : hongqiangren.
 * @since: 2018/11/17 17:01
 */
public final class SpringContextListener implements ApplicationContextAware {

    public SpringContextListener() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.setApplicationContext(applicationContext);
        ConfigurableApplicationContext configurable = (ConfigurableApplicationContext) applicationContext;
        ConfigurableEnvironment environment = configurable.getEnvironment();
        SpringContext.setConfigurableEnvironment(environment);
    }
}
