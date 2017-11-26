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
public class Book extends Items
{

    private String isbn;
    private String author;
    private int pageCount;
    private double edition;

    public Book()
    {
    }

    public Book(String type, String title, String genre, String description, double price, int releaseYear, String isbn, String author, int pageCount, double edition)
    {
        super(type, title, genre, description, price, releaseYear);
        this.isbn = isbn;
        this.author = author;
        this.pageCount = pageCount;
        this.edition = edition;
    }

    // Copy Constructor - used to clone an object
    public Book(Book original)
    {
        super(original);
        this.isbn = original.isbn;  // ok, as Strings are immutable
        this.author = original.author;
        this.pageCount = original.pageCount;
        this.edition = original.edition;
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
        return getClass().getName() + super.toString() + " isbn=" + isbn + ", author=" + author + ", pageCount=" + pageCount + ", edition=" + edition + '}';
    }

}
