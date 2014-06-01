/**
 * 
 */
package escheduler.Test.Controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import escheduler.controller.NewEventController;
import escheduler.model.EType;
import escheduler.model.Event;
import escheduler.model.Eventdate;
import escheduler.model.User;

/**
 * @author Ari
 *
 */
public class TestNewEventController {

	/**
	 * Test method for {@link escheduler.controller.NewEventController#createEvent(escheduler.model.Event)}.
	 */
	@Test
	public void testCreateEvent() {
		User u1=new User("neo", "xxx");
		ArrayList<Eventdate> al=new ArrayList<Eventdate>();
		Event e= new Event();
		al.add(new Eventdate(new Date(),new Date(), e));
		e.setEventdates(al);
		e.setName("testname");
		e.setType(EType.DATE_SINGLEUSER);
		e.setOrganisator(u1);
		e.setVotingactive(true);
		NewEventController ne=new NewEventController();
		
		assertTrue(ne.createEvent(e));
		assertFalse(ne.createEvent(null));
	}

}
