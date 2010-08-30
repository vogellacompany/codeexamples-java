package de.vogella.sap.jco.connection;

import java.util.Properties;

import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.ext.DestinationDataProvider;

import de.vogella.sap.jco.model.SapSystem;

/**
 * Connection allows to get and execute SAP functions. The constructor expect a
 * SapSystem and will save the connection data to a file. The connection will
 * also be automatically be established.
 */
public class Connection {
	static String SAP_SERVER = "SAP_SERVER";
	private JCoRepository repos;
	private JCoDestination dest;
	private final Properties properties;

	public Connection(SapSystem system) {
		properties = new Properties();
		properties.setProperty(DestinationDataProvider.JCO_ASHOST,
				system.getHost());
		properties.setProperty(DestinationDataProvider.JCO_SYSNR,
				system.getSystemNumber());
		properties.setProperty(DestinationDataProvider.JCO_CLIENT,
				system.getClient());
		properties.setProperty(DestinationDataProvider.JCO_USER,
				system.getUser());
		properties.setProperty(DestinationDataProvider.JCO_PASSWD,
				system.getPassword());
		properties.setProperty(DestinationDataProvider.JCO_LANG,
				system.getLanguage());
		MyDestinationDataProvider myProvider = new MyDestinationDataProvider();
		myProvider.changePropertiesForABAP_AS(properties);
		com.sap.conn.jco.ext.Environment
				.registerDestinationDataProvider(myProvider);

		try {
			dest = JCoDestinationManager.getDestination(SAP_SERVER);
			System.out.println("Attributes:");
			System.out.println(dest.getAttributes());
			System.out.println();
			repos = dest.getRepository();
		} catch (JCoException e) {
			throw new RuntimeException(e);
		}

	}

	// public void disconnect

	/**
	 * Method getFunction read a SAP Function and return it to the caller. The
	 * caller can then set parameters (import, export, tables) on this function
	 * and call later the method execute.
	 * 
	 * getFunction translates the JCo checked exceptions into a non-checked
	 * exceptions
	 */
	public JCoFunction getFunction(String functionStr) {
		JCoFunction function = null;
		try {
			function = repos.getFunction(functionStr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Problem retrieving JCO.Function object.");
		}
		if (function == null) {
			throw new RuntimeException("Not possible to receive function. ");
		}

		return function;
	}

	/**
	 * Method execute will call a function. The Caller of this function has
	 * already set all required parameters of the function
	 * 
	 */
	public void execute(JCoFunction function) {
		try {
			JCoContext.begin(dest);
			function.execute(dest);

		} catch (JCoException e) {
			e.printStackTrace();
		} finally {
			try {
				JCoContext.end(dest);
			} catch (JCoException e) {
				e.printStackTrace();
			}
		}
	}

}
