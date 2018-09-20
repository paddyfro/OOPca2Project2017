/** This App demonstrates the use of a Data Access Object (DAO)
 * to separate Business logic from Database specific logic.
 * It uses DAOs, Data Transfer Objects (DTOs), and
 * a DaoInterface to define a contract between Business Objects
 * and DAOs.
 *
 * "Use a Data Access Object (DAO) to abstract and encapsulate all
 * access to the data source. The DAO manages the connection with
 * the data source to obtain and store data" Ref: oracle.com
 *
 * Use the SQL script included with this project to create the
 * required MySQL user_database and user table
 * 
 * 
*/
package BusinessObjects;
/*
 * Name: Patrick McDonnell
 * ID: D00006968
 * Course: Bachelor of Science (Honours) in Computing in DKIT
 * Subject:Object Orientated Programming
 * 25/04/2018
 */
import DAOs.MovieDaoInterface;
import DAOs.MySqlMovieDao;
import DTOs.Movie;
import Exceptions.DaoException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import knockknocktest.KnockKnockServer;
import java.util.concurrent.ThreadLocalRandom;

public class MainApp {

    public static void main(String[] args) throws DaoException, IOException {

        //user id = 1;
//        int userID = 1;
        //        UserDaoInterface userDao = new MySqlUserDao();
        HashMap<String, Movie> movieMap = new HashMap<>();
        Scanner kb = new Scanner(System.in);
        MovieDaoInterface movieDao = new MySqlMovieDao();
        // Notice that the userDao reference is an Interface type.
        // This allows for the use of different concrete implementations.
        // e.g. we could replace the MySqlUserDao with an OracleUserDao
        // without changing anything in the Interface. 
        // If the Interface doesn't change, then none of the
        // code below that uses the interface needs to change.
        // The 'contract' defined by the interface holds true.

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive
        //create a random num for the ports
        int randomNum = ThreadLocalRandom.current().nextInt(4444, 9999);
//        System.out.println("Enter port number: ");update 
//        int pNum = kb.nextInt();
//        kb.reset();

//create a sever thread witha  radnom port
        KnockKnockServer c1 = new KnockKnockServer(randomNum);
        Thread t1 = new Thread(c1);
        //start the new thread
        t1.start();

        String hostName = "localhost";  // the server (running on local machine)
        int portNumber = randomNum;          // port number the server is listening on
        System.out.println("POrt number: " + randomNum);
        System.out.println("This is the Client program.");
        System.out.println("Connecting to server ... ");

        try ( /// try-with-resources - will autoclose resources including sockets
                Socket kkSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer = "";
            String userInput;
//            // The Business Objects requires that all User DAOs implement
            // the interfcae called "UserDaoInterface"

            mainMenu();
            System.out.println("enter command: ");
            String choice = kb.nextLine();
            String msg = "";

            //splits up user input to determine what operations they would like to use
            while (!choice.regionMatches(true, 0, "exit", 0, "exit".length())) {

                //display all details of movies in entire db
                if (choice.regionMatches(true, 0, "displayAll", 0, "displayAll".length())) {
                    msg = msg + "displayall,";
                    System.out.println("msg1:" + msg);
                    displayAllMovies(msg, out, in, fromServer);
                    //search for a director by a name that kinda matchs whats in db
                } else if (choice.regionMatches(true, 0, "search director", 0, "search director".length())) {
                    System.out.println("Search director method\n");
                    msg = "searchdirector," + choice.substring("search director".length() + 1);
                    System.out.println("msg02 :" + msg);
                    returnedArrayListMovies(msg, out, in, fromServer);
                    //search for an exact title of a movie
                } else if (choice.regionMatches(true, 0, "search title", 0, "search title".length())) {
                    String title = choice.substring("search title".length() + 1);
                    msg = "searchmovie," + title;
                    System.out.println("msg03 :" + msg);
                    System.out.println("Search title method\n");
                    Movie m = searchTitle(movieMap, msg, title, out, in, fromServer);
                    //add a movie to db
                } else if (choice.regionMatches(true, 0, "add", 0, "add".length())) {
                    msg = "add,";
                    System.out.println("msg04: " + msg);
                    addMovie(movieDao, msg, out, in, fromServer);
                    System.out.println("Add emthod");
                    //remove a movie from db
                } else if (choice.regionMatches(true, 0, "remove", 0, "remove".length())) {
                    System.out.println("Delete method");
                    msg = "remove,";
                    System.out.println("msg06: " + msg);
                    deleteMovie(movieDao, msg, out, in, fromServer);
                    //search for all movies that kind amatch a user input
                } else if (choice.regionMatches(true, 0, "search all", 0, "search all".length())) {
                    System.out.println("search all");
                    msg = "searchall," + choice.substring("search all".length() + 1);
                    System.out.println("msg :" + msg);
                    returnedArrayListMovies(msg, out, in, fromServer);
                    //display a chacehed map of what was looked at so far
                } else if (choice.regionMatches(true, 0, "display cache", 0, "display cache".length())) {
                    System.out.println("display map");
                    displayMap(movieMap);
                    //get the watched list from db
                } else if (choice.regionMatches(true, 0, "get Watched List", 0, "get Watched List".length())) {
                    System.out.println("getWatchedList method\n");
                    msg = "getWatchedList,";
                    System.out.println("msg02 :" + msg);
                    returnedArrayListMovies(msg, out, in, fromServer);
                    //get a recommendations list based on directors movies of what has been watched so 
                } else if (choice.regionMatches(true, 0, "get recommendations", 0, "get Watched List".length())) {
                    getRecommendationsList(out, in, fromServer);
                } //updateMovieDeets
                else if (choice.regionMatches(true, 0, "update movie", 0, "update movie".length())) {
                    updateMovieDeets(movieMap, out, in, fromServer);
                }
                msg = "";
                mainMenu();
                System.out.println("enter command: ");
                choice = kb.nextLine();
            }
            System.out.println("toodles noodles..");

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }
    }

    /**
     * menu print out for user information
     */
    public static void mainMenu() {
        System.out.println("\n=====================================");
        System.out.println("\tCommands:");
        System.out.println("\tdisplayAll");
        System.out.println("\tsearch title + \" movie title\"");
        System.out.println("\tsearch director + \" director name\"");
        System.out.println("\tsearch all + \" movie title\"");
        System.out.println("\tadd");
        System.out.println("\tremove");
        System.out.println("\tupdate movie");
        System.out.println("\tget watched list");
        System.out.println("\tget recommendations");
        System.out.println("\tdisplay cache");
        System.out.println("\texit");
        System.out.println("=====================================\n");
    }

    /**
     * Prints out all of a movie objects details ina neat format
     *
     * @param m movie object
     */
    public static void MovieDeets(Movie m) {
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("\t\t" + m.getTitle());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Director: " + m.getDirector());
        System.out.println("Genere: " + m.getGenre());
        System.out.println("Plot: " + m.getPlot());
        System.out.println("Rating: " + m.getRating());
        System.out.println("Runtime: " + m.getRuntime());
        System.out.println("Starring: " + m.getStarring());
        System.out.println("Year: " + m.getYear());
        System.out.println("Location: " + m.getLocation());
        System.out.println("User rating: " + m.getUser_rating());
        System.out.println("Poster: " + m.getPoster());
        System.out.println("Barcode: " + m.getBarcode());
        System.out.println("Copies: " + m.getCopies());
        System.out.println("Id: " + m.getId());
        System.out.println("-------------------------------------------------------------------\n");
    }

    /**
     * prints outa trimmed down version of the movie object
     *
     * @param m movie object to be printed
     */
    public static void MovieDeetsTrimmed(Movie m) {
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("\t\t" + m.getTitle());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Director: " + m.getDirector());
        System.out.println("Genere: " + m.getGenre());
        System.out.println("Plot: " + m.getPlot());
        System.out.println("Rating: " + m.getRating());
        System.out.println("Starring: " + m.getStarring());
        System.out.println("Year: " + m.getYear());
        System.out.println("-------------------------------------------------------------------\n");
    }

    /**
     * displays a print out of the hasmap cache that is being updated as the
     * user navigates the system
     *
     * @param hmap hashmap<String, Movie> object
     */
    public static void displayMap(HashMap<String, Movie> hmap) {
        Set set = hmap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.print("key is: " + mentry.getKey() + " & Value is: ");
            System.out.println(mentry.getValue());
        }
    }

    /**
     * checks to see if a particular movie is in the hashmap cache
     *
     * @param hmap hashmap<String, Movie> object
     * @param title title of movie to check in map
     * @return true or false dependsing if movie object was found
     */
    public static boolean checkIfInMap(HashMap<String, Movie> hmap, String title) {
        Set set = hmap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getKey().equals(title)) {
                return true;
            }
        }
        return false;
    }

    /**
     * display all the movies in the movies table from the database
     *
     * @param msg String - command to be send to teh server. format -
     * command,variable
     * @param out printwriter object to write to teh server
     * @param in bufferreader objectto get data back from server
     * @param fromServer String message sent back from server
     * @throws DaoException
     * @throws IOException
     */
    public static void displayAllMovies(String msg, PrintWriter out, BufferedReader in, String fromServer) throws DaoException, IOException {
        System.out.println("Sending (Client -> Server): " + msg);
        out.println(msg);  // send message to server
        // read lines from socket until the socket has been closed.
        // readline() is a blocking method, so it waits until data + '\n' is entered
        // or until the socket is closed.
        fromServer = in.readLine();  // keep reading from stream
        System.out.println("Received (Server -> Client): " + fromServer);
        Type type = new TypeToken<List<Movie>>() {
        }.getType();
        Gson gson = new Gson();
        // pass the JSON String and the type of the object into the deserializer
        // and it will deserialize the JSON and create a List of Movies.
        List<Movie> moviesList = gson.fromJson(fromServer, type);
        for (Movie movie : moviesList) {
            System.out.println(movie);
        }
    }

    /**
     *
     * @param msg String - command to be send to teh server. format -
     * command,variable
     * @param out printwriter object to write to teh server
     * @param in bufferreader objectto get data back from server
     * @param fromServer String message sent back from server
     * @throws IOException
     */
    public static void returnedArrayListMovies(String msg, PrintWriter out, BufferedReader in, String fromServer) throws IOException {

        System.out.println("Sending (Client -> Server): " + msg);
        out.println(msg);  // send message to server

        // read lines from socket until the socket has been closed.
        // readline() is a blocking method, so it waits until data + '\n' is entered
        // or until the socket is closed.
        fromServer = in.readLine();  // keep reading from stream
        System.out.println("Received (Server -> Client): " + fromServer);

        Type type = new TypeToken<List<Movie>>() {
        }.getType();
        Gson gson = new Gson();

        // pass the JSON String and the type of the object into the deserializer
        // and it will deserialize the JSON and create a List of Movies.
        List<Movie> moviesList = gson.fromJson(fromServer, type);

        if (!moviesList.isEmpty()) {

            for (Movie movie : moviesList) {
                MovieDeetsTrimmed(movie);
                if (!msg.equals("getWatchedList,")) {
                    addToWatchList(movie, out, in, fromServer);
                }
            }
        } else {
            System.out.println("No movies found, please try again");

        }
    }

    /**
     * searchs the movies table for a particular movie, need to be matched
     * exactly
     *
     * @param msg String - command to be send to teh server. format -
     * command,variable
     * @param out printwriter object to write to teh server
     * @param in bufferreader objectto get data back from server
     * @param fromServer String message sent back from server
     * @return either the movie object fo the found movie or a blank moie
     * object, empty title field can be checked for this
     * @throws IOException
     */
    public static Movie searchSingleMovie(String msg, PrintWriter out, BufferedReader in, String fromServer) throws IOException {

        System.out.println("Sending (Client -> Server): " + msg);
        out.println(msg);  // send message to server

        // read lines from socket until the socket has been closed.
        // readline() is a blocking method, so it waits until data + '\n' is entered
        // or until the socket is closed.
        fromServer = in.readLine();  // keep reading from stream
        System.out.println("Received (Server -> Client): " + fromServer);

        Type type = new TypeToken<Movie>() {
        }.getType();

        Gson gson = new Gson();

        // pass the JSON String and the type of the object into the deserializer
        // and it will deserialize the JSON and create a List of Movies.
        Movie movie = gson.fromJson(fromServer, type);

        if (movie.getTitle().equalsIgnoreCase("")) {
            System.out.println("No movie Found!");
//        } else {
//            MovieDeets(movie);
        }
//                        searchByTitle(movieDao, msg);
        return movie;
    }

    /**
     * takes movie details off a user then adds them to the database as a new
     * movie entry
     *
     * @param movieDao
     * @param msg String - command to be send to teh server. format -
     * command,variable
     * @param out printwriter object to write to teh server
     * @param in bufferreader objectto get data back from server
     * @param fromServer String message sent back from server
     * @throws DaoException
     * @throws IOException
     */
    public static void addMovie(MovieDaoInterface movieDao, String msg, PrintWriter out, BufferedReader in, String fromServer) throws DaoException, IOException {
        Scanner kb = new Scanner(System.in);
        System.out.println("Adding mobie to db");
        System.out.println("title:(text)");
        String title = kb.nextLine();
        System.out.println("genere:(text)");
        String genere = kb.nextLine();
        System.out.println("director:(text)");
        String director = kb.nextLine();
        System.out.println("runtime:(text)");
        String runtime = kb.nextLine();
        System.out.println("plot:(text)");
        String plot = kb.nextLine();
        System.out.println("location(text)");
        String location = kb.nextLine();
        System.out.println("poster:(text)");
        String poster = kb.nextLine();
        System.out.println("rating(text)");
        String rating = kb.nextLine();
        System.out.println("format(text)");
        String format = kb.nextLine();
        System.out.println("Year:(Number)");
        int year = kb.nextInt();
        kb.nextLine();
        System.out.println("starring(text)");
        String starring = kb.nextLine();
        System.out.println("copies(Number)");
        int copies = kb.nextInt();
        kb.nextLine();
        System.out.println("barcode(text)");
        String barcode = kb.nextLine();
        System.out.println("user rating:(text)");
        String user_rating = kb.nextLine();

        Movie m = new Movie(title, genere, director, runtime, plot, location, rating, format, year, starring, copies, barcode, user_rating);
        //create json string fo movie object? - issues with when recieved by server
        MovieDeets(m);

        String mooovie = title + "," + genere + "," + director + "," + runtime + "," + plot + "," + location + "," + rating + "," + format + "," + year + "," + starring + "," + copies + "," + barcode + "," + user_rating;
        msg += mooovie;
        System.out.println("Sending (Client -> Server): " + msg);
        out.println(msg);  // send message to server

        fromServer = in.readLine();  // keep reading from stream
        System.out.println("Received (Server -> Client): " + fromServer);

        if (fromServer.equalsIgnoreCase("true")) {
            System.out.println("Movie has been added!");
        } else {
            System.out.println("there has been an error, try again!");
        }

    }

    /**
     * deletes a movie from the movies table usinga movie id, that the user
     * confirms before hand
     *
     * @param movieDao
     * @param msg String - command to be send to teh server. format -
     * command,variable
     * @param out printwriter object to write to teh server
     * @param in bufferreader objectto get data back from server
     * @param fromServer String message sent back from server
     * @throws DaoException
     * @throws IOException
     */
    public static void deleteMovie(MovieDaoInterface movieDao, String msg, PrintWriter out, BufferedReader in, String fromServer) throws DaoException, IOException {
        //take in title of movie user wishes to delete
        Scanner kb = new Scanner(System.in);
        System.out.println("enter name of movie you wish to delete: ");
        String movieName = kb.nextLine();
        msg = "searchmovie," + movieName;
        //checks to see if movie exists
        Movie m = searchSingleMovie(msg, out, in, fromServer);

//            Movie m = movieDao.findMovieByTitle(movieName);
//            System.out.println("Movie: " + m);
//            if (m != null) {
        if (!m.getTitle().equals("")) {
            MovieDeets(m);
            System.out.println("is this the correct movie you wish to delete, y- yes, n - no");
            String choice = kb.nextLine();
            int id = m.getId();
            //confirms wether to delete or keep the movie in db
            if (choice.equalsIgnoreCase("y")) {
                msg = "remove," + id;
                System.out.println("Sending (Client -> Server): " + msg);
                out.println(msg);  // send message to server

                fromServer = in.readLine();  // keep reading from stream
                System.out.println("Received (Server -> Client): " + fromServer);

                if (fromServer.equalsIgnoreCase("true")) {
                    System.out.println("movie has been deleted: " + movieName);
                } else {
                    System.out.println("Movie has not been deleted, an unexpected error has occured");
                }
            } else {
                System.out.println("Well maybe you will choose another.");
            }

        } else {
            System.out.println("that movie was not found");
        }

    }

    /**
     * search for a title and if found add it to teh hashmap
     *
     * @param movieMap hashMap<String, Movie> hashmap to update
     * @param title title of movie to search for
     * @param msg String - command to be send to teh server. format -
     * command,variable
     * @param out printwriter object to write to teh server
     * @param in bufferreader objectto get data back from server
     * @param fromServer String message sent back from server
     * @return
     * @throws DaoException
     * @throws IOException
     */
    public static Movie searchTitle(HashMap<String, Movie> movieMap, String msg, String title, PrintWriter out, BufferedReader in, String fromServer) throws DaoException, IOException {
        System.out.println("check if in map");
        Movie m = null;
        boolean check = checkIfInMap(movieMap, title);
        System.out.println("is it in : " + check);
        if (!check) {
            m = searchSingleMovie(msg, out, in, fromServer);
            if (!m.getTitle().equalsIgnoreCase("")) {
                MovieDeets(m);
                movieMap.put(m.getTitle(), m);
                addToWatchList(m, out, in, fromServer);
            }
        } else {
            m = movieMap.get(title);
            MovieDeets(m);
            addToWatchList(m, out, in, fromServer);
        }
        return m;
    }

    /**
     *
     * @param m movie object to be added to watchlist table
     * @param out printwriter object to write to teh server
     * @param in bufferreader objectto get data back from server
     * @param fromServer String message sent back from server
     * @throws IOException
     */
    public static void addToWatchList(Movie m, PrintWriter out, BufferedReader in, String fromServer) throws IOException {
        Scanner kb = new Scanner(System.in);
        System.out.println("add movie to watched list? (yes / no)");
        String addWatched = kb.nextLine();
        if (addWatched.equalsIgnoreCase("yes")) {
            System.out.println("add to watched list");
            System.out.println("id - " + m.getId() + " title: " + m.getTitle());
            String msg = "addToWatched," + m.getId();
//            System.out.println("seerv message: " + msg);

            System.out.println("Sending (Client -> Server): " + msg);
            out.println(msg);  // send message to server

            fromServer = in.readLine();  // keep reading from stream
            System.out.println("Received (Server -> Client): " + fromServer);

            if (fromServer.equalsIgnoreCase("true")) {
                System.out.println("movie has been added to watched list: " + m.getTitle());
            } else {
                System.out.println("Movie has not been added, an unexpected error has occured");
            }

        } else {
            System.out.println("dont add the movie " + m.getTitle() + "to watched list");
        }
    }

    /**
     * send the command to get teh arraylist of recommended movies from he db, created by what movies of the directors in your watched movies tables
     * @param out printwriter object to write to teh server
     * @param in bufferreader objectto get data back from server
     * @param fromServer String message sent back from server
     * @throws IOException 
     */
    public static void getRecommendationsList(PrintWriter out, BufferedReader in, String fromServer) throws IOException {
        System.out.println("get recommendations method\n");
        String msg = "getRecommendations,";
        System.out.println("msg02 :" + msg);
        returnedArrayListMovies(msg, out, in, fromServer);

    }

    /**
     * update a aprticular movies details, gets user to select movie then eter new details and then updates the moie entry in the database
     * @param movieMap = hashmap for use in searchtitele mothod utilised in here
     * @param out printwriter object to write to teh server
     * @param in bufferreader objectto get data back from server
     * @param fromServer String message sent back from server
     * @throws IOException
     * @throws DaoException 
     */
    public static void updateMovieDeets(HashMap<String, Movie> movieMap, PrintWriter out, BufferedReader in, String fromServer) throws IOException, DaoException {
        Scanner kb = new Scanner(System.in);
        //find a movie the suer wnats to edit
        System.out.println("update Movie method");
        System.out.println("title of movie you want to update: ");
        String title = kb.nextLine();
        String msg = "searchmovie," + title;
        Movie m = searchTitle(movieMap, msg, title, out, in, fromServer);
//        System.out.println("id: " + m.getId() + m.getTitle());
        MovieDeets(m);
        //take in data form user
        System.out.println("edit title: ");
        String newTitle = kb.nextLine();
        System.out.println("director: ");
        String newDirector = kb.nextLine();
        System.out.println("edit genere: ");
        String newGenere = kb.nextLine();
        System.out.println("edit runtime: ");
        String newRuntime = kb.nextLine();
        //created new movie object to store edited data and initial movie id
//        Movie editMovie = new Movie();
//        editMovie.setId(m.getId());
//        editMovie.setTitle(newTitle);
//        editMovie.setDirector(newDirector);
//        editMovie.setGenre(newGenere);
//        editMovie.setRuntime(newRuntime);
//        System.out.println("new movie deets");
//        MovieDeets(editMovie);
        msg = "editMovie," + m.getId() + "," + newTitle + "," + newDirector + "," + newGenere + "," + newRuntime;
        System.out.println("msg: " + msg);
        //create json string for movie object to be sent to server
//        Type type = new TypeToken<Movie>() {
//        }.getType();
//        Gson gson = new Gson();
//        String json = gson.toJson(editMovie, type);
        //return json string representation srraylist of movie objects

        System.out.println("Sending (Client -> Server): " + msg);
        out.println(msg);  // send message to server
        // read lines from socket until the socket has been closed.
        // readline() is a blocking method, so it waits until data + '\n' is entered
        // or until the socket is closed.
        fromServer = in.readLine();  // keep reading from stream
        System.out.println("Received (Server -> Client): " + fromServer);
        if (fromServer.equalsIgnoreCase("true")) {
            System.out.println("edit successful");
        } else {
            System.out.println("there was an error with editing yon movie");
        }

    }

}
