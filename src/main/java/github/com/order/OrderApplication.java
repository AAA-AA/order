package github.com.order;

import github.com.order.handler.*;
import github.com.order.handler.handles.HandlerClient;
import github.com.order.handler.handles.OrderCommand;
import github.com.order.handler.handles.OrderContext;
import github.com.order.service.TestService;
import github.com.order.spring.EnableSpringContext;
import github.com.order.spring.SpringContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSpringContext
public class OrderApplication {

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(OrderApplication.class, args);
        TestService bean = SpringContext.getBean(TestService.class);
        bean.testExeCute();

        HandlerClient.invoke(new OrderContext(OrderCommand.ORDER_CREATE,HandleStrategy.NORMAL));
    }
}
