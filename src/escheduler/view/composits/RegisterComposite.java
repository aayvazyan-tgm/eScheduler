package escheduler.view.composits;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class RegisterComposite extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Label error;
	@AutoGenerated
	private Button register;
	@AutoGenerated
	private PasswordField pass2;
	@AutoGenerated
	private PasswordField pass1;
	@AutoGenerated
	private TextField user;
	@AutoGenerated
	private Label heading;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public RegisterComposite() {
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
		register.setCaption("Register");
		register.setImmediate(true);
		register.setWidth("-1px");
		register.setHeight("-1px");
		mainLayout.addComponent(register, "top:152.0px;left:70.0px;");
		
		// error
		error = new Label();
		error.setImmediate(false);
		error.setWidth("-1px");
		error.setHeight("-1px");
		mainLayout.addComponent(error, "top:182.0px;left:0.0px;");
		
		return mainLayout;
	}

}
