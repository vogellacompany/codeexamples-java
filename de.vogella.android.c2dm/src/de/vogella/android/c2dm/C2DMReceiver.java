package de.vogella.android.c2dm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.c2dm.C2DMBaseReceiver;

public class C2DMReceiver extends C2DMBaseReceiver {
	public C2DMReceiver() {
		// Email address currently not used by the C2DM Messaging framework
		super("dummy@google.com");
	}

	@Override
	public void onRegistered(Context context, String registrationId)
			throws java.io.IOException {
		Log.e("C2DM", "Registration ID arrived: Fantastic!!!");
		Log.e("C2DM", registrationId);
	};

	@Override
	protected void onMessage(Context context, Intent intent) {
		Log.e("C2DM", "Message: Fantastic!!!");
	}

	@Override
	public void onError(Context context, String errorId) {
		Log.e("C2DM", "Fuck: Error!!!");
	}

}
