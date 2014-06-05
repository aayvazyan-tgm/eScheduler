package escheduler.controller.listener;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

import escheduler.controller.EventsController;
import escheduler.model.Event;
import escheduler.view.MainView;
import escheduler.view.composites.EventComposite;
import escheduler.view.composites.EventDetailComposite;
import escheduler.view.composites.LoggedInComposite;

/**
 * Handles clicks in the Event-List table to show the selected event
 * in the EventDetail tab
 * 
 * @author Freudensprung Fabian
 * @version Jun 5, 2014
 *
 */
@SuppressWarnings("serial")
public class EventSelectListener implements ItemClickListener{

	private MainView mv;
	private EventsController ec;
	
	/**
	 * Constructor of EventSelectListener
	 * 
	 * @param mv MainView containing the Composite
	 */
	public EventSelectListener(MainView mv) {
		this.mv = mv;
		ec = new EventsController();
	}


	/* (non-Javadoc)
	 * @see com.vaadin.event.ItemClickEvent.ItemClickListener#itemClick(com.vaadin.event.ItemClickEvent)
	 */
	@Override
	public void itemClick(ItemClickEvent event) {
		Long id = (Long) event.getItemId();
		Event e = ec.getEventById(id);
		EventDetailComposite edc = ((LoggedInComposite) mv.getContent()).getEvents().getEventDetail();
		edc.loadDetail(e);
	}

}
