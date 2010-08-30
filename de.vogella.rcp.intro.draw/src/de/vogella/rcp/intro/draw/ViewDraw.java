package de.vogella.rcp.intro.draw;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ViewDraw extends ViewPart {

	public ViewDraw() {
	}

	@Override
	public void createPartControl(Composite parent) {
		final Canvas canvas = new Canvas(parent, SWT.BORDER);
		canvas.setSize(200, 200);
		canvas.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				GC gc = new GC(canvas);

				gc.setBackground(getSite().getShell().getDisplay()
						.getSystemColor(SWT.COLOR_YELLOW));
				gc.fillOval(0, 0, 100, 100);
				gc.setBackground(getSite().getShell().getDisplay()
						.getSystemColor(SWT.COLOR_BLACK));
				gc.fillOval(25, 35, 10, 10);
				gc.fillOval(65, 35, 10, 10);
				gc.setLineWidth(2);
				gc.drawArc(20, 60, 60, 20, 180, 180);
			}

		});

	}

	@Override
	public void setFocus() {

	}

}
