package github.com.order.chain;

/**
 * @author : hongqiangren.
 * @since: 2018/11/16 22:52
 */
public interface IProcessContext {

    IStatus status();

    String user();

    Long brandId();

    Long shopId();

    String shopName();

    long executeTime();

    String tokenId();

}
