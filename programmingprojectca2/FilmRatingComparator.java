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
public class FilmRatingComparator implements Comparator<Film>
{
    public int compare(Film a, Film b)
    {
        if (a.getRating() < b.getRating())
        {
            return -1;
        } else if (a.getRating() == b.getRating())
        {
            return 0;
        } else
        {
            return 1;
        }
    }
    
    
}
