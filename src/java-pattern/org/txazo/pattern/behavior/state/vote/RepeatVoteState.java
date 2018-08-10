package org.txazo.pattern.behavior.state.vote;

public class RepeatVoteState implements VoteState {

	@Override
	public void vote(Integer id, int voteItem, VoteManager voteManager) {
		System.out.println("请不要重复投票");
	}

}
