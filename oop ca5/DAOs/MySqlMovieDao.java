/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;
/*
 * Name: Patrick McDonnell
 * ID: D00006968
 * Course: Bachelor of Science (Honours) in Computing in DKIT
 * Subject:Object Orientated Programming
 * 25/04/2018
 */
import DTOs.Movie;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlMovieDao extends Daos.MySqlDao implements MovieDaoInterface {

    //initalise some global variables to do with connection to db and results sets
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * gets all the movies in the db and ads them to an arralits ofmovie objects
     *
     * @return arraylist fo mmoie objects
     * @throws DaoException
     */
    @Override
    public List<Movie> findAllMovies() throws DaoException {
        //initalise an arraylist fo movie objects
        List<Movie> movies = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();
            //query to get all data from movies table
            String query = "SELECT * FROM movies";
            //prepare the statement above
            ps = con.prepareStatement(query);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            //while there is more data in teh resuts set
            while (rs.next()) {
                //add in each field into a variable of appropriate type, using heading in movies db
                int id = rs.getInt("id");
                String title = rs.getString("title");
//                String[] genere = rs.getArray("genere");
                String genre = rs.getString("genre");
                String director = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("plot");
                String location = rs.getString("location");
                String poster = rs.getString("poster");
                String rating = rs.getString("rating");
                String format = rs.getString("format");
                int year = rs.getInt("year");
//                ArrayList<String> starring = rs.getArray("starring");
                String starring = rs.getString("starring");
                int copies = rs.getInt("copies");
                String barcode = rs.getString("barcode");
                String user_rating = rs.getString("user_rating");
                //create movie object using data collected form above
                Movie m = new Movie(id, title, genre, director, runtime, plot, location, rating, format, year, starring, copies, barcode, user_rating);
                //add movie to arralist of movie objects
                movies.add(m);
            }

        } catch (SQLException e) {
            throw new DaoException("findAllUsers() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllMovies" + e.getMessage());
            }
        }
        //return arraylist fo movie objects
        return movies;
    }

    /**
     * find a specific movie by title
     *
     * @param title title of movie to search for, moust be correct else wont
     * find anything
     * @return moivie object, either of the movie found or a blank object
     * showing that nothing was found
     * @throws DaoException
     */
    @Override
    public Movie findMovieByTitle(String title) throws DaoException {
        //create a movie bject
        Movie m = null;

        try {
            con = this.getConnection();
            //query to cehck db for a specefic movie
            String query = "SELECT  * FROM movies WHERE title = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, title);

            rs = ps.executeQuery();
            //if there is annything returned from the db
            if (rs.next()) {
                //get data from the rs
                int id = rs.getInt("id");
                String title1 = rs.getString("title");
//                String[] genere = rs.getArray("genere");
                String genre = rs.getString("genre");
//                System.out.println("genre: " + genre);
                String director = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("plot");
                String location = rs.getString("location");
                String poster = rs.getString("poster");
                String rating = rs.getString("rating");
                String format = rs.getString("format");
                int year = rs.getInt("year");
                String starring = rs.getString("starring");
//                ArrayList<String> starring = rs.getArray("starring");
                int copies = rs.getInt("copies");
                String barcode = rs.getString("barcode");
                String user_rating = rs.getString("user_rating");
                //create new movie object and reference it in m
                m = new Movie(id, title, genre, director, runtime, plot, location, rating, format, year, starring, copies, barcode, user_rating);
//                m = new Movie(id, title1, director, runtime, location, rating, format, year, copies, barcode, user_rating);

            } else {
                //if nothing found blank movie object created
                m = new Movie();
            }
        } catch (SQLException e) {
            throw new DaoException("findMovieByTitle " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findMovieByTitle" + e.getMessage());
            }
        }
        //return movie object, either found movie, or blank movie object
        return m;
    }

    /**
     * finds a list of movies that kind fo match a title
     *
     * @param title string that will be checked against whilst searching the db
     * @return arraylist<movie> of movies found kind of matching the title
     * @throws DaoException
     */
    @Override
    public List<Movie> findAllMovieByTitle(String title) throws DaoException {
        //initalise movie and arraylist variables
        Movie m = null;
        ArrayList<Movie> moviesMatched = new ArrayList<>();

        try {
            con = this.getConnection();
            //query to check for movies that kind of match the title
            String query = "SELECT  * FROM movies WHERE title like ?";
//            System.out.println(query);
            ps = con.prepareStatement(query);
//            title = title.replace("%", "!%");
//append the qualifier  wildcard to teh query
            ps.setString(1, title + "%");
//            System.out.println("query compiled : " + query);
            rs = ps.executeQuery();
            //while there are more entrys in resuts set
            while (rs.next()) {
                //take in data from rs and using db headers
//                System.out.println("ex01");
                int id = rs.getInt("id");
                String title1 = rs.getString("title");
//                String[] genere = rs.getArray("genere");
                String genre = rs.getString("genre");
//                System.out.println("genre: " + genre);
                String director = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("plot");
                String location = rs.getString("location");
                String poster = rs.getString("poster");
                String rating = rs.getString("rating");
                String format = rs.getString("format");
                int year = rs.getInt("year");
                String starring = rs.getString("starring");
//                ArrayList<String> starring = rs.getArray("starring");
                int copies = rs.getInt("copies");
                String barcode = rs.getString("barcode");
                String user_rating = rs.getString("user_rating");
                //create new movie object using data from above
                m = new Movie(id, title1, genre, director, runtime, plot, location, rating, format, year, starring, copies, barcode, user_rating);
//                m = new Movie(id, title1, director, runtime, location, rating, format, year, copies, barcode, user_rating);
                //add movie to the arraylist of movie objects
                moviesMatched.add(m);
            }
        } catch (SQLException e) {
            throw new DaoException("findMovieByTitle " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findMovieByTitle" + e.getMessage());
            }
        }
        //return arraylist of movie objects
        return moviesMatched;
    }

    /**
     * find a arraylist fo moveis thats based on the director name
     *
     * @param directorName string of director name to be checkd fro teh movies
     * @return arraylist<movies> that match the director from above
     * @throws DaoException
     */
    @Override
    public List<Movie> findMovieByDirector(String directorName) throws DaoException {

        List<Movie> movies = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();
            //query to geta  list of movies based on a directors name
            String query = "SELECT * FROM movies where director like ? ORDER BY title ASC";
            ps = con.prepareStatement(query);
            ps.setString(1, directorName + "%");

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
//                String[] genere = rs.getArray("genere");
                String genre = rs.getString("genre");
                String director = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("plot");
                String location = rs.getString("location");
                String poster = rs.getString("poster");
                String rating = rs.getString("rating");
                String format = rs.getString("format");
                int year = rs.getInt("year");
//                ArrayList<String> starring = rs.getArray("starring");
                String starring = rs.getString("starring");
                int copies = rs.getInt("copies");
                String barcode = rs.getString("barcode");
                String user_rating = rs.getString("user_rating");
                Movie m = new Movie(id, title, genre, director, runtime, plot, location, rating, format, year, starring, copies, barcode, user_rating);
                movies.add(m);
            }

        } catch (SQLException e) {
            throw new DaoException("findMoviesByDirector() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findMoviesByDirector()" + e.getMessage());
            }
        }

        return movies;
    }

    /**
     * add a movie to teh db
     *
     * @param m movie object to be added
     * @return true or fals eif operation was successful
     * @throws DaoException
     */
    @Override
    public boolean addMovie(Movie m) throws DaoException {
        try {
            con = this.getConnection();

            String query = "INSERT INTO movies VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getGenre());
            ps.setString(3, m.getDirector());
            ps.setString(4, m.getRuntime());
            ps.setString(5, m.getPlot());
            ps.setString(6, m.getLocation());
            ps.setString(7, m.getPoster());
            ps.setString(8, m.getRating());
            ps.setString(9, m.getFormat());
            ps.setInt(10, m.getYear());
            ps.setString(11, m.getStarring());
            ps.setInt(12, m.getCopies());
            ps.setString(13, m.getBarcode());
            ps.setString(14, m.getUser_rating());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("addMovie()" + e.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("addMovie()" + e.getMessage());
            }
        }
        return true;
    }

    /**
     * delete a movie from the db based on teh movie id
     *
     * @param id int fo movie id to be deleted from db
     * @return true or false if operation was succesful
     * @throws DaoException
     */
    @Override
    public boolean deleteMovie(int id) throws DaoException {

        try {
            con = this.getConnection();
            String query = "DELETE FROM movies WHERE id = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("addMovie()" + e.getMessage());
        }
        return true;
    }

    /**
     * add a movie to the watched list, wither adds a new entry or increments a
     * pervious entrys number count of times watched
     *
     * @param userID - int of userod thats logged into system
     * @param movieID - int of movie id that has been watched
     * @return true or fals ef operation has been successful
     * @throws DaoException
     */
    @Override
    public boolean addToWatchList(int userID, int movieID) throws DaoException {

        int numTimesWatched = checkIfOnWatchList(movieID);

        if (numTimesWatched == 0) {
//INSERT INTO `watchedmovies` (`userId`, `MovieId`, `Watched`, `numTimes`) VALUES ('1', '1052', '1', '1');
            try {
                con = this.getConnection();
                String query = "INSERT INTO watchedmovies VALUES (?,?,?,?)";
                ps = con.prepareStatement(query);

                ps.setInt(1, userID);
                ps.setInt(2, movieID);
                ps.setInt(3, 1);
                ps.setInt(4, 1);

                ps.executeUpdate();

            } catch (SQLException e) {
                throw new DaoException("addToWatchList()" + e.getMessage());
            }

        } else {
            //update entry
            //UPDATE `watchedmovies`SET numTimes =3 WHERE `MovieId`= 1052 and `userId` = 1
            try {
                con = this.getConnection();
                String query = "UPDATE watchedmovies SET numTimes = ? WHERE MovieId = ? and userId = ?";
                ps = con.prepareStatement(query);

                ps.setInt(1, numTimesWatched + 1);
                ps.setInt(2, movieID);
                ps.setInt(3, 1);
                ps.executeUpdate();

            } catch (SQLException e) {
                throw new DaoException("addToWatchList()" + e.getMessage());
            }
        }
        return true;
    }

    /**
     * checeks watched list to see if a movie is on the watched list or not
     *
     * @param movieId int of movie to be checked
     * @return if found returns number of times movie has been watched, if not
     * found returns zero
     * @throws DaoException
     */
    @Override
    public int checkIfOnWatchList(int movieId) throws DaoException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //SELECT * FROM `watchedmovies` WHERE `MovieId` = 1052

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM watchedmovies where MovieId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, movieId);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            if (rs.next()) {
                int numTimes = rs.getInt("numTimes");
                return numTimes;
            } else {
                return 0;
            }

        } catch (SQLException e) {
            throw new DaoException("checkIfOnWatchList() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("checkIfOnWatchList()" + e.getMessage());
            }
        }

    }

    /**
     * gets the wtchedmovies data from teh db
     *
     * @return arralist<novie> of movies on watched list
     * @throws DaoException
     */
    @Override
    public List<Movie> getWatchList() throws DaoException {
        ArrayList<Movie> moviesList = new ArrayList<>();
//        select * from watchedmovies, movies where watchedmovies.MovieId = movies.id

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "select * from watchedmovies, movies where watchedmovies.MovieId = movies.id ORDER BY title ASC";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
//                String[] genere = rs.getArray("genere");
                String genre = rs.getString("genre");
                String director = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("plot");
                String location = rs.getString("location");
                String poster = rs.getString("poster");
                String rating = rs.getString("rating");
                String format = rs.getString("format");
                int year = rs.getInt("year");
//                ArrayList<String> starring = rs.getArray("starring");
                String starring = rs.getString("starring");
                int copies = rs.getInt("copies");
                String barcode = rs.getString("barcode");
                String user_rating = rs.getString("user_rating");
                Movie m = new Movie(id, title, genre, director, runtime, plot, location, rating, format, year, starring, copies, barcode, user_rating);
                moviesList.add(m);
            }

        } catch (SQLException e) {
            throw new DaoException("findMoviesByDirector() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findMoviesByDirector()" + e.getMessage());
            }
        }

        return moviesList;

    }

    /**
     * gets a arraylist fo movies based on directors films and runtimes that the
     * user has viewed and stored in the watchedmovies table
     *
     * @return arraylist<movie> of recommendations
     * @throws DaoException
     */
    @Override
    public List<Movie> getRecommnedations() throws DaoException {
        ArrayList<Movie> recommendationList = new ArrayList<>();
//    recommendationsList = getWatchList();
//select * from movies where movies.director in (select movies.director from watchedmovies, movies where watchedmovies.MovieId = movies.id)  
//ORDER BY `movies`.`director` ASC

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "select * from movies where movies.director in (select movies.director from watchedmovies, movies where watchedmovies.MovieId = movies.id) ORDER BY movies.runtime ASC limit 8";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String director = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("plot");
                String location = rs.getString("location");
                String rating = rs.getString("rating");
                String format = rs.getString("format");
                int year = rs.getInt("year");
                String starring = rs.getString("starring");
                int copies = rs.getInt("copies");
                String barcode = rs.getString("barcode");
                String user_rating = rs.getString("user_rating");
                Movie m = new Movie(id, title, genre, director, runtime, plot, location, rating, format, year, starring, copies, barcode, user_rating);
                recommendationList.add(m);
            }

        } catch (SQLException e) {
            throw new DaoException("findMoviesByDirector() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findMoviesByDirector()" + e.getMessage());
            }
        }
        return recommendationList;
        //update
    }

    /**
     * takes in a movieid and new movie object with updated details and updates
     * the data for that movie in teh db
     *
     * @param movieId int movie id of movie whose data you want to edit
     * @param m movie object holding data for which you want changed into
     * @return true or false wether or not operatino successful or not
     * @throws DaoException
     */
    @Override
    public boolean updateMovie(Movie m) throws DaoException {
        /*
        UPDATE `movies` SET `title` = 'Anchorman: The Legend of Ron Burgundy ', `genre` = 'Comedy ', 
        `director` = 'Adam McKay ', `runtime` = '94 min ', `plot` = 'l? ', `location` = ' ', `poster` = ' ', 
        `rating` = 'PG-13 ', `format` = 'DVD ', `year` = '2004 ', `
        starring` = 'Will Ferrell, Christina Applegate, Paul Rudd, Steve Carell ', 
        `barcode` = '5051188154636 ', `user_rating` = '7.3 ' WHERE `movies`.`id` = 409;
        
         msg = "editMovie,"+m.getId() + "," + newTitle + "," + newDirector + "," + newGenere + "," + newRuntime;
         */
        
        
        try {
            con = this.getConnection();

            String query = "UPDATE movies SET title = ?, director = ?, genre = ?, runtime = ? WHERE movies.id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getDirector());
            ps.setString(3, m.getGenre());
            ps.setString(4, m.getRuntime());
            ps.setInt(5, m.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("addMovie()" + e.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("editMovie()" + e.getMessage());
            }
        }
        return true;

    }
}
