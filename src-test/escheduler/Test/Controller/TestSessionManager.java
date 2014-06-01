package escheduler.Test.Controller;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

import escheduler.controller.SessionManager;
import escheduler.view.IViewElement;

/**
 * Tests the Session Manager
 * @author Ari Ayvazyan
 * @version 29.05.2014
 */
public class TestSessionManager {
	
	@Test
	public void testInstanceEquality() {
		SessionManager sm=SessionManager.getInstance();
		SessionManager sm2=SessionManager.getInstance();
		assertEquals(sm, sm2);
	}

	@Test
	public void testListenerFunctionality(){
		SessionManager sm=SessionManager.getInstance();
		TestIView ti=new TestIView();
		TestIView ti2=new TestIView();
		TestIView ti3=new TestIView();
		sm.addListener(ti);
		sm.addListener(ti2);
		sm.notifyListeners();
		assertTrue(ti.updated);
		assertTrue(ti2.updated);
		sm.addListener(ti3);
		sm.removeListener(ti3);
		assertFalse(ti3.updated);
	}
	
	@Test
	public void testHibernateSession(){
		Session s1=SessionManager.getInstance().getHibernateSession();
		assertTrue(s1.isOpen());
	}
}
	

class TestIView implements IViewElement{
	public boolean updated=false;
	@Override
	public void update() {
		this.updated=true;
	}
	
}