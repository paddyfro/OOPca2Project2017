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
public class BookAuthorComparator implements Comparator<Book>
{
    public int compare(Book a, Book b)
    {
        return a.getAuthor().toLowerCase().compareTo(b.getAuthor().toLowerCase());

    }
    
}
