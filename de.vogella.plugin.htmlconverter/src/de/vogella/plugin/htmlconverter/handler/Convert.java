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
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class Convert extends AbstractHandler {
	private QualifiedName path = new QualifiedName("html", "path");

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IStructuredSelection selection = (IStructuredSelection) HandlerUtil
				.getActiveMenuSelection(event);
		DirectoryDialog fileDialog = new DirectoryDialog(HandlerUtil
				.getActiveShell(event));
		String directory = "";
		Object firstElement = selection.getFirstElement();
		if (firstElement instanceof ICompilationUnit) {
			ICompilationUnit cu = (ICompilationUnit) firstElement;
			IResource res = cu.getResource();
			boolean newDirectory = true;
			directory = getPersistentProperty(res, path);

			if (directory != null && directory.length() > 0) {
				newDirectory = !(MessageDialog.openQuestion(HandlerUtil
						.getActiveShell(event), "Question",
						"Use the previous output directory?"));
			}
			if (newDirectory) {
				directory = fileDialog.open();

			}
			if (directory != null && directory.length() > 0) {
				analyze(cu);
				setPersistentProperty(res, path, directory);
				write(directory, cu);
			}

		} else {
			MessageDialog.openInformation(HandlerUtil.getActiveShell(event),
					"Information", "Please select a Java source file");
		}

		// iterator.next();

		// }
		return null;
	}

	protected String getPersistentProperty(IResource res, QualifiedName qn) {
		try {
			return res.getPersistentProperty(qn);
		} catch (CoreException e) {
			return "";
		}
	}

	// TODO: Include this in the HTML output

	private void analyze(ICompilationUnit cu) {
		// Cool JDT allows you to analyze the code easily
		// I don't see really a use case here but I just wanted to do this here
		// as I consider this as cool and
		// what to have a place where I can store the data
		try {

			IType type = null;
			IType[] allTypes;
			allTypes = cu.getAllTypes();
			/**
			 * Search the public class
			 */
			for (int t = 0; t < allTypes.length; t++) {
				if (Flags.isPublic((allTypes[t].getFlags()))) {
					type = allTypes[t];
					break;
				}
			}
			if (type != null) {
				String classname = type.getFullyQualifiedName();
				IMethod[] methods = type.getMethods();
			}

		} catch (JavaModelException e) {
			e.printStackTrace();
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
			System.out.println(test);
			System.out.println(name.length);
			String htmlFile = dir + "\\" + name[0] + ".html";

			System.out.println(htmlFile);
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
