package services;

import jakarta.xml.ws.Endpoint;
import persistencia.AutorDAO;

public class AutorService {

	public static void main(String[] args) {

		/**
		 * con el web service explorer de eclipse, ni con boomerang me funcionaban el metodo de agregar Autor
		 * pero con el SoapUI me anda perfecto, asique supongo que debe ser problema del ide y del boomerang!
		 */
		
		Endpoint endPoint = Endpoint.publish("http://localhost:8084/Autor", new AutorDAO());

	}
	
	

}
