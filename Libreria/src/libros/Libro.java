package libros;

import java.io.Serializable;

public class Libro implements Serializable{
	//VARIABLES QUE DEFINEN LA CLASE LIBRO
	private String titulo;
	private String autor;
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
	public Libro(String titulo, String autor, int a�oPubli, String editor,int numPaginas) {
		this.titulo = titulo;
		this.autor = autor;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
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

	
	
}
