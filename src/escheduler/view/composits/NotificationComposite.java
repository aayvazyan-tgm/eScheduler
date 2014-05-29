package escheduler.view.composits;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

public class NotificationComposite extends CustomComponent {

	int id=0;	
	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Table notificationTable;
	@AutoGenerated
	private Label heading;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public NotificationComposite() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@AutoGenerated
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
		
		mainLayout.addComponent(notificationTable, "top:40.0px;left:0.0px;");
		
		
		//Add Test Data-Sets
		this.addNotification("All Participants have voted for your Event \"Eventname\"");
		this.addNotification("A date has been chosen for \"Event#2\"");
		
		
		return mainLayout;
	}
	
	public void addNotification(String note) {
		notificationTable.addItem(new Object[] {note, new Button("X")}, new Integer(id));
		id++;
	}

}
