package org.txazo.pattern.behavior.state.vote;

public class BlackVoteState implements VoteState {

	@Override
	public void vote(Integer id, int voteItem, VoteManager voteManager) {
		System.out.println("进入黑名单，将禁止登录和使用本系统");
	}

}
