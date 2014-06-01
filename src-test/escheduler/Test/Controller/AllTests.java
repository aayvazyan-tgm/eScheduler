/**
 * 
 */
package escheduler.Test.Controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Ari
 * @version 01.06.2014
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestControllerProxy.class, TestEventsController.class,
		TestInvitesController.class, TestLoginController.class,
		TestNewEventController.class, TestNotificationsController.class,
		TestRegisterController.class, TestSessionManager.class })
public class AllTests {

}
