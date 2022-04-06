package multiThreading;

/*
 * part 3 of project 4
 * use ExecutorService to execute several GeneticThread and integrate their results.
 * 
 * */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import geneticalgorithm.*;
public class MultiThreading {

	public static final int POP_SIZE = 100;
	public static final int NUM_EPOCHS = 1000;
	public static final int NUM_THREADS = 1;
	public static final int Mutation_rate = 6;
	
	private static ArrayList<Item> m_datas;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		m_datas = GeneticAlgorithm.readData("items.txt");
		
		ArrayList<Chromosome> population = new ArrayList<Chromosome>();
		population = GeneticAlgorithm.initializePopulation(m_datas, POP_SIZE);
		
		ArrayList<Chromosome> result_population = new ArrayList<Chromosome>();
		
		ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
		int num = 0;
		while (num < NUM_THREADS) {
			executorService.execute(new GeneticThread(POP_SIZE
													, Mutation_rate
													, NUM_EPOCHS / NUM_THREADS
													, NUM_THREADS
													, population
													, result_population));
			num ++;
		}
		executorService.shutdown();
		try {
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.print(result_population.get(0).toString());
	}
}
