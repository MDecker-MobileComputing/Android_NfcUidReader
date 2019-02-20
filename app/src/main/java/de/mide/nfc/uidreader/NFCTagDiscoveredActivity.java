package de.mide.nfc.uidreader;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Activity-Klasse, die aufgerufen wird, wenn ein NFC-Tag mit dem MIME-Type <i>"application/abc"</i>
 * an diese App weitergeleitet wurde. 
 * <br><br>
 *
 * Bitte den Intent-Filter für diese Activity in der Manifest-Datei beachten!
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class NFCTagDiscoveredActivity extends Activity {

	/** TextView zur Ausgabe der Informationen zu den erkannten Tags. */
	protected TextView _textView = null;
	
	
	/**
	 * Aufbau der UI und Aufruf der Methode zu eigentlichen Auswerten 
	 * der NFC-Daten.
	 * 
	 * Diese Methode wird bei jedem neu erkannten Tag aufgerufen,
	 * es wird also für jeden Tag diese Activity neu erzeugt!
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nfctag_discovered);

		_textView = findViewById(R.id.textviewMitErkanntenUIDs);
		
		nfcTagAuswerten();
	}
	
	
	/** 
	 * Methode, die die aus dem Intent-Objekt ausgelesenen Informationen 
	 * auswertet und an das TextView-Element anhängt.
	 */
	protected void nfcTagAuswerten() {
		
		Intent intent = getIntent();
		
		String actionStr = intent.getAction(); // erwartet: android.nfc.action.NDEF_DISCOVERED
		if ( actionStr.equals(NfcAdapter.ACTION_NDEF_DISCOVERED) ) {
			
			Tag tag = getIntent().getParcelableExtra( NfcAdapter.EXTRA_TAG );
			byte[] uidBytes = tag.getId(); 
			// UID sollte für einen Typ-2-Tag eine Länge von 7 Bytes haben.
			// Zur Erkennung muss man Tags verwenden, bei denen die UID nicht geändert
			// werden kann (z.B. NTAG203 von NXP).
			
			String uidString = uid2string( uidBytes );
			
			_textView.append("NFC-Tag gefunden:\n" + uidString + "\n");			
		}		
	}
	

	/**
	 * Methode zur Umwandlung eines Byte-Arrays in einen String, der
	 * die Zahlen als Hex-Ziffern enthält (zweistellige Hex-Ziffern
	 * durch Doppelpunkte getrennt).
	 * 
	 * @param bytes   Byte-Array, der als NFC-Tag-UID ausgelesen wurde.
	 * 
	 * @return String-Darstellung in Hex-Format, z.B. <i>FF:AA:00</i>.
	 */
	public static String uid2string(byte[] bytes) {
		
		StringBuffer sb = new StringBuffer();
		
		int len = bytes.length;
		
		if (len == 0) return "<leer>";
		
		int counter = 0;
		
		for (byte currentByte : bytes) {
			String hexStr = String.format("%02X", currentByte);
			sb.append(hexStr);
			
			// ggf. noch Doppelpunkt als Trenner zwischen zwei Bytes einfügen
			counter++;
			if (counter < len) 
				sb.append(':');
		}
		
		return sb.toString();
	}
	
};
