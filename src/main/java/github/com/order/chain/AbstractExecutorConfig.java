package github.com.order.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : hongqiangren.
 * @since: 2018/11/17 17:26
 */
public abstract class AbstractExecutorConfig {
    private final Logger log = LoggerFactory.getLogger("EXECUTOR-CONFIG");
    private final Map<Integer, Class<? extends IProcessExecutor<IProcessContext>>> regExcutors = new HashMap<>();
    private final List<IProcessExecutor<IProcessContext>> singletonExecutors = new ArrayList<>();
    private final List<IAroundInterceptor<IProcessContext>> aroundInterceptors = new ArrayList<>();


    private Class<? extends IProcessExecutor<IProcessContext>>[] getExecutorClass() {
        List<Class<? extends IProcessExecutor<IProcessContext>>> ecs = new ArrayList<>();
        Map<Integer, Class<? extends IProcessExecutor<IProcessContext>>> emps = getExecutorOrders();
        List<Integer> orders = emps.keySet().stream().sorted().collect(Collectors.toList());
        for (Integer order : orders) {
            ecs.add(emps.get(order));
        }
        return ecs.stream().distinct().toArray(Class[]::new);
    }


    private List<IProcessExecutor<IProcessContext>> threadSafeExecutors() {
        List<IProcessExecutor<IProcessContext>> newExecutors = new ArrayList<>();
        Class<? extends IProcessExecutor<IProcessContext>>[] executorClass = getExecutorClass();
        for (int index = 0; index < executorClass.length; index++) {
            try {
                IProcessExecutor<IProcessContext> executor = executorClass[index].newInstance();
                log.info("加载订单处理处理单元-{}-{}", index, executor.name());
                newExecutors.add(executor);
            } catch (Exception e) {
                log.error("加载订单处理单元失败-{}-{}-{}", executorClass[index].getName(), e.getClass().getName(), e.getMessage());
                throw new RuntimeException("get threadSafeExecutors failed", e);
            }
        }
        return newExecutors;
    }

    private Map<Integer, Class<? extends IProcessExecutor<IProcessContext>>> getExecutorOrders() {
        return this.regExcutors;
    }

    private List<IAroundInterceptor<IProcessContext>> getAroundIntercepts() {
        return this.aroundInterceptors;
    }

    private List<IProcessExecutor<IProcessContext>> getExecutors() {
        return threadSafeExecutors();
    }

    /**
     * 注册相应的处理器
     * @return
     */
    protected final void register(int position, Class<? extends IProcessExecutor<?>> executor) {
        regExcutors.put(position, (Class<? extends IProcessExecutor<IProcessContext>>) executor);
    }


    /**
     * 注册方法处理拦截器
     * @param aroundInterceptor
     */
    protected final void register(Class<? extends IAroundInterceptor<?>> aroundInterceptor) {
        try {
            aroundInterceptors.add((IAroundInterceptor<IProcessContext>) aroundInterceptor.newInstance());
        } catch (Exception e) {
            log.error("加载订单处理单元失败-{}-{}-{}", aroundInterceptor.getName(), e.getClass().getName(), e.getMessage());
            throw new RuntimeException("register aroundInterceptor failed ",e);
        }
    }

    protected void ready() {
        List<IProcessExecutor<IProcessContext>> executors = this.threadSafeExecutors();
        List<IAroundInterceptor<IProcessContext>> beforeAfters = this.getAroundIntercepts();
        ExecutorContext.initExecuteContext(executors, beforeAfters);
    }

}
