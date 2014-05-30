package escheduler.Test.Controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestInvitesController.class,
		TestLoginAndRegisterController.class, TestLoginController.class,
		TestNewEventController.class, TestNotificationsController.class,
		TestSessionManager.class })
public class AllTests {

}
