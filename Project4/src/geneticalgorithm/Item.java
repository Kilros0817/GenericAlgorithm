
package geneticalgorithm;

public class Item {
    
    public String m_name = "";
    private double m_weight = 0.0;
    private int m_value = 0;
    private boolean m_included = true;
    
    // Constructor definition
    public Item(String i_name, double i_weight, int i_value){
        
        m_name = i_name;
        m_weight = i_weight;
        m_value = i_value;
        
        m_included = false;
    }
    
    // Constructor multiple definition
    public Item(Item other){
        
        m_name = other.m_name;
        m_weight = other.getWeight();
        m_value = other.getValue();
        if (other.m_included)
        	m_included = true;
        else 
        	m_included = false;
    }
    
    // Get the weight
    public double getWeight(){
        
        return m_weight;
    }
    
    // Get the value
    public int getValue(){
        
        return m_value;
    }
    
    // Check the included
    public boolean isIncluded(){
        
        return m_included;
    }
    // Set the included
    public void setIncluded(boolean _included){
        
        m_included = _included;
    }
    
    // Display the values
    public String toString(){
        
        String _szResult;
        _szResult = m_name + "(" + String.valueOf(m_weight) + " lbs, $" + String.valueOf( m_value) + ")";
        return _szResult;
    }     
}
