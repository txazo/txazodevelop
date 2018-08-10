package org.txazo.pattern.behavior.state.vote;

public interface VoteState {

	public void vote(Integer id, int voteItem, VoteManager voteManager);

}
