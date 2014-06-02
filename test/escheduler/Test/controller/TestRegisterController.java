package escheduler.Test.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import escheduler.controller.LoginController;
import escheduler.controller.RegisterController;

/**
 * @author Ari Ayvazyan
 * @version 29.05.2014
 */
public class TestRegisterController
{
	private RegisterController r1;
	private LoginController l1;
	
	@Before
	public void setUp()
	{
		this.r1 = new RegisterController();
		this.l1 = new LoginController();;
	}
	/**
	 * Test method for {@link escheduler.controller.RegisterController#register(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRegisterAndLogin() 
	{
		assertTrue(r1.register("test", "test"));		
		assertTrue(l1.login("test", "test"));
		
		assertFalse(r1.register(null, "valid"));
		assertFalse(r1.register("valid", null));
		assertFalse(r1.register(null, null));
		
		assertTrue(r1.register("test2", "test"));
		assertFalse(r1.register("test2", "test"));
	}
}