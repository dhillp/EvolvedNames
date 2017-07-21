import java.util.ArrayList;
import java.util.Random;

/**
 * Creates a population of Genomes.
 * 
 * @author Pamaldeep Dhillon
 * @version 1.0
 */
public class Population {
	
	public String target = "PAMALDEEP SINGH DHILLON";
	
	public Genome mostFit;
	
	private ArrayList<Genome> myPop;
	
	public Population(int theNumGenomes, double theMutationRate) {
		myPop = new ArrayList<Genome>();
		for (int i = 0; i < theNumGenomes; i++) {
			myPop.add(new Genome(theMutationRate));
		}
		mostFit = myPop.get(0);
	}
	
	public void day() {
		int initialSize = myPop.size();
		//Insertion Sort
		for (int i = 1; i < initialSize; i++) {
			Genome temp = myPop.get(i);
			int key = temp.fitness();
            for (int j = i - 1; j >= 0; j--) {
                if (key < myPop.get(j).fitness()) {
                    myPop.set(j + 1, myPop.get(j));
                    if (j == 0) {
                        myPop.set(0, temp);
                    }
                } else {
                    myPop.set(j + 1,temp);
                    break;
                }
            }
        }
		for (int i = initialSize - 1; i >= initialSize / 2; i--) {
			myPop.remove(i);
		}
		Random r = new Random();
		while (myPop.size() != initialSize) {
			if (Math.random() < 0.5) {
				Genome newMGene = new Genome(myPop.get(r.nextInt(myPop.size())));
				newMGene.mutate();
				myPop.add(newMGene);
			} else {
				Genome newCGene = new Genome(myPop.get(r.nextInt(myPop.size())));
				newCGene.crossover(myPop.get(r.nextInt(myPop.size())));
				newCGene.mutate();
				myPop.add(newCGene);
			}
		}
		for (Genome g : myPop) {
			if (g.fitness() < mostFit.fitness()) {
				mostFit = g;
			}
		}
	}
}
