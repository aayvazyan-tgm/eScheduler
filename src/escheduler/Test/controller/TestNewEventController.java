package escheduler.Test.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import escheduler.controller.*;
import escheduler.model.*;
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
		EventsController evC=new EventsController();
		RegisterController rc=new RegisterController();
		if(evC.searchUser("neo") == null) assertTrue(rc.register("neo", "xxx"));
		User u1 = evC.searchUser("neo");
		assertNotNull(u1);
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
