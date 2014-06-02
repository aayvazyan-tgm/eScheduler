package escheduler.controller.listener;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;

import escheduler.controller.EventsController;
import escheduler.view.MainView;
import escheduler.view.composites.LoggedInComposite;

public class EventSelectListener implements ValueChangeListener{

	private MainView mv;
	private EventsController ec;
	
	public EventSelectListener(MainView mv) {
		this.mv = mv;
		ec = new EventsController();
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		Long id = (Long) event.getProperty().getValue();
		escheduler.model.Event e = ec.getEventById(id);
		((LoggedInComposite)mv.getContent()).getEvents().getEventDetail().loadDetail(e);
	}

}
