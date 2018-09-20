/**                                                         
 * UserDaoInterface
 * 
 * Declares the methods that all UserDAO types must implement,
 * be they MySql User Daos or Oracle User Daos etc...
 * 
 * Classes from the Business Layer (users of this Dao)
 * should use reference variables of this type so that 
 * the underlying concrete classes can be changed as required.
 * More sophistocated implementations will use a factory
 * method to instantiate the Dao concrete classes.
 * 
 * Interfaces are also useful when testing, as concrete classes
 * can be replaced by mock DAO objects.
 */
package Daos;

import DTOs.User;
import Exceptions.DaoException;
import java.util.List;

public interface UserDaoInterface 
{
    public List<User> findAllUsers() throws DaoException;
    public User findUserByUsernamePassword(String uname, String pword) throws DaoException ;
}
