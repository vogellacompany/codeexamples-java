package de.vogella.databinding.example.validators;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class StringLongerThenTwo implements IValidator {

	@Override
	public IStatus validate(Object value) {
		if (value instanceof String) {
			String s = (String) value;
			// We check if the string is longer then 2 signs
			if (s.length() > 2) {
				return Status.OK_STATUS;
			} else {
				return ValidationStatus
						.error("Name must be longer two letters");
			}
		} else {
			throw new RuntimeException(
					"Not supposed to be called for non-strings.");
		}
	}
}