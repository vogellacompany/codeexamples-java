package de.vogella.jdt.codeanalysis.views;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.ViewPart;

import de.vogella.jdt.codeanalysis.model.MethodInformation;

public class ResultView extends ViewPart {
	public static final String ID = "de.vogella.jdt.codeanalysis.ResultView";
	private TableViewer viewer;

	public void setInput(List<MethodInformation> list) {
		viewer.setInput(list);
	}

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		buildTableColumns(viewer);
		viewer.setLabelProvider(new AnalysisLabelProvider());
		viewer.setContentProvider(new AnalysisContentProvider());

		viewer.getTable().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				Object data = e.item.getData();
				if (!(data instanceof MethodInformation))
					return;
				MethodInformation info = (MethodInformation) data;
				IMethod method = info.getMethod();
				ICompilationUnit cu = info.getResource();
				if (cu == null)
					return;
				try {
					IEditorPart part = JavaUI.openInEditor(cu);
					JavaUI.revealInEditor(part, (IJavaElement) method);
				} catch (CoreException ex) {
					// error handling
				}
			}
		});
	}

	@Override
	public void setFocus() {
	}

	private void buildTableColumns(TableViewer viewer) {
		String[] titles = { "File", "Method", "Number of Calls" };
		int[] bounds = { 100, 100, 100 };

		for (int i = 0; i < titles.length; i++) {
			TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
			column.getColumn().setText(titles[i]);
			column.getColumn().setWidth(bounds[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(true);
		}
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	public class AnalysisLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			MethodInformation metric = (MethodInformation) element;
			switch (columnIndex) {
			case 0:
				return metric.getResource().getElementName();
			case 1:
				return metric.getMethodName();
			default:
				return String.valueOf(metric.getNumberOfCalls());
			}

		}
	}
	

	public class AnalysisContentProvider implements IStructuredContentProvider {

		@Override
		public Object[] getElements(Object inputElement) {
			List<MethodInformation> list = (List<MethodInformation>) inputElement;
			return list.toArray();
		}

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

	}

}
