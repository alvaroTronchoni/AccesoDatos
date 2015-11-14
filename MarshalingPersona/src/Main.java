import java.io.File;
import java.util.ArrayList;

import javax.xml.transform.TransformerException;

public class Main {

	public static void main(String[] args) {
		ArrayList<Libro> libros = new ArrayList<Libro>();
		ArrayList<String> nombre1 = new ArrayList<String>();
		ArrayList<String> nombre2 = new ArrayList<String>();
		nombre1.add("J.K");
		nombre1.add("Rowling");
		nombre2.add("J.K");
		nombre2.add("Tolkien");
		libros.add(new Libro("harry potter", nombre1, 1999, "Anaya", 888));
		libros.add(new Libro("El señor de los anillos", nombre2, 1925, "Anaya", 1025));
		
		Marshaller mar = new Marshaller(libros);
		
		mar.crearDocumento();
		mar.crearArbolDOM();
		
		File file = new File("libros2.xml");
		
		try {
			mar.escribirDocumentoXML(file);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
