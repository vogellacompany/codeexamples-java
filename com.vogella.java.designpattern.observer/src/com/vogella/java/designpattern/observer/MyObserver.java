package com.vogella.java.designpattern.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyObserver implements PropertyChangeListener {
  public MyObserver(MyModel model) {
    model.addChangeListener(this);
  }

  @Override
  public void propertyChange(PropertyChangeEvent arg0) {
    System.out.println("Things are changing...");
  }
} 