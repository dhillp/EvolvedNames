/**
 * Driver program for EvolvedNames which also tests the Population and Genome
 * classes (tests are commented out).
 * 
 * @author Pamaldeep Dhillon
 * @version 1.0
 */
public class Main {
	
	public static void main(String[] theArgs) {
		long startTime = System.currentTimeMillis();
		Population p = new Population(100, .05);
		int i = 1;
		while (p.mostFit.fitness() != 0) {
			p.day();
			System.out.println("Day " + i + ": " + p.mostFit);
			i++;
		}
		long stopTime = System.currentTimeMillis();
		System.out.println("Generations: " + (i - 1) + "\nRunning Time: " 
							+ (stopTime - startTime) + " milliseconds");
//		testGenome();
//		testPopulation();
	}
	
	@SuppressWarnings("unused")
	private static void testGenome() {
		Genome test1 = new Genome(0.05);
		Genome test2 = new Genome(0.05);
		System.out.println("Mutate:");
		for (int i = 0; i < 20; i++) {
			System.out.println(test1.toString());
			test1.mutate();
		}
		System.out.println("Crossover:");
		System.out.println("First Genome: " + test1.toString());
		for (int i = 0; i < 100; i++) {
			test2.mutate();
		}
		System.out.println("2nd Genome: " + test2.toString());
		for (int i = 0; i < 5; i++) {
			test1.crossover(test2);
			System.out.println(test1.toString());
		}
	}
	
	@SuppressWarnings("unused")
	private static void testPopulation() {
		Population testP = new Population(100, 0.05);
		for (int i = 0; i < 20; i++) {
			testP.day();
			System.out.println(testP.mostFit);
		}
	}
}
