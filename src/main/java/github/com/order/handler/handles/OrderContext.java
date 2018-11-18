package github.com.order.handler.handles;

import github.com.order.handler.HandleStrategy;
import github.com.order.handler.ICommand;
import github.com.order.handler.IHandlerContext;
import github.com.order.handler.handles.TestOrder;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 18:23
 */
public class OrderContext implements IHandlerContext {

    private ICommand command;
    private HandleStrategy strategy;
    private TestOrder order;

    public OrderContext(ICommand command,HandleStrategy strategy) {
        this.command = command;
        this.strategy = strategy;
    }


    @Override
    public ICommand command() {
        return this.command;
    }

    @Override
    public HandleStrategy strategy() {
        return this.strategy;
    }

    public void setOrder(TestOrder order) {
        this.order = order;
    }

    public TestOrder getOrder() {
        return order;
    }
}
