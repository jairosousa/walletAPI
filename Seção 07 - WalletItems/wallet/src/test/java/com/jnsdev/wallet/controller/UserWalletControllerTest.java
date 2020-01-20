package com.jnsdev.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
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
import com.jnsdev.wallet.dto.UserWalletDTO;
import com.jnsdev.wallet.entity.User;
import com.jnsdev.wallet.entity.UserWallet;
import com.jnsdev.wallet.entity.Wallet;
import com.jnsdev.wallet.service.UserWalletService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserWalletControllerTest {

	private static final Long ID = 1L;
	private static final Long ID_USER = 1L;
	private static final Long ID_WALLET = 1L;
	private static final String URL = "/user-wallet";

	@MockBean
	UserWalletService service;

	@Autowired
	MockMvc mvc;

	@Test
	public void testSave() throws Exception {

		BDDMockito.given(service.save(Mockito.any(UserWallet.class))).willReturn(getMockUserWallet());

		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, ID_USER, ID_WALLET))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.users").value(ID_USER))
				.andExpect(jsonPath("$.data.wallet").value(ID_WALLET));
	}
	
	@Test
	public void testSaveInvalidUserWallet() throws JsonProcessingException, Exception {
		BDDMockito.given(service.save(Mockito.any(UserWallet.class))).willReturn(getMockUserWallet());

		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, null, null))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]").value("Informe o id da carteira"))
				.andExpect(jsonPath("$.errors[1]").value("Informe o id do usuário"));

	}

	private UserWallet getMockUserWallet() {
		User u = new User();
		u.setId(ID_USER);

		Wallet w = new Wallet();
		w.setId(ID_WALLET);

		UserWallet uw = new UserWallet();
		uw.setId(ID);
		uw.setUsers(u);
		uw.setWallet(w);

		return uw;
	}

	private String getJsonPayload(Long id, Long idUser, Long idWallet) throws JsonProcessingException {
		UserWalletDTO dto = new UserWalletDTO();
		dto.setId(id);
		dto.setUsers(idUser);
		dto.setWallet(idWallet);

		return new ObjectMapper().writeValueAsString(dto);
	}
}
