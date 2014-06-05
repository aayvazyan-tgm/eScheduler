package escheduler.view.composites;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.vaadin.data.Item;
import com.vaadin.server.UserError;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import escheduler.controller.EventsController;
import escheduler.controller.listener.AddDateListener;
import escheduler.controller.listener.AddUserListener;
import escheduler.controller.listener.FormRemoveListener;
import escheduler.controller.listener.NewEventListener;
import escheduler.model.EType;
import escheduler.model.Eventdate;
import escheduler.model.User;
import escheduler.view.MainView;

// TODO: Auto-generated Javadoc
/**
 * Custom Vaadin Component that displays a form that is used to create a new events.
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
@SuppressWarnings("serial")
public class NewEventComposite extends CustomComponent {

	/** The main layout. */
	private AbsoluteLayout mainLayout;
	
	/** The create. */
	private Button create;
	
	/** The user list. */
	private ListSelect userList;
	
	/** The user picker. */
	private TextField userPicker;
	
	/** The add user. */
	private Button addUser;
	
	/** The type. */
	private OptionGroup type;
	
	/** The date picker1. */
	private PopupDateField datePicker1;
	
	/** The date picker2. */
	private PopupDateField datePicker2;
	
	/** The add date. */
	private Button addDate;
	
	/** The remove date. */
	private Button removeDate;
	
	/** The remove user. */
	private Button removeUser;
	
	/** The date list. */
	private ComboBox dateList;
	
	/** The description. */
	private TextArea description;
	
	/** The event name. */
	private TextField eventName;
	
	/** The users. */
	private Collection<User> users;

	/** The startdates. */
	private ArrayList<Date> startdates;
	
	/** The enddates. */
	private ArrayList<Date> enddates;
	
	/** The mv. */
	private MainView mv;
	
	/** The ec. */
	private EventsController ec;
	
	/** The date id. */
	private int dateID;
	
	/** The user id. */
	private int userID;
	
	/**
	 * Constructor of NewEventComposite.
	 *
	 * @param mv the mv
	 */
	public NewEventComposite(MainView mv) {
		this.mv = mv;
		ec = new EventsController();
		users = new ArrayList<User>();
		startdates = new ArrayList<Date>();
		enddates = new ArrayList<Date>();
		dateID = 0;
		userID = 0;
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
		
		// eventName
		eventName = new TextField();
		eventName.setCaption("Eventname:");
		eventName.setImmediate(false);
		eventName.setWidth("300px");
		eventName.setHeight("-1px");
		mainLayout.addComponent(eventName, "top:20.0px;left:0.0px;");
		
		// description
		description = new TextArea();
		description.setCaption("Description(Optional):");
		description.setImmediate(false);
		description.setWidth("301px");
		description.setHeight("-1px");
		mainLayout.addComponent(description, "top:68.0px;left:0.0px;");
		
		// datePicker1
		datePicker1 = new PopupDateField();
		datePicker1.setImmediate(false);
		datePicker1.setWidth("-1px");
		datePicker1.setHeight("-1px");
		mainLayout.addComponent(datePicker1, "top:172.0px;left:0.0px;");
		
		//datePicker2
		datePicker2 = new PopupDateField();
		datePicker2.setImmediate(false);
		datePicker2.setWidth("-1px");
		datePicker2.setHeight("-1px");
		mainLayout.addComponent(datePicker2, "top:172.0px;left:160.0px;");
		
		//addDate
		addDate = new Button();
		addDate.setCaption("Add Date");
		addDate.setImmediate(false);
		addDate.setWidth("-1px");
		addDate.setHeight("-1px");
		addDate.addClickListener(new AddDateListener(mv));
		mainLayout.addComponent(addDate, "top:172.0px;left:320.0px;");
		
		// dateList
		dateList = new ComboBox();
		dateList.setCaption("Dates:");
		dateList.setImmediate(false);
		dateList.setWidth("140px");
		dateList.setHeight("-1px");
		mainLayout.addComponent(dateList, "top:224.0px;left:0.0px;");
		
		//removeDate
		removeDate = new Button();
		removeDate.setCaption("Remove Date");
		removeDate.setImmediate(false);
		removeDate.setWidth("-1px");
		removeDate.setHeight("-1px");
		removeDate.addClickListener(new FormRemoveListener(mv));
		mainLayout.addComponent(removeDate, "top:224.0px;left:160.0px;");
		
		// type
		type = new OptionGroup();
		type.setCaption("Type:");
		type.setImmediate(false);
		type.setWidth("300px");
		type.setHeight("40px");
		type.addItem("One date for all users");
		type.addItem("One date per user");
		type.select("One date for all users");
		mainLayout.addComponent(type, "top:276.0px;left:0.0px;");
		
		// userPicker
		userPicker = new TextField();
		userPicker.setCaption("Users:");
		userPicker.setImmediate(false);
		userPicker.setWidth("300px");
		userPicker.setHeight("-1px");
		mainLayout.addComponent(userPicker, "top:339.0px;left:0.0px;");
		
		//addUser
		addUser = new Button();
		addUser.setCaption("Add User");
		addUser.setImmediate(false);
		addUser.setWidth("-1px");
		addUser.setHeight("-1px");
		addUser.addClickListener(new AddUserListener(mv));
		mainLayout.addComponent(addUser, "top:339.0px;left:320px;");
		
		// listSelect_1
		userList = new ListSelect();
		userList.setImmediate(false);
		userList.setWidth("301px");
		userList.setHeight("-1px");
		mainLayout.addComponent(userList, "top:372.0px;left:0.0px;");
		
		removeUser = new Button();
		removeUser.setCaption("Remove User");
		removeUser.setImmediate(false);
		removeUser.setWidth("-1px");
		removeUser.setHeight("-1px");
		removeUser.addClickListener(new FormRemoveListener(mv));
		mainLayout.addComponent(removeUser, "top:372.0px;left:320.0px;");
		
		// create
		create = new Button();
		create.setCaption("Create Event");
		create.setImmediate(false);
		create.setWidth("-1px");
		create.setHeight("-1px");
		create.addClickListener(new NewEventListener(mv));
		mainLayout.addComponent(create, "top:575.0px;left:320.0px;");
		
		return mainLayout;
	}


	/**
	 * Gets the create
	 *
	 * @return the create
	 */
	public Button getCreate() {
		return create;
	}


	/**
	 * Sets the create
	 *
	 * @param create the new create
	 */
	public void setCreate(Button create) {
		this.create = create;
	}


	/**
	 * Gets the user picker.
	 *
	 * @return the user picker
	 */
	public TextField getUserPicker() {
		return userPicker;
	}


	/**
	 * Sets the user picker.
	 *
	 * @param userPicker the new user picker
	 */
	public void setUserPicker(TextField userPicker) {
		this.userPicker = userPicker;
	}


	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public OptionGroup getType() {
		return type;
	}


	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(OptionGroup type) {
		this.type = type;
	}


	/**
	 * Gets the date picker.
	 *
	 * @return the date picker
	 */
	public PopupDateField getDatePicker() {
		return datePicker1;
	}


	/**
	 * Sets the date picker.
	 *
	 * @param datePicker the new date picker
	 */
	public void setDatePicker(PopupDateField datePicker) {
		this.datePicker1 = datePicker;
	}


	/**
	 * Gets the date list.
	 *
	 * @return the date list
	 */
	public ComboBox getDateList() {
		return dateList;
	}


	/**
	 * Sets the date list.
	 *
	 * @param dateList the new date list
	 */
	public void setDateList(ComboBox dateList) {
		this.dateList = dateList;
	}


	/**
	 * Gets the description text.
	 *
	 * @return the description text
	 */
	public String getDescriptionText() {
		return description.getValue().toString();
	}


	/**
	 * Sets the description text.
	 *
	 * @param description the new description text
	 */
	public void setDescriptionText(String description) {
		this.description.setValue(description);
	}


	/**
	 * Gets the event name.
	 *
	 * @return the event name
	 */
	public TextField getEventName() {
		return eventName;
	}


	/**
	 * Sets the event name.
	 *
	 * @param eventName the new event name
	 */
	public void setEventName(TextField eventName) {
		this.eventName = eventName;
	}


	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public Collection<User> getUsers() {
		return users;
	}


	/**
	 * Gets the start dates.
	 *
	 * @return the start dates
	 */
	public Collection<Date> getStartDates() {
		return startdates;
	}
	
	/**
	 * Gets the end dates.
	 *
	 * @return the end dates
	 */
	public Collection<Date> getEndDates() {
		return startdates;
	}

	/**
	 * Adds the user.
	 *
	 * @return true, if successful
	 */
	public boolean addUser() {
		String userName = userPicker.getValue();
		User user = ec.searchUser(userName);
		
		if(user==null) {
			setUserError("User doesn't exist");
			return false;
		}
		else {
			boolean alreadyIn = false;
			Iterator<User> it = users.iterator();
			while(it.hasNext()) {
				if(user.getUsername().equals(it.next().getUsername())) {
					alreadyIn = true;
				}
			}
			if(!alreadyIn) {
				setUserError("User already added");
				return false;
			}
			if(user.getUsername().equals(mv.getUser().getUsername())) {
				setUserError("Can't add yourself");
				return false;
			}
			users.add(user);
			userList.addItem(userID);
			userList.setItemCaption(userID, user.getUsername());
			userID++;
			userPicker.setValue(null);
			return true;
		}		
	}


	/**
	 * Sets the user error.
	 */
	public void setUserError() {
		addUser.setComponentError(new UserError("User doesn't exist!"));		
	}
	
	/**
	 * Sets the user error.
	 *
	 * @param error the new user error
	 */
	public void setUserError(String error) {
		addUser.setComponentError(new UserError(error));		
	}
	
	
	
	/**
	 * Sets the date error.
	 */
	public void setDateError() {
		addDate.setComponentError(new UserError("Couldn't add Date"));		
	}
	
	/**
	 * Sets the date error.
	 *
	 * @param error the new date error
	 */
	public void setDateError(String error) {
		addDate.setComponentError(new UserError(error));		
	}


	/**
	 * Adds the date.
	 *
	 * @return true, if successful
	 */
	public boolean addDate() {
		if(datePicker1.getValue()==null || datePicker2.getValue()==null) {
			setDateError("You have to pick a start and end Date");
			return false;
		}
		if(datePicker1.getValue().after(datePicker2.getValue())) {
			setDateError("End Date has to be after Start Date");
			return false;
		}
		if(datePicker1.getValue().before(new Date()) || datePicker1.getValue().before(new Date())) {
			setDateError("You can't pick Dates that already happened");
			return false;
		}
		Date start = datePicker1.getValue();
		Date end = datePicker2.getValue();
		String dateString = ""+new SimpleDateFormat("dd/MM/yyyy").format(start)+" to "+new SimpleDateFormat("dd/MM/yyyy").format(end);
		dateList.addItem(dateID);
		dateList.setItemCaption(dateID, dateString);
		dateID++;
		startdates.add(start);
		enddates.add(end);
		datePicker1.setValue(null);
		datePicker2.setValue(null);
		return true;
	}


	/**
	 * Sets the error.
	 */
	public void setError() {
		create.setComponentError(new UserError("Unable to create Event"));
	}
	
	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(String error) {
		create.setComponentError(new UserError(error));
	}
	
	
	/**
	 * Check values.
	 *
	 * @return true, if successful
	 */
	public boolean checkValues() {
		boolean ret = true;
		String error = "";
		
		if(eventName.getValue()==null || eventName.getValue().equals("") || eventName.getValue().trim().length()<=0) {
			error += "You have to enter an EventName \n";
			ret=false;
		}
		
		if(type.getValue()==null) {
			error += "You have to pick a type \n";
			ret=false;
		}
		else {
			EType etype= EType.DATE_SINGLEUSER;
			if(getType().getValue().toString().equals("One date for all users")) {
				etype = EType.DATE_MULTIUSER;
			}
			if(etype==EType.DATE_MULTIUSER) {
				if(startdates.size()<=1 || enddates.size()<=1) {
					error += "You have to pick at least 2 Dates for Voting \n";
					ret=false;
				}
			}
			else {
				if(startdates.size()<=users.size() || enddates.size()<=users.size()) {
					error += "You have to make at least one Date available per user for this type of event \n";
					ret=false;
				}
			}
		}
		
		if(userList.size()==0) {
			error += "You have to add at least 1 user \n";
			ret=false;
		}
		
		if(!(error.equals(""))) {setError(error);}
		return ret;
	}

	/**
	 * Removes the selected date.
	 */
	public void removeSelectedDate() {
		Integer id = (Integer) dateList.getValue();
		if(id == null) {
			removeDate.setComponentError(new UserError("No Date Selected"));
		}
		dateList.removeItem(id);
		startdates.remove(id);
		enddates.remove(id);
	}
	
	/**
	 * Removes the selected user.
	 */
	public void removeSelectedUser() {
		Integer id = (Integer) userList.getValue();
		if(id == null) {
			removeUser.setComponentError(new UserError("No Date Selected"));
		}
		userList.removeItem(id);
		users.remove(id);
	}

}
