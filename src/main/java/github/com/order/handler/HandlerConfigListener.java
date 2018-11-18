package github.com.order.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 15:16
 */
@Component
public class HandlerConfigListener implements ApplicationListener {

    @Autowired
    private Map<String,IHandler> handlerMap;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        HandlerFactory.setHandlerMap(handlerMap);
    }
}
