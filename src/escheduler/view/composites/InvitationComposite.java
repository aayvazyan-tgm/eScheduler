package escheduler.view.composites;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

import escheduler.controller.InvitesController;
import escheduler.controller.listener.InvitationListener;
import escheduler.model.Eventdate;
import escheduler.model.Participant;
import escheduler.view.MainView;

/**
 * Custom Vaadin Component that displays a list of all invitations a user hasn't accepted or declined yet
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
public class InvitationComposite extends CustomComponent {

	int iid=0;

	private AbsoluteLayout mainLayout;

	private Table inviteTable;
	
	private InvitesController ic;

	private Label heading;
	private MainView mv;
	/**
	 * Constructor of InvitationComposite
	 */
	public InvitationComposite(MainView mv) {
		ic = new InvitesController();
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
		
		loadInvitations();
		
		mainLayout.addComponent(inviteTable,
				"top:40.0px;right:26.0px;left:0.0px;");
		
		return mainLayout;
	}
	
	/**
	 * Gets the iid.
	 *
	 * @return the iid
	 */
	public int getIid() {
		return iid;
	}

	/**
	 * Sets the iid.
	 *
	 * @param id the new iid
	 */
	public void setIid(int id) {
		this.iid = id;
	}

	/**
	 * Gets the invite table.
	 *
	 * @return the invite table
	 */
	public Table getInviteTable() {
		return inviteTable;
	}

	/**
	 * Sets the invite table.
	 *
	 * @param inviteTable the new invite table
	 */
	public void setInviteTable(Table inviteTable) {
		this.inviteTable = inviteTable;
	}

	/**
	 * Adds the invitation.
	 *
	 * @param event the event
	 * @param description the description
	 * @param organiser the organiser
	 * @param participating the participating
	 * @param date the date
	 * @param id the id
	 */
	public void addInvitation(String event, String description, String organiser, Integer participating, String date, Long id) {
		Button a=new Button("A");
		a.addClickListener(new InvitationListener(mv,id));
		Button d=new Button("D");
		d.addClickListener(new InvitationListener(mv,id));
		inviteTable.addItem(new Object[] {event,description,organiser,participating,date,a,d},id);
	}

	public void loadInvitations() {
		List<escheduler.model.Event> events = ic.getInvites(mv.getUser());
		System.out.println("");
		System.out.println(""+events.size());
		System.out.println("");
		Iterator<escheduler.model.Event> it = events.iterator();
		while(it.hasNext()) {
			escheduler.model.Event e = it.next();
			String name = e.getName();
			String description = e.getDescription();
			String organiser = e.getOrganisator().getUsername();
			
			//Counts the Users that have accepted the Invitation
			Integer accepted = 0;
			Collection<Participant> p = e.getParticipants();
			Iterator<Participant> itP = p.iterator();
			while(itP.hasNext()) {
				if(itP.next().isStatus()) {
					accepted++;
				}
			}
			
			//Checks if the Date has bin set yet
			String dateText;
			if(e.getEventdates().size()==1) {
				Iterator<Eventdate> itDate = e.getEventdates().iterator();
				Eventdate ed = itDate.next();
				Date start = ed.getStart();
				Date end = ed.getEnd();
				dateText = ""+new SimpleDateFormat("dd/MM/yyyy").format(start)+" to "+new SimpleDateFormat("dd/MM/yyyy").format(end);
			}
			else {
				dateText = "Date not yet set";
			}
			
			Long id = e.getID();
			
			addInvitation(name,description,organiser,accepted,dateText,id);
		}
	}
	
	/**
	 * Removes the invitation.
	 *
	 * @param id the id
	 */
	public void removeInvitation(Long id) {
		inviteTable.removeItem(id);
	}

}
