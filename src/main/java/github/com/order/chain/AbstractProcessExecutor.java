package github.com.order.chain;

import github.com.order.consts.Consts;
import org.slf4j.Logger;
import org.slf4j.MDC;

/**
 * @author : hongqiangren.
 * @since: 2018/11/17 23:17
 */
public abstract class AbstractProcessExecutor<T extends IProcessContext> implements IProcessExecutor<T>{
    public abstract boolean filter(IStatus command);

    public abstract boolean doExecute(T context);

    protected abstract Logger getLog();

    protected abstract String module();

    protected final Logger log = getLog();

    @Override
    public void execute(T context, IProcessChain chain) {
        if (!filter(context.status())) {
            chain.doExecute(context);
            return;
        }
        log.info("餐厅：{}-{}-{}-{}-开始-{}", context.shopId(), context.shopName(), module(), name(), MDC.get(Consts.RPID));
        boolean next = doExecute(context);
        log.info("餐厅：{}-{}-{}-{}-完毕-{}", context.shopId(), context.shopName(), module(), name(), MDC.get(Consts.RPID));
        if (next) {
            chain.doExecute(context);
            return;
        }
        log.info("餐厅：{}-{}-{}-{}-清空上下文-{}", context.shopId(), module(), context.shopName(), name(), MDC.get(Consts.RPID));

    }
}
