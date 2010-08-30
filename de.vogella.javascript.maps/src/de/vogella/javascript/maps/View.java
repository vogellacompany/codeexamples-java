package de.vogella.javascript.maps;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	
	public static final String ID = "de.vogella.javascript.maps.view";
	private static List list;
	
	
	public void createPartControl(Composite parent) {
		SashForm sash = new SashForm(parent, SWT.HORIZONTAL);
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost", "proxy");
		System.setProperty("http.proxyPort", "8080");

		// Load the map file
		File f = new File("C:/temp/demofile/map.html");
		final Browser browser = new Browser(parent, SWT.NONE); // Uses IE on MS
		
		browser.addControlListener(new ControlListener() {
			public void controlResized(ControlEvent e) {
				// Use Javascript to set the browser width and height
				browser
						.execute("document.getElementById('map_canvas').style.width= "
								+ (browser.getSize().x - 20) + ";");
				browser
						.execute("document.getElementById('map_canvas').style.height= "
								+ (browser.getSize().y - 20) + ";");
			}

			public void controlMoved(ControlEvent e) {
			}
		});
		
		 new CustomFunction (browser, "theJavaFunction");
		 
		    Composite c = new Composite(sash, SWT.BORDER);
		    c.setLayout(new GridLayout(2, true));
		    Button b = new Button(c, SWT.PUSH);
		    b.setText("Where Am I ?");
		    b.addSelectionListener(new SelectionAdapter() {
		        public void widgetSelected(SelectionEvent e) {
		            double lat = ((Double) browser.evaluate("return map.getCenter().lat();")).doubleValue();
		            double lng = ((Double) browser.evaluate("return map.getCenter().lng();")).doubleValue();
		            list.add(lat + " : " + lng);
		        }
		    });
		    
		    Button addMarker = new Button(c, SWT.PUSH);
		    addMarker.setText("Add Marker");
		    addMarker.addSelectionListener(new SelectionAdapter() {
		        public void widgetSelected(SelectionEvent e) {
		            browser.evaluate("createMarker();");
		          
		        }
		    });
		    list = new List(c, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		    gridData.horizontalSpan=2;
		    list.setLayoutData(gridData);
		  
		   
		    browser.setUrl(f.toURI().toString());
//		    sash.setWeights(new int[] {4,1});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {

	}
	
	 // Called by JavaScript
	 class CustomFunction extends BrowserFunction {
	    CustomFunctionData data = new CustomFunctionData(null);
	    
		CustomFunction (Browser browser, String name) {
	        super (browser, name);
	        this.data.browser = browser;
	    }
	    public Object function (Object[] arguments) {
	        double lat = ((Double) arguments[0]).doubleValue();
	        double lng = ((Double) arguments[1]).doubleValue();
	        list.add(lat + " : " + lng);
	        data.browser.execute("document.getElementById('map_canvas').style.width= "+ (data.browser.getSize().x - 20) + ";");
	        data.browser.execute("document.getElementById('map_canvas').style.height= "+ (data.browser.getSize().y - 20) + ";");
	        return null;
	    }
	 }
}