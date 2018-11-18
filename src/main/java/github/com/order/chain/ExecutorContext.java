package github.com.order.chain;

import java.util.List;

/**
 * @author : hongqiangren.
 * @since: 2018/11/17 16:21
 */
public final class ExecutorContext {

    private static final ThreadLocal<ExecuteContext> executeContext = new ThreadLocal<>();

    public static void initExecuteContext(List<IProcessExecutor<IProcessContext>> executors,
                                          List<IAroundInterceptor<IProcessContext>> aroundIntercepts){
        if (executeContext.get() == null && executors != null) {
            executeContext.set(new ExecuteContext(executors,aroundIntercepts));
        } else {
            throw new IllegalArgumentException("ExecuteContext already exist ");
        }
    }

    public static List<IAroundInterceptor<IProcessContext>> getAroundIntercepts() {
        return executeContext.get().getAroundIntercepts();
    }

    public static IProcessExecutor<IProcessContext> getExecutors(int position) {
        if (position < 0 || executeContext.get().getExecutors().size() < position) {
            return null;
        }
        return executeContext.get().getExecutors().get(position);
    }

    public static int increasePosition() {
        return executeContext.get().increasePosition();
    }

    public static void clear() {
        executeContext.remove();
    }

    private static class ExecuteContext {
        public ExecuteContext(List<IProcessExecutor<IProcessContext>> executors,
                              List<IAroundInterceptor<IProcessContext>> aroundIntercepts) {
            this.executors = executors;
            this.aroundIntercepts = aroundIntercepts;
        }

        private List<IAroundInterceptor<IProcessContext>> aroundIntercepts;
        private List<IProcessExecutor<IProcessContext>> executors;
        private int position = -1;

        public int increasePosition() {
            return ++this.position;
        }

        public List<IProcessExecutor<IProcessContext>> getExecutors() {
            return executors;
        }

        public List<IAroundInterceptor<IProcessContext>> getAroundIntercepts() {
            return aroundIntercepts;
        }
    }




}
