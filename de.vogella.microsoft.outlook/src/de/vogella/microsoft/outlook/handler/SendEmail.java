package de.vogella.microsoft.outlook.handler;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SendEmail extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Display display = Display.getCurrent();
		Shell shell = new Shell(display);
		OleFrame frame = new OleFrame(shell, SWT.NONE);
		// This should start outlook if it is not running yet
		OleClientSite site = new OleClientSite(frame, SWT.NONE, "OVCtl.OVCtl");
		site.doVerb(OLE.OLEIVERB_INPLACEACTIVATE);
		// Now get the outlook application
		OleClientSite site2 = new OleClientSite(frame, SWT.NONE,
				"Outlook.Application");
		OleAutomation outlook = new OleAutomation(site2);
		// 
		OleAutomation mail = invoke(outlook, "CreateItem", 0 /* Mail item */)
				.getAutomation();
		setProperty(mail, "To", "test@gmail.com"); /*
													 * Empty but could also be
													 * predefined
													 */
		setProperty(mail, "Bcc", "test@gmail.com"); /*
													 * Empty but could also be
													 * predefined
													 */
		setProperty(mail, "BodyFormat", 2 /* HTML */);
		setProperty(mail, "Subject", "Top News for you");
		setProperty(mail, "HtmlBody",
				"<html>Hello<p>, please find some infos here.</html>");
		File file = new File("c:/temp/test.txt");
		if (file.exists()) {
			OleAutomation attachments = getProperty(mail, "Attachments");
			invoke(attachments, "Add", "c:/temp/test.txt");
		} else {
			MessageDialog
					.openInformation(shell, "Info",
							"Attachment File c:/temp/test.txt not found; will send email with attachment");
		}
		invoke(mail, "Display" /* or "Send" */);
		return null;
	}

	private static OleAutomation getProperty(OleAutomation auto, String name) {
		Variant varResult = auto.getProperty(property(auto, name));
		if (varResult != null && varResult.getType() != OLE.VT_EMPTY) {
			OleAutomation result = varResult.getAutomation();
			varResult.dispose();
			return result;
		}
		return null;
	}

	private static Variant invoke(OleAutomation auto, String command,
			String value) {
		return auto.invoke(property(auto, command),
				new Variant[] { new Variant(value) });
	}

	private static Variant invoke(OleAutomation auto, String command) {
		return auto.invoke(property(auto, command));
	}

	private static Variant invoke(OleAutomation auto, String command, int value) {
		return auto.invoke(property(auto, command),
				new Variant[] { new Variant(value) });
	}

	private static boolean setProperty(OleAutomation auto, String name,
			String value) {
		return auto.setProperty(property(auto, name), new Variant(value));
	}

	private static boolean setProperty(OleAutomation auto, String name,
			int value) {
		return auto.setProperty(property(auto, name), new Variant(value));
	}

	private static int property(OleAutomation auto, String name) {
		return auto.getIDsOfNames(new String[] { name })[0];
	}

}
