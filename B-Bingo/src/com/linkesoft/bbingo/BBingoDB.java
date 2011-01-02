package com.linkesoft.bbingo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Datenbankhilfsklasse, kapselt alle DB-Zugriffe
 */
public class BBingoDB extends SQLiteOpenHelper {

	public final static String MAIN_TABLE="wordlists"; // Tabellenname
	// Datenbankfelder
	private final static String ID="_id";
	public final static String TITLE="title";
	private final static String WORDS="words"; // durch \n getrennte Wortliste
	
/**
 * Java-Objekt für unsere Daten.
 * Aus Performance-Gründen wird auf getter/setter verzichtet
 */
	public static class WordList {
		public long id;
		public String title;
		public String words;
	}

    private static final String MAIN_DATABASE_CREATE =
        "create table "+MAIN_TABLE
        		+" (_id integer primary key autoincrement, "
        		+ TITLE+" text not null,"
        		+ WORDS+" text not null);";
	
	// hard-codierte Liste von Standard-Buzz-Wörtern 
	private static final List<String> DEFAULT_WORDS=new ArrayList<String>(
			Arrays.asList(
					"Android","Architektur",
					"Big Picture","Benchmark",
					"Context","Core",
					"Gadget",
					"Hut aufhaben",
					"Internet","iPhone",
					"Kundenorientiert",
					"Mobile irgendwas",
					"Netzwerk",
					"People","pro-aktiv",
					"Qualität",
					"Ressourcen","Revolution","Runde drehen",
					"Sozial","Synergie",
					"Technologie",
					"Überall",
					"Values","Virtuell","Vision",
					"Weltneuheit","Web 2.0","Win-Win",
					"Zielführend"
			));
	
    public BBingoDB(Context ctx)     {
    	super(ctx,MAIN_TABLE, null, 1); // DB version 1
    }

    /**
     * Legt die Datenbank(en) an
     */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(MAIN_DATABASE_CREATE);
		// ggf. weitere Tabellen anlegen
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// wird gerufen, wenn die Datenbankversion niedriger 
// als die im Constructor angegebene Version ist
// hier können Tabellen erweitert (ALTER TABLE) und ggf. Daten umkopiert werden 
	}
	
	// Wichtig: vor dem endgültigen Verlassen der Activity immer close() aufrufen
	// Methode ist hier nur zu Dokumentationszwecken überschrieben
	@Override
	public synchronized void close() {
		super.close();
	}
	
/**
 * Gibt Wortliste zur id zurück	
 * @param id Schlüsselfeld in der Datenbank
 * @return Wortliste oder null falls nicht gefunden
 */
	public WordList getWordList(long id){
		Cursor c=null;
		try {
			c=getReadableDatabase().query(MAIN_TABLE, new String[]{ID,TITLE,WORDS}, 
					ID+"=?", new String[]{String.valueOf(id)}, null,null,null);
			if(!c.moveToFirst())
				return null; // keine Wortliste zur ID gefunden 
			WordList wordlist=new WordList();
			wordlist.id=id;
			wordlist.title=c.getString(c.getColumnIndexOrThrow(TITLE));
			wordlist.words=c.getString(c.getColumnIndexOrThrow(WORDS));
			return wordlist;
		}
		finally {
			if (c != null)
				c.close(); // Cursor sollte stets in einem finally-Block geschlossen werden
		}
	}
	
/**
 * Speichere Wortliste. Falls id 0, wird ein neuer Eintrag angelegt, anderenfalls
 * wird der Eintrag aktualisiert.	
 * @param wordlist zu speichernde Wortlist
 * @return id der gespeicherten Wortliste
 */
	public long setWordList(WordList wordlist) {
		ContentValues values=new ContentValues();
		if(wordlist.id!=0)
			values.put(ID, wordlist.id);
		values.put(TITLE, wordlist.title);
		values.put(WORDS, wordlist.words);
		if(wordlist.id==0) {			
			wordlist.id=getWritableDatabase().insert(MAIN_TABLE,null,values);
		}
		else {
			getWritableDatabase().update(MAIN_TABLE, values, 
					ID+"=?", new String[]{String.valueOf(wordlist.id)});
		}
		return wordlist.id;
	}
/**
 * Lege Standard-(Beispiel)Wortliste an	
 * @return id der gespeicherten Wortliste
 */
	public long createDefaultWordList()
	{
		return setWordList(getDefaultWordList());
	}
	
	private WordList getDefaultWordList() {
		WordList wordlist = new WordList();
		wordlist.title = "IT";
		wordlist.words = listToString(DEFAULT_WORDS);
		return wordlist;
	}
/**
 * Liefere ID der ersten gespeicherten Wortliste zurück, 
 * als Fallback, falls die aktuelle Wortliste nicht mehr existiert	
 * @return ID der Wortliste oder 0 falls Tabelle leer
 */
	public long getFirstWordListID() {
		Cursor c = null;
		try {
			c = getReadableDatabase().query(MAIN_TABLE, new String[] { ID },
					null, null, null, null, null, null);
			if (c.moveToFirst())
				return c.getLong(c.getColumnIndexOrThrow(ID));
			else
				return 0;
		} finally {
			if (c != null && !c.isClosed()) {
				c.close();
			}
		}
	}
/**
 * Zugriff auf alle Wortlisten, sortiert nach Titel	
 * @return Cursor über die Daten, Aufrufer muss Cursor selbst schließen
 */
	public Cursor getWordListsCursor() {
		return getReadableDatabase().query(MAIN_TABLE, new String[]{ID,TITLE}, 
				null, null, null,null,
				TITLE+" COLLATE LOCALIZED"); // sortiere nach Titel, Sortierreihenfolge der aktuellen Sprache
	}
	
	public void removeWordList(long id) {
		getWritableDatabase().delete(MAIN_TABLE, "_id=" + id, null);
	}
	
	// Hilfsfunktionen zur Konvertierung von Java-Listen in \n-getrennte Strings und umgekehrt
	public static String listToString(List<String> list)
	{
		Iterator<String> iter = list.iterator();
		StringBuffer words=new StringBuffer(iter.next());
		while(iter.hasNext())
			words.append("\n").append(iter.next());
		return words.toString();
	}
	public static List<String> stringToList(String string)
	{
		return Arrays.asList(string.split("\n"));
	}
}
