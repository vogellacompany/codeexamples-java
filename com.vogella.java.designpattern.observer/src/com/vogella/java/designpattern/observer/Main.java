package com.vogella.java.designpattern.observer;

import com.vogella.java.designpattern.observer.MyModel.Person;

public class Main {


  public static void main(String[] args) {
    MyModel model = new MyModel();
    // We change the last name of the person, observer will get notified
    for (Person person : model.getPersons()) {
    	person.setLastName(person.getLastName() + "new");
	}
    // We change the first name of the person, observer will get notified
    for (Person person : model.getPersons()) {
    	person.setLastName(person.getFirstName() + "new");
	}
  }
} 