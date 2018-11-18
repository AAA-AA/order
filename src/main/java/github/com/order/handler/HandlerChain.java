package github.com.order.handler;


import github.com.order.spring.SpringContext;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 11:23
 */
@Slf4j
public  class HandlerChain {

    private List<IHandler> handlers = new ArrayList<>();

    public IHandlerContext doExecute(IHandlerContext context) throws Throwable {
        for (IHandler handler : handlers) {
            try {
                handler.handler(context);
            } catch (Throwable throwable) {
                throw new RuntimeException(handler.getClass().getName() + " handle failed", throwable);
            }
        }
        return context;
    }

    public HandlerChain chain(Class<? extends IHandler>... handlerClasses) {
        for (Class<? extends IHandler> handlerClass : handlerClasses) {
            handlers.add(HandlerFactory.getHandler(handlerClass));
        }
        return this;
    }
}
