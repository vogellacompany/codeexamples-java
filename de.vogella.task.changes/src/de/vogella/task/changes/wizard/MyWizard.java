package de.vogella.task.changes.wizard;

import org.eclipse.jface.wizard.Wizard;

import de.vogella.task.model.Factory;
import de.vogella.task.model.ITask;

public class MyWizard extends Wizard {
	private MyPageOne one;
	private MyPageTwo two;
	private ITask task;

	public MyWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		task = Factory.createRequirementInstance();
		one = new MyPageOne(task);
		two = new MyPageTwo(task);
		addPage(one);
		addPage(two);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	public ITask getTask() {
		return task;
	}

}
