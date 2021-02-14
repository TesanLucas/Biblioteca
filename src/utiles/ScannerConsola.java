package utiles;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ScannerConsola {

	private BufferedReader reader;
	private String entrada;

	public ScannerConsola() {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	public String nextLine() {
		try {
			entrada = reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entrada;
	}
	
	
	
	public long nextLong() {
		try {
			entrada = reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(entrada);
	}
}
