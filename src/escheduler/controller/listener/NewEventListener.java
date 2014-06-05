/**
 * 
 */
package escheduler.controller.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import escheduler.controller.EventsController;
import escheduler.controller.NewEventController;
import escheduler.model.EType;
import escheduler.model.Event;
import escheduler.model.Eventdate;
import escheduler.model.User;
import escheduler.view.MainView;
import escheduler.view.composites.NewEventComposite;

/**
 * Handles Button CLicks for creating a new event
 * 
 * @author ffreu_000
 * @version Jun 3, 2014
 */
@SuppressWarnings("serial")
public class NewEventListener implements ClickListener{

	private MainView mv;
	private NewEventController nec;
	private EventsController ec;

	public NewEventListener(MainView mv) {
		nec = new NewEventController();
		ec = new EventsController();
		this.mv = mv;
	}
	
	
	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		Button b = event.getButton();
		String c = b.getCaption();
		//Checks if the NewEvent Form should be opened or submitted
		if(c.equals("New Event")) {
			mv.newEvent();
		}
		else {
			if(c.equals("Create Event")) {
				boolean dateCheck;
				boolean userCheck = true;
				
				NewEventComposite ne = (NewEventComposite) mv.getContent();
							
				//Checks if the entered Values are allowed for an Event
				if(ne.checkValues()) {
					
					String name = ne.getEventName().getValue();
					
					String description = ne.getDescriptionText();
					if(description == null) {
						description = "";
					}
					
					User user = mv.getUser();
					
					EType etype= EType.DATE_SINGLEUSER;
					if(ne.getType().getValue().toString().equals("One date for all users")) {
						etype = EType.DATE_MULTIUSER;
					}
					
					//Create Event with EventName, Organiser and Type
					Event e = new Event(name, user, etype);
					nec.createEvent(e);
					
					//Set Description
					e.setDescription(description);
					boolean updateCheck = ec.updateEvent(e);
					
					//Add the EventDates
					Collection<Date> startdates = ne.getStartDates();
					Collection<Date> enddates = ne.getEndDates();
					Collection<Eventdate> dates = new ArrayList<Eventdate>();
					Iterator<Date> it1 = startdates.iterator();
					Iterator<Date> it2 = enddates.iterator();
					while(it1.hasNext() && it2.hasNext()) {
						dates.add(new Eventdate(it1.next(), it2.next(), e));
					}
					e.setEventdates(dates);
					dateCheck = ec.updateEvent(e);
					e = ec.getEventById(e.getID());
					if(dateCheck && updateCheck) {
						//Add the Participants
						Collection<User> userList =  ne.getUsers(); 
						Iterator<User> it = userList.iterator();
						while(it.hasNext()) {
							boolean s = ec.addUser(it.next(), e);
							if(!s) {
								userCheck = s;
							}
						}
						
						if(userCheck) {
							//Close the NewEvent Form
							mv.viewEvents();
						}
						else {
							ne.setError();
						}
						
					}
					else {
						ne.setError();
					}
				}
				
			}
		}
		
	}

}
