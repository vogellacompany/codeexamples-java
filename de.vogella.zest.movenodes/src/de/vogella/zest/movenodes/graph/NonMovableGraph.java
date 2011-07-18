package de.vogella.zest.movenodes.graph;

import org.eclipse.draw2d.SWTEventDispatcher;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.zest.core.widgets.Graph;

public class NonMovableGraph extends Graph {

	public NonMovableGraph(Composite parent, int style) {
		super(parent, style);
		this.getLightweightSystem().setEventDispatcher(
				new SWTEventDispatcher() {
					public void dispatchMouseMoved(
							org.eclipse.swt.events.MouseEvent me) {
						// Doing nothing
					}
				});
	}

}
