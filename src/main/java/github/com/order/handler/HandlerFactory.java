package github.com.order.handler;

import lombok.Setter;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 14:09
 */
public final class HandlerFactory {
    @Setter
    private static Map<String, IHandler> handlerMap = new HashMap<>();

    public static IHandler getHandler(Class<? extends IHandler> beanClass) {
        return getHandler(StringUtils.uncapitalize(beanClass.getSimpleName()));
    }

    public static IHandler  getHandler(String beanName) {
        if (handlerMap.containsKey(beanName)) {
            return handlerMap.get(beanName);
        }
        throw new NoSuchBeanDefinitionException("没有找到对应的beanName=" + beanName + "的处理器");
    }


}
