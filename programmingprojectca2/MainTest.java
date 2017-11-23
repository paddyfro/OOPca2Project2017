/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA2Project;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author corma
 */
public class MainTest
{

    public static void main(String[] args)
    {
        ArrayList<Items> list = new ArrayList<>();

        list.add(new Items("", "The Matrix", "Sify", "Movie", 11.50, 2001));
        list.add(new Items("","The Dark Night", "Thriller", "Movie", 12.60, 2008));
        list.add(new Book("book", "everyone poops", "comedy", "a rip roaring tale", 23.56, 1986, "123456sd5", "mr poopington", 356, 2.5));
        list.add(new Film("film", "Dog Soldiers", "horror", "werewolves", 19.99, 1998, "neil bloomkamp", 18, 123, "universal"));
        list.add(new Film("film","Dog Soldiers", "horror", "werewolves", 19.99, 1998, "neil bloomkamp", 18, 123, "universal"));
        list.add(new Film("film","The mummy", "comedy", "mumy", 19.99, 1998, "neil bloomkamp", 15, 90, "universal"));
        list.add(new Film("film","Evil dead", "slapstick", "zombies", 19.99, 1998, "neil bloomkamp", 21, 150, "universal"));
        list.add(new Book("book","everyone poops", "comedy", "a rip roaring tale", 23.56, 1986, "123456sd5", "mr poopington", 356, 2.5));
        list.add(new Book("book","no one poops", "comedy", "a rip roaring tale", 23.56, 1986, "123456sd5", "Brandon Sanderson", 456, 2.5));
        list.add(new Book("book","only some poops", "comedy", "a rip roaring tale", 23.56, 1986, "123456sd5", "Stephen King", 26, 2.5));
        
        Items.DisplayItems(list);
        

        System.out.println("\n sorting by  item price\n");
        ItemPriceComparator mileageComparator = new ItemPriceComparator();
        Collections.sort(list, mileageComparator);
        Items.DisplayItems(list);

        System.out.println("\n sort by item title");
        ItemTitleComparator titleComparator = new ItemTitleComparator();
        Collections.sort(list, titleComparator);
        Items.DisplayItems(list);

        System.out.println("\nchecking book comparators\n");
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("book","everyone poops", "comedy", "a rip roaring tale", 23.56, 1986, "123456sd5", "mr poopington", 356, 2.5));
        books.add(new Book("book","no one poops", "comedy", "a rip roaring tale", 23.56, 1986, "123456sd5", "Brandon Sanderson", 456, 2.5));
        books.add(new Book("book","only some poops", "comedy", "a rip roaring tale", 23.56, 1986, "123456sd5", "Stephen King", 26, 2.5));
        System.out.println("\n Books: ");
        displayBooks(books);
        System.out.println("\n sort by author\n");
        BookAuthorComparator authorComparator = new BookAuthorComparator();
        Collections.sort(books, authorComparator);
        displayBooks(books);
        System.out.println("\n sort by page count");
        BookPageComparator pageComparator = new BookPageComparator();
        Collections.sort(books, pageComparator);
        displayBooks(books);

        System.out.println("/n checking Film comparators");
        ArrayList<Film> films = new ArrayList<>();
        films.add(new Film("film","Dog Soldiers", "horror", "werewolves", 19.99, 1998, "neil bloomkamp", 18, 123, "universal"));
        films.add(new Film("film","The mummy", "comedy", "mumy", 19.99, 1998, "neil bloomkamp", 15, 90, "universal"));
        films.add(new Film("film","Evil dead", "slapstick", "zombies", 19.99, 1998, "neil bloomkamp", 21, 150, "universal"));
        displayFilms(films);
        
        System.out.println("\n sort by film length");
        FilmLengthComparator lengthComparator = new FilmLengthComparator();
        Collections.sort(films, lengthComparator);
        displayFilms(films);
        
        System.out.println("\n sort by film rating");
        FilmRatingComparator ratingComparator = new FilmRatingComparator();
        Collections.sort(films, ratingComparator);
        displayFilms(films);
        
        
        
        System.out.println("\n *********************************\n");
        Items.DisplayItems(list);
        System.out.println("\n filter out books");
        ArrayList<Book> booksList = filterForBooks(list);
        displayBooks(booksList);
        
        System.out.println("\n filter out films");
        ArrayList<Film> filmsList = filterForFilms(list);
        displayFilms(filmsList);
        
        
        
//        LibraryApp L1 = new LibraryApp("Library", list, "Dundalk", 025151500000);
    }

    public static void displayBooks(ArrayList<Book> list)
    {
        for (Book books : list)
        {
            System.out.println(books);
        }
    }
    
    public static void displayFilms(ArrayList<Film> list)
    {
        for(Film films : list)
        {
            System.out.println(films);
        }
    }

    public static ArrayList<Book> filterForBooks(ArrayList<Items> list)
    {
        ArrayList<Book> books = new ArrayList<>();
        for (Items item : list)
        {
            //code to check for type pf books
            if(item.getType().toLowerCase().equals("book"))
            {
                Book newBook = (Book) item;
                books.add(newBook);
            }
        }
        return books;
    }
    
    public static ArrayList<Film> filterForFilms(ArrayList<Items> list)
    {
        ArrayList<Film> films = new ArrayList<>();
        for(Items item : list)
        {
            //check if type of ite matches "film"
            if(item.getType().toLowerCase().equals("film"))
            {
//                System.out.println("item type: " + item.getType());
//                System.out.println("item name: " + item.getTitle());
                //if it does convert it to a film object so it can be added to new film arraylist
                Film newFilm = (Film) item;
                films.add(newFilm);
            }
        }
        return films;
    }

}
