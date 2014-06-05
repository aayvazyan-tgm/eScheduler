/**
 * 
 */
package escheduler.controller.listener;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import escheduler.view.MainView;
import escheduler.view.composites.EventDetailComposite;
import escheduler.view.composites.LoggedInComposite;

/**
 * @author ffreu_000
 * @version Jun 5, 2014
 *
 */
public class RemoveCommentListener implements ClickListener {

	private MainView mv;
	private Long id;
	
	public RemoveCommentListener(MainView mv, Long id) {
		this.mv = mv;
	}
	
	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		Button b = event.getButton();
		String cap = b.getCaption();
		if(cap.equals("X")) {
			EventDetailComposite ed = ((LoggedInComposite)mv.getContent()).getEvents().getEventDetail();
			ed.deleteComment(id);
		}
	}

}
