package github.com.order;

/**
 * @author : hongqiangren.
 * @since: 2018/11/17 17:58
 */
public class TestThrowException {
    public static void main(String[] args) {

        int num = 0;
        num = getNum();

    }

    private static int getNum() {
        try {
            if (1 == 1) {
                throw new IllegalArgumentException("参数错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            return 2;
        }
    }
}
