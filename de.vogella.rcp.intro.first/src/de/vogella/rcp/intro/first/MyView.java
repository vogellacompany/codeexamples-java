package de.vogella.rcp.intro.first;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class MyView extends ViewPart {
	public static final String ID = "de.vogella.rcp.intro.fieldassist.view";

	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label label = new Label(parent, SWT.NONE);
		label.setText("Please select a value:   ");
		Text text = new Text(parent, SWT.BORDER);
		createDeco(text, "Use CNTL + SPACE to see possible values");
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		text.setLayoutData(data);
		ControlDecoration deco = new ControlDecoration(text, SWT.LEFT);
		deco.setDescriptionText("Use CNTL + SPACE to see possible values");
		deco.setImage(FieldDecorationRegistry.getDefault()
				.getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION)
				.getImage());
		deco.setShowOnlyOnFocus(false);
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

	private void createDeco(Text text, String s) {

	}
}