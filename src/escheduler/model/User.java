package escheduler.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@NamedQueries({
	@NamedQuery(name="checkLogin", query="FROM User u WHERE u.username = :username AND u.password = :password"),
	@NamedQuery(name="checkExistance", query="FROM User u WHERE u.username = :username"),
	@NamedQuery(name="searchUser", query="FROM User u WHERE u.username LIKE CONCAT('%', :q, '%')")
})
@Entity
public class User
{
	@Id
	private String username;
	
	@NotNull
	private String password;

	private EntityManager entityManager;

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
