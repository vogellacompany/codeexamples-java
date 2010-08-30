package de.vogella.jface.tableviewer.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Display;

public class SearchUtil {
	public static int[] getSearchTermOccurrences(String searchTerm,
			String content) {
		List<StyleRange> styleRange;
		List<Integer> ranges;
		Display disp = Display.getCurrent();
		StyleRange myStyleRange = new StyleRange(0, 0, null, disp
				.getSystemColor(SWT.COLOR_YELLOW));

		styleRange = new ArrayList<StyleRange>(); // reset the StyleRange-Array
		// for each new field
		ranges = new ArrayList<Integer>(); // reset the ranges-array
		// empty search term ==> return an empty StyleRange array
		if (searchTerm.equals("")) {
			return new int[] {};
		}

		// determine all occurrences of the searchText and write the beginning
		// and length of each occurrence into an array
		for (int i = 0; i < content.length(); i++) {
			if (i + searchTerm.length() <= content.length()
					&& content.substring(i, i + searchTerm.length())
							.equalsIgnoreCase(searchTerm)) {
				// ranges format: n->start of the range, n+1->length of the
				// range
				ranges.add(i);
				ranges.add(searchTerm.length());
			}
		}
		// convert the list into an int[] and make sure that overlapping
		// search term occurrences are are merged
		int[] intRanges = new int[ranges.size()];
		int arrayIndexCounter = 0;
		for (int listIndexCounter = 0; listIndexCounter < ranges.size(); listIndexCounter++) {
			if (listIndexCounter % 2 == 0) {
				if (searchTerm.length() > 1
						&& listIndexCounter != 0
						&& ranges.get(listIndexCounter - 2)
								+ ranges.get(listIndexCounter - 1) >= ranges
								.get(listIndexCounter)) {
					intRanges[arrayIndexCounter - 1] = 0
							- ranges.get(listIndexCounter - 2)
							+ ranges.get(listIndexCounter)
							+ ranges.get(++listIndexCounter);
				} else {
					intRanges[arrayIndexCounter++] = ranges
							.get(listIndexCounter);
				}
			} else {
				intRanges[arrayIndexCounter++] = ranges.get(listIndexCounter);
				styleRange.add(myStyleRange);
			}
		}
		// if there have been any overlappings we need to reduce the size of
		// the array to avoid conflicts in the setStyleRanges method
		int[] intRangesCorrectSize = new int[arrayIndexCounter];
		System.arraycopy(intRanges, 0, intRangesCorrectSize, 0,
				arrayIndexCounter);

		return intRangesCorrectSize;
	}
}
