package github.com.order.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author : hongqiangren.
 * @since: 2018/11/17 17:26
 */
public class AbstractProcessChain extends AbstractExecutorConfig implements IProcessChain, IProcessStarter {
    private static final Logger log = LoggerFactory.getLogger("PROCESS-CHAIN");

    @Override
    public void doExecute(IProcessContext context) {
        int position = ExecutorContext.increasePosition();
        if (0 == position) {
            log.info("start to execute: {}-{}", context.shopId(), context.shopName());
        }
        IProcessExecutor<IProcessContext> executor = ExecutorContext.getExecutors(position);
        if (executor == null) {
            log.info("task has done:{}-{}", context.shopId(), context.shopName());
        }

        Long sid = context.shopId();
        String snm = context.shopName();
        String enm = executor.name();

        try {
            executor.execute(context, this);
        } catch (Exception e) {
            log.info("餐厅-{}-{}-{}-原因-{}-异常-{}:", sid, snm, enm, e.getMessage(), e);
            log.info("餐厅：{}-{}异常处理器开始", sid, enm);
            executor.handleException(e, context);
            log.info("餐厅：{}-{}异常处理器结束", sid, enm);
            throw e;
        } finally {
            log.info("餐厅-{}-{}-{}-结束-耗时-{}:", sid, snm, enm, context.executeTime());
        }
    }

    @Override
    public void start(IProcessContext context) {
        try {
            ready();
            boolean canContinue = todo(context);
            if (canContinue) {
                try {
                    doExecute(context);
                } finally {
                    done(context);
                }
            }
        } finally {
            ExecutorContext.clear();
        }
    }

    private boolean todo(IProcessContext context) {
        List<IAroundInterceptor<IProcessContext>> aroundIntercepts = ExecutorContext.getAroundIntercepts();
        if (null == aroundIntercepts || aroundIntercepts.size() == 0) {
            return true;
        }
        for (IAroundInterceptor<IProcessContext> aroundInterceptor : aroundIntercepts) {
            if (!aroundInterceptor.filter(context.status())) {
                continue;
            }
            if (aroundInterceptor.isHandleException()) {
                log.info("餐厅：{}-{}-{}-执行开始", context.shopId(), context.shopName(), aroundInterceptor.name());
                boolean canContinue = aroundInterceptor.before(context);
                log.info("餐厅：{}-{}-{}-执行结束", context.shopId(), context.shopName(), aroundInterceptor.name());
                if (canContinue) {
                    continue;
                } else {
                    return false;
                }
            }

            boolean canContinue = true;
            try {
                log.info("餐厅：{}-{}-{}-执行开始", context.shopId(), context.shopName(), aroundInterceptor.name());
                canContinue = aroundInterceptor.before(context);
                log.info("餐厅：{}-{}-{}-执行结束", context.shopId(), context.shopName(), aroundInterceptor.name());
            } catch (Exception e) {
                Long sid = context.shopId();
                String snm = context.shopName();
                String enm = aroundInterceptor.name();
                log.info("餐厅-{}-{}-{}-原因-{}-异常-{}:", sid, snm, enm, e.getMessage(), e);
                log.error("", e);
                if (canContinue) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private void done(IProcessContext context) {
        List<IAroundInterceptor<IProcessContext>> aroundIntercepts = ExecutorContext.getAroundIntercepts();
        if (null == aroundIntercepts || aroundIntercepts.size() == 0) {
            return;
        }
        for (IAroundInterceptor<IProcessContext> aroundInterceptor : aroundIntercepts) {
            if (!aroundInterceptor.filter(context.status())) {
                continue;
            }
            if (aroundInterceptor.isHandleException()) {
                log.info("餐厅：{}-{}-{}-执行开始", context.shopId(), context.shopName(), aroundInterceptor.name());
                aroundInterceptor.after(context);
                log.info("餐厅：{}-{}-{}-执行结束", context.shopId(), context.shopName(), aroundInterceptor.name());
                continue;
            }
            try {
                log.info("餐厅：{}-{}-{}-执行开始", context.shopId(), context.shopName(), aroundInterceptor.name());
                aroundInterceptor.after(context);
                log.info("餐厅：{}-{}-{}-执行结束", context.shopId(), context.shopName(), aroundInterceptor.name());
            } catch (Exception e) {
                Long sid = context.shopId();
                String snm = context.shopName();
                String enm = aroundInterceptor.name();
                log.info("餐厅-{}-{}-{}-原因-{}-异常-{}:", sid, snm, enm, e.getMessage(), e.getClass().getName());
                log.error("", e);
            }
        }
    }
}
