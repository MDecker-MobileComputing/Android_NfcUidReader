package de.mide.nfc.uidreader;

import android.app.Activity;
import android.os.Bundle;


/**
 * Demo-App zu Auslesen/Erkennen von NFC-Tags bzw. der UID.
 * Es werden aber nur NFC-Tags erkannt, die den MIME-Type "application/abc" haben
 * (siehe Manifest-Datei).
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends Activity {
	
	/**
	 * Lifecycle-Methode, wir nur einmal beim Erzeugen der
	 * Activity für ein Bildschirm-Format (hoch/quer) aufgerufen.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);				
	}
	
};
