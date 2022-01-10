package dev.freelance.freeserve;

import dev.freelance.freeserve.entity.AuthRequest;
import dev.freelance.freeserve.entity.BuyerClient;
import dev.freelance.freeserve.repository.OrderRepository;
import dev.freelance.freeserve.service.AbstractOrderService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/*@SpringBootTest
@WebMvcTest
class FreeserveApplicationTests {

	@Autowired
	private MockMvc mvc;
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void Message() {
	//	mvc.perform(MockMvcRequestBuilders.post("/login").content(mapper.writeValueAsString(new AuthRequest("mark","09721"))).accept(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

	}
}*/
