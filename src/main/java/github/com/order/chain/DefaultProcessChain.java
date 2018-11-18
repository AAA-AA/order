package github.com.order.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author : hongqiangren.
 * @since: 2018/11/17 17:21
 */
public final class DefaultProcessChain implements IProcessChain, IProcessStarter{
    private static final Logger log = LoggerFactory.getLogger(DefaultProcessChain.class);

    private DefaultProcessChain() {

    }
    static class DefaultProcessChainHolder {
        static final IProcessStarter chain = new DefaultProcessChain();
    }

    public static IProcessStarter getChain(List<IProcessExecutor<IProcessContext>> executors,
                                           List<IAroundInterceptor<IProcessContext>> beforeAfters) {
        ExecutorContext.initExecuteContext(executors, beforeAfters);
        return DefaultProcessChainHolder.chain;
    }




    @Override
    public void doExecute(IProcessContext context) {

    }

    @Override
    public void start(IProcessContext context) {
        try {
            doExecute(context);
        } finally {
            ExecutorContext.clear();
        }
    }
}
