package com.vogella.ide.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoProvider {
  private static int current = 0;

  // example data, change if you like
  public static List<Todo> createInitialModel() {
    ArrayList<Todo> list = new ArrayList<Todo>();
    list.add(createTodo("SWT", "Learn Widgets"));
    list.add(createTodo("JFace", "Especially Viewers!"));
    list.add(createTodo("DI", "@Inject looks interesting"));
    list.add(createTodo("OSGi", "Services"));
    list.add(createTodo("Compatibility Layer", "Run Eclipse 3.x"));
    return list;
  }

  private static Todo createTodo(String summary, String description) {
    return new Todo(current++, summary, description, false, new Date());
  }
} 