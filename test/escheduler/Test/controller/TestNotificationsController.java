package escheduler.Test.controller;
/**
 * 
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import escheduler.controller.*;
import escheduler.model.*;

/**
 * @author Ari Ayvazyan
 * @version 29.05.2014
 *
 */
public class TestNotificationsController {

	/**
	 * Test method for {@link escheduler.controller.NotificationsController#addNotitification(escheduler.model.NType, escheduler.model.User, escheduler.model.Event)}.
	 */
	@Test
	public void testNotitifications() {
		EventsController evC=new EventsController();
		RegisterController rc=new RegisterController();
		if(evC.searchUser("asd") == null) assertTrue(rc.register("asd", "pasd"));
		User u1 = evC.searchUser("asd");
		assertNotNull(u1);
		
		NotificationsController n=new NotificationsController();
		ArrayList<Eventdate> al=new ArrayList<Eventdate>();
		Event e= new Event();
		al.add(new Eventdate(new Date(),new Date(), e));
		e.setEventdates(al);
		e.setName("testname");
		e.setType(EType.DATE_SINGLEUSER);
		e.setOrganisator(u1);
		e.setVotingactive(true);
		Notification note=n.createNotification(NType.EVENT_NEW, u1,e );
		
		assertNotNull(note);
		
		n.addNotification(note);
		n.markAsRead(note);
		List<Notification> notes= n.getNotificationsForUser(u1);
		assertNotNull(notes);
		
		assertFalse(n.addNotification(null));
		
		Notification n1 = new Notification();
		n1.setType(NType.ALL_INVITES_ACCEPTED);
		n1.setTarget(null);
		
		assertFalse(n.addNotification(n1));
		
		n1.setType(NType.DATE_FIXED);
		n1.setTrigger(null);
		
		assertFalse(n.addNotification(n1));
		
		assertFalse(n.markAsRead(null));
	}

	/**
	 * Test method for {@link escheduler.controller.NotificationsController#getDetails(escheduler.model.Notification)}.
	 */
	@Test
	public void testGetDescription() {
		NotificationsController n = new NotificationsController();
		for(int i = 0;i < NType.values().length; i++)
		assertNotNull(n.getDescription(NType.values()[i]));
		
		assertNull(n.getNotificationsForUser(null));
	}
}
