
package de.vogella.swt.customwidgets;

import java.util.EventListener;

/**
 * @author bpasero@rssowl.org
 * 
 * Lars Vogel - Created based on an EclipseMagazin Article from Benjamin Pasero
 */
public interface OpenListener extends EventListener {

  /** Ein CLink wurde geöffnet */
  void linkOpened(OpenEvent e);
}