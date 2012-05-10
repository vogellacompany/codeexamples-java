package phonebook;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;

public class ListSelectionUpdateValueStrategy extends UpdateValueStrategy {
	protected IStatus doSet(IObservableValue observableValue, Object value) {
		return super.doSet(observableValue, value == null ? Boolean.FALSE
				: Boolean.TRUE);
	}
}