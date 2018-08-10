package org.txazo.pattern.behavior.state.vote;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StateTest {

	@Test
	public void testState() {
		VoteManager voteManager = new VoteManager();
		for (int i = 0; i < 9; i++) {
			voteManager.vote(1, 1);
		}
	}

}
