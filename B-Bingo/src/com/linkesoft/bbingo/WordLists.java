package com.linkesoft.bbingo;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;

/**
 * Verwalte beliebig viele Wortlisten in einem ListView: Auswahl, Anlegen und Löschen. 
 */
public class WordLists extends ListActivity {

	public static final String ID="id"; // Parameter zur Übergabe der ausgewählten ID 
	private static final int ADD = Menu.FIRST;
	private static final int DELETE = Menu.FIRST + 1;
	private BBingoDB db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    db = new BBingoDB(this);
	    Cursor cursor=db.getWordListsCursor(); // Cursor über alle Einträge, wird vom ListAdapter verwaltet und geschlossen
	    setListAdapter(
	    		new SimpleCursorAdapter(this, 
	    			android.R.layout.simple_list_item_1, // Layout für Listeneintrag, hier ein einfacher TextView
	    			cursor, 
	    			new String[]{BBingoDB.TITLE}, new int[]{android.R.id.text1}) // Mapping zwischen ID im Eintragslayout und Feld in der Datenbank
	    		);
	    registerForContextMenu(getListView()); // zeige popup menü für Liste
	}
	
	@Override
	protected void onResume() {
		((CursorAdapter)getListAdapter()).getCursor().requery(); // frische Liste auf    		
		super.onResume();
	}
	
	@Override
	protected void onDestroy() {
		db.close();
		super.onDestroy();
	}

/**
 * Benutzer klickt auf einen Eintrag in der Liste
 */
	@Override
	protected void onListItemClick(final ListView l, final View v,
			final int position, final long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent=new Intent();
		intent.putExtra(ID, id);
		setResult(RESULT_OK,intent);
		finish(); // beende Aktivität erfolgreich
	}

/**
 * Erzeuge Context(Popup)-Menü, durch langen Tap auf einen Listeintrag ausgewählt
 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// DELETE
        if(v.equals(getListView()))
    	{
       	AdapterContextMenuInfo info=(AdapterContextMenuInfo) menuInfo;
    	if(info.id>=0)
    		{
        	menu.setHeaderTitle(db.getWordList(info.id).title);
        	menu.add(0, DELETE, 0, R.string.Delete);
        	return;
    		}
    	}
		super.onCreateContextMenu(menu, v, menuInfo);
	}

/**
 * Context-Menü für List-Eintrag wurde durch Tap-and-Hold ausgewählt
 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()) {
    	case DELETE:
    		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    		// hier ggf. noch Bestätigungs-Popup per AlertBuilder zeige
    		db.removeWordList(info.id); // lösche Wortliste
    		((CursorAdapter)getListAdapter()).getCursor().requery(); // frische Liste auf 
    		break;
		}
		return super.onContextItemSelected(item);
	}
// normales Menü, zum Anlegen neuer Wortlisten	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(0, ADD, 0, R.string.Add).setIcon(android.R.drawable.ic_menu_add);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
    	case ADD:
    		startActivity(new Intent(this,EditWordList.class)); // keine ID übergeben = neue Wortliste
    		break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
		
}
