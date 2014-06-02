package escheduler.Test.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import escheduler.controller.ControllerProxy;

/**
 * @author Ari
 * @version 01.06.2014
 *
 */
public class TestControllerProxy {
	private ControllerProxy cp;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.cp=new ControllerProxy();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link escheduler.controller.ControllerProxy#ControllerProxy(escheduler.controller.LoginController, escheduler.controller.RegisterController, escheduler.controller.NewEventController, escheduler.controller.InvitesController, escheduler.controller.EventsController, escheduler.controller.NotificationsController)}.
	 */
	@Test
	public void testControllerProxy() {
		assertNotNull(this.cp=new ControllerProxy());
	}

	/**
	 * Test method for {@link escheduler.controller.ControllerProxy#getLoginController()}.
	 */
	@Test
	public void testGetLoginController() {
		assertNotNull(cp.getLoginController());
	}

	/**
	 * Test method for {@link escheduler.controller.ControllerProxy#getRegisterController()}.
	 */
	@Test
	public void testGetRegisterController() {
		assertNotNull(cp.getRegisterController());
	}

	/**
	 * Test method for {@link escheduler.controller.ControllerProxy#getNewEventController()}.
	 */
	@Test
	public void testGetNewEventController() {
		assertNotNull(cp.getNewEventController());
	}

	/**
	 * Test method for {@link escheduler.controller.ControllerProxy#getInvitesController()}.
	 */
	@Test
	public void testGetInvitesController() {
		assertNotNull(cp.getInvitesController());
	}

	/**
	 * Test method for {@link escheduler.controller.ControllerProxy#getEventsController()}.
	 */
	@Test
	public void testGetEventsController() {
		assertNotNull(cp.getEventsController());
	}

	/**
	 * Test method for {@link escheduler.controller.ControllerProxy#getNotificationsController()}.
	 */
	@Test
	public void testGetNotificationsController() {
		assertNotNull(cp.getNotificationsController());
	}

}
