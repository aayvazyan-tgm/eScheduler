/**
 * 
 */
package escheduler.Test.Controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import escheduler.controller.NotificationsController;
import escheduler.model.EType;
import escheduler.model.Event;
import escheduler.model.Eventdate;
import escheduler.model.NType;
import escheduler.model.Notification;
import escheduler.model.User;

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
		NotificationsController n=new NotificationsController();
		User u1=new User("asd", "pasd");
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
		
		n.addNotitification(note);
		n.markAsRead(note);
		List<Notification> notes= n.getNotificationsForUser(u1);
		
		assertNotNull(notes);
	}

	/**
	 * Test method for {@link escheduler.controller.NotificationsController#getDetails(escheduler.model.Notification)}.
	 */
	@Test
	public void testGetDetails() {
		NotificationsController n=new NotificationsController();
		for(int i=0;i<NType.values().length;i++)
		assertNotNull(n.getDescription(NType.values()[i]));
	}

}
