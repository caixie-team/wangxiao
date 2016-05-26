package com.atdld.os.sns.util;

import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.util.BytesRef;

/**
 * @author :
 * @ClassName com.supergenius.sns.util.lucene.SNSSimilarity
 * @description
 * @Create Date : 2014-5-19 下午12:42:09
 */
public class SNSSimilarity extends DefaultSimilarity {


    /**
     * Sole constructor: parameter-free
     */
    public SNSSimilarity() {
    }

    /**
     * Implemented as <code>overlap / maxOverlap</code>.
     */
    @Override
    public float coord(int overlap, int maxOverlap) {
        return overlap / (float) maxOverlap;
    }

    /**
     * Implemented as <code>1/sqrt(sumOfSquaredWeights)</code>.
     */
    @Override
    public float queryNorm(float sumOfSquaredWeights) {
        // return (float)(1.0 / Math.sqrt(sumOfSquaredWeights));
        return 1.0f;
    }


    /**
     * Implemented as
     * <code>state.getBoost()*lengthNorm(numTerms)</code>, where
     * <code>numTerms</code> is {@link FieldInvertState#getLength()} if {@link
     * #setDiscountOverlaps} is false, else it's {@link
     * FieldInvertState#getLength()} - {@link
     * FieldInvertState#getNumOverlap()}.
     *
     * @lucene.experimental
     */
    @Override
    public float lengthNorm(FieldInvertState state) {
        final int numTerms;
        if (discountOverlaps)
            numTerms = state.getLength() - state.getNumOverlap();
        else
            numTerms = state.getLength();
        return state.getBoost() * ((float) (1.0 / Math.sqrt(numTerms)));

    }

    /**
     * Implemented as <code>sqrt(freq)</code>.
     */
    @Override
    public float tf(float freq) {
        return (float) Math.sqrt(freq);
        //return 1.0f;
    }

    /**
     * Implemented as <code>1 / (distance + 1)</code>.
     */
    @Override
    public float sloppyFreq(int distance) {
        //return 1.0f / (distance + 1);
        return 1.0f;
    }

    /**
     * The default implementation returns <code>1</code>
     */
    @Override
    public float scorePayload(int doc, int start, int end, BytesRef payload) {
        return 1;
    }

    /**
     * Implemented as <code>log(numDocs/(docFreq+1)) + 1</code>.
     */
    @Override
    public float idf(long docFreq, long numDocs) {
        // return (float)(Math.log(numDocs/(double)(docFreq+1)) + 1.0);
        return 1.0f;
    }

    /**
     * True if overlap tokens (tokens with a position of increment of zero) are
     * discounted from the document's length.
     */
    protected boolean discountOverlaps = true;

    /**
     * Determines whether overlap tokens (Tokens with
     * 0 position increment) are ignored when computing
     * norm.  By default this is true, meaning overlap
     * tokens do not count when computing norms.
     *
     * @lucene.experimental
     * @see #computeNorm
     */
    public void setDiscountOverlaps(boolean v) {
        discountOverlaps = v;
    }

    /**
     * Returns true if overlap tokens are discounted from the document's length.
     *
     * @see #setDiscountOverlaps
     */
    public boolean getDiscountOverlaps() {
        return discountOverlaps;
    }

    @Override
    public String toString() {
        return "DefaultSimilarity";
    }

}
