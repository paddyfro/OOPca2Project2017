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
public class Book extends Item
{
    private String isbn;
    private String author;
    private int pageCount;
    private double edition;

    public Book()
    {
    }

    public Book(String isbn, String author, int pageCount, double edition)
    {
        this.isbn = isbn;
        this.author = author;
        this.pageCount = pageCount;
        this.edition = edition;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public double getEdition()
    {
        return edition;
    }

    public void setEdition(double edition)
    {
        this.edition = edition;
    }

    @Override
    public String toString()
    {
        return getClass().getName() + " isbn=" + isbn + ", author=" + author + ", pageCount=" + pageCount + ", edition=" + edition + '}';
    }
    
    
    
    
    
}
