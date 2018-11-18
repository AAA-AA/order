package github.com.order.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 16:17
 */
public abstract class AbstractStrategyHandlerRegistry {
    private static Map<IStrategy, HandlerChain> chainCache = new HashMap<>();

    protected static void register(IStrategy strategy, Class<? extends IHandler>... handlers) {
        if (strategy == null || handlers == null) {
            throw new IllegalArgumentException("strategy or handler can't be null when register");
        }
        HandlerChain chain = chainCache.get(strategy);
        if (chain != null) {
            chain.chain(handlers);
        } else {
            chain = new HandlerChain();
            chain.chain(handlers);
            chainCache.put(strategy, chain);
        }
    }

    public static HandlerChain getByStrategy(IStrategy strategy) {
        HandlerChain chain = chainCache.get(strategy);
        return chain;
    }
}
