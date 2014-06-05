package escheduler.view.composites;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.vaadin.server.UserError;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

import escheduler.controller.EventsController;
import escheduler.controller.listener.RemoveUserListener;
import escheduler.controller.listener.VoteListener;
import escheduler.model.Comment;
import escheduler.model.Eventdate;
import escheduler.model.Participant;
import escheduler.model.User;
import escheduler.view.MainView;

/**
 * Custom Vaadin Component that displays the details (type, organiser, description, participants)
 * of an event as well as a voting area for the dates and the comments for the chosen event
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
@SuppressWarnings("serial")
public class EventDetailComposite extends CustomComponent {
	private EventsController ec;
	private VerticalLayout mainLayout;
	private VerticalSplitPanel splitPanel;
	private TabSheet detailTabs;
	private Panel commentPanel;
	private VerticalLayout commentLayout;
	private ListSelect participantList;
	private OptionGroup dateVote;
	private Panel topPanel;
	private Panel participantPanel;
	private Panel datePanel;
	private AbsoluteLayout dateLayout;
	private Button dateButton;
	private AbsoluteLayout participantLayout;
	private Button removeUser;
	private VerticalLayout topLayout;
	private Label description;
	private Label organiser;
	private Label type;
	private Label eventName;
	private escheduler.model.Event e;
	private MainView mv;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public EventDetailComposite(MainView mv) {
		this.mv = mv;
		ec = new EventsController();
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	
	/**
	 * Builds the main layout.
	 *
	 * @return the vertical layout
	 */
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// verticalSplitPanel_1
		splitPanel = buildVerticalSplitPanel_1();
		mainLayout.addComponent(splitPanel);
		
		return mainLayout;
	}

	
	/**
	 * Builds the vertical split panel_1.
	 *
	 * @return the vertical split panel
	 */
	private VerticalSplitPanel buildVerticalSplitPanel_1() {
		// common part: create layout
		splitPanel = new VerticalSplitPanel();
		splitPanel.setImmediate(false);
		splitPanel.setWidth("100.0%");
		splitPanel.setHeight("100.0%");
		
		// topPanel
		topPanel = buildTopPanel();
		splitPanel.addComponent(topPanel);
		
		// detailTabs
		detailTabs = buildDetailTabs();
		splitPanel.addComponent(detailTabs);
		
		return splitPanel;
	}

	
	/**
	 * Builds the top panel.
	 *
	 * @return the panel
	 */
	private Panel buildTopPanel() {
		// common part: create layout
		topPanel = new Panel();
		topPanel.setImmediate(false);
		topPanel.setWidth("100.0%");
		topPanel.setHeight("100.0%");
		
		// topLayout
		topLayout = buildTopLayout();
		topPanel.setContent(topLayout);
		
		return topPanel;
	}

	
	/**
	 * Builds the top layout.
	 *
	 * @return the vertical layout
	 */
	private VerticalLayout buildTopLayout() {
		// common part: create layout
		topLayout = new VerticalLayout();
		topLayout.setImmediate(false);
		topLayout.setWidth("100.0%");
		topLayout.setHeight("100.0%");
		topLayout.setMargin(false);
		
		// eventName
		eventName = new Label();
		eventName.setCaption("Eventname:");
		eventName.setStyleName("h1");
		eventName.setImmediate(false);
		eventName.setWidth("-1px");
		eventName.setHeight("31px");
		topLayout.addComponent(eventName);
		
		// type
		type = new Label();
		type.setStyleName("light");
		type.setCaption("Type:");
		type.setImmediate(false);
		type.setWidth("-1px");
		type.setHeight("-1px");
		topLayout.addComponent(type);
		
		// organiser
		organiser = new Label();
		organiser.setStyleName("light");
		organiser.setCaption("Organiser:");
		organiser.setImmediate(false);
		organiser.setWidth("-1px");
		organiser.setHeight("-1px");
		topLayout.addComponent(organiser);
		
		// description
		description = new Label();
		description.setCaption("Description:");
		description.setStyleName("light");
		description.setCaption("Description");
		description.setImmediate(false);
		description.setWidth("100.0%");
		description.setHeight("-1px");
		topLayout.addComponent(description);
		
		return topLayout;
	}

	
	/**
	 * Builds the detail tabs.
	 *
	 * @return the tab sheet
	 */
	private TabSheet buildDetailTabs() {
		// common part: create layout
		detailTabs = new TabSheet();
		detailTabs.setImmediate(true);
		detailTabs.setWidth("100.0%");
		detailTabs.setHeight("100.0%");
		
		//dateLayout
		dateLayout = new AbsoluteLayout();
		dateLayout.setImmediate(false);
		dateLayout.setSizeFull();
		
		//datePanel
		datePanel = new Panel();
		datePanel.setImmediate(false);
		datePanel.setSizeFull();
		datePanel.setContent(dateLayout);
		
		// dateVote
		dateVote = new OptionGroup();
		dateVote.setImmediate(false);
		dateVote.setWidth("100.0%");
		dateVote.setHeight("80.0%");
		dateLayout.addComponent(dateVote, "top:0.0px;left:0.0px;right:0.0px;");
		
		//dateButton
		dateButton = new Button();
		dateButton.setImmediate(false);
		dateButton.setHeight("-1px");
		dateButton.setWidth("-1px");
		dateButton.addClickListener(new VoteListener(mv));
		dateLayout.addComponent(dateButton, "top:85.0%;right:6.0px");
		
		detailTabs.addTab(datePanel, "Date", null);
		
		//participantLayout
		participantLayout = new AbsoluteLayout();
		participantLayout.setSizeFull();
		participantLayout.setImmediate(false);
		
		//participantPanel
		participantPanel = new Panel();
		participantPanel.setImmediate(false);
		participantPanel.setSizeFull();
		participantPanel.setContent(participantLayout);

		// participantList
		participantList = new ListSelect();
		participantList.setImmediate(false);
		participantList.setWidth("100.0%");
		participantList.setHeight("80.0%");
		participantLayout.addComponent(participantList, "top:0.0px;left:0.0px;right:0.0px;");
		
		//removeUser
		removeUser = new Button();
		removeUser.setCaption("Remove User");
		removeUser.setImmediate(false);
		removeUser.setWidth("-1px");
		removeUser.setHeight("-1px");
		removeUser.addClickListener(new RemoveUserListener(mv));
		participantLayout.addComponent(removeUser, "top:85%;right:6.0px;");
		
		
		detailTabs.addTab(participantPanel, "Participants", null);
		
		// commentPanel
		commentPanel = buildCommentPanel();
		detailTabs.addTab(commentPanel, "Comments", null);
		
		return detailTabs;
	}

	
	/**
	 * Builds the comment panel.
	 *
	 * @return the panel
	 */
	private Panel buildCommentPanel() {
		// common part: create layout
		commentPanel = new Panel();
		commentPanel.setImmediate(false);
		commentPanel.setWidth("100.0%");
		commentPanel.setHeight("100.0%");
		
		// commentLayout
		commentLayout = new VerticalLayout();
		commentLayout.setImmediate(false);
		commentLayout.setWidth("100.0%");
		commentLayout.setHeight("100.0%");
		commentLayout.setMargin(false);
		commentPanel.setContent(commentLayout);
		commentPanel.setSizeFull();
		
		return commentPanel;
	}
	
	/**
	 * Gets the participant list.
	 *
	 * @return the participant list
	 */
	public ListSelect getParticipantList() {
		return participantList;
	}


	/**
	 * Sets the participant list.
	 *
	 * @param participantList the new participant list
	 */
	public void setParticipantList(ListSelect participantList) {
		this.participantList = participantList;
	}


	/**
	 * Gets the date vote.
	 *
	 * @return the date vote
	 */
	public OptionGroup getDateVote() {
		return dateVote;
	}


	/**
	 * Sets the date vote.
	 *
	 * @param dateVote the new date vote
	 */
	public void setDateVote(OptionGroup dateVote) {
		this.dateVote = dateVote;
	}


	/**
	 * Gets the description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description.getValue();
	}


	/**
	 * Sets the description
	 * 
	 * @param description the description
	 */
	public void setDescription(String description) {
		this.description.setValue(description);
	}


	/**
	 * Gets the organiser.
	 *
	 * @return the organiser
	 */
	public String getOrganiser() {
		return organiser.getValue();
	}


	/**
	 * Sets the organiser.
	 *
	 * @param organiser the new organiser
	 */
	public void setOrganiser(String organiser) {
		this.organiser.setValue(organiser);
	}


	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Label getType() {
		return type;
	}


	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type.setValue(type);
	}


	/**
	 * Gets the event name.
	 *
	 * @return the event name
	 */
	public String getEventName() {
		return eventName.getValue();
	}


	/**
	 * Sets the event name.
	 *
	 * @param eventName the new event name
	 */
	public void setEventName(String eventName) {
		this.eventName.setValue(eventName);
	}


	/**
	 * Adds the participant.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public boolean addParticipant(String name) {
		participantList.addItem(name);
		return true;
	}
	
	/**
	 * Adds the date.
	 *
	 * @param date the date
	 * @return true, if successful
	 */
	public boolean addDate(String date) {
		dateVote.addItem(date);
		return true;
	}
	
	/**
	 * Removes the date.
	 *
	 * @param date the date
	 * @return true, if successful
	 */
	public boolean removeDate(String date) {
		dateVote.removeItem(date);
		return true;
	}
	
	/**
	 * Load detail.
	 *
	 * @param e the e
	 * @return true, if successful
	 */
	public boolean loadDetail(escheduler.model.Event e) {
		if(e.getOrganisator().getUsername().equals(mv.getUser().getUsername())) {
			dateButton.setCaption("Pick Date");
		}
		else {
			dateButton.setCaption("Vote");
		}
		
		this.e = e;
		setEventName(e.getName());
		setDescription(e.getDescription());
		setOrganiser(e.getOrganisator().getUsername());
		
		setType(e.getType().name());
		
		participantList.removeAllItems();
		Collection<Participant> pa = e.getParticipants();
		Iterator<Participant> itpa = pa.iterator();
		while(itpa.hasNext()) {
			Participant p = itpa.next();
			String text = p.getUser().getUsername();
			if(p.isStatus()) {
				text += " (Accepted)";
			}
			else {
				text += " (Invited)";
			}
			participantList.addItem(p.getUser());
			participantList.setItemCaption(p.getUser(), text);
		}
		
		dateVote.removeAllItems();
		Collection<Eventdate> coled = e.getEventdates();
		Iterator<Eventdate> ited = coled.iterator();
		while(ited.hasNext()) {
			Eventdate ed = ited.next();
			Date start = ed.getStart();
			Date end = ed.getEnd();
			String dateText = ""+new SimpleDateFormat("dd/MM/yyyy").format(start)+" to "+new SimpleDateFormat("dd/MM/yyyy").format(end);
			dateVote.addItem(ed);
			dateVote.setItemCaption(ed, dateText);
		}
		return true;
	}


	/**
	 * 
	 */
	public void removeSelectedUser() {
		if(mv.getUser().getUsername().equals(e.getOrganisator().getUsername())) {
			Collection<Participant> colP = e.getParticipants();
			Iterator<Participant> it = colP.iterator();
			boolean accepted = false;
			while(it.hasNext()) {
				if(it.next().isStatus()) {
					accepted = true;
				}
			}
				
			if(!accepted) {
				User u = (User) participantList.getValue();
				ec.removeUser(u, e);
				participantList.removeItem(u);
			}
			else {
				removeUser.setComponentError(new UserError("At least one user has already accepted the Invitation"));
			}
		}
		else {
			removeUser.setComponentError(new UserError("Only the Organiser can do that"));
		}
	}


	/**
	 * 
	 */
	public void vote() {
		if(dateVote.getValue()!=null) {
			ec.Vote(e, mv.getUser(), (Eventdate)dateVote.getValue());
		}
		else {
			dateButton.setComponentError(new UserError("You have to select a Date"));
		}
	}


	/**
	 * 
	 */
	public void pickDate() {
		if(dateVote.getValue()!=null) {
			Collection<Participant> colPa = e.getParticipants();
			Iterator<Participant> itPa = colPa.iterator();
			boolean allVoted = true;
			while(itPa.hasNext()) {
				if(itPa.next().getEventdate()==null) {
					allVoted = false;
				}
			}
			if(allVoted) {
				ec.fixEvent(e, (Eventdate)dateVote.getValue());
			}
			else {
				dateButton.setComponentError(new UserError("Some Users haven't voted yet"));
			}
			
		}
		else {
			dateButton.setComponentError(new UserError("You have to select a Date"));
		}
		
	}
		

}
