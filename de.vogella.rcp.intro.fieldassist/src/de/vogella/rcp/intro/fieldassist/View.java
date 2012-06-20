package de.vogella.rcp.intro.fieldassist;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "de.vogella.rcp.intro.fieldassist.view";

	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 10;
		parent.setLayout(layout);
		Label label = new Label(parent, SWT.NONE);
		label.setText("Please select a value:   ");
		Text text = new Text(parent, SWT.BORDER);

		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		text.setLayoutData(data);

		// Create the decoration for the text UI component
		final ControlDecoration deco = new ControlDecoration(text, SWT.TOP
				| SWT.RIGHT);

		// Re-use an existing image
		Image image = FieldDecorationRegistry.getDefault()
				.getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION)
				.getImage();
		// Set description and image
		deco.setDescriptionText("Use CNTR + SPACE to see possible values");
		deco.setImage(image);
		// Hide deco if not in focus
		deco.setShowOnlyOnFocus(false);

		// Also if the text UI componet is not empty hide the decoration
		text.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				Text text = (Text) e.getSource();
				if (text.getText().length() > 0) {
					deco.hide();
				} else {
					deco.show();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		// Help the user with the possible inputs
		// "." and "#" will also activate the content proposals
		char[] autoActivationCharacters = new char[] { '.', '#' };
		KeyStroke keyStroke;
		try {
			//
			keyStroke = KeyStroke.getInstance("Ctrl+Space");
			// assume that myTextControl has already been created in some way
			ContentProposalAdapter adapter = new ContentProposalAdapter(text,
					new TextContentAdapter(),
					new SimpleContentProposalProvider(new String[] {
							"ProposalOne", "ProposalTwo", "ProposalThree" }),
					keyStroke, autoActivationCharacters);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void setFocus() {
	}

}