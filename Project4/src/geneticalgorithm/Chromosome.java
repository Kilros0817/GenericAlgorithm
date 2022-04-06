

package geneticalgorithm;

import java.util.Random;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {
    
	public static long dummy = 0;
    protected static final Random random = new Random();
    ArrayList<Item> m_items;
    
    public Chromosome(){
        
    }
    //randomInclude -- true-> item included randomly
    //				   false-> item included according to parent.
    public Chromosome(ArrayList<Item> items, boolean randomInclude){
        
        int _i, _numItems;
        _numItems = items.size();
    
        m_items = new ArrayList<Item>();
        
        for (_i = 0; _i < _numItems; _i++){
            
        	int rnd = random.nextInt(10);
            Item _item = new Item( items.get(_i));
            
	        if(randomInclude) {
	            if (rnd > 5){
	                
	                _item.setIncluded( true );
	            } else {
	                
	                _item.setIncluded( false );
	            }
	        }
	        m_items.add( _item);
        }
    }
    
    // Crossover
// Those two parents will create a child whose DNA is related to the parents’.
// It works like this:	for each of the	seven genes in the chromosome, we will randomly	pick a number
// between 1 and 10 and	use it to choose which parents’ value the child	will get.
// If the random number	is 1 through 5, we will	use Parent 1’s included	value for the child;
// if it is 6 through 10, we’ll	use Parent 2’s.
    public Chromosome crossover(Chromosome other){
       
        ArrayList<Item> _items;
        _items = new ArrayList< Item>();
        
        int _random, _i, _numItems;
        
        _numItems = m_items.size();
        for (_i = 0; _i < _numItems; _i++){
        
            _random = random.nextInt( 10);
            if (_random <= 5){
                
                Item _item = new Item( m_items.get( _i));
                _items.add( _item);
            } else {
                
                Item _item = new Item( other.m_items.get( _i));
                _items.add( _item);
            }
        }
        
        Chromosome _child = new Chromosome( _items, false);
        return _child;
    }
    
    // Mutate
    // Mutation is even simpler:
    // we choose a single individual from our population and again
    // generate a random number between 1 and 10 for each nucleotide.
    // Mutation generally happens more rarely than reproduction,
    // so if the random number is 1,we will lip that gene	
    // in the individual;
    // otherwise, we will leave it the same.
    public void mutate(){
        
        int _i, _numItems, _random;
              
        _numItems = m_items.size();
        for (_i = 0; _i < _numItems; _i++){
            
            Item _item = new Item( m_items.get( _i));
            
            _random = random.nextInt( 10);
            if (_random == 1){

                // Flip that
                if (_item.isIncluded()){
                    
                    _item.setIncluded( false);
                } else {
                    
                    _item.setIncluded( false);
                }
            m_items.set( _i, _item);
            }
        } // end for _i
    }
    
    // Get the fitness
    //Returns the fitness of this chromosome.
    // If the sum of all of the included items’ weights
    // are greater than 10, the fitness	is zero.
    // Otherwise, the fitness is equal to the sum of all of the	
    // included	items’ values.
    public int getFitness(){
    	dummy = 0;
    	for (int i=0; i<this.size()*1000; i++) {
    		dummy += i;
    	}
    	
        double _weight, _sumWeight;
        int _i, _numItems, _fitness, _value, _sumValue;
        
        _numItems = m_items.size();
        _sumWeight = 0;
        _sumValue = 0;
        for (_i = 0; _i < _numItems; _i++){
            
            Item _item = new Item( m_items.get( _i));
            if (_item.isIncluded()){
                
                _weight = _item.getWeight();
                _sumWeight += _weight;
                
                _value = _item.getValue();
                _sumValue += _value;
            }
        }
          
        // If sum of all of the included items's weights
        // are greater than 10, the fitness is zero. Otherwise, fitness is equal
        // to the sum of all of the cluded items' values
        if (_sumWeight > 10){
            
            _fitness = 0;
        } else {
            
            _fitness = _sumValue;
        }
        
        return _fitness;
    }
    
    // Compare 
    @Override
    public int compareTo(Chromosome other) {
    
        int _iResult;
        if (this.getFitness() > other.getFitness()){
            
            _iResult = -1;
        } else if (this.getFitness() < other.getFitness()){
            
            _iResult = 1;
        } else {
            
            _iResult = 0;
        }
        return _iResult;
    }
    
    // Display the items
    @Override
    public String toString(){
        
        String _szResult;
        
        int _i, _numItems;
        _numItems = m_items.size();
        
        _szResult = "";
        for (_i = 0; _i < _numItems; _i++){
            
            Item _item = new Item(m_items.get( _i));
            if (_item.isIncluded())
                _szResult += _item.toString() + '\n';
        }
        
        _szResult = String.format("=====Fitness=====\n%d\n===Itmes===\n%s", this.getFitness(), _szResult); 
        return _szResult;
    }    
}
