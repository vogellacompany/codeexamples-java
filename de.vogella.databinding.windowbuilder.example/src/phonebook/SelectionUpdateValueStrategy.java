package phonebook;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;

public class SelectionUpdateValueStrategy extends UpdateValueStrategy {

	public SelectionUpdateValueStrategy() {
	}

	protected IStatus doSet(IObservableValue observableValue, Object value) {		
		Integer selection = (Integer) value;
		return super.doSet(observableValue,
				selection.intValue() == -1 ? Boolean.FALSE : Boolean.TRUE);
	}

}