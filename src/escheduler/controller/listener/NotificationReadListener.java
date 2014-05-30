package escheduler.controller.listener;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import escheduler.view.MainView;
import escheduler.view.composites.LoggedInComposite;

public class NotificationReadListener implements ClickListener {

	private MainView mv;
	private int id;

	public NotificationReadListener(MainView mv, int id) {
		this.mv=mv;
		this.id=id;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		((LoggedInComposite) mv.getContent()).getNotifications().removeNotification(id);
	}

}
