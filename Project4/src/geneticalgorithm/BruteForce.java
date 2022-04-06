package geneticalgorithm;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

/*
 * part 2 of project 4.
 * subsets(), subsetsHelper() functions are for getting whole subsets of items.
 * */
public class BruteForce {

	public static ArrayList<Item> getOptimalSet(ArrayList<Item> items){
		
		ArrayList<ArrayList<Item>> list = subsets(items); //list of all subsets of items.
		ArrayList<Chromosome> chList = new ArrayList<>(); //a subset list of items
		for(ArrayList<Item> array : list) {
			chList.add(new Chromosome(array, false));
		}
		
		Collections.sort(chList);
		
		return chList.get(0);
	}
	
	public static ArrayList<ArrayList<Item>> subsets(ArrayList<Item> items) {
		ArrayList<ArrayList<Item>> list = new ArrayList<>();
        subsetsHelper(list, new ArrayList<>(), items, 0);
        return list;
    }
 
    private static void subsetsHelper(ArrayList<ArrayList<Item>> list, ArrayList<Item> resultList, ArrayList<Item> items, int start){
        list.add(new ArrayList<>(resultList));
        for(int i = start; i < items.size(); i++){
           // add element
        	Item w_itemItem = new Item(items.get(i));
        	w_itemItem.setIncluded(true);
            resultList.add(w_itemItem);
           // Explore
            subsetsHelper(list, resultList, items, i + 1);
           // remove
            resultList.remove(resultList.size() - 1);
        }
    }
	
    
	private static void checkSize(ArrayList<Item> items) {
		if (items.size() > 10) {
			throw new IllegalArgumentException("ArrayList contains more than 10 items!");
		}
	}
	
	private static ArrayList<Item> m_datas;
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		m_datas = GeneticAlgorithm.readData("items.txt");
		checkSize(m_datas);	
		
		Chromosome chromosome = (Chromosome) getOptimalSet(m_datas);
		System.out.print(chromosome.toString());
	}
}
