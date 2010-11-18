// 3 Abkürzungen für oft benötigte DOM-Funktionen:
function $(str, nr) {return document.getElementsByTagName(str)[nr];}
// findet ein Element
function c(str) {return document.createElement(str);}
// erzeugt ein Element
function txt(str) {return document.createTextNode(str);}
// erzeugt einen Textknoten
var abschnitt, neueintrag, button, startnachricht, aufgeraeumt, storage, d, eingabe, autor, autor_db; // später benötigte Variablen
// Wochentage und Kalendermonate für Datumsausgabe:
var Wochentage = new Array('Sonntag', 'Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag');
var Monate = new Array('Januar', 'Februar', 'März', 'April', 'Mai', 'Juni', 'Juli', 'August', 'September', 'Oktober', 'November', 'Dezember');

window.onload = init; // Nach dem Laden des Dokuments geht's mit init() los

function init() { // startet nach dem Laden des HTML-Dokuments
 // liest einige HTML-Elemente ein:
 abschnitt = $('section', 0);
 neueintrag = $('textarea', 0);
 button = $('input', 0);
 startnachricht = neueintrag.value; // speichert den Text "Schreib hier!"
 neueintrag.onclick = aufraeumen; // ... der beim Anklicken ...
 neueintrag.onfocus = aufraeumen; // ... und beim Fokussieren gelöscht wird
 button.onclick = speichern; // löst die Speicherfunktion aus
 // Prüft, ob Speicherung möglich ist (-> storage = true):
 if (typeof(localStorage) == 'undefined' || typeof(localStorage) == 'unknown') {
  alert('Speichern nicht möglich.');
 } else {
  storage = true;
  lesen(); // liest die Datenbankeinträge aus
 }
}

function aufraeumen() { // nach Anklicken oder Fokussieren des Eingabefelds
 // Der (Standard-)Text soll nur gelöscht werden, wenn nicht "aufgeraeumt" ist:
 if (aufgeraeumt) return;
 neueintrag.value = '';
 aufgeraeumt = true;
}

function speichern() { // nach Anklicken des Speicher-Buttons
 if (!storage) return; // Abbruch, wenn Speichern nicht möglich ist
 eingabe = neueintrag.value; // eingegebener Text
 if (eingabe == '' || eingabe == startnachricht) return; // es muss etwas drinstehen
 var datum = new Date();
 d = datum.getTime(); // aktuelles Datum in Form einer Ganzzahl
 autor = $('h2', 0).firstChild.nodeValue; // Inhalt der <h2>
 autor_db = localStorage.getItem('autor'); // Datenbankeintrag "autor"
 if (navigator.geolocation) { // ist Ortung möglich?
  // versuch zu orten. Wenn es klappt: adresse_ermitteln(), sonst adresse_fehler(); Abbruch nach 1,5 Sekunden:
  navigator.geolocation.getCurrentPosition(adresse_ermitteln, adresse_fehler, {timeout:1500});
 } else { // wenn Ortung nicht möglich ist, speichere sofort:
  daten_schreiben();
 }
}

function daten_schreiben() { // beendet den Speichervorgang: speichern() leitet direkt oder über den Umweg adresse_ermitteln() hierher weiter
 try { // fängt eventuelle Fehler ab
  // speichere den <h2>-Inhalt, wenn er sich geändert hat:
  if (autor != autor_db) localStorage.setItem('autor', autor);
  localStorage.setItem(d, eingabe); // speichert den eingegebenen Text
  neueintrag.value = startnachricht; // setzt den <textarea>-Inhalt zurück
  aufgeraeumt = false; // stellt den Ausgangszustand wieder her
 } catch (e) { // wenn Fehler aufgetreten sind:
  alert('Fehler beim Speichern: ' + e);
 }
 lesen(); // lies die aktualisierte Datenbank aus
}

function lesen() { // beim Start, nach dem Speichern oder Löschen ausgelöst
 if (!storage) return;
 autor = localStorage.getItem('autor'); // sucht in der Datenbank nach "autor"
 // gibt es einen Eintrag, ersetze damit den Inhalt von <h2>:
 if (autor) $('h2', 0).firstChild.nodeValue = autor;
 // lösche eine vom letzten lesen() eventuell vorhandene <ol>:
 if ($('ol', 0)) abschnitt.removeChild($('ol', 0));
 var ol = c('ol'); // erzeuge eine neue <ol>
 // lies die Schlüssel der Datenbank in das Array keys ein:
 var keys = Array();
 for (var i = 0; i < localStorage.length; i++) {
  keys.push(localStorage.key(i));
 }
 keys = keys.sort(); // sortiert die Schlüssel
 // durchläuft die Schlüssel (in umgekehrter Reihenfolge):
 for (var i = keys.length; i-- > 0;) {
  // Lies den Datenbankeintrag in schluessel und wert ein:
  var schluessel = keys[i];
  var wert;
  // wenn wert leer ist, geh zum nächsten Eintrag:
  if (!(wert = localStorage.getItem(schluessel))) continue;
  // trennt den Datenbankeintrag an ":ORT:" auf; hier steht das Ergebnis der Ortung:
  var tmp = wert.split(':ORT:', 2);
  wert = tmp[0]; // Eingabetext
  var ort = tmp[1]; // Ortung (falls vorhanden)
  schluessel = parseInt(schluessel);
  if (isNaN(schluessel)) continue; // schluessel muss ein Datum im Zahlenformat sein
  // erzeuge folgende Struktur:
  // <li>
  //   <div id="schluessel">
  //     <h3>Datum<br/>Ort</h3>
  //     <p>Eintrag</p>
  //   </div>
  // </li>
  var li = c('li');
  var div = c('div');
  var h3 = c('h3');
  var p = c('p');
  // mach aus dem Datum im Zahlenformat ein menschenlesbares:
  var dat = new Date(schluessel);
  var dat_wochentag = Wochentage[dat.getDay()];
  var dat_kalendertag = dat.getDate();
  var dat_monat = dat.getMonth();
  var dat_jahr = dat.getFullYear();
  var dat_stunde = dat.getHours();
  var dat_minute = dat.getMinutes();
  if (dat_minute < 10) dat_minute = '0' + dat_minute;
  var aenderungsdatum = dat_wochentag + ', ' + dat_kalendertag + '. ' + Monate[dat_monat] + ' ' + dat_jahr + ' um ' + dat_stunde + ':' + dat_minute;
  // beim ersten Durchlauf passen wir auch das Datum im <footer> an:
  if (!letzte_aenderung) {
   var letzte_aenderung = aenderungsdatum;
   if (++dat_monat < 10) dat_monat = '0' + dat_monat;
   if (dat_kalendertag < 10) dat_kalendertag = '0' + dat_kalendertag;
   if (dat_stunde < 10) dat_stunde = '0' + dat_stunde;
   var letzte_aenderung_pd = dat_jahr + '-' + dat_monat + '-' + dat_kalendertag + 'T' + dat_stunde + ':' + dat_minute;
   // aktualisiere den Inhalt von <time> und das datetime-Attribut:
   var time = $('time', 0);
   time.firstChild.nodeValue = letzte_aenderung;
   time.setAttribute('datetime', letzte_aenderung_pd);
  }
  // bau die erzeugten HTML-Elemente und die Inhalte in die Seite ein:
  h3.appendChild(txt(aenderungsdatum));
  if (ort) {
   h3.appendChild(c('br'));
   h3.appendChild(txt('in ' + ort));
  }
  p.appendChild(txt(wert));
  div.appendChild(h3);
  div.appendChild(p);
  div.setAttribute('id', schluessel);
  li.appendChild(div);
  ol.appendChild(li);
 }
 // füg die <ol>-Liste (wenn sie einen Inhalt hat) ans Ende von <section> ein:
 if (li) abschnitt.appendChild(ol);
 // verbinde jeden Listeneintrag über den Doppelklick mit der Funktion loeschen():
 var eintraege = ol.getElementsByTagName('div');
 for (var i = 0; i < eintraege.length; i++) eintraege[i].ondblclick = loeschen;
}

function loeschen() { // nach Doppelklick auf einen Eintrag
 if (!storage) return;
 // zeig zur Sicherheit einen Bestätigungsdialog:
 var frage = confirm("Wollen Sie diesen Eintrag wirklich löschen?");
 if (frage) {
  localStorage.removeItem(this.id); // der Inhalt von <div id="..."> ist der Datenbankschlüssel
  lesen(); // lies die aktualisierte Datenbank neu ein
 }
}

function adresse_ermitteln(pos) { // Callback-Funktion, beim speichern() ausgelöst
 var geo = new google.maps.Geocoder(); // Geocoder-Objekt aus dem Google-Maps-API
 var coords = new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude); // Koordinaten-Objekt, gefüttert mit den Daten der Browser-Ortung
 // übergib die Koordinaten an das Google-Maps-API:
 geo.geocode({'latLng': coords}, function(results, status) {
  var adresse = (status == google.maps.GeocoderStatus.OK)? results[0].formatted_address : 'Adresse nicht ermittelt: ' + status; // adresse enthält entweder eine formatierte Straßenadresse oder einen Fehlerhinweis
  eingabe += ':ORT:' + adresse; // hängt die Adresse an den Eingabetext, getrennt durch ":ORT:"
  daten_schreiben(); // schreibt die Daten in die Datenbank
 });
}

function adresse_fehler(err) { // Geolocation ist bei der Ortung gescheitert
 eingabe += ':ORT:Probleme bei der Ortung ';
 // err.message sollte einen Fehlertext enthalten, das klappt in der Praxis jedoch nicht immer. Zuverlässiger ist err.code:
 switch(err.code) {
  case 1 : eingabe += '(keine Erlaubnis)'; break;
  case 2 : eingabe += '(Server konnte nicht orten)'; break;
  case 3 : eingabe += '(Timeout)'; break;
  default : eingabe += '(keine Angabe)'; break;
 }
 daten_schreiben(); // schreibt die Daten in die Datenbank
}