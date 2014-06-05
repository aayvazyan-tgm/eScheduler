/*
 * 
 */
package escheduler.view.composites;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

import escheduler.controller.listener.NotificationReadListener;
import escheduler.model.Notification;
import escheduler.view.MainView;

/**
 * Custom Vaadin Component that displays a list of all notifications a user has ever received,
 * seperating between those that have been read and those that haven't.
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
@SuppressWarnings("serial")
public class NotificationComposite extends CustomComponent {

	private AbsoluteLayout mainLayout;
	 
	private Table notificationTable;
	 
	private Label heading;
	private MainView mv;
	/**
	 * Constructor of NotificationComposite
	 */
	public NotificationComposite(MainView mv) {
		this.mv=mv;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	 
	/**
	 * Builds the main layout.
	 *
	 * @return the absolute layout
	 */
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// heading
		heading = new Label();
		heading.setStyleName("v-label-h1");
		heading.setImmediate(false);
		heading.setWidth("-1px");
		heading.setHeight("-1px");
		heading.setValue("Notifications:");
		mainLayout.addComponent(heading, "top:0.0px;left:0.0px;");
		
		// notificationTable
		notificationTable = new Table();
		notificationTable.setImmediate(false);
		notificationTable.setWidth("100.0%");
		notificationTable.setHeight("-1px");
		
		notificationTable.addContainerProperty("Notification", String.class, null);
		notificationTable.addContainerProperty("Read", Button.class, null);
		
		mainLayout.addComponent(notificationTable, "top:40.0px;right:23.0px;left:0.0px;");
		
		return mainLayout;
	}
	
	/**
	 * Adds the notification.
	 *
	 * @param notification the notification
	 */
	public void addNotification(Notification notification) {
		Button x = new Button("X");
		x.addClickListener(new NotificationReadListener(mv,notification.getID()));
		notificationTable.addItem(new Object[] {notification.getDescription(), x}, notification.getID());
	}


	/**
	 * Removes the notification.
	 *
	 * @param id the id
	 */
	public void removeNotification(Long id) {
		notificationTable.removeItem(id);
	}

}
