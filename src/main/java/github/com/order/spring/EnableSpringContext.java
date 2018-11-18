package github.com.order.spring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author : hongqiangren.
 * @since: 2018/11/17 17:08
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({SpringContextConfiguration.class})
public @interface EnableSpringContext {
}
