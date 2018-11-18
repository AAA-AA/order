package github.com.order.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author : hongqiangren.
 * @since: 2018/11/17 17:04
 */
public final class SpringContext {
    private static ApplicationContext ctx;
    private static ConfigurableEnvironment env;

    public static <T> T getBean(String name) {
        return (T) ctx.getBean(name);
    }

    public static <T> T getBean(Class<T> cls) {
        return ctx.getBean(cls);
    }

    public static String getProp(String key, String def) {
        return null == env ? def : env.getProperty(key, def);
    }

    public static String getProp(String key) {
        return getProp(key, "");
    }

    public static ConfigurableEnvironment getEnv() {
        return env;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    public static void setConfigurableEnvironment(ConfigurableEnvironment environment) {
        env = environment;
    }
}
