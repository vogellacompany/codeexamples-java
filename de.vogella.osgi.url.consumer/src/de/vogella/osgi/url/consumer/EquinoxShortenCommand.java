package de.vogella.osgi.url.consumer;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.framework.BundleContext;


public class EquinoxShortenCommand extends ShortenCommand implements CommandProvider {

	public EquinoxShortenCommand(BundleContext context) {
		super(context);
	}

	public void _shorten(CommandInterpreter ci) {
		try {
			String result = shorten((ci.nextArgument()));
			if(result == null)
				ci.println("Unable to shorten; service unavailable");
			else
				ci.println(result);
		} catch (Exception e) {
			ci.printStackTrace(e);
		}
	}
	public String getHelp() {
		return "\tshorten <url> - shortens the URL and prints out the result\n";
	}

}
