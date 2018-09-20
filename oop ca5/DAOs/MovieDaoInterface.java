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
import java.util.List;

public interface MovieDaoInterface {

    public List<Movie> findAllMovies() throws DaoException;
    public Movie findMovieByTitle(String title) throws DaoException;
    public boolean addMovie(Movie m)throws DaoException;
    public boolean deleteMovie(int id) throws DaoException;
    public List<Movie> findMovieByDirector(String director) throws DaoException;
    public List<Movie> findAllMovieByTitle(String title) throws DaoException;
    public boolean addToWatchList(int userID, int movieID) throws DaoException;
    public int checkIfOnWatchList(int movieId) throws DaoException;
    public List<Movie> getWatchList() throws DaoException;
    public List<Movie> getRecommnedations() throws DaoException;
    public boolean updateMovie(Movie m) throws DaoException;
}
