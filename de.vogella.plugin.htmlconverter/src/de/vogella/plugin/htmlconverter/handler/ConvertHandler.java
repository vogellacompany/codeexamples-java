package de.vogella.plugin.htmlconverter.handler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class ConvertHandler extends AbstractHandler {
	private QualifiedName path = new QualifiedName("html", "path");

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveShell(event);
		ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		IStructuredSelection selection = (IStructuredSelection) sel;

		Object firstElement = selection.getFirstElement();
		if (firstElement instanceof ICompilationUnit) {
			createOutput(shell, firstElement);

		} else {
			MessageDialog.openInformation(shell, "Info",
					"Please select a Java source file");
		}
		return null;
	}

	private void createOutput(Shell shell, Object firstElement) {
		String directory;
		ICompilationUnit cu = (ICompilationUnit) firstElement;
		IResource res = cu.getResource();
		boolean newDirectory = true;
		directory = getPersistentProperty(res, path);

		if (directory != null && directory.length() > 0) {
			newDirectory = !(MessageDialog.openQuestion(shell, "Question",
					"Use the previous output directory?"));
		}
		if (newDirectory) {
			DirectoryDialog fileDialog = new DirectoryDialog(shell);
			directory = fileDialog.open();

		}
		if (directory != null && directory.length() > 0) {
			setPersistentProperty(res, path, directory);
			write(directory, cu);
		}
	}

	protected String getPersistentProperty(IResource res, QualifiedName qn) {
		try {
			return res.getPersistentProperty(qn);
		} catch (CoreException e) {
			return "";
		}
	}

	protected void setPersistentProperty(IResource res, QualifiedName qn,
			String value) {
		try {
			res.setPersistentProperty(qn, value);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private void write(String dir, ICompilationUnit cu) {
		try {
			cu.getCorrespondingResource().getName();
			String test = cu.getCorrespondingResource().getName();
			// Need
			String[] name = test.split("\\.");
			String htmlFile = dir + "\\" + name[0] + ".html";
			FileWriter output = new FileWriter(htmlFile);
			BufferedWriter writer = new BufferedWriter(output);
			writer.write("<html>");
			writer.write("<head>");
			writer.write("</head>");
			writer.write("<body>");
			writer.write("<pre>");
			writer.write(cu.getSource());
			writer.write("</pre>");
			writer.write("</body>");
			writer.write("</html>");
			writer.flush();
		} catch (JavaModelException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
