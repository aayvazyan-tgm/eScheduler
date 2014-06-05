/**
 * 
 */
package escheduler.controller.listener;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import escheduler.view.MainView;
import escheduler.view.composites.LoggedInComposite;

/**
 * @author ffreu_000
 * @version Jun 5, 2014
 *
 */
public class AddCommentListener implements ClickListener {

	private MainView mv;
	
	public AddCommentListener(MainView mv) {
		this.mv = mv;
	}
	
	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		((LoggedInComposite)mv.getContent()).getEvents().getEventDetail().addComment();
	}

}
