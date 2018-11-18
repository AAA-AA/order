package github.com.order.chain;

/**
 * @author : hongqiangren.
 * @since: 2018/11/16 22:49
 */
public interface IStatus {

    boolean oneOf(IStatus... statuses);

    int getCode();
}
