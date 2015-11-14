import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

	private Document dom = null;
	private ArrayList<Libro> libros;
	
	public Parser() {
		libros = new ArrayList<Libro>();
	}
	
	public void parseFicheroXml(String fichero){
		//creamoos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try{
			//creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parseamos el XML y obtenemos una representacion DOM
			this.dom = db.parse(fichero);
		} catch(ParserConfigurationException pce){
			pce.printStackTrace();
		} catch(SAXException se){
			se.printStackTrace();
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	public void paseDocument(){
		//obtenemos el elemento raiz
		Element docFile = dom.getDocumentElement();
		//obtenemos el nodelist de este elemento
		NodeList nl = docFile.getElementsByTagName("libro");
		if(nl != null && nl.getLength() > 0){
			for(int i = 0; i < nl.getLength();i++){
				//obtenemos un elemento de la lista
				Element el = (Element) nl.item(i);
				//obtenemos una persona
				Libro p = getLibro(el);
				//lo añadimos al array
				libros.add(p);
			}
		}
	}
	
	private Libro getLibro (Element personaElem){
		//obtenemos el nombre y la edad del elemento dado
		String titulo = getTextValue(personaElem,"titulo");
		ArrayList<String> autor = getTextAutor(personaElem, "autor");
		int año = getAtributeValue(personaElem, "titulo","anyo");
		String editorial = getTextValue(personaElem, "editor");
		int paginas = getIntValue(personaElem, "paginas");
		//creamos la persona con los nuevos elementos
		Libro p = new Libro(titulo, autor, año, editorial, paginas);
		
		return p;
	}
	
	private int getAtributeValue (Element personaElem,String dato,String atributo){
		int datoDev = 0;
		//creamos  la lista de nodos en la que esta nuestro dato(en este caso siempre 1)
		NodeList nl = personaElem.getElementsByTagName(dato);
		//comprovamos que exista el dato
		if(nl !=null && nl.getLength() > 0){
			//creamos un Element para extrar en él el dato
			Element el = (Element) nl.item(0);
			//extraemos este dato de el  Element
			datoDev = Integer.parseInt(el.getAttribute(atributo));
		}
		return datoDev;
	}
	
	private ArrayList<String> getTextAutor(Element autorElem,String dato){
		ArrayList<String> datoDev = new ArrayList<String>();
		//creamos  la lista de nodos en la que esta nuestro dato(en este caso siempre 1)
		NodeList nl = autorElem.getElementsByTagName(dato);
		//comprovamos que exista el dato
		if(nl !=null && nl.getLength() > 0){
			//creamos un Element para extrar en él el dato
			Element el = (Element) nl.item(0);
			
			datoDev = getTextValueAutor(el, "nombre");
			
		}
		return datoDev;
	}
	
	private ArrayList<String> getTextValueAutor (Element personaElem,String dato){
		ArrayList<String> datoDev = new ArrayList<String>();
		//creamos  la lista de nodos en la que esta nuestro dato(en este caso siempre 1)
		NodeList nl = personaElem.getElementsByTagName(dato);
		//comprovamos que exista el dato
		if(nl !=null && nl.getLength() > 0){
			for(int x = 0;x<nl.getLength();x++){
				//creamos un Element para extrar en él el dato
				Element el = (Element) nl.item(x);
				//extraemos este dato de el  Element
				datoDev.add(el.getFirstChild().getNodeValue());
			}
		}
		return datoDev;
	}
	
	private String getTextValue (Element personaElem,String dato){
		String datoDev = null;
		//creamos  la lista de nodos en la que esta nuestro dato(en este caso siempre 1)
		NodeList nl = personaElem.getElementsByTagName(dato);
		//comprovamos que exista el dato
		if(nl !=null && nl.getLength() > 0){
			//creamos un Element para extrar en él el dato
			Element el = (Element) nl.item(0);
			//extraemos este dato de el  Element
			datoDev = el.getFirstChild().getNodeValue();
		}
		return datoDev;
	}
	
	private int getIntValue (Element personaElem,String dato){
		//utilizamos la funcion getTextValue y la parseamos a un int que es el que devolvemos
		return Integer.parseInt(getTextValue(personaElem,dato));
	}
	
	
	/**
	 * metodo que llamara al metodo imprimir de todos los libros para mostrarlos todos
	 */
	public void print(){
		Iterator it = libros.iterator();
		while(it.hasNext()){
			Libro p = (Libro) it.next();
			p.print();
		}
	}

}
