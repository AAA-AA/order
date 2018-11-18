package github.com.order;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author : hongqiangren.
 * @since: 2018/11/18 14:02
 */
public class ArrayListTest {
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(2, 3, 1, 4, 56, 45);
        for (Integer num : list) {
            System.out.println(num);
        }









    }
}
