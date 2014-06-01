package escheduler.Test.Controller;

import static org.junit.Assert.*;

import org.junit.Test;

import escheduler.controller.LoginController;
import escheduler.controller.RegisterController;

/**
 * @author Ari Ayvazyan
 * @version 29.05.2014
 */
public class TestRegisterController {

	/**
	 * Test method for {@link escheduler.controller.RegisterController#register(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRegisterAndLogin() {
		RegisterController r1=new RegisterController();
		assertTrue(r1.register("test", "test"));
		assertFalse(r1.register("test", "test"));
		LoginController l1=new LoginController();
		assertTrue(l1.login("test", "test"));
	}

}