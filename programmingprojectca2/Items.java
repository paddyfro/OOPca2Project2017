/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingprojectca2;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author cormac
 */
public class Items
{
    private static int idCount = 1; 
    private int id;
    private String title;
    private String genre;
    private String description;
    private double price;
    private int releaseYear;
    

    public Items()
    {
        
        this.title = "";
        this.genre = "";
        this.description = "";
        this.price = 0.0;
        this.releaseYear = 0;
        
    }
    
    public Items(String title, String genre, String description, double price, int releaseYear)
    {
        this.id = idCount;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.price = price;
        this.releaseYear = releaseYear;
        idCount++;
    }
    
    public Items(Items org){
        this.id = org.id;
        this.title = org.title;
        this.genre = org.genre;
        this.description = org.description;
        this.price = org.price;
        this.releaseYear = org.releaseYear;
    }

    public static int getIdCount()
    {
        return idCount;
    }

    public static void setIdCount(int idCount)
    {
        Items.idCount = idCount;
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getReleaseYear()
    {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear)
    {
        this.releaseYear = releaseYear;
    }

    
    
    public static void DisplayItems(ArrayList<Items> list)
    {
        list.forEach((i) ->
        {
            System.out.println(i);
        });
    }

    @Override
    public String toString()
    {
        return "Items{" + "title=" + title + ", genre=" + genre + ", description=" + description + ", price=" + price + ", releaseYear=" + releaseYear + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + Objects.hashCode(this.genre);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 59 * hash + this.releaseYear;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Items other = (Items) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price))
        {
            return false;
        }
        if (this.releaseYear != other.releaseYear)
        {
            return false;
        }
        if (!Objects.equals(this.title, other.title))
        {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre))
        {
            return false;
        }
        if (!Objects.equals(this.description, other.description))
        {
            return false;
        }
        return true;
    }
    
    public Items clone()
    {
//  make a DEEP COPY of the existing object (i.e. a separate/unique object in memory)
        Items clone = new Items(this.title, this.genre, this.description, this.price, this.releaseYear);

//  the cloned object 'clone' will be allocated a new id from the 
//  static idCount sequence but we must set the id to 
//  the value of the id from the object being cloned.
        clone.id = this.getId();

        return clone;
    }
    
    
    
    

    
    
    
    
    
    
}
