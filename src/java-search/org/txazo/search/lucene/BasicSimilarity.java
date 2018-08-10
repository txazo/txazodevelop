package org.txazo.search.lucene;

import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.util.BytesRef;

/**
 * Similarity
 * 
 * @author txazo
 * 
 */
public class BasicSimilarity extends DefaultSimilarity {

	/**
	 * @param overlap 文档中命中个数
	 * @param maxOverlap 搜索条件个数
	 */
	@Override
	public float coord(int overlap, int maxOverlap) {
		return overlap / maxOverlap;
	}

	/**
	 * @param sumOfSquaredWeights 不影响排序
	 */
	@Override
	public float queryNorm(float sumOfSquaredWeights) {
		return (float) (1.0D / Math.sqrt(sumOfSquaredWeights));
	}

	/**
	 * @param freq 频次
	 */
	@Override
	public float tf(float freq) {
		return (float) Math.sqrt(freq);
	}

	/**
	 * @param docFreq
	 * @param numDocs
	 */
	@Override
	public float idf(long docFreq, long numDocs) {
		return (float) (Math.log(numDocs / (docFreq + 1L)) + 1.0D);
	}

	/**
	 * @param state
	 */
	@Override
	public float lengthNorm(FieldInvertState state) {
		int numTerms;
		if (discountOverlaps)
			numTerms = state.getLength() - state.getNumOverlap();
		else
			numTerms = state.getLength();
		return state.getBoost() * (float) (1.0D / Math.sqrt(numTerms));
	}

	/**
	 * @param doc
	 * @param start
	 * @param end
	 * @param payload
	 */
	@Override
	public float scorePayload(int doc, int start, int end, BytesRef payload) {
		return 1;
	}

}
