/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingprojectca2;

import java.util.ArrayList;

/**
 *
 * @author corma
 */
public class main
{
    public static void main(String[] args)
    {
        ArrayList<Items> list = new ArrayList<>();
         
        list.add(new Items("The Matrix", "Sify", "Movie", 11.50, 2001));
        list.add(new Items("The Dark Night" , "Thriller" , "Movie" , 12.60 , 2008));
         
        Items.DisplayItems(list);
         
         
         
        LibraryApp L1= new LibraryApp("Library", list, "Dundalk", 025151500000);
    }
    
}
