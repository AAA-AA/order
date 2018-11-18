package github.com.order.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 11:16
 */
@Slf4j
public abstract class AbstractHandler<T extends IHandlerContext > implements IHandler<T> {

    @Override
    public void handler(T context) {
        if (filter(context)) {
            log.info(this.getClass().getSimpleName() + "handle");
            doHandle(context);
        }
    }

    protected abstract void doHandle(T context);

    protected abstract boolean filter(T context);
}
