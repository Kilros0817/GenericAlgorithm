

package geneticalgorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Random;

public class GeneticAlgorithm {

    private static ArrayList<Item> m_datas;
    protected static final Random random = new Random();
    
    // Read the data from the file
    public static ArrayList<Item> readData(String i_fileName)
                    throws FileNotFoundException{
    
        Integer _iValue;
        Double _weight, _dblData;
        ArrayList<Item> _list;
        _list = new ArrayList< Item>();

        try (BufferedReader _buffer = new BufferedReader(new FileReader(i_fileName)))
        {

            String _szLine;
            while ((_szLine = _buffer.readLine()) != null) {
                
                String[] _szValues = _szLine.split(",");
                if (_szValues.length > 2){
                              
                    _weight = Double.valueOf( _szValues[ 1]);
                    _dblData = Double.valueOf( _szValues[ 2]);
                    _iValue = _dblData.intValue();
                    Item _item = new Item(_szValues[ 0], _weight, _iValue);
                    _list.add( _item);
                }
            }

        } catch (IOException e) {
            
            e.printStackTrace();
        } 
        
        return _list;
    }
    
    // Initialize population
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> i_items, int populationSize){
        
        ArrayList<Chromosome> _list;
        _list = new ArrayList< Chromosome>();
        
        for (int _i = 0; _i < populationSize; _i++){
            
            Chromosome _chromosome = new Chromosome( i_items, true);
            _list.add( _chromosome);
        }
        
        return _list;
    }
    
    
    public static ArrayList<Chromosome> updatePopulation(int popSize
    													, int mutationRate
    													, int epochSize
    													, ArrayList<Chromosome> population){
    	ArrayList< Chromosome> _population_new = new ArrayList< Chromosome>();
        // 7. Repeat steps 2 through 6 twenty times
        for (int _loop = 0; _loop < epochSize; _loop++){
            //2. Add each of the individuals in the current population to the next generation
        	_population_new.clear();
        	
            for (int _i = 0; _i < popSize; _i++)
                _population_new.add( population.get( _i));

            
            //3. Randomly pair off the individuals and perform crossover to create a child and add it to
            // the next generation as well. produce 100 children
            Chromosome _chromosome1;
            Chromosome _chromosome2;
            Chromosome _child;
            
            int _randomIndex, _i;
            while(population.size() > 2) {
            	_randomIndex = random.nextInt(population.size());
                 _chromosome1 = population.get( _randomIndex);
                 population.remove(_randomIndex);
                _randomIndex = random.nextInt(population.size() - 1);
                _chromosome2 = population.get( _randomIndex);
                population.remove(_randomIndex);
                _child = _chromosome1.crossover(_chromosome2);
                _population_new.add( _child);
            }
            _child = population.get(0).crossover(population.get(1));
            _population_new.add(_child);
            // 4. Randomly choose ten percent of the individuals in the	next generation	and expose them	to mutation
            int _count = _population_new.size() / mutationRate;
            for (_i = 0; _i < _count; _i++){
                
                _randomIndex = random.nextInt(_population_new.size());
                Chromosome _chromosome = _population_new.get( _randomIndex);
                _chromosome.mutate();
                _population_new.set( _randomIndex, _chromosome);
            }
            
            //5. Sort the individuals in the next generation according to their	fitness
        	Collections.sort(_population_new);
            	
            //6.Clear out the current population and add the top ten of	the next generation back into the	
            // population            
            for (_i = 0; _i < popSize; _i++)
                population.add( _population_new.get( _i));
            
        }
    	return population;
    }

    public static void main(String[] args) throws FileNotFoundException{
        
        // Read the datas from file
        m_datas = readData("items.txt");
    	ArrayList< Chromosome> _population = new ArrayList<Chromosome>();
    	
        int _numPopulations = 100; //population size
        int _mutationRate = 6;	//mutation rate
        int _numEpochs = 1000;	//number of epochs
        //1. Create a set of ten random	individuals to serve as	the initial population	
        _population = initializePopulation( m_datas, _numPopulations);
        
        _population = updatePopulation(_numPopulations, _mutationRate, _numEpochs, _population);
        
        // 8. Sort the population and display the fittest individual to	the console
        System.out.print(_population.get(0).toString());
    }
    
}
