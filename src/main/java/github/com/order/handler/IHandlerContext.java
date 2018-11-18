package github.com.order.handler;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 11:12
 */
public interface IHandlerContext {

    ICommand command();

    HandleStrategy strategy();
}
