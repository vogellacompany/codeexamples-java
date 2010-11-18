package de.vogella.gae.java.apiproxy;

import java.util.concurrent.Future;

import org.mortbay.log.Log;

import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.ApiProxy.ApiProxyException;
import com.google.apphosting.api.ApiProxy.Delegate;
import com.google.apphosting.api.ApiProxy.Environment;
import com.google.apphosting.api.ApiProxy.LogRecord;

public class ProfilingDelegate implements Delegate{
	Delegate parent;
	public ProfilingDelegate(Delegate parent) {
		this.parent = parent;
	}
	@Override
	public byte[] makeSyncCall(Environment env, String pgk, String method,
			byte[] request) throws ApiProxyException {
		long start = System.nanoTime();
		byte[] result = parent.makeSyncCall(env, pgk, method, request);
		long end = System.nanoTime();
		Log.info(pgk + "." + method + ": " + (end - start));
		return result;
	}
	@Override
	public void log(Environment arg0, LogRecord arg1) {
		parent.log(arg0, arg1);
	}
	@Override
	public Future makeAsyncCall(Environment arg0, String arg1, String arg2,
			byte[] arg3, ApiConfig arg4) {
		return parent.makeAsyncCall(arg0, arg1, arg2, arg3, arg4);
	}
}
