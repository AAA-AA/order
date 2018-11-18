package github.com.order.handler.handles;

import github.com.order.handler.AbstractHandler;
import org.springframework.stereotype.Service;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 14:51
 */
@Service
public class OrderCancelHandler<T extends OrderContext> extends AbstractHandler<T> {

    @Override
    protected void doHandle(T context) {
        System.out.println("NormalHandler");
    }

    @Override
    protected boolean filter(T context) {
        if (context.command().oneOf(OrderCommand.ORDER_CANCEL)) {
            return true;
        }
        return false;
    }
}
