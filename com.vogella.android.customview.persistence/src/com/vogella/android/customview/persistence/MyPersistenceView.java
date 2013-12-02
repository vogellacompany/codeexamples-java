package com.vogella.android.customview.persistence;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

public class MyPersistenceView extends TextView {
	int stateToSave;

	public MyPersistenceView(Context context) {
		super(context);
	}
	
	public MyPersistenceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyPersistenceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		setText(String.valueOf(stateToSave));
		super.onDraw(canvas);
	}

	@Override
	public Parcelable onSaveInstanceState() {
		Toast.makeText(getContext(), "onSaveInstanceState", Toast.LENGTH_LONG).show();
		Parcelable superState = super.onSaveInstanceState();
		SavedState ss = new SavedState(superState);
		ss.stateToSave = stateToSave+1;
		return ss;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		Toast.makeText(getContext(), "onRestoreInstanceState", Toast.LENGTH_LONG).show();
		// begin boilerplate code so parent classes can restore state
		if (!(state instanceof SavedState)) {
			super.onRestoreInstanceState(state);
			return;
		}

		SavedState ss = (SavedState) state;
		super.onRestoreInstanceState(ss.getSuperState());
		// end
		
		this.stateToSave = ss.stateToSave ;
	}

	static class SavedState extends BaseSavedState {
		int stateToSave;

		SavedState(Parcelable superState) {
			super(superState);
		}

		private SavedState(Parcel in) {
			super(in);
			this.stateToSave = in.readInt();
		}

		@Override
		public void writeToParcel(Parcel out, int flags) {
			super.writeToParcel(out, flags);
			out.writeInt(this.stateToSave);
		}

		// required field that makes Parcelables from a Parcel
		public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
			public SavedState createFromParcel(Parcel in) {
				return new SavedState(in);
			}

			public SavedState[] newArray(int size) {
				return new SavedState[size];
			}
		};
	}

}
