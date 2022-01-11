package dev.freelance.freeserve;

import dev.freelance.freeserve.config.JwtTokenFilter;
import dev.freelance.freeserve.controller.AuthController;
import dev.freelance.freeserve.controller.ClientController;
import dev.freelance.freeserve.controller.OrderController;
import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.entity.AuthRequest;
import dev.freelance.freeserve.entity.BuyerClient;
import dev.freelance.freeserve.repository.ClientRepository;
import dev.freelance.freeserve.repository.OrderRepository;
import dev.freelance.freeserve.service.AbstractClientService;
import dev.freelance.freeserve.service.AbstractOrderService;
import io.jsonwebtoken.Jwts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class FreeserveApplicationTests {

	@Autowired
	private MockMvc mvc;
	ObjectMapper mapper = new ObjectMapper();


	@Test
	public void Message() throws JsonProcessingException, Exception {
		mvc.perform(MockMvcRequestBuilders.post("/login").content(mapper.writeValueAsString(new AuthRequest("mark","09721"))).accept(org.springframework.http.MediaType.APPLICATION_JSON).contentType(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
		System.out.println("ok!");
	}
}
