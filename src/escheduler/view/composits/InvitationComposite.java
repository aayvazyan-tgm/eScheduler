package escheduler.view.composits;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

public class InvitationComposite extends CustomComponent {

	int id=0;
	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Table inviteTable;
	@AutoGenerated
	private Label heading;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public InvitationComposite() {
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
		heading.setValue("You have been invited to:");
		mainLayout.addComponent(heading, "top:0.0px;left:0.0px;");
		
		// inviteTable
		inviteTable = new Table();
		inviteTable.setImmediate(false);
		inviteTable.setWidth("100.0%");
		inviteTable.setHeight("-1px");
		
		inviteTable.addContainerProperty("Event", String.class, null);
		inviteTable.addContainerProperty("Description", String.class, null);
		inviteTable.addContainerProperty("Organiser", String.class, null);
		inviteTable.addContainerProperty("Participating", Integer.class, null);
		inviteTable.addContainerProperty("Date", String.class, null);
		inviteTable.addContainerProperty("Accept", Button.class, null);
		inviteTable.addContainerProperty("Decline", Button.class, null);
		
		//Add Test Data-Sets
		this.addInvitation("TestEvent","blablabla","Hans Bauer",93,"12-12-12");
		this.addInvitation("TestEvent#2","blablabla","Hugo Bauer",12,"13-12-13");
		
		mainLayout.addComponent(inviteTable,
				"top:40.0px;right:26.0px;left:0.0px;");
		
		return mainLayout;
	}
	
	public void addInvitation(String event, String description, String organiser, Integer participating, String date) {
		inviteTable.addItem(new Object[] {event,description,organiser,participating,date,new Button("A"),new Button("D")},new Integer(id));
		id++;
		
		
	}

}