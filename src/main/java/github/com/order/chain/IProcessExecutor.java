package github.com.order.chain;

/**
 * @author : hongqiangren.
 * @since: 2018/11/16 22:54
 */
public interface IProcessExecutor<T extends IProcessContext> {

    void execute(T context, IProcessChain chain);

    String name();

    void handleException(Throwable e, T context);

}
