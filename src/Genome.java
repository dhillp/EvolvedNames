import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
/**
 * Creates a Genome (implements Wagner-Fischer algorithm).
 * 
 * @author Pamaldeep Dhillon
 * @version 1.0
 */
public class Genome {
	
	public String target = "PAMALDEEP SINGH DHILLON";
	
	private double myMutationRate;
	
	private String myGenome;
	
	private List<Character> myChars = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			                                        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			                                        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			                                        'Y', 'Z', ' ', '-', '\'');
	
	private ArrayList<Character> myGeneChars;
	
	public Genome(double theMutationRate) {
		myMutationRate = theMutationRate;
		myGeneChars = new ArrayList<Character>();
		myGeneChars.add('A');
		myGenome = "A";
	}
	
	public Genome(Genome theGene) {
		myMutationRate = theGene.myMutationRate;
		myGenome = theGene.myGenome;
		myGeneChars = new ArrayList<Character>();
		for (char c : theGene.myGeneChars) {
			myGeneChars.add(c);
		}
	}
	
	public void mutate() {
		Random r = new Random();
		if (Math.random() < myMutationRate) {
			myGeneChars.add(r.nextInt(myGeneChars.size() + 1),
					                  myChars.get(r.nextInt(myChars.size())));
		}
		if (myGeneChars.size() >= 2 && Math.random() < myMutationRate) {
			myGeneChars.remove(r.nextInt(myGeneChars.size()));
		}
		for (int i = 0; i < myGeneChars.size(); i++) {
			if (Math.random() < myMutationRate) {
				myGeneChars.set(i, myChars.get(r.nextInt(myChars.size())));
			}
		}
	}
	
	public void crossover(Genome theOther) {
		ArrayList<Character> tempList = new ArrayList<Character>();
		int thisSize = myGeneChars.size();
		int theOtherSize = theOther.myGeneChars.size();
		int maxSize = Math.max(thisSize, theOtherSize);
		for (int i = 0; i < maxSize; i++) {
			double r = Math.random();
			if (r < 0.50 && thisSize > i) {
				tempList.add(i, myGeneChars.get(i));
			} else if (r >= 0.50 && theOtherSize > i) {
				tempList.add(i, theOther.myGeneChars.get(i));
			} else {
				break;
			}
		}
		myGeneChars = tempList;
	}
	
	//Wagner-Fischer algorithm
	public int fitness() {
		int n = myGeneChars.size();
		int m = target.length();
		int[][] D = new int[n + 1][m + 1];
		for (int i = 0; i <= m; i++) {
			D[0][i] = i;
		}
		for (int j = 0; j <= n; j++) {
			D[j][0] = j;
		}
		for (int j = 1; j <= m; j++) {
			for (int i = 1; i <= n; i++) {
				if (myGeneChars.get(i - 1) == target.charAt(j - 1)) {
					D[i][j] = D[i - 1][j - 1];
				} else {
					D[i][j] = Math.min(Math.min(D[i - 1][j] + 1, D[i][j - 1] + 1),
							           D[i - 1][j - 1] + 1);
				}
			}
		}
		return D[n][m] + (Math.abs(n - m) + 1) / 2;
		
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder(myGeneChars.size());
	    for(Character ch: myGeneChars) {
	        builder.append(ch);
	    }
	    myGenome = builder.toString();
		return myGenome + " - Fitness: " + fitness();
	}
}
