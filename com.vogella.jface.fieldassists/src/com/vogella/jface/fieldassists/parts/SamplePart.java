/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.vogella.jface.fieldassists.parts;


import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class SamplePart {


	@PostConstruct
	public void createComposite(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		// parent is a Composite
		parent.setLayout(layout);
		Label lblPleaseEnterA = new Label(parent, SWT.NONE);
		lblPleaseEnterA.setText("Please enter a value:");
		
		Text text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_text.horizontalIndent = 8;
		text.setLayoutData(gd_text);
		GridData data = new GridData(SWT.FILL, SWT.TOP, true, false);
		text.setData(data);

		// create the decoration for the text component
		final ControlDecoration deco = new ControlDecoration(text, SWT.TOP
			| SWT.LEFT);

		// use an existing image
		Image image = FieldDecorationRegistry.getDefault()
			.getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION)
			.getImage();

		// set description and image
		deco.setDescriptionText("Use CNTL + SPACE to see possible values");
		deco.setImage(image);

		// always show decoration
		deco.setShowOnlyOnFocus(false);

		// hide the decoration if the text componet has content
		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text) e.getSource();
				if (text.getText().length() > 0) {
				  deco.hide();
				} else {
				  deco.show();
				}
			}
		});

		// help the user with the possible inputs
		// "." and "#" activate the content proposals
		char[] autoActivationCharacters = new char[] { '.', '#' };
		KeyStroke keyStroke;
		//
		try {
			keyStroke = KeyStroke.getInstance("Ctrl+Space");
			ContentProposalAdapter adapter = new ContentProposalAdapter(text,
				new TextContentAdapter(),
				new SimpleContentProposalProvider(new String[] {
					"ProposalOne", "ProposalTwo", "ProposalThree" }),
					keyStroke, autoActivationCharacters);
			} catch (ParseException e1) {
			e1.printStackTrace();
		}

	}

	
}
