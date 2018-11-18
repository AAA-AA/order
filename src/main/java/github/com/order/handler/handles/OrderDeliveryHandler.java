package github.com.order.handler.handles;

import github.com.order.handler.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 16:31
 */
@Service
@Slf4j
public class OrderDeliveryHandler<T extends OrderContext> extends AbstractHandler<T> {
    @Override
    protected void doHandle(T context) {
        log.info("start do order delivery");
    }

    @Override
    protected boolean filter(T context) {
        if (context.strategy() == HandleStrategy.NORMAL
                && context.getOrder().getStatus().equals(12)) {
            return true;
        }
        return false;
    }
}
