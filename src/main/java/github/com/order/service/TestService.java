package github.com.order.service;

import org.springframework.stereotype.Service;

/**
 * @author : hongqiangren.
 * @since: 2018/11/17 17:11
 */
@Service
public class TestService {

    public void testExeCute() {
        System.out.println("1111"+this.getClass().getName());
    }
}
