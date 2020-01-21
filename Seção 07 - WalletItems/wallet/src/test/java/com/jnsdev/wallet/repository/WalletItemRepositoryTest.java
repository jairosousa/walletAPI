package com.jnsdev.wallet.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jnsdev.wallet.entity.Wallet;
import com.jnsdev.wallet.entity.WalletItem;
import com.jnsdev.wallet.util.enums.TypeEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletItemRepositoryTest {

	private static final Date DATE = new Date();
	private static final TypeEnum TYPE = TypeEnum.EN;
	private static final String DESCRIPTION = "Conta de Luz";
	private static final BigDecimal VALUE = BigDecimal.valueOf(65);
	
	private Long savedWalletItemId = null;
	private Long savedWalletId = null;

	@Autowired
	WalletItemRepository repository;

	@Autowired
	WalletRepository walletRepository;

	@Before
	public void setup() {
		Wallet w = new Wallet();
		w.setName("Carteira teste");
		w.setValue(BigDecimal.valueOf(250));
		walletRepository.save(w);

		WalletItem wi = new WalletItem(null, w, DATE, TYPE, DESCRIPTION, VALUE);
		repository.save(wi);
		
		savedWalletItemId = wi.getId();
		savedWalletId = w.getId();
	}

	@After
	public void tearDown() {
		repository.deleteAll();
		walletRepository.deleteAll();
	}
	
	@Test
	public void testSave() {
		Wallet w = new Wallet();
		w.setName("Carteira 1");
		w.setValue(BigDecimal.valueOf(500));
		walletRepository.save(w);

		WalletItem wi = new WalletItem(1L, w, DATE, TYPE, DESCRIPTION, VALUE);

		WalletItem response = repository.save(wi);

		assertNotNull(response);

		assertThat(response.getDescription(), is(DESCRIPTION));
		assertEquals(response.getType(), TYPE);
		assertEquals(response.getValue(), VALUE);
		assertEquals(response.getWallet().getId(), w.getId());
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testSaveInvalidWalletItem() {
		WalletItem wi = new WalletItem(null, null, DATE, null, DESCRIPTION, null);
		repository.save(wi);
	}
	
	@Test
	public void testUpdate() {
		Optional<WalletItem> wi = repository.findById(savedWalletItemId);
		
		String description = "Descrição alterado";
		
		WalletItem changed = wi.get();
		changed.setDescription(description);
		
		repository.save(changed);
		
		Optional<WalletItem> newWalletItem = repository.findById(savedWalletItemId);
		
		assertEquals(description, newWalletItem.get().getDescription());
	}
	
	@Test
	public void deleteWalletItem() {
		Optional<Wallet> wallet = walletRepository.findById(savedWalletId);
		
		WalletItem wi = new WalletItem(null, wallet.get(), DATE, TYPE, DESCRIPTION, VALUE);
		
		repository.save(wi);
		
		repository.deleteById(wi.getId());
		
		Optional<WalletItem> response = repository.findById(wi.getId());
		
		assertFalse(response.isPresent());
		
	}
}
