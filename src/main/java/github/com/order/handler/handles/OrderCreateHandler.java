package github.com.order.handler.handles;

import github.com.order.handler.*;
import org.springframework.stereotype.Service;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 14:58
 */
@Service
public class OrderCreateHandler<T extends OrderContext> extends AbstractHandler<T> {

    @Override
    protected void doHandle(T context) {
        System.out.println("OrderCreateHandler");
        TestOrder order = context.getOrder();
        if (order == null) {
            order = new TestOrder();
        }
        order.setStatus(12);
        context.setOrder(order);
    }

    @Override
    protected boolean filter(T context) {
        if (context.command().oneOf(OrderCommand.ORDER_CREATE)) {
            return true;
        }
        return false;
    }
}
