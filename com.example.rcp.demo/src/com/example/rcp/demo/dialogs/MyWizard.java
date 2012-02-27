package com.example.rcp.demo.dialogs;

import org.eclipse.jface.wizard.Wizard;

public class MyWizard extends Wizard {

	public MyWizard() {
		setWindowTitle("New Wizard");
	}

	@Override
	public void addPages() {
		MyPage1 myPage1 = new MyPage1();
		addPage(myPage1);
		MyPage1 myPage2 = new MyPage1();
		addPage(myPage2);
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
