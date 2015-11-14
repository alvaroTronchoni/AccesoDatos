

import java.io.Serializable;
import java.util.ArrayList;

public class Libro implements Serializable{
	//VARIABLES QUE DEFINEN LA CLASE LIBRO
	private String titulo;
	private ArrayList<String> autores;
	private int a�oPubli;
	private String editor;
	private int numPaginas;
	
	/**
	 * CONTRUCTOR DE LIBROS VACIOS
	 */
	public Libro(){
		
	}
	
	/**
	 * CONTRUCTOR DE UN LIBRO CON TODOS SUS DATOS
	 * @param titulo
	 * @param autor
	 * @param a�oPubli
	 * @param editor
	 * @param numPaginas
	 */
	public Libro(String titulo, ArrayList<String> autor, int a�oPubli, String editor,int numPaginas) {
		this.titulo = titulo;
		this.autores = autor;
		this.a�oPubli = a�oPubli;
		this.editor = editor;
		this.numPaginas = numPaginas;
	}

	/*
	 * ZONA DE GETERS Y SETERS QUE MANEJARAN EL OBJETO LIBRO
	 */
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	

	public ArrayList<String> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}

	public int getA�oPubli() {
		return a�oPubli;
	}

	public void setA�oPubli(int a�oPubli) {
		this.a�oPubli = a�oPubli;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}
	
	public void print(){
		System.out.println("El titulo es: "+getTitulo());
		System.out.println("El autor es: " + getAutores().get(0));
		System.out.println("El autor es: " + getAutores().get(1));
		System.out.println("El a�o de publicacion es: " + getA�oPubli());
		System.out.println("La editorial es: " + getEditor());
		System.out.println("El numero de paginas es: " + getNumPaginas());
		System.out.println();
	}

	
	
}
