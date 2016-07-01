package de.vogella.plugin.adapter;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.model.WorkbenchAdapter;
import org.eclipse.ui.views.properties.IPropertySource;

import de.vogella.plugin.adapter.model.Todo;

public class TodoAdapterFactory implements IAdapterFactory {

	private static final Class<?>[] adapterList = new Class<?>[] { IPropertySource.class, WorkbenchAdapter.class };

	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (adapterType== IPropertySource.class && adaptableObject instanceof Todo){
			return adapterType.cast(new TodoPropertySource((Todo) adaptableObject));
		} else if (adapterType.isAssignableFrom(WorkbenchAdapter.class) && adaptableObject instanceof Todo) {
			return adapterType.cast(new TodoWorkbenchAdapter());
		}
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return adapterList;
	}

}
