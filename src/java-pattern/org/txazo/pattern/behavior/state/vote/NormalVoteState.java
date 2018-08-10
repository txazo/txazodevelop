package org.txazo.pattern.behavior.state.vote;

public class NormalVoteState implements VoteState {

	@Override
	public void vote(Integer id, int voteItem, VoteManager voteManager) {
		voteManager.getVoteMap().put(id, voteItem);
		System.out.println("恭喜投票成功");
	}

}
