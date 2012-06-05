package de.vogella.jersey.todo.client;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

import de.vogella.jersey.todo.model.Todo;

public class Tester {
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		// Create one todo
		Todo todo = new Todo("3", "Blabla");
		ClientResponse response = service.path("rest").path("todos")
				.path(todo.getId()).accept(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, todo);
		// Return code should be 201 == created resource
		System.out.println(response.getStatus());
		// Get the Todos
		System.out.println(service.path("rest").path("todos")
				.accept(MediaType.TEXT_XML).get(String.class));
		// Get XML for application
		System.out.println(service.path("rest").path("todos")
				.accept(MediaType.APPLICATION_JSON).get(String.class));
		// Get JSON for application
		System.out.println(service.path("rest").path("todos")
				.accept(MediaType.APPLICATION_XML).get(String.class));

		// Get the Todo with id 1
		System.out.println(service.path("rest").path("todos/1")
				.accept(MediaType.APPLICATION_XML).get(String.class));
		// get Todo with id 1
		service.path("rest").path("todos/1").delete();
		// Get the all todos, id 1 should be deleted
		System.out.println(service.path("rest").path("todos")
				.accept(MediaType.APPLICATION_XML).get(String.class));

		// Create a Todo
		Form form = new Form();
		form.add("id", "4");
		form.add("summary", "Demonstration of the client lib for forms");
		response = service.path("rest").path("todos")
				.type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(ClientResponse.class, form);
		System.out.println("Form response " + response.getEntity(String.class));
		// Get the all todos, id 4 should be created
		System.out.println(service.path("rest").path("todos")
				.accept(MediaType.APPLICATION_XML).get(String.class));

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/de.vogella.jersey.todo").build();
	}
}
