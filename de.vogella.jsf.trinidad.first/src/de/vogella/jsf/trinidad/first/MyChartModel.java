package de.vogella.jsf.trinidad.first;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.trinidad.model.ChartModel;

public class MyChartModel extends ChartModel {

	// How many charts are you going to have
	@Override
	public List<String> getGroupLabels() {
		List<String> groupLabels = new ArrayList<String>();
		groupLabels.add("Java");
		groupLabels.add("Linux");
		groupLabels.add(".NET");
		return groupLabels;
	}

	// How many parts (data areas) per chart
	@Override
	public List<String> getSeriesLabels() {
		List<String> seriesLabels = new ArrayList<String>();
		seriesLabels.add("Love it");
		seriesLabels.add("Hate it");
		return seriesLabels;
	}

	@Override
	public List<List<Double>> getYValues() {
		List<List<Double>> chartValues = new ArrayList<List<Double>>();
		// Fill the groups
		for (int i = 0; i < getGroupLabels().size(); i++) {
			List<Double> numbers = new ArrayList<Double>();

			// fill the series per group
			for (int j = 0; j < getSeriesLabels().size(); j++) {
				numbers.add(100* Math.random());
			}
			chartValues.add(numbers);
		}
		return chartValues;

	}

}
