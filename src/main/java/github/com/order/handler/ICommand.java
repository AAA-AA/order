package github.com.order.handler;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 11:15
 */
public interface ICommand {

    boolean oneOf(ICommand... commands);

    int getCode();
}
