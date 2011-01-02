package com.linkesoft.bbingo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.linkesoft.bbingo.BBingoDB.WordList;

/**
 * Bearbeite eine Wortliste, bestehend aus zwei EditText-Steuerelementen für
 * Titel und Buzzwort-Liste (durch \n getrennt)  
 */
public class EditWordList extends Activity {
	public final static String ID="id"; // Parameter zur Übergabe der ID
	private BBingoDB db;
	private EditText editTitle;
	private EditText editWords;
	private long id; // record ID des bearbeiteten Eintrags (0 für neuen Eintrag)

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.editwordlist);
	    editTitle=(EditText) findViewById(R.id.EditTitle);
	    editWords=(EditText) findViewById(R.id.EditWords);
	    db=new BBingoDB(this); // siehe onDestroy
	}
	@Override
	protected void onResume() {
		super.onResume();
		Bundle extras = getIntent().getExtras();
		if(extras!=null && extras.containsKey(ID))
			{
			// Bearbeite existierende Wortliste (sonst: neue Wortliste anlegen)
			id=extras.getLong(ID);
			WordList wordlist = db.getWordList(id);
			editTitle.setText(wordlist.title);
			editWords.setText(wordlist.words);
			editWords.requestFocus();
			}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		// Daten speichern
		WordList wordlist=new WordList();
		wordlist.id=id;
		wordlist.title=editTitle.getText().toString().trim();
		wordlist.words=editWords.getText().toString().trim();
		if(wordlist.title.length()!=0 && wordlist.words.length()!=0) {			
			id=db.setWordList(wordlist); // setze aktuelle ID (z.B. wenn eine neue Wortliste gespeichert wird)
			Prefs.setID(this, id);
		}
	}
	
	@Override
	protected void onDestroy() {
		db.close();
		super.onDestroy();
	}

}
