/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA2Project;

import java.util.Comparator;

/**
 *
 * @author patri
 */
public class ItemPriceComparator implements Comparator<Items>
{
    
    
    @Override
    public int compare(Items a, Items b)
	{
		if ( a.getPrice() < b.getPrice() ) {
			return -1; 
		} else if ( a.getPrice() == b.getPrice()) { 
			return 0; 
		} else { 
			return 1; 
		}
		// String class has a compareTo() method that
		// returns -1, 0, or +1 as appropriate
	}
    

    
}
