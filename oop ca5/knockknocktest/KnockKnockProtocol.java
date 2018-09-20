/**
 * This is the Model that maintains the STATE of the interaction.
 * The PROTOCOL is implemented using logic and state transitions.
 *
 * Name: Patrick McDonnell
 * ID: D00006968
 * Course: Bachelor of Science (Honours) in Computing in DKIT
 * Subject:Object Orientated Programming
 * 25/04/2018
 * */
package knockknocktest;

import DTOs.Movie;
import DAOs.MySqlMovieDao;
import Exceptions.DaoException;
import java.util.List;
import java.lang.reflect.Type;
import DAOs.MovieDaoInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class KnockKnockProtocol {

    //creates global varibales
    //create movieDao for interacting with the db
    private final MovieDaoInterface MOVIEDAO = new MySqlMovieDao();
    //GSON for converting to and from json
    private final Gson GSON = new Gson();
    //hardcoded userid to make personalisation available
    private final int USERID = 1;
    //initalise a type object thats a arrayLIst of movie objects
    private final Type TYPELIST = new TypeToken<List<Movie>>() {
    }.getType();
    //create a type so the gson knows what type of object to convert to json
    private final Type TYPEOBJECT = new TypeToken<Movie>() {
    }.getType();

    public String processInput(String theInput) throws DaoException {

        String output = null;
        ArrayList<String> input = new ArrayList();
        //break up the string from the server/client
        StringTokenizer st = new StringTokenizer(theInput, ",");
        //adds each , seperated value to an arraylist fo strings
        while (st.hasMoreTokens()) {
            input.add(st.nextToken());
        }
        //menu system to check what comman was sent through teh server by the client
        //only valid commands shoul make their way through
        //searchall
        if (input.get(0).equalsIgnoreCase("searchall")) {
            //returns back  astring to the server to be returned to the user
            //send the arraylist fo strings 'inout' to the method
            output = searchAllMovies(input);
            //displayall
        } else if (input.get(0).equalsIgnoreCase("displayall")) {
            System.out.println("displayall protocol");
            output = displayAll(input);
            //searchdirector
        } else if (input.get(0).equalsIgnoreCase("searchdirector")) {
            System.out.println("searchdirector protocol");
            output = searchDirector(input);
            //searchmovie
        } else if (input.get(0).equalsIgnoreCase("searchmovie")) {
            System.out.println("searchmovie protocol");
            output = searchMovie(input);
            //add
        } else if (input.get(0).equalsIgnoreCase("add")) {
            System.out.println("add protocol");
            output = addMovie(input);
            //remove
        } else if (input.get(0).equalsIgnoreCase("remove")) {
            System.out.println("remove protocol");
            output = removeMovie(input);
        } //addToWatched
        else if (input.get(0).equalsIgnoreCase("addToWatched")) {
            System.out.println("addToWatched protocol");
            output = addToWatched(input);
        }//getWatchedList
        else if (input.get(0).equalsIgnoreCase("getWatchedList")) {
            System.out.println("getWatchedList protocol");
            output = getWatchedList(input);
        }//getRecommendations
        else if (input.get(0).equalsIgnoreCase("getRecommendations")) {
            System.out.println("getRecommendations protocol");
            output = getRecommendations(input);
        }//editMovie
        else if (input.get(0).equalsIgnoreCase("editMovie")) {
            System.out.println("editMovie protocol");
            output = editMovie(input);
        }
//        Gson gson = new Gson();
//        String json = gson.toJson(movies, type);
        return output;
    }

    /**
     * searchs the db for a particular movie
     *
     * @param List String arayList that holds the command and the movie title in
     * index location 1
     * @return json string of the found movie object, it will be a empty object
     * if no movie found
     * @throws DaoException
     */
    public String searchMovie(ArrayList<String> List) throws DaoException {
//        System.out.println("test1");
//        MovieDaoInterface MovieDao = new MySqlMovieDao();

        //initalise a movie object
        Movie movie = null;
        //print out the title for confirmation of title to search for
        System.out.println(List.get(1));
        //getting the movie title from the arraylist of string objects
        String moovie = List.get(1);
//        System.out.println("variable: " + hello);
        try {
//            System.out.println("test2");
            //access moviedao and expect a movie object to be returned based o wether it has been found in the db
            movie = MOVIEDAO.findMovieByTitle(moovie);

            //printing out the movie found for confirmation on the server side
            System.out.println(movie);

        } catch (DaoException e) {
            e.printStackTrace();
        }
//        Gson gson = new Gson();
        //create a json string using the Gson library, passing in the movie object and the type of object you are converting
        String json = GSON.toJson(movie, TYPEOBJECT);
        //return the json string for sending back to teh user
        return json;
    }

    /**
     * searches the db for all movies that kind of match the user input
     *
     * @param List arraylist of strings that hold the command and title of movie
     * to search for
     * @return json string that represents a arraylist of movie objects
     * @throws DaoException
     */
    public String searchAllMovies(ArrayList<String> List) throws DaoException {
//        System.out.println("test1");
//        MovieDaoInterface MovieDao = new MySqlMovieDao();

        //create arraylist and 
        List<Movie> movies = null;
        // getting printing out the title to be searched by
        System.out.println(List.get(1));
        String title = List.get(1);
//        System.out.println("variable: " + hello);
        try {
//            System.out.println("test2");
            //accessing the moviedao to get an arraylist of movie objects that kind of match the title given
            movies = MOVIEDAO.findAllMovieByTitle(title);

            //prints out the movies for cnfirmation on the server side
            for (Movie movie : movies) {
//            System.out.println("movie: " + movie.toString());
                System.out.println(movie);
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }
//        Gson gson = new Gson();
        //convert and create a json string using  gson and the movies arraylist and the type variable
        String json = GSON.toJson(movies, TYPELIST);
        //return backa  json string representing the arraylist of movie objects
        return json;
    }

    /**
     * search for alist of movies by a certain director
     *
     * @param List arraylist fo strng objects that hole the command and the
     * directors name to be search for
     * @return json string representing and arraylist of movie objects
     * @throws DaoException
     */
    public String searchDirector(ArrayList<String> List) throws DaoException {
//        System.out.println("test1");
//        MovieDaoInterface MovieDao = new MySqlMovieDao();
        
        List<Movie> directorsMoivies = null;
        System.out.println(List.get(1));

        String director = List.get(1);
//        System.out.println("variable: " + hello);
        try {
//            System.out.println("test2");
            directorsMoivies = MOVIEDAO.findMovieByDirector(director);

//            for (Movie movie : directorsMoivies) {
////            System.out.println("movie: " + movie.toString());
//                System.out.println(movie);
//            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
//        Gson gson = new Gson();
        String json = GSON.toJson(directorsMoivies, TYPELIST);
        //return backa  json representation fo te arraylist fo movies objects
        return json;
    }

    /**
     * searches teh db for all moveis and returns them in an arraylist fo movie objects
     * @param List stringa rraylist that holds command
     * @return arraylist fo movie objects
     * @throws DaoException 
     */
    public String displayAll(ArrayList<String> List) throws DaoException {
//        System.out.println("test1");
//        MovieDaoInterface MovieDao = new MySqlMovieDao();
        
        List<Movie> movies = null;
        //getas all he movies from the db and stores them in an arraylist of movie objects
        try {
            movies = MOVIEDAO.findAllMovies();
        } catch (DaoException e) {
            e.printStackTrace();
        }
//        Gson gson = new Gson();
        String json = GSON.toJson(movies, TYPELIST);
        return json;
    }

    /**
     * adds a movie to the movies database table
     * @param List string arraylist that holds the command and all the movie data to be added
     * @return string saying true if successsful, fals eif not
     * @throws DaoException 
     */
    public String addMovie(ArrayList<String> List) throws DaoException {

        System.out.println("Adding moovie to db");
        //creates a new movie object
        Movie movie = new Movie();
        String output = "";
        //takes avkues from stringa rraylist and adds them to the movie object
        movie.setTitle(List.get(1));
        movie.setGenre(List.get(2));
        movie.setDirector(List.get(3));
        movie.setRuntime(List.get(4));
        movie.setPlot(List.get(5));
        movie.setLocation(List.get(6));
        movie.setRating(List.get(7));
        movie.setFormat(List.get(8));
        movie.setYear(Integer.parseInt(List.get(9)));
        movie.setStarring(List.get(10));
        movie.setCopies(Integer.parseInt(List.get(11)));
        movie.setBarcode(List.get(12));
        movie.setUser_rating(List.get(13));
        System.out.println("Movie object to add");
        System.out.println(movie);

        try {
            //sends movie object to moviedao for addition to the database
            //if successful it returns true if not returns false
            if (MOVIEDAO.addMovie(movie)) {
                output = "true";
            } else {
                output = "false";
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }

        return output;

    }

    /**
     * removes a movie from the movies table, takes in the movies id and command
     * @param List stringa rraylist the holds command and movie id for deletion
     * @return string stating true or false if operation was successful
     * @throws DaoException 
     */
    public String removeMovie(ArrayList<String> List) throws DaoException {
//        System.out.println("test1");
//        MovieDaoInterface MovieDao = new MySqlMovieDao();
//initalise output to be false initially
        String output = "false";
        //prints out movie id for confirmation
        System.out.println(List.get(1));
        //takes and parses out the int of the movie id
        int id = Integer.parseInt(List.get(1));
        System.out.println("movie id to be deleted: " + id);
        try {
            //if moviedao returns true, then movies based on id has been expunged from the db
            if (MOVIEDAO.deleteMovie(id)) {
                output = "true";
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }

        return output;
    }

    /**
     * adds a movies based ona  movie id and user id to the watchedmovies table
     * @param List string arraylist that holds command and the movie id
     * @return string , true of false if operation was successful
     * @throws DaoException 
     */
    public String addToWatched(ArrayList<String> List) throws DaoException {

        String output = "false";
        //print and get movie id for computation
        System.out.println(List.get(1));
        int MovieId = Integer.parseInt(List.get(1));
        System.out.println("movie id to be added: " + MovieId);
        
        try {
            //calls moviedao to add movie to watch list 
            if (MOVIEDAO.addToWatchList(USERID, MovieId)) {
                //sets output to trueif successful
                output = "true";
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        //returns true or false wether or now successful or not
        return output;
    }

    /**
     * gest an array of movie objects that represent the data in the watched movies table
     * @param List arraylist fo strings holding command details
     * @return ajson string representation of arraylist of movie objects
     * @throws DaoException 
     */
    public String getWatchedList(ArrayList<String> List) throws DaoException {
//        System.out.println("test1");
//        MovieDaoInterface MovieDao = new MySqlMovieDao();
        ;
        List<Movie> watchedList = null;
        try {
//            System.out.println("test2");
            watchedList = MOVIEDAO.getWatchList();

//            for (Movie movie : watchedList) {
////            System.out.println("movie: " + movie.toString());
//                System.out.println(movie);
//            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
//        Gson gson = new Gson();
        String json = GSON.toJson(watchedList, TYPELIST);
        return json;
    }

    /**
     * gets an arraylist fo movies objects of movie recommendations based on directors in the watched movies table
     * @param List arraylist of strings that holds command
     * @return arraylist of movie objects that contains the movie recommendations
     * @throws DaoException 
     */
    public String getRecommendations(ArrayList<String> List) throws DaoException {
//        System.out.println("test1");
//        MovieDaoInterface MovieDao = new MySqlMovieDao();
        //initalise a arraylist of movie objects to hold recommendations
        List<Movie> recommendationsList = null;

        try {
//            System.out.println("test2");
//calls movie dao to get arraylist fo recommendations
            recommendationsList = MOVIEDAO.getRecommnedations();

//            for (Movie movie : directorsMoivies) {
////            System.out.println("movie: " + movie.toString());
//                System.out.println(movie);
//            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
//        Gson gson = new Gson();
//converts arraylist to json string using gson
        String json = GSON.toJson(recommendationsList, TYPELIST);
        //return json string representation srraylist of movie objects
        return json;
    }
    
    /**
     * takes ina  movie object with edited values to change based on the movie id
     * @param List string arraylist holding values that need to be used to update movie
     * @return true or false based on successful operation
     * @throws DaoException 
     */
    public String editMovie(ArrayList<String> List) throws DaoException{
        
        System.out.println("editing moovie in db");
        //creates a new movie object
        Movie movie = new Movie();
        String output = "";
        //takes avkues from stringa rraylist and adds them to the movie object
        movie.setId(Integer.parseInt(List.get(1)));
        movie.setTitle(List.get(2));
        movie.setDirector(List.get(3));
        movie.setGenre(List.get(4));
        movie.setRuntime(List.get(5));        
        System.out.println("Movie object to edit");
        System.out.println(movie);

        try {
            //sends movie object to moviedao for addition to the database
            //if successful it returns true if not returns false
            if (MOVIEDAO.updateMovie(movie)) {
                output = "true";
            } else {
                output = "false";
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }

        return output;
    }

}
