/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA2Project;

/**
 *
 * @author patri
 */
public class Film extends Items
{
   private String director;
   private int rating;
   private int length;
   private String studio;

    public Film()
    {
    }

    public Film( String type, String title, String genre, String description, double price, int releaseYear, String director, int rating, int length, String studio)
    {
        super(type,title, genre, description, price, releaseYear);
        this.director = director;
        this.rating = rating;
        this.length = length;
        this.studio = studio;
    }
    
        // Copy Constructor - used to clone an object
    public Film(Film original)
    {
        super(original);
        this.director = original.director;  // ok, as Strings are immutable
        this.rating = original.rating;
        this.length = original.length;
        this.studio = original.studio;
    }

   

    public String getDirector()
    {
        return director;
    }

    public void setDirector(String director)
    {
        this.director = director;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public String getStudio()
    {
        return studio;
    }

    public void setStudio(String studio)
    {
        this.studio = studio;
    }

   

    @Override
    public String toString()
    {
        return getClass().getName() + super.toString() +  " director=" + director + ", rating=" + rating + ", length=" + length + ", studio=" + studio + '}';
    }
    
    
   
   
    
}
