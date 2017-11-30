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
public class FilmLengthComparator implements Comparator<Film>
{
    @Override
    public int compare(Film a, Film b)
    {
//        return a.getLength() - b.getLength();
        if (a.getLength()< b.getLength())
        {
            return -1;
        } else if (a.getLength() == b.getLength())
        {
            return 0;
        } else
        {
            return 1;
        }
    }
    
}
