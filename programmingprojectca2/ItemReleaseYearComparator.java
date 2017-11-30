/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA2Project;

import java.util.Comparator;

/**
 *
 * @author Patrick
 */
public class ItemReleaseYearComparator implements Comparator<Items>
{
        @Override
    public int compare(Items a, Items b)
	{
		if ( a.getReleaseYear()< b.getReleaseYear()) {
			return -1; 
		} else if ( a.getReleaseYear()== b.getReleaseYear()) { 
			return 0; 
		} else { 
			return 1; 
		}
		// String class has a compareTo() method that
		// returns -1, 0, or +1 as appropriate
	}
    
}
