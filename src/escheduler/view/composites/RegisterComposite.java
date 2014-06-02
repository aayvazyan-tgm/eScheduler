package escheduler.view.composites;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import escheduler.controller.listener.RegisterListener;
import escheduler.view.MainView;

/**
 * Custom Vaadin Component that displays a form that is used to register new users
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
public class RegisterComposite extends CustomComponent {

	private AbsoluteLayout mainLayout;
	
	private Label error;
	
	private Button register;
	
	private PasswordField pass2;
	
	private PasswordField pass1;
	
	private TextField user;
	
	private Label heading;
	private MainView mv;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 * @param mainView 
	 */
	public RegisterComposite(MainView mv) {
		this.mv=mv;
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	
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
		heading.setValue("Register");
		mainLayout.addComponent(heading, "top:0.0px;left:0.0px;");
		
		// user
		user = new TextField();
		user.setCaption("Username:");
		user.setImmediate(false);
		user.setWidth("-1px");
		user.setHeight("-1px");
		mainLayout.addComponent(user, "top:44.0px;left:0.0px;");
		
		// pass1
		pass1 = new PasswordField();
		pass1.setCaption("Password:");
		pass1.setImmediate(false);
		pass1.setWidth("-1px");
		pass1.setHeight("-1px");
		mainLayout.addComponent(pass1, "top:82.0px;left:0.0px;");
		
		// pass2
		pass2 = new PasswordField();
		pass2.setCaption("Confirm Password:");
		pass2.setImmediate(false);
		pass2.setWidth("-1px");
		pass2.setHeight("-1px");
		mainLayout.addComponent(pass2, "top:121.0px;left:0.0px;");
		
		// register
		register = new Button();
		register.setCaption("Submit Registration");
		register.setImmediate(true);
		register.setWidth("-1px");
		register.setHeight("-1px");
		register.addClickListener(new RegisterListener(mv));
		mainLayout.addComponent(register, "top:152.0px;left:70.0px;");
		
		// error
		error = new Label();
		error.setImmediate(false);
		error.setWidth("-1px");
		error.setHeight("-1px");
		mainLayout.addComponent(error, "top:182.0px;left:0.0px;");
		
		return mainLayout;
	}


	public String getError() {
		return error.getValue();
	}


	public void setError(String error) {
		this.error.setValue(error);
	}


	public Button getRegister() {
		return register;
	}


	public void setRegister(Button register) {
		this.register = register;
	}


	public PasswordField getPass2() {
		return pass2;
	}


	public void setPass2(PasswordField pass2) {
		this.pass2 = pass2;
	}


	public PasswordField getPass1() {
		return pass1;
	}


	public void setPass1(PasswordField pass1) {
		this.pass1 = pass1;
	}


	public TextField getUser() {
		return user;
	}


	public void setUser(TextField user) {
		this.user = user;
	}


	public String getHeading() {
		return heading.getValue();
	}


	public void setHeading(String heading) {
		this.heading.setValue(heading);
	}

}
