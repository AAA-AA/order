package github.com.order.handler.handles;

import github.com.order.handler.HandlerChain;
import github.com.order.handler.IHandlerContext;
import github.com.order.handler.handles.DefaultStrategyHandlerFactory;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 15:04
 */
public final class HandlerClient {

    public static IHandlerContext invoke(IHandlerContext context) throws Throwable {
        DefaultStrategyHandlerFactory.init();
        HandlerChain chain = DefaultStrategyHandlerFactory.getByStrategy(context.strategy());
        chain.doExecute(context);
        return context;
    }

}
