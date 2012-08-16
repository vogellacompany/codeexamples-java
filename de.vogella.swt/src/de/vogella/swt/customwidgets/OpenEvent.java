
package de.vogella.swt.customwidgets;

import java.util.EventObject;

/**
 * @author bpasero@rssowl.org
 * 
 * Lars Vogel - Created based on an EclipseMagazin Article from Benjamin Pasero
 */
public class OpenEvent extends EventObject {
  private String link;

  public OpenEvent(Object source, String link) {
    super(source);
    this.link = link;
  }

  public String getLink() {
    return link;
  }
}