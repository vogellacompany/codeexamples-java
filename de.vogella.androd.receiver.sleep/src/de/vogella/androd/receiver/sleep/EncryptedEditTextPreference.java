package de.vogella.androd.receiver.sleep;

public class EncryptedEditTextPreference extends EditTextPreference {
	public EncryptedEditTextPreference(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public EncryptedEditTextPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public EncryptedEditTextPreference(Context context) {
		super(context);
	}

	public String getText() {
		String value = super.getText();
		return SecurityUtils.decrypt(value);
	}

	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
		super.setText(restoreValue ? getPersistedString(null)
				: (String) defaultValue);
	}

	@Override
	public void setText(String text) {
		if (Utils.isStringBlank(text)) {
			super.setText(null);
			return;
		}¸¸
		super.setText(SecurityUtils.encrypt(text));
	}
}