package de.vogella.scala.plugin

import org.eclipse.swt.SWT
import org.eclipse.swt.widgets._
import org.eclipse.swt.layout.FillLayout

class ScalaView (parent : Composite, style : Int) extends Composite(parent, style) {
  setLayout(new FillLayout())
  new Label(this, SWT.NULL).setText("Hello, Scala.")
}
