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
 * @author Ari Ayvazyan
 * @version 01.06.2014
 *
 */
public class TestEventsController {
	User u1;
	Event event;
	EventsController ec=new EventsController();
	Eventdate eDate;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		if(ec.searchUser("neo")==null){
			assertTrue(new RegisterController().register("neo", "xxx"));
		}
		ec=new EventsController();
		u1 = ec.searchUser("neo");
		
		assertEquals(u1.getUsername(), "neo");
		
		ArrayList<Eventdate> al=new ArrayList<Eventdate>();
		event= new Event();
		
		eDate=new Eventdate(new Date(),new Date(), event);
		al.add(eDate);
		event.setEventdates(al);
		event.setName("testname");
		event.setType(EType.DATE_SINGLEUSER);
		event.setOrganisator(u1);
		event.setVotingactive(true);
		NewEventController ne=new NewEventController(); //Events have a generated id, so it is not required to have a different event every call
		assertTrue(ne.createEvent(event));
		
		assertTrue(ec.addUser(u1, event));
		
		event = ec.getEventById(event.getID());
		assertNotNull(event);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
	}

	/**
	 * Test method for {@link escheduler.controller.EventsController#fixEvent(escheduler.model.Event, escheduler.model.Eventdate)}.
	 */
	@Test
	public void testFixEvent() {
		assertTrue(ec.fixEvent(event, eDate));
	}

	/**
	 * Test method for {@link escheduler.controller.EventsController#addUser(escheduler.model.User, escheduler.model.Event)}.
	 */
	@Test
	public void testAddUser() {
		assertTrue(new RegisterController().register("anderson", "xxx"));
		User u2l = ec.searchUser("anderson");
		assertNotNull(u2l);
		assertTrue(ec.addUser(u2l, event));
	}

	/**
	 * Test method for {@link escheduler.controller.EventsController#removeUser(escheduler.model.User, escheduler.model.Event)}.
	 */
	@Test
	public void testRemoveUser() {
		assertTrue(new RegisterController().register("anderson2", "xxx"));
		User u2l=ec.searchUser("anderson2");
		assertNotNull(u2l);
		assertTrue(ec.addUser(u2l, event));
		assertTrue(ec.removeUser(u2l, event));
	}

	/**
	 * Test method for {@link escheduler.controller.EventsController#Vote(escheduler.model.Event, escheduler.model.User, escheduler.model.Eventdate)}.
	 */
	@Test
	public void testVote() {
		assertTrue(new RegisterController().register("anderson3", "xxx"));
		User u2l=ec.searchUser("anderson3");
		assertNotNull(u2l);
		assertTrue(ec.addUser(u2l, event));
		assertTrue(ec.Vote(event, u2l, eDate));
	}

	/**
	 * Test method for {@link escheduler.controller.EventsController#addComment(java.lang.String, escheduler.model.User, escheduler.model.Event)}.
	 */
	@Test
	public void testAddComment() {
		assertTrue(new RegisterController().register("anderson4", "xxx"));
		User u2l = ec.searchUser("anderson4");
		assertNotNull(u2l);
		assertTrue(ec.addUser(u2l, event));
		assertTrue(ec.addComment("myComment", u1, event)); //Administrator comments to a comment
		assertTrue(ec.addComment("NormalUserComment", u2l, event));
	}

	/**
	 * Test method for {@link escheduler.controller.EventsController#deleteComment(escheduler.model.Comment, escheduler.model.User, escheduler.model.Event)}.
	 */
	@Test
	public void testDeleteComment() {
		assertTrue(ec.addComment("myComment", u1, event)); //Administrator comments to a comment
		event = ec.getEventById(event.getID());
		assertNotNull(event);
		assertNotNull(event.getComments());
		
		System.out.println("start loop");
		for(Comment comment:event.getComments())
		{
			if(comment.getText().equals("myComment"))
			{
				assertTrue(ec.deleteComment(comment, u1, event));
				break;
			}
		}
	}

	/**
	 * Test method for {@link escheduler.controller.EventsController#updateEvent(escheduler.model.Event)}.
	 */
	@Test
	public void testUpdateEvent() {
		event.setDescription("new Description");
		assertTrue(ec.updateEvent(event));
	}

	/**
	 * Test method for {@link escheduler.controller.EventsController#deleteEvent(escheduler.model.Event)}.
	 */
	@Test
	public void testDeleteEvent() {
		assertTrue(ec.deleteEvent(event));
	}

	/**
	 * Test method for {@link escheduler.controller.EventsController#searchUser(java.lang.String)}.
	 */
	@Test
	public void testSearchUser() {
		assertNotNull(ec.searchUser("neo"));
	}

	/**
	 * Test method for {@link escheduler.controller.EventsController#getEventsForUser(escheduler.model.User)}.
	 */
	@Test
	public void testGetEventsForUser() {
		assertTrue(new RegisterController().register("anderson5", "xxx"));
		User u2l = ec.searchUser("anderson5");
		assertNotNull(u2l);
		assertTrue(ec.addUser(u2l, event));
		assertNotNull(ec.getEventsForUser(u2l));
	}

	/**
	 * Test method for {@link escheduler.controller.EventsController#getEventById(java.lang.Long)}.
	 */
	@Test
	public void testGetEventById() {
		assertEquals(ec.getEventById(event.getID()).getName(), event.getName());
	}
}
