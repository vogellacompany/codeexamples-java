package de.vogella.jsf.starter.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class LoginValidator implements Validator {
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String user = (String) value;
		if (!user.equalsIgnoreCase("tester")) {
			FacesMessage message = new FacesMessage();
			message.setDetail("User " + user + " does not exists");
			message.setSummary("Login Incorrect");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
