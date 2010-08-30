package test;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

import de.vogella.sap.jco.adapter.TableAdapterReader;
import de.vogella.sap.jco.connection.Connection;
import de.vogella.sap.jco.model.SapSystem;

public class Tester {
	static String SAP = "SAP_SERVER";

	public static void main(String[] args) {
		// SAP System
		SapSystem system = new SapSystem();
		system.setClient("600");
		system.setHost("pwdf6394.wdf.sap.corp");
		system.setLanguage("en");
		system.setSystemNumber("76");
		system.setUser("mytester");
		system.setPassword("welcome");

		Connection connect = new Connection(system);

		JCoFunction function = connect.getFunction("BAPI_USER_GETLIST");
		function.getImportParameterList().setValue("MAX_ROWS", 10);
		connect.execute(function);
		JCoTable table = function.getTableParameterList().getTable("USERLIST");
		System.out.println(table.isEmpty());

		TableAdapterReader tableAdapter = new TableAdapterReader(table);
		System.out.println("Number of Users: " + tableAdapter.size());
		for (int i = 0; i < tableAdapter.size(); i++) {
			// USERNAME is on the fields in the structure user
			System.out.println(tableAdapter.get("USERNAME"));
			tableAdapter.next();
		}
	}
}
