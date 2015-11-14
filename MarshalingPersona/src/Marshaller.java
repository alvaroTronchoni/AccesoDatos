import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Marshaller {

	private Document dom = null;
	private ArrayList<Libro> libros = null;
	
	public Marshaller(ArrayList<Libro> p) {
		this.libros = p;
	}
	
	public void crearDocumento(){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.newDocument();
		}catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}
	}
	
	public void  crearArbolDOM(){
		Element docEle = dom.createElement("Libros");
		dom.appendChild(docEle);
		
		Iterator it = libros.iterator();
		while(it.hasNext()){
			Libro e = (Libro) it.next();
			Element personaEle = setPersona(e);
			docEle.appendChild(personaEle);
		}
	}
	
	private Element setPersona(Libro p){
		Element LibroEle = dom.createElement("libro");
	
		//creamos el campo titulo
		Element tituloEle = dom.createElement("titulo");
		Text tituloTexto = dom.createTextNode(p.getTitulo());
		//creamos el campo año de pueblicacin
		tituloEle.setAttribute("anyo", Integer.toString(p.getAñoPubli()));
		tituloEle.appendChild(tituloTexto);
		LibroEle.appendChild(tituloEle);
		//creamos el campo autor
		Element autorEle = dom.createElement("autor");
		Element autorNombreEle = dom.createElement("nombre");
		Text nombreAutorTexto = dom.createTextNode(p.getAutores().get(0));
		autorNombreEle.appendChild(nombreAutorTexto);
		Element autorApellidoEle = dom.createElement("apellido");
		Text apellidoAutorTexto = dom.createTextNode(p.getAutores().get(1));
		autorApellidoEle.appendChild(apellidoAutorTexto);
		
		autorEle.appendChild(autorNombreEle);
		autorEle.appendChild(autorApellidoEle);
		
		LibroEle.appendChild(autorEle);
		
		//creamos el campo editorial
		Element editorialEle = dom.createElement("editorial");
		Text editorialTexto = dom.createTextNode(p.getEditor());
		editorialEle.appendChild(editorialTexto);
		LibroEle.appendChild(editorialEle);
		//creamos el campo numero de paginas
		Element paginasEle = dom.createElement("paginas");
		Text paginasTexto = dom.createTextNode(Integer.toString(p.getNumPaginas()));
		paginasEle.appendChild(paginasTexto);
		LibroEle.appendChild(paginasEle);
		
		return LibroEle;
	}
	
	public void escribirDocumentoXML(File file) throws TransformerException {
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult result = new StreamResult(file);
		DOMSource source = new DOMSource(dom);
		trans.transform(source, result);
	}

}
