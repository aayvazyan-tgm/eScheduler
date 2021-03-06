package escheduler.Test.controller;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import escheduler.controller.*;
import escheduler.model.*;
/**
 * @author Ari
 * @version 01.06.2014
 *
 */
public class TestInvitesController {
	User admin;
	Event e;
	User invitedUser;
	User invitedUser2;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		EventsController evC = new EventsController();
		RegisterController rc = new RegisterController();
		if(evC.searchUser("neo") == null) assertTrue(rc.register("neo", "xxx"));
		this.admin = evC.searchUser("neo");
		assertNotNull(this.admin);
		
		ArrayList<Eventdate> al = new ArrayList<Eventdate>();
		this.e = new Event();
		al.add(new Eventdate(new Date(),new Date(), e));
		e.setEventdates(al);
		e.setName("testname");
		e.setType(EType.DATE_SINGLEUSER);
		e.setOrganisator(this.admin);
		e.setVotingactive(true);
		NewEventController ne = new NewEventController();
		assertTrue(ne.createEvent(e));
		
		rc.register("andersonX","nosredna.rm");
		rc.register("andersonY","nosredna.rm");
		
		this.invitedUser = evC.searchUser("andersonX");
		this.invitedUser2 = evC.searchUser("andersonY");
		
		this.e = evC.getEventById(e.getID());
		assertNotNull(this.e);
		
		evC.addUser(invitedUser, e);
		evC.addUser(invitedUser2, e);
		
		this.e = evC.getEventById(e.getID());
		assertNotNull(this.e);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
	}

	/**
	 * Test method for {@link escheduler.controller.InvitesController#acceptInvite(escheduler.model.User, escheduler.model.Event)}.
	 */
	@Test
	public void testAcceptInvite() {
		InvitesController i1 =new InvitesController();
		assertTrue(i1.acceptInvite(invitedUser, e));
		
		Event e = new Event();
		User u = new User();
		assertFalse(i1.acceptInvite(null, e));
		assertFalse(i1.acceptInvite(u, null));
		assertFalse(i1.acceptInvite(null, null));
		
		e.setParticipants(null);
		assertFalse(i1.acceptInvite(u, e));
	}

	/**
	 * Test method for {@link escheduler.controller.InvitesController#declineInvite(escheduler.model.User, escheduler.model.Event)}.
	 */
	@Test
	public void testDeclineInvite() {
		InvitesController i1 =new InvitesController();
		assertTrue(i1.declineInvite(invitedUser, e));
		
		Event e = new Event();
		User u = new User();
		assertFalse(i1.declineInvite(null, e));
		assertFalse(i1.declineInvite(u, null));
		assertFalse(i1.declineInvite(null, null));
		
		e.setParticipants(null);
		assertFalse(i1.declineInvite(u, e));
	}

	/**
	 * Test method for {@link escheduler.controller.InvitesController#getInvites(escheduler.model.User)}.
	 */
	@Test
	public void testGetInvites() {
		InvitesController i1 =new InvitesController();
		assertEquals(i1.getInvites(invitedUser).get(0).getID(),e.getID());
		
		assertNull(i1.getInvites(null));
	}
}
