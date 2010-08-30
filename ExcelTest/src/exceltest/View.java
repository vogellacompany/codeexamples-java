package exceltest;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {

	public static final String ID = "ExcelTest.view";
	private OleClientSite site;

	public View() {
	}

	@Override
	public void createPartControl(Composite parent) {
		try {
			OleFrame frame = new OleFrame(parent, SWT.NONE);
			site = new OleClientSite(frame, SWT.NONE, "Excel.Sheet");
		} catch (SWTError e) {
			System.out.println("Unable to open activeX control");
			return;
		}
	}

	@Override
	public void setFocus() {
		// Have to set the focus see
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=207688
		site.setFocus();
	}
}
