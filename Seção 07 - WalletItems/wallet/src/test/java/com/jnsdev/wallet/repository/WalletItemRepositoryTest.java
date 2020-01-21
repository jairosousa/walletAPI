package com.jnsdev.wallet.repository;

import com.jnsdev.wallet.entity.Wallet;
import com.jnsdev.wallet.entity.WalletItem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletItemRepositoryTest {

    private static final Date DATE = new Date();
    private static final String TYPT = "ENTRADA";
    private static final String DESCRIPTION = "Conta de Luz";
    private static final BigDecimal VALUE = BigDecimal.valueOf(65);

    @Autowired
    WalletItemRepository repository;

    @Test
    public void testSave(){
        Wallet w = new Wallet();
        w.setName("Carteira 1");
        w.setValue(BigDecimal.valueOf(500));

        WalletItem wi = new WalletItem(1L, w, DATE, TYPT, DESCRIPTION, VALUE);

        WalletItem response = repository.save(wi);

        assertNotNull(response);
    }
}
