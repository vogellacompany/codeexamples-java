package de.vogella.microsoft.fileexplorer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class FileExplorerView extends ViewPart {
	private OleClientSite site;
	static final int Navigate = 0x68;

	@Override
	public void createPartControl(Composite parent) {
		try {
			OleFrame frame = new OleFrame(parent, SWT.NONE);
			site = new OleClientSite(frame, SWT.NONE, "Shell.Explorer.1");
			site.doVerb(OLE.OLEIVERB_INPLACEACTIVATE);
			OleAutomation auto = new OleAutomation(site);
			auto.invoke(Navigate, new Variant[] { new Variant("c:\\temp") });
		} catch (SWTError e) {
			System.out.println("Unable to open activeX control");
			return;
		}

	}

	@Override
	public void setFocus() {
		site.setFocus();
	}

}
