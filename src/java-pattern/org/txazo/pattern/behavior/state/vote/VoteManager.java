package org.txazo.pattern.behavior.state.vote;

import java.util.HashMap;
import java.util.Map;

public class VoteManager {

	private VoteState state = null;

	private Map<Integer, Integer> voteMap = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> voteCountMap = new HashMap<Integer, Integer>();

	public void vote(Integer id, int voteItem) {
		Integer voteCount = voteCountMap.get(id);

		if (voteCount == null) {
			voteCount = 0;
		}

		voteCount++;
		voteCountMap.put(id, voteCount);

		if (voteCount == 1) {
			state = new NormalVoteState();
		} else if (voteCount > 1 && voteCount < 5) {
			state = new RepeatVoteState();
		} else if (voteCount >= 5 && voteCount < 8) {
			state = new SpiteVoteState();
		} else if (voteCount >= 8) {
			state = new BlackVoteState();
		}

		state.vote(id, voteItem, this);
	}

	public Map<Integer, Integer> getVoteMap() {
		return voteMap;
	}

}
