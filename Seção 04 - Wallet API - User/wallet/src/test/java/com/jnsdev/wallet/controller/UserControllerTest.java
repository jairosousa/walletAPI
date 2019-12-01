package com.jnsdev.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jnsdev.wallet.dto.UserDTO;
import com.jnsdev.wallet.entity.User;
import com.jnsdev.wallet.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

	private static final String EMAIL = "email@teste.com";

	private static final String NAME = "User teste";

	private static final String PASSWORD = "123456";

	private static final String URL = "/user";

	/**
	 * Vamos simular requisições na rota de usuario
	 */
	
	@MockBean
	UserService service;
	
	@Autowired
	MockMvc mvc;
	
	public void testSave() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJsonPayload())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}
	
	public User getMockUser() {
		User u = new User();
		u.setEmail(EMAIL);
		u.setName(NAME);
		u.setPassowrd(PASSWORD);
		
		return u;
	}
	
	public String getJsonPayload() throws JsonProcessingException {
		UserDTO dto = new UserDTO();
		dto.setEmail(EMAIL);
		dto.setName(NAME);
		dto.setPassowrd(PASSWORD);
		
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(dto);
		
	}
}
