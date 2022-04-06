package multiThreading;

import java.util.ArrayList;

import geneticalgorithm.*;

public class GeneticThread implements Runnable {
	private int popSize;
	private int mutationRate;
	private int epochSize;
	private int threadNum;
	private ArrayList<Chromosome> population;
	private ArrayList<Chromosome> temp;
	
	public GeneticThread(int _popSize
						, int _mutationRate
						, int _epochSize
						, int _threadNum
						, ArrayList<Chromosome> _population
						, ArrayList<Chromosome> _temp
						) {
		popSize = _popSize;
		mutationRate = _mutationRate;
		epochSize = _epochSize;
		threadNum = _threadNum;
		population = _population;
		temp = _temp;
	}
	
	@Override
	public void run() {
		population = GeneticAlgorithm.updatePopulation(popSize, mutationRate, epochSize, population);
		
		for(int i = 0; i < popSize/threadNum; i++)
			temp.add(population.get(i));
	}
}
