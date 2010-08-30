package de.vogella.jersey.todo.resources;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import de.vogella.jersey.todo.dao.TodoDao;
import de.vogella.jersey.todo.model.Todo;

public class TodoResourceWithHTMLHeader_NotUsed {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	public TodoResourceWithHTMLHeader_NotUsed(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	//Application integration 		
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Todo getTodo() {
		Todo todo = TodoDao.instance.getModel().get(id);
		if(todo==null)
			throw new RuntimeException("Get: Todo with " + id +  " not found");
		return todo;
	}
	
	// For the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Todo getTodoHTML() {
		Todo todo = TodoDao.instance.getModel().get(id);
		if(todo==null)
			throw new RuntimeException("Get: Todo with " + id +  " not found");
		return todo;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putContact(JAXBElement<Todo> todo) {
		Todo c = todo.getValue();
		return putAndGetResponse(c);
	}
	
	
	@PUT
	public Response putTodo(@Context HttpHeaders headers, byte[] in) {
		Map<String,String> params = parse(new String(in));
		Todo c = new Todo(params.get("id"), params.get("summary"));
		
		return putAndGetResponse(c);
	}
	
	@DELETE
	public void deleteTodo() {
		Todo c = TodoDao.instance.getModel().remove(id);
		if(c==null)
			throw new RuntimeException("Delete: Todo with " + id +  " not found");
	}
	
	private Response putAndGetResponse(Todo todo) {
		Response res;
		if(TodoDao.instance.getModel().containsKey(todo.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		TodoDao.instance.getModel().put(todo.getId(), todo);
		return res;
	}
	
	

	public Map<String,String> parse(String paramString) {
		Map<String,String> params = new HashMap<String,String>();
		String[] paramPairs = paramString.split("&");
		for(String param : paramPairs) {
			String[] key_value = param.split("=");
			params.put(key_value[0], key_value[1]);
		}
		return params;
	}
}
