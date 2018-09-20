/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;
/*
 * Name: Patrick McDonnell
 * ID: D00006968
 * Course: Bachelor of Science (Honours) in Computing in DKIT
 * Subject:Object Orientated Programming
 * 25/04/2018
 */
import java.util.Objects;

/**
 *
 * @author patri
 */
public class Movie {
    private int id ;
    private String title;
//    ArrayList<String> genere = new ArrayList<>();
    private String genre;
    private String director;
    private String runtime;
    private String plot;
    private String location;
    private String poster = null;
    private String rating;
    private String format;
    private int year;
    private String starring;
//    ArrayList<String> starring = new ArrayList<>();
    private int copies;
    private String barcode;
    private String user_rating;

    public Movie() {
        this.title = "";
    }

    public Movie(String title, String genre, String director, String runtime, String plot, String location, String rating, String format, int year, String starring, int copies, String barcode, String user_rating) {
        
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot  = plot;
        this.location = location;
        this.rating = rating;
        this.format = format;
        this.year = year;
        this.starring = starring;
        this.copies = copies;
        this.barcode = barcode;
        this.user_rating = user_rating;
    }

    public Movie(int id, String title, String genre, String director, String runtime, String plot, String location, String rating, String format, int year, String starring, int copies, String barcode, String user_rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
        this.rating = rating;
        this.format = format;
        this.year = year;
        this.starring = starring;
        this.copies = copies;
        this.barcode = barcode;
        this.user_rating = user_rating;
    }
    
    

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(String user_rating) {
        this.user_rating = user_rating;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", genre=" + genre + ", director=" + director + ", runtime=" + runtime + ", plot=" + plot + ", location=" + location + ", poster=" + poster + ", rating=" + rating + ", format=" + format + ", year=" + year + ", starring=" + starring + ", copies=" + copies + ", barcode=" + barcode + ", user_rating=" + user_rating + '}';
    }

   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + Objects.hashCode(this.director);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        return true;
    }
    
    
}
