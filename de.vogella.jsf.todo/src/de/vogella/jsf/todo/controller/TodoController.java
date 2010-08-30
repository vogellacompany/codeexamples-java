package de.vogella.jsf.todo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import de.vogella.jsf.todo.model.Todo;

public class TodoController {
	// Domain model related variables
	private List<Todo> todos;
	private Todo todo;

	// JavaServerFaces related variables
	private UIForm form;
	private UIForm tableForm;
	private UICommand addCommand;

	public TodoController() {
		todos = new ArrayList<Todo>();
		todos.add(new Todo("Learn JFS", "Finish this article", 1));
		todos.add(new Todo("Stop drinking to much coffee", "Coffee is evil!", 3));
	}

	public String addNew() {
		todo = new Todo("", "", 3);
		form.setRendered(true);
		addCommand.setRendered(false);
		return null;
	}

	public String save() {
		todos.add(todo);
		form.setRendered(false);
		addCommand.setRendered(true);
		return null;
	}

	public String cancel() {
		todo = null;
		form.setRendered(false);
		addCommand.setRendered(true);
		return null;
	}

	public String delete() {
		todos.remove(todo);
		return null;
	}

	public void displayTable(ActionEvent event) {
		if (event.getComponent().getId().equalsIgnoreCase("hide")) {
			tableForm.setRendered(false);
		} else {
			tableForm.setRendered(true);
		}
	}

	public List<SelectItem> getPriorities() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(1, "High"));
		list.add(new SelectItem(2, "Medium"));
		list.add(new SelectItem(3, "Low"));
		return list;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public UICommand getAddCommand() {
		return addCommand;
	}

	public void setAddCommand(UICommand addCommand) {
		this.addCommand = addCommand;
	}

	public UIForm getTableForm() {
		return tableForm;
	}

	public void setTableForm(UIForm tableForm) {
		this.tableForm = tableForm;
	}

}
