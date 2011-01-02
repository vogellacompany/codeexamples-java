package com.linkesoft.bbingo;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.linkesoft.bbingo.BBingoDB.WordList;

/**
 * Hauptklasse-Activity der B-Bingo-Anwendung,
 * zeigt ein Gitter von 5x5 Buttons zur Auswahl von Buzzwörtern.
 * 
 * Siehe {@link http://de.wikipedia.org/wiki/Buzzword-Bingo}
 * 
 * @author Andreas Linke
 *
 */
public class Main extends Activity {
	private static final int SIZE = 5;
	// menu IDs
	private static final int EDIT = Menu.FIRST; // akt. Wortliste bearbeiten
	private static final int LISTS = Menu.FIRST+1; // Wortlisten auswählen/anlegen/löschen
	
	private String currentWordlist=null;

	private BBingoDB db;

/**
 * Startmethode, wird beim ersten Start der Activity aufgerufen,
 * auch bei Änderung der Orientierung (horizontal/vertikal).
 * Definiere GUI-Layout und verknüpfe Event-Handler.	
 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		boolean staticUI=false; // zwei Wege, um das UI zu erzeugen
		if(staticUI)
			setContentView(R.layout.main); // aus XML
		else
			createUIDynamically(); // dynamisch			
		// verknüpfe Buttons mit Aktionen	
		for (int y = 0; y < SIZE; y++)
			for (int x = 0; x < SIZE; x++) {
				Button button = (Button) findViewById(getButtonID(x, y));				
				button.setOnClickListener(onButtonClick); 
			}
		db=new BBingoDB(this); // öffne Datenbank, wird in onDestroy wieder geschlossen
	}
	
	/**
	 * Wird nach onCreate und nach dem Beenden einer anderen Activity aufgerufen.
	 * Lade die aktuelle Wortliste. Wenn sie nicht mit der aktuellen Liste übereinstimmt,
	 * mische und setze alle Buttons neu. 
	 */
	@Override
	protected void onResume() {
		super.onResume();
		// hole aktuelle Wortlist aus der DB
		// falls noch keine Liste gespeichert wurde,
		// wird auf eine eingebaute Standard-Liste zurückgegriffen
		WordList wordlist = db.getWordList(Prefs.getID(this));
		if (wordlist == null) {
			// keine Wortliste zur ID gefunden, nimm erste
			long id = db.getFirstWordListID();
			if (id == 0) {
				// keine Wortliste gespeichert
				id = db.createDefaultWordList(); // erzeuge Standard-Liste
			}
			Prefs.setID(this, id);
			wordlist = db.getWordList(id);
		}
		if (currentWordlist == null || !currentWordlist.equals(wordlist.words)) {
			// Wortliste hat sich geändert, mische und setze Button-Titel
			// entsprechend
			currentWordlist = wordlist.words;
			shuffleWords();
		}
	}
	
	@Override
	protected void onDestroy() {
		db.close();
		super.onDestroy();
	}
/**
 * Button-Klick-Handler für alle Buttons	
 */
	final View.OnClickListener onButtonClick=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			toggleButtonState((Button) v);
			checkForBingo();
		}
	};
	
/**
 * Anlegen von Menüs (die per Menü-Taste aufgerufen werden)	
 */
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		menu.add(Menu.NONE, EDIT, Menu.NONE, R.string.EditWords);
		menu.add(Menu.NONE, LISTS, Menu.NONE, R.string.WordLists);
		return super.onCreateOptionsMenu(menu);
	};

/**
 * Auswahl eines Menü-Eintrags	
 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case EDIT: {
				Intent intent = new Intent(this, EditWordList.class);
				intent.putExtra(EditWordList.ID, Prefs.getID(this)); // übergebe aktuelle ID
				startActivity(intent);
			}
			break;
		case LISTS:
			startActivityForResult(new Intent(this, WordLists.class), LISTS);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

/**
 * Wird nach Beenden einer per <code>startActivityForResult</code> gestarteten 
 * Activity gerufen	
 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == LISTS && resultCode == RESULT_OK) {
			// eine andere Liste wurde ausgewählt
			long id = data.getExtras().getLong(WordLists.ID);
			Prefs.setID(this, id);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

/**
 * Liefere numerische Button-ID per Reflection aus den Konstanten der R-Klasse
 * @param x Spaltennummer 0..4
 * @param y Zeilennummer 0..4
 * @return
 */
	private int getButtonID(int x, int y) {
		try {
			// lookup id by reflection
			// button id is a static int defined in R.id, e.g. R.id.Button01
			String buttonid = "Button" + y + "" + (x + 1); // e.g. "Button01" 
			return R.id.class.getField(buttonid).getInt(null);
		} catch (Exception e) {
			// reflection lookup could throw exceptions e.g. if the button is
			// not found
			throw new RuntimeException("Internal error", e);
		}
	}

	private boolean isButtonSet(Button button) {
		return button.getTag() != null;
	}

/**
 * Ändere Zustand eines Buttons von nicht gesetzt auf gesetzt 
 * und umgekehrt. Dabei wird die Button-Farbe entsprechend angepasst. 
 * @param button der zu ändernde Button
 */
	private void toggleButtonState(Button button) {
		if (isButtonSet(button)) {
			button.getBackground().clearColorFilter();
			button.setTag(null);
		} else {
			// Button-Hintergrund-Farbe wird über einen Filter geändert 
			button.getBackground().setColorFilter(Color.GREEN,
					PorterDuff.Mode.MULTIPLY);
			button.setTag("x"); // verwende die frei tag-Property zum Speichern des Zustands
		}
	}

/**
 * Prüfe auf vollständige ausgewählte Button-Reihe 
 * vertikal, horizontal und die beiden Diagonalen 
 */
	private void checkForBingo() {
		boolean bingo = false;
		int x, y;
		// vertikal
		for (x = 0; x < SIZE && !bingo; x++) {
			for (y = 0; y < SIZE; y++) {
				Button button = (Button) findViewById(getButtonID(x, y));
				if (!isButtonSet(button))
					break;
			}
			bingo = (y == SIZE);
		}
		// horizontal
		for (y = 0; y < SIZE && !bingo; y++) {
			for (x = 0; x < SIZE; x++) {
				Button button = (Button) findViewById(getButtonID(x, y));
				if (!isButtonSet(button))
					break;
			}
			bingo = (x == SIZE);
		}
		// diagonal
		for (x = 0; x < SIZE && !bingo; x++) {
			Button button = (Button) findViewById(getButtonID(x, x));
			if (!isButtonSet(button))
				break;
		}
		if (x == SIZE)
			bingo = true;
		for (x = 0; x < SIZE && !bingo; x++) {
			Button button = (Button) findViewById(getButtonID(x, SIZE - 1 - x));
			if (!isButtonSet(button))
				break;
		}
		if (x == SIZE)
			bingo = true;

		if (bingo) {
			// Gewonnen, zeige ein einfaches Popup.
			// Die Methoden geben jeweils wieder das Builder-Objekt zurück
			// und lassen sich so leicht verketten
			new AlertDialog.Builder(this)
					.setTitle(R.string.BBingoWonTitle)
					.setMessage(R.string.BBingoWon)
					.setPositiveButton(android.R.string.ok, null)
					.show();
		}
	}
/**
 * Worte der Wortliste mischen und auf die Buttons verteilen
 */
	private void shuffleWords()
	{
		List<String> words=BBingoDB.stringToList(currentWordlist);
		// Liste darf nicht leer sein
		if(words.size()==0)	
			words.add(""); 
		// Liste mischen und den Buttons zuweisen
		Collections.shuffle(words);
		int i=0;
		for (int x = 0; x < SIZE; x++) 
			for (int y = 0; y < SIZE; y++)
			{
				Button button=(Button) findViewById(getButtonID(x, y));
				if(i>=words.size())
					{
					// overflow
					Collections.shuffle(words);
					i=0;
					}
				button.setText(words.get(i++)); 
			}				
	}
/**
 * Alternative zur XML-basierten GUI-Definition	
 */
	private void createUIDynamically() {
		// wichtig: für LayoutParams immer die passende Layout-Klasse verwenden
		LinearLayout main = new LinearLayout(this);
		main.setOrientation(LinearLayout.VERTICAL);
		for (int y = 0; y < SIZE; y++) {
			LinearLayout row = new LinearLayout(this);
			for (int x = 0; x < SIZE; x++) {
				Button button = new Button(this);
				// die ID wird hier der Einfachheit halber aus den generierten R-Konstanten
				// gesetzt, damit das restliche Coding weiter funktioniert.
				button.setId(getButtonID(x, y)); 
				button.setWidth(0);
				button.setHeight(0);
				row.addView(button, new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.FILL_PARENT, 1)); // weight 1
			}
			main.addView(row, new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1)); // jede Zeile soll komplette Breite ausfüllen 
		}
		setContentView(main);
	}
	

}