

import java.io.Serializable;
import java.util.ArrayList;

public class Libro implements Serializable{
	//VARIABLES QUE DEFINEN LA CLASE LIBRO
	private String titulo;
	private ArrayList<String> autores;
	private int añoPubli;
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
	 * @param añoPubli
	 * @param editor
	 * @param numPaginas
	 */
	public Libro(String titulo, ArrayList<String> autor, int añoPubli, String editor,int numPaginas) {
		this.titulo = titulo;
		this.autores = autor;
		this.añoPubli = añoPubli;
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

	public int getAñoPubli() {
		return añoPubli;
	}

	public void setAñoPubli(int añoPubli) {
		this.añoPubli = añoPubli;
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
		System.out.println("El año de publicacion es: " + getAñoPubli());
		System.out.println("La editorial es: " + getEditor());
		System.out.println("El numero de paginas es: " + getNumPaginas());
		System.out.println();
	}

	
	
}
