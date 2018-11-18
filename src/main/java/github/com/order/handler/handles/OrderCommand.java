package github.com.order.handler.handles;

import github.com.order.handler.ICommand;
import lombok.Getter;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 14:54
 */
public enum OrderCommand implements ICommand {

    ORDER_CREATE(10,"创建订单"),
    ORDER_DELIVERY(40,"订单配送"),
    ORDER_CANCEL(500,"订单取消")
    ;
    @Getter
    private int code;
    @Getter
    private String type;

    OrderCommand(int code, String type) {
        this.code = code;
        this.type = type;
    }

    @Override
    public boolean oneOf(ICommand... commands) {
        if (null == commands || commands.length == 0) {
            return true;
        }
        for (ICommand command : commands) {
            if (this.equals(command)) {
                return true;
            }
        }
        return false;
    }

    public static ICommand from(int code) {
        for(OrderCommand command : OrderCommand.values()) {
            if(command.getCode() == code) {
                return command;
            }
        }
        return null;
    }



    @Override
    public int getCode() {
        return 0;
    }
}
