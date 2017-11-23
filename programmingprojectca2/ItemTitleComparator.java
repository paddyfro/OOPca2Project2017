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
public class ItemTitleComparator implements Comparator<Items>
{
     @Override
     public int compare(Items a, Items b)
	{
		return a.getTitle().toLowerCase().compareTo(b.getTitle().toLowerCase());

		// String class has a compareTo() method that
		// returns -1, 0, or +1 as appropriate
	}
}
