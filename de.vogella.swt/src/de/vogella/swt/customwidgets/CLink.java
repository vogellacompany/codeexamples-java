
package de.vogella.swt.customwidgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author bpasero@rssowl.org
 * 
 * Lars Vogel - Created based on an EclipseMagazin Article from Benjamin Pasero
 */
public class CLink extends Canvas {
  private Image image;
  private String text;
  private String link;
  private boolean isFocussed; //TRUE: Focus im Widget
  private List<OpenListener> listeners = new ArrayList<OpenListener>();

  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new GridLayout());

    Font font = new Font(display, display.getSystemFont().getFontData()[0].getName(), 14, SWT.NORMAL);

    final CLink link = new CLink(shell, SWT.NONE);
    link.setFont(font);
    link.setText("Eclipse Experts Day");
    link.setLink("http://www.jax.de");
    link.setImage(new Image(display, "eclipse.gif"));

    /* Drag Source für den CLink */
    final DragSource source = new DragSource(link, DND.DROP_MOVE);

    source.setDragSourceEffect(new CLinkDragSourceEffect(link)); //Effekt für den Drag

    source.setTransfer(new Transfer[] { TextTransfer.getInstance() });
    source.addDragListener(new DragSourceAdapter() {
      public void dragSetData(DragSourceEvent e) {
        e.data = link.getLink();
      }
    });

    /* Link im Browser öffnen */
    link.addOpenListener(new OpenListener() {
      public void linkOpened(OpenEvent e) {
        Program.launch(e.getLink());
      }
    });

    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }

  public CLink(Composite parent, int style) {
    super(parent, style);

    addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent e) {
        onPaint(e);
      }
    });

    addMouseTrackListener(new MouseTrackAdapter() {
      public void mouseEnter(MouseEvent e) {
        onMouseEnter();
      }

      public void mouseExit(MouseEvent e) {
        onMouseExit();
      }
    });

    /* Auf Maus-Klick-Events reagieren */
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseUp(MouseEvent e) {
        notifyOpen();
      }
    });

    /* Key Listener um Traverse-Events zu erhalten */
    addKeyListener(new KeyAdapter() {});

    /* Verarbeitung der Traverse-Events */
    addTraverseListener(new TraverseListener() {
      public void keyTraversed(TraverseEvent e) {
        onKeyTraversed(e);
      }
    });

    addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent e) {
        onFocusGained();
      }

      public void focusLost(FocusEvent e) {
        onFocusLost();
      }
    });

    /* Support für Screen Reader */
    getAccessible().addAccessibleListener(new AccessibleAdapter() {
      @Override
      public void getName(AccessibleEvent e) {
        e.result = text;
      }
    });
  }

  private void onFocusLost() {
    isFocussed = false;
    redraw();
  }

  private void onFocusGained() {
    isFocussed = true;
    redraw();
  }

  /* Traverse Events verarbeiten */
  private void onKeyTraversed(TraverseEvent e) {

    switch (e.detail) {
      case SWT.TRAVERSE_TAB_NEXT:
        e.doit = true;
        break;

      case SWT.TRAVERSE_TAB_PREVIOUS:
        e.doit = true;
        break;

      case SWT.TRAVERSE_RETURN:
        notifyOpen();
        break;
    }
  }

  public void addOpenListener(OpenListener listener) {
    if (!listeners.contains(listener))
      listeners.add(listener);
  }

  public void removeOpenListener(OpenListener listener) {
    listeners.remove(listener);
  }

  private void notifyOpen() {
    OpenEvent event = new OpenEvent(this, link);
    for (OpenListener listener : listeners) {
      listener.linkOpened(event);
    }
  }

  /* Maus betritt das CLink Widget */
  private void onMouseEnter() {
    setCursor(getDisplay().getSystemCursor(SWT.CURSOR_HAND));
  }

  /* Maus verlässt das CLink Widget */
  private void onMouseExit() {
    setCursor(null);
  }

  private void onPaint(PaintEvent e) {
    GC gc = e.gc;
    int x = 1;
    int y = 1;

    if (image != null) {
      gc.drawImage(image, x, 1);
      x = image.getBounds().width + 5;
      y += image.getBounds().height;
    }

    if (text != null) {
      gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_BLUE));

      Point stringExtent = gc.stringExtent(text);
      gc.drawString(text, x, 1);

      /* Link Unterstreichen */
      gc.drawLine(x, 1 + stringExtent.y, x + stringExtent.x, 1 + stringExtent.y);

      x += stringExtent.x;
      y = Math.max(y, stringExtent.y + 1);
    }

    /* Focus Border zeichnen */
    if (isFocussed)
      gc.drawFocus(1, 1, x, y);
  }

  /*
   * @see org.eclipse.swt.widgets.Composite#computeSize(int, int, boolean)
   */
  public Point computeSize(int wHint, int hHint, boolean changed) {
    int width = 0, height = 0;

    /* Größe des Bildes */
    if (image != null) {
      Rectangle bounds = image.getBounds();
      width = bounds.width + 5;
      height = bounds.height;
    }

    /* Größes des Textes */
    if (text != null) {
      GC gc = new GC(this);
      Point extent = gc.stringExtent(text);
      gc.dispose(); // Ressourcen wieder freigeben!
      width += extent.x;
      height = Math.max(height, extent.y);
    }

    /* Hints berücksichtigen (naiv) */
    if (wHint != SWT.DEFAULT)
      width = wHint;
    if (hHint != SWT.DEFAULT)
      height = hHint;

    /* 1 Pixel Margins hinzufügen */
    return new Point(width + 2, height + 2);
  }

  public void setImage(Image image) {
    this.image = image;
    redraw();
  }

  public void setText(String text) {
    this.text = text;
    redraw();
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getLink() {
    return link;
  }

  public Image getImage() {
    return image;
  }
}