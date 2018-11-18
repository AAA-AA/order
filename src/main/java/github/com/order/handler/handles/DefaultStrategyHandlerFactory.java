package github.com.order.handler.handles;

import github.com.order.handler.AbstractStrategyHandlerRegistry;
import github.com.order.handler.HandleStrategy;
import github.com.order.handler.handles.OrderCreateHandler;
import github.com.order.handler.handles.OrderDeliveryHandler;

import java.util.Map;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 16:25
 */
public class DefaultStrategyHandlerFactory extends AbstractStrategyHandlerRegistry {

    static {
        register(HandleStrategy.NORMAL,
                OrderCreateHandler.class,
                OrderDeliveryHandler.class);
    }

    public static void init() {

    }
}
