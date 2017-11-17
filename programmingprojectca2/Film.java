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
public class Film extends Item
{
   private String director;
   private int rating;
   private int length;
   private String studio;
   private String writer;

    public Film()
    {
    }

    public Film(String director, int rating, int length, String studio, String writer)
    {
        this.director = director;
        this.rating = rating;
        this.length = length;
        this.studio = studio;
        this.writer = writer;
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

    public String getWriter()
    {
        return writer;
    }

    public void setWriter(String writer)
    {
        this.writer = writer;
    }

    @Override
    public String toString()
    {
        return getClass().getName() +  " director=" + director + ", rating=" + rating + ", length=" + length + ", studio=" + studio + ", writer=" + writer + '}';
    }
    
    
   
   
    
}
