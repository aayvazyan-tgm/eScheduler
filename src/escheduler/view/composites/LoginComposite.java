package escheduler.view.composites;

import com.vaadin.server.UserError;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import escheduler.controller.listener.LoginListener;
import escheduler.view.MainView;


/**
 * Custom Vaadin Component that shows a simple login form
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
@SuppressWarnings("serial")
public class LoginComposite extends CustomComponent {

	private AbsoluteLayout mainLayout;
	private Button submit;
	private PasswordField password;
	private TextField user;
	private Label heading;
	private Label error;
	private MainView mv;
	
	
	/**
	 * Constructor of LoginComposite
	 */
	public LoginComposite(MainView mv) {
		this.mv=mv;
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
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
		heading.setValue("Login");
		mainLayout.addComponent(heading, "top:0.0px;left:0.0px;");
		
		// user
		user = new TextField();
		user.setCaption("Username");
		user.setImmediate(false);
		user.setWidth("-1px");
		user.setHeight("-1px");
		mainLayout.addComponent(user, "top:46.0px;left:0.0px;");
		
		// password
		password = new PasswordField();
		password.setCaption("Password");
		password.setImmediate(false);
		password.setWidth("-1px");
		password.setHeight("-1px");
		mainLayout.addComponent(password, "top:89.0px;left:0.0px;");
		
		// submit
		submit = new Button();
		submit.setCaption("Submit Login");
		submit.setImmediate(false);
		submit.setWidth("-1px");
		submit.setHeight("-1px");
		submit.addClickListener(new LoginListener(mv));
		mainLayout.addComponent(submit, "top:120.0px;left:40.0px;");
		
		return mainLayout;
	}

	/**
	 * Gets the submit.
	 *
	 * @return the submit
	 */
	public Button getSubmit() {
		return submit;
	}


	/**
	 * Sets the submit.
	 *
	 * @param submit the new submit
	 */
	public void setSubmit(Button submit) {
		this.submit = submit;
	}


	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public PasswordField getPassword() {
		return password;
	}


	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(PasswordField password) {
		this.password = password;
	}


	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public TextField getUser() {
		return user;
	}


	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(TextField user) {
		this.user = user;
	}


	/**
	 * Gets the heading.
	 *
	 * @return the heading
	 */
	public Label getHeading() {
		return heading;
	}


	/**
	 * Sets the heading.
	 *
	 * @param heading the new heading
	 */
	public void setHeading(Label heading) {
		this.heading = heading;
	}


	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public Label getError() {
		return error;
	}


	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(String error) {
		submit.setComponentError(new UserError(error));
	}


	/**
	 * Gets the mv.
	 *
	 * @return the mv
	 */
	public MainView getMv() {
		return mv;
	}


	/**
	 * Sets the mv.
	 *
	 * @param mv the new mv
	 */
	public void setMv(MainView mv) {
		this.mv = mv;
	}

}
