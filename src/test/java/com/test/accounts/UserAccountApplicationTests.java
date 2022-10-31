package com.test.accounts;

import com.test.accounts.controller.AccountStatementRestController;
import com.test.accounts.controller.AuthController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserAccountApplicationTests {

	@Autowired
	private AccountStatementRestController accountStatementRestController;

	@Autowired
	private AuthController authController;

	@Test
	void contextLoads() {
		assertNotNull(authController);
		assertNotNull(authController);
	}
}
