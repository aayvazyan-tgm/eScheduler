package escheduler.model;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Represents a single User in the System, who can login, participate in events, votes, etc.
 * 
 * @author Andreas Willinger
 * @version 29.05.2014
 */
@NamedQueries({
	@NamedQuery(name="checkLogin", query="FROM User u WHERE u.username = :username AND u.password = :password"),
	@NamedQuery(name="checkExistance", query="FROM User u WHERE u.username = :username"),
	@NamedQuery(name="searchUser", query="FROM User u WHERE u.username LIKE CONCAT('%', :q, '%')")
})
@Entity
public class User
{
	/** the unique username. */
	@Id
	private String username;
	
	/** the corresponding password. */
	@NotNull
	private String password;
	
	/** default constructor, required for Hibernate to work properly */
	public User()
	{
	
	}
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() 
	{
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) 
	{
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() 
	{
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
}
