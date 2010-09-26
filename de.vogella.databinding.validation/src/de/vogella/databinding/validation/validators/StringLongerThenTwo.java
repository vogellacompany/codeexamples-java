package de.vogella.databinding.validation.validators;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.fieldassist.ControlDecoration;

public class StringLongerThenTwo implements IValidator {

	private final String message;
	private final ControlDecoration controlDecoration;

	public StringLongerThenTwo(String message,
			ControlDecoration controlDecoration) {
		super();
		this.message = message;
		this.controlDecoration = controlDecoration;
	}

	@Override
	public IStatus validate(Object value) {
		if (value instanceof String) {
			String s = (String) value;
			// We check if the string is longer then 2 signs
			if (s.length() > 2) {
				controlDecoration.hide();
				return ValidationStatus.ok();
			} else {
				controlDecoration.show();
				return ValidationStatus.error(message);
			}
		} else {
			throw new RuntimeException(
					"Not supposed to be called for non-strings.");
		}
	}
}
