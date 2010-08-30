package de.vogella.jface.tableviewer.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import de.vogella.jface.tableviewer.Activator;
import de.vogella.jface.tableviewer.model.Person;
import de.vogella.jface.tableviewer.util.SearchUtil;

public class PersonLabelProvider extends StyledCellLabelProvider {
	// We use icons
	private static final Image CHECKED = Activator.getImageDescriptor(
			"icons/checked.gif").createImage();
	private static final Image UNCHECKED = Activator.getImageDescriptor(
					"icons/unchecked.gif").createImage();
	
	private String searchText;
	private Color systemColor;

	public PersonLabelProvider() {
		systemColor = Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW);
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;

	}

	@Override
	public void update(ViewerCell cell) {
		Person element = (Person) cell.getElement();
		int index = cell.getColumnIndex();
		String columnText = getColumnText(element, index);
		cell.setText(columnText);
		cell.setImage(getColumnImage(element, index));
		if (searchText != null && searchText.length() > 0) {
			int intRangesCorrectSize[] = SearchUtil.getSearchTermOccurrences(
					searchText, columnText);
			List<StyleRange> styleRange = new ArrayList<StyleRange>();
			for (int i = 0; i < intRangesCorrectSize.length / 2; i++) {
				StyleRange myStyleRange = new StyleRange(0, 0, null,
						systemColor);
				myStyleRange.start = intRangesCorrectSize[i];
				myStyleRange.length = intRangesCorrectSize[++i];
				styleRange.add(myStyleRange);
			}
			cell.setStyleRanges(styleRange.toArray(new StyleRange[styleRange
					.size()]));
		} else {
			cell.setStyleRanges(null);
		}

		super.update(cell);

	}

	private String getColumnText(Object element, int columnIndex) {
		Person person = (Person) element;
		switch (columnIndex) {
		case 0:
			return person.getFirstName();
		case 1:
			return person.getLastName();
		case 2:
			return person.getGender();
		case 3:
			return String.valueOf(person.isMarried());
		default:
			throw new RuntimeException("Should not happen");
		}
	}

	private Image getColumnImage(Object element, int columnIndex) {
		// In case you don't like image just return null here
		if (columnIndex == 3) {
			if (((Person) element).isMarried()) {
				return CHECKED;
			}
			return UNCHECKED;
		}
		return null;
	}

}
