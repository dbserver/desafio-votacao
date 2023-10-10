package com.challenge;

import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VoteChallengeApplicationTests {

	@Mock
	private VoteChallengeApplication voteChallengeApplication;

	@Mock
	private ApplicationContext context;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(context);
	}

	@Test
	void voteChallengeApplication() {
		Assertions.assertNotNull(voteChallengeApplication);
	}
}
