package escheduler.view.composites;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Custom Vaadin Component that is used to display User-Comments
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
public class CommentComposite extends CustomComponent {

	private AbsoluteLayout mainLayout;
	private HorizontalLayout commentDetails;
	private Label commentText;
	private Label dateLabel;
	private Label authorLabel;

	public CommentComposite(String author, String date, String comment) {
		buildMainLayout(author, date, comment);
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	private AbsoluteLayout buildMainLayout(String author, String date, String comment) {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100px");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100px");
		
		// commentDetails
		commentDetails = buildCommentDetails(author, date);
		mainLayout.addComponent(commentDetails);
		
		// commentText
		commentText = new Label();
		commentText.setImmediate(false);
		commentText.setWidth("100.0%");
		commentText.setHeight("80.0%");
		commentText.setValue(comment);
		mainLayout.addComponent(commentText);
		
		return mainLayout;
	}

	private HorizontalLayout buildCommentDetails(String author, String date) {
		// common part: create layout
		commentDetails = new HorizontalLayout();
		commentDetails.setImmediate(false);
		commentDetails.setWidth("100.0%");
		commentDetails.setHeight("20.0%");
		commentDetails.setMargin(false);
		
		// authorLabel
		authorLabel = new Label();
		authorLabel.setStyleName("light");
		authorLabel.setImmediate(false);
		authorLabel.setWidth("100%");
		authorLabel.setHeight("100%");
		authorLabel.setValue(author);
		commentDetails.addComponent(authorLabel);
		commentDetails.setComponentAlignment(authorLabel, new Alignment(33));
		
		// dateLabel
		dateLabel = new Label();
		dateLabel.setStyleName("light");
		dateLabel.setImmediate(false);
		dateLabel.setWidth("100%");
		dateLabel.setHeight("100%");
		dateLabel.setValue(date);
		commentDetails.addComponent(dateLabel);
		commentDetails.setComponentAlignment(dateLabel, new Alignment(33));
		
		return commentDetails;
	}

	public Label getCommentText() {
		return commentText;
	}

	public void setCommentText(Label commentText) {
		this.commentText = commentText;
	}

	public Label getDateLabel() {
		return dateLabel;
	}

	public void setDateLabel(Label dateLabel) {
		this.dateLabel = dateLabel;
	}

	public Label getAuthorLabel() {
		return authorLabel;
	}

	public void setAuthorLabel(Label authorLabel) {
		this.authorLabel = authorLabel;
	}

}
