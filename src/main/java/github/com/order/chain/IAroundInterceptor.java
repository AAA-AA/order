package github.com.order.chain;

/**
 * @author : hongqiangren.
 * @since: 2018/11/17 16:09
 */
public interface IAroundInterceptor<T extends IProcessContext> {

    /**
     * 拦截器名称
     * @return
     */
    String name();

    /**
     * 可过滤指定想要的状态
     * @param status
     * @return
     */
    boolean filter(IStatus status);

    /**
     * true：支持抛出异常后继续处理；false：抛出异常后结束接下去的before、executor、after处理
     * @return
     */
    boolean isHandleException();

    /**
     * 返回是否继续处理。true:继续接下去的before、executor、after处理；false:中断接下去的before、executor、after处理，直接结束
     * @param context
     * @return
     */
    boolean before(T context);

    /**
     * executor 的后续处理
     * @param context
     */
    void after(T context);

}

