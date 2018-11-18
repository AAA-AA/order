package github.com.order.consts;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author : hongqiangren.
 * @since: 2018/11/17 23:20
 */
public final class Consts {
    public static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TF = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter TF_NS = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DTF_NS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final ZoneOffset ZONE_OFFSET = OffsetDateTime.now().getOffset();

    public static final String SUCCESS = "成功";

    public static final String RPID = "rpid";

    public static final String SYS_USER = "系统";

    public static final String SALT = "qwertyuiop[]";

    public static final String AMQP_TM = "rabbitTransactionManager";

    public static final String DBMS_TM = "dbmsTransactionManager";


}
