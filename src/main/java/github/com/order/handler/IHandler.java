package github.com.order.handler;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 11:10
 */
public interface IHandler<T extends IHandlerContext> {

    void handler(T context) throws Throwable;
}
