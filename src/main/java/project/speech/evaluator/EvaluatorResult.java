package project.speech.evaluator;

public class EvaluatorResult {
	private int hits;
	private int substitutions;
	private int deletions;
	private int insertions;
	private int numberOfWords;
	private String timeSpan;

/**
 * Constructor used by model1
 * @param h number of hits/correct words
 * @param s number of substituted words
 * @param d number of deleted words
 * @param i number of inserted words
 * @param n number of words in reference text
 * @param t time taken for recognition
 */
	public EvaluatorResult(int h, int s, int d, int i, int n, String t) {
		hits = h;
		substitutions = s;
		deletions = d;
		insertions = i;
		numberOfWords = n;
		timeSpan = t;
	}

/**
 * Constructor used by model2
 * @param h number of hits/correct words
 * @param s number of substituted words
 * @param d number of deleted words
 * @param i number of inserted words
 * @param n number of words in reference text
 */
	public EvaluatorResult(int h, int s, int d, int i, int n) {
		hits = h;
		substitutions = s;
		deletions = d;
		insertions = i;
		numberOfWords = n;
	}

/**
* @return number of hits/correct word pairs
*/
	public int getHits() {
		return hits;
	}

/**
* @return number of substituted words
*/
	public int getSubstitutions() {
		return substitutions;
	}

/**
* @return number of substituted words
*/
	public int getDeletions() {
		return deletions;
	}
	
/**
* @return number of inserted words
*/
	public int getInsertions() {
		return insertions;
	}
	
/**
* @return number of words in reference text
*/
	public int getNumberOfWords(){
		return numberOfWords;
	}
	
/**
* @return time consumed by speech recognizer
*/
	public String getTime(){
		return timeSpan;
	}
}
