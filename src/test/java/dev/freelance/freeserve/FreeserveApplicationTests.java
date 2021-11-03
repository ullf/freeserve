package dev.freelance.freeserve;

import dev.freelance.freeserve.entity.AbstractClient;
import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.entity.BuyerClient;
import dev.freelance.freeserve.repository.OrderRepository;
import dev.freelance.freeserve.service.AbstractOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FreeserveApplicationTests {

	@MockBean
	private AbstractOrderService abstractOrderService;

	@MockBean
	private OrderRepository orderRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void pojoServeClient() {
		AbstractClient client = new AbstractClient(1,"Mark","Sokolov",true);
	}

	@Test
	void pojoBuyerClient() {
		var client = new BuyerClient("eee","aaa");
		System.out.println(client.getName());
	}

	/*@Test
	void findOrder() {
		AbstractOrder order = new AbstractOrder();
		order.setAbstractId(1);
		order.setAbstractName("order 3");
		order.setAbstractDescription("description of order 3");
		Mockito.when(abstractOrderService.createOrder(order.getAbstractName(),order.getAbstractDescription())).thenReturn(order);
		Mockito.when(orderRepository.findById(1).get()).thenReturn(order);
		//AbstractOrder order = new AbstractOrder(1,"order 4","description of order 3",null);

	}*/

}
