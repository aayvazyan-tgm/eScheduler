package escheduler.view.composites;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

import escheduler.controller.listener.LoginListener;
import escheduler.controller.listener.RegisterListener;
import escheduler.view.MainView;

/**
 * Custom Vaadin Component that is displayed for people that aren't logged in.
 * It features a short promotional text and buttons to open LoginComposite or
 * RegisterComposite
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
public class LoggedOutComposite extends CustomComponent {

	private AbsoluteLayout mainLayout;
	 
	private AbsoluteLayout absoluteLayout_2;
	 
	private Label point3;
	 
	private Label point2;
	 
	private Label point1;
	 
	private Label subtitle;
	 
	private Label heading;
	 
	private Button register;
	 
	private Button login;
	private MainView mv;
	/**
	 * Constructor of LoggedOutComposite
	 */
	public LoggedOutComposite(MainView mv) {
		this.mv = mv;
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
		
		// login
		login = new Button();
		login.setCaption("Login");
		login.setImmediate(false);
		login.setWidth("100px");
		login.setHeight("25px");
		login.addClickListener(new LoginListener(mv));
		mainLayout.addComponent(login, "top:12.0px;left:12.0px;");
		
		// register
		register = new Button();
		register.setCaption("Register");
		register.setImmediate(false);
		register.setWidth("100px");
		register.setHeight("25px");
		register.addClickListener(new RegisterListener(mv));
		mainLayout.addComponent(register, "top:12.0px;left:112.0px;");
		
		// absoluteLayout_2
		absoluteLayout_2 = buildAbsoluteLayout_2();
		mainLayout.addComponent(absoluteLayout_2,
				"top:50.0px;right:-2.0px;bottom:0.0px;left:0.0px;");
		
		return mainLayout;
	}

	 
	/**
	 * Builds the absolute layout_2.
	 *
	 * @return the absolute layout
	 */
	private AbsoluteLayout buildAbsoluteLayout_2() {
		// common part: create layout
		absoluteLayout_2 = new AbsoluteLayout();
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("100.0%");
		absoluteLayout_2.setHeight("100.0%");
		
		// heading
		heading = new Label();
		heading.setStyleName("h1");
		heading.setImmediate(false);
		heading.setWidth("-1px");
		heading.setHeight("-1px");
		heading.setValue("eScheduler");
		absoluteLayout_2.addComponent(heading, "top:0.0px;left:0.0px;");
		
		// subtitle
		subtitle = new Label();
		subtitle.setStyleName("h2");
		subtitle.setImmediate(false);
		subtitle.setWidth("-1px");
		subtitle.setHeight("-1px");
		subtitle.setValue("helps you to");
		absoluteLayout_2.addComponent(subtitle, "top:30.0px;left:57.0px;");
		
		// point1
		point1 = new Label();
		point1.setStyleName("h2");
		point1.setImmediate(false);
		point1.setWidth("-1px");
		point1.setHeight("-1px");
		point1.setValue("...never forget an appointment again");
		absoluteLayout_2.addComponent(point1, "top:52.0px;left:96.0px;");
		
		// label_1
		point2 = new Label();
		point2.setStyleName("h2");
		point2.setImmediate(false);
		point2.setWidth("-1px");
		point2.setHeight("-1px");
		point2.setValue("...plan events easily");
		absoluteLayout_2.addComponent(point2, "top:70.0px;left:96.0px;");
		
		// label_2
		point3 = new Label();
		point3.setStyleName("h2");
		point3.setImmediate(false);
		point3.setWidth("-1px");
		point3.setHeight("-1px");
		point3.setValue("...share events with your friends");
		absoluteLayout_2.addComponent(point3, "top:90.0px;left:96.0px;");
		
		return absoluteLayout_2;
	}

}
