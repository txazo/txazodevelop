package org.txazo.pattern.behavior.state.vote;

public class SpiteVoteState implements VoteState {

	@Override
	public void vote(Integer id, int voteItem, VoteManager voteManager) {
		Integer user = voteManager.getVoteMap().get(id);
		if (user != null) {
			voteManager.getVoteMap().remove(id);
		}
		System.out.println("恶意投票，取消投票资格");
	}

}
