package convert;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CashView {
	private Label cashValue;
	private Cash m_cash = new Cash();
	private Label symbol;
	private Text text_1;
	private Text text;
	protected Shell shell;
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String[] args)  {
 		Display display = Display.getDefault();
 		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
 			public void run() {
 				try {
 					CashView window = new CashView();
 					window.open();
 				} catch (Exception e) {
 					e.printStackTrace();
 				}
 			}
 		});
 	}

	/**
	 * Open the window
	 */
	public void open() {
		final Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		shell = new Shell();
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		shell.setLayout(gridLayout);
		shell.setSize(500, 375);
		shell.setText("SWT Application");

		final Label cashLabel = new Label(shell, SWT.NONE);
		cashLabel.setText("Cash:");
		new Label(shell, SWT.NONE);

		text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		cashValue = new Label(shell, SWT.NONE);
		cashValue.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
		final GridData gd_cashValue = new GridData(SWT.FILL, SWT.CENTER, false, false);
		gd_cashValue.widthHint = 150;
		cashValue.setLayoutData(gd_cashValue);

		final Label currencyLabel = new Label(shell, SWT.NONE);
		currencyLabel.setText("Currency:");

		symbol = new Label(shell, SWT.NONE);
		final GridData gd_symbol = new GridData(SWT.FILL, SWT.CENTER, false, false);
		gd_symbol.widthHint = 30;
		symbol.setLayoutData(gd_symbol);

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		initDataBindings();
		//
	}
	protected DataBindingContext initDataBindings() {
		IObservableValue m_cashCurrencyObserveValue = BeansObservables.observeValue(m_cash, "currency");
		IObservableValue m_cashValueObserveValue = BeansObservables.observeValue(m_cash, "value");
		IObservableValue cashValueTextObserveWidget = SWTObservables.observeText(cashValue);
		IObservableValue text_1TextObserveWidget = SWTObservables.observeText(text_1, SWT.Modify);
		IObservableValue m_cashValueObserveValue_1 = BeansObservables.observeValue(m_cash, "value");
		IObservableValue symbolTextObserveWidget = SWTObservables.observeText(symbol);
		IObservableValue m_cashCurrencyObserveValue_1 = BeansObservables.observeValue(m_cash, "currency");
		IObservableValue textTextObserveWidget = SWTObservables.observeText(text, SWT.Modify);
		//
		//
		DataBindingContext bindingContext = new DataBindingContext();
		//
		bindingContext.bindValue(textTextObserveWidget, m_cashValueObserveValue, null, null);
		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setConverter(new convert.ConverterCurrencyToSymbolString());
		bindingContext.bindValue(symbolTextObserveWidget, m_cashCurrencyObserveValue_1, null, strategy);
		UpdateValueStrategy strategy_1 = new UpdateValueStrategy();
		strategy_1.setConverter(new convert.ConverterStringToCurrency());
		UpdateValueStrategy strategy_2 = new UpdateValueStrategy();
		strategy_2.setConverter(new convert.ConverterCurrencyToString());
		bindingContext.bindValue(text_1TextObserveWidget, m_cashCurrencyObserveValue, strategy_1, strategy_2);
		bindingContext.bindValue(m_cashValueObserveValue_1, cashValueTextObserveWidget, null, null);
		//
		return bindingContext;
	}
}
