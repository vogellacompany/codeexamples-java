
package de.vogella.swt.customwidgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DragSourceEffect;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

/**
 * @author bpasero@rssowl.org
 * 
 *  Lars Vogel - Created based on an EclipseMagazin Article from Benjamin Pasero
 */
public class CLinkDragSourceEffect extends DragSourceEffect {
  private Image image;
  private final CLink link;

  public CLinkDragSourceEffect(CLink link) {
    super(link);
    this.link = link;
  }

  /*
   * @see org.eclipse.swt.dnd.DragSourceAdapter#dragStart(org.eclipse.swt.dnd.DragSourceEvent)
   */
  @Override
  public void dragStart(DragSourceEvent event) {
    Point linkSize = link.computeSize(SWT.DEFAULT, SWT.DEFAULT);
    GC gc = new GC(link);

    /* Erst altes Image disposen falls nötig */
    dispose();

    /* Neues Bild erzeugen und als DragSourceEffect festlegen */
    image = new Image(link.getDisplay(), linkSize.x, linkSize.y);
    gc.copyArea(image, 0, 0); //Kopiert das UI des Widgets in das Bild
    gc.dispose();

    event.image = image;
  }

  /*
   * @see org.eclipse.swt.dnd.DragSourceAdapter#dragFinished(org.eclipse.swt.dnd.DragSourceEvent)
   */
  @Override
  public void dragFinished(DragSourceEvent event) {
    dispose();
  }

  /* Nie vergessen Ressourcen zu disposen! */
  private void dispose() {
    if (image != null)
      image.dispose();
  }
}