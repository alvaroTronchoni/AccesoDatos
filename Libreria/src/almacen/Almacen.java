package almacen;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.AbstractListModel;

import libros.Libro;

public class Almacen {
	//GUARDAMOS NUESTRO OBJETO SCANNER PARA INTERACTUAR CON EL USUARIO
	Scanner sc;
	/**
	 * CONTRUCTOR DE ALMACEN EN EL QUE INICIARA LA LOGICA DE LA APLICACION
	 */
	public Almacen() {
		logica();
	}
	
	/**
	 * LOGICA DE LA APLICACION
	 */
	public void logica(){
		//CREAMO LA VARIABLE QUE NOS DIRA CUANDO TERMINA LA APLICACION
		boolean puedoTerminar = false;
		while (puedoTerminar==false){
			//INICIAMOS EL OBJETO QUE LEERA EL TECLADO DEL USUARIO
			sc = new Scanner(System.in);
			//CREAMOS LAS VARIABLES QUE GUARDARAN LAS RESPUESTAS Y SI SON CORRECTAS
			int eleccion = 0;
			boolean bien = false;
			//MOSTRAMOS LOS MENSAGES QUE NECESITEMOS PARA INFORMAR AL USUARIO
			System.out.println("Bienbenido al sistema de almacenamiento de clases");
			System.out.println("1 - Guardar un nuevo libro");
			System.out.println("2 - Recuperar un libro");
			System.out.println("3 - Guardar una nueva lista de libros");
			System.out.println("4 - Modificar titulo o autor de un libro existente");
			System.out.println("5 - Borrar un fichero");
			System.out.println();
			//GUARDAMOS LA ELECCION EN NUESTRA VARIABLE
			eleccion = escaneaNumeroLimite(5,"Introduce el numero de la opcion que deseas realizar:");
			//COMPROVAMOS CUAL HA SIDO LA RESPUESTA DEL USUARIO Y ACTUAREMOS DEPENDIENDO DE ESTA
			switch(eleccion){
				case 1:
					guardarNuevo();
					break;
				case 2:
					recuperar();
					break;
				case 3:
					guardarLista();
					break;
				case 4:
					//EN ESTE CASO LE VOLVEREMOS A PREGUNTAR CUAL DE LAS DOS OPCIONES QUIERE REALIZAR
					System.out.println("1 - Modificar titulo");
					System.out.println("2 - Modificar autor");
					System.out.println();
					eleccion = escaneaNumeroLimite(2, "Introduce el numero de la opcion que desees");
					if(eleccion==1){
						modificarTitulo();
					}
					if(eleccion==2){
						modificaAutor();
					}
					
					break;
				case 5:
					String g = escaneaStringRelleno("Introduce el nombre del archivo a borrar");
					borrarFichero(g);
					
			}
			//UNA VEZ TERMINADO EL PROCESO ELEJIDO PREGUNTAREMOS SI QUIERE EL USUARIO REALIZAR OTRA OPCION
			System.out.println("¿Desea realizar otra operacion?");
			System.out.println("1 - Si");
			System.out.println("2 - No");
			System.out.println();
			eleccion = escaneaNumeroLimite(2, "Introduce el numero de la opcion que deseas realizar:");
			//EN CASO AFIRMATIVO LA VARIABLE puedoTerminar SEGUIRA FALSE Y NO TERMINARA EL BUCLE
			if(eleccion == 1){
				puedoTerminar = false;
			}
			//EN CASO NEGATIVO PONDREMOS NUESTRA VARIABLE A TRUE PARA INDICAR QUE EL BUCLE PUEDE TERMINAR
			if(eleccion == 2){
				puedoTerminar = true;
				System.out.println("Gracias por utilizar el sistema de almacenamiento de clases");
				System.out.println("Hasta Pronto!!");
			}
		}
		//CERRAMOS EL OBJETO QUE LEIA EL TECLADO AHORA QUE YA HEMOS TERMINADO DE LEER
		cerrarCanal(sc);
	}
	
	/**
	 * GUARDAREMOS UN LIBRO CREANDOLO DESDE 0
	 */
	public void guardarNuevo(){
		//RECOJEMOS EL OBJETO QUE CONTENDRA EL LIBRO Y SU TITULO PARA PONERLO DE INDICE
		ArrayList lista = crearLibroScaner();
		ObjectOutputStream obj = null;
		try {
			//CREAMOS EL OBJETO QUE GUARDARA EL ARCHIVO Y LE PASAMOS OTRO OBJETO QUE A SU VEZ RECOGE EL TITULO DEL ARCHIVO
			obj = new ObjectOutputStream(new FileOutputStream(((Libro)lista.get(0)).getTitulo()+".dat"));
			//ESCRIVIMOS EN EL OBJETO CREADO EL LIBRO A GUARDAR
			obj.writeObject((Libro)lista.get(0));
			System.out.println("El siguiente libro se ha guardado correctamnte:");
			System.out.println();
		} catch (IOException e) {
			System.err.println("No se ha podido guardar el libro");
			System.out.println();
		}finally{
			//SE PUEDA O NO CREAR CERRAMOS EL OBJETO QUE INTERACTUA CON EL ARCHIVO
			cerrarCanal(obj);
		}
	}
	
	/**
	 * GUARDAMOS UN LIBRO QUE NOS PASAN CON EL TITULO QUE NOS PASAN
	 * @param libro
	 * @param titulo
	 */
	public void guardarLibro(Libro libro,String titulo){
		//LA MECANICA ES LA MISMA QUE LA DE GUARDAR UN NUEVO LIBRO PERO SIN CREAR EL LIBRO YA QUE NOS LO PASAN DE PARAMETRO
		ObjectOutputStream obj = null;
		try {
			File file = new File(titulo+".dat");
			System.out.println(file.getAbsolutePath());
			FileOutputStream fos = new FileOutputStream(file);
			obj = new ObjectOutputStream(fos);
			obj.writeObject(libro);
			System.out.println("El siguiente libro se ha guardado correctamnte:");
			System.out.println();
		} catch (IOException e) {
			System.err.println("No se ha podido guardar el libro");
			System.out.println();
		}finally{
			cerrarCanal(obj);
		}
	}
	
	/**
	 * CREAMOS UN LIBRO PREGUNTANDO AL USUARIO CUALES SON LOS ATRIBUTOS DE ESTE
	 * @return V : ARRAYLIST QUE CONTENDRA EN LA PRIMERA POSICION UN LIBRO Y EN LA SEGUNDA SU TITULO
	 */
	public ArrayList crearLibroScaner(){
		//PREGUNTAMOS AL USUARIO LOS DATOS DE ESTE LIBRO
		String titulo= escaneaStringRelleno("Introduce el titulo del libro");
		String autor = escaneaStringRelleno("Introduce el autor del libro");
		int añoPubli = escaneaNumeroLimite(2016, "Introduce el año de publicacion del libro");
		String editor = escaneaStringRelleno("Introduce el editor del libro");
		int numPaginas = escaneaNumeroLimite(10000, "Introduce el numero de paginas del libro");
		System.out.println();
		//CREAMOS EL LIBRO CON LOS DATOS RECOGIDOS
		Libro libro = new Libro(titulo, autor, añoPubli, editor, numPaginas);
		//CREAMOS EL ARRAYLIST QUE DEVOLVEREMOS Y LE INTRODUCIMOS EL LIBRO Y EL TITULO
		ArrayList v = new ArrayList();
		v.add(libro);
		v.add(titulo);
		//DEVOLVEMOS ESTE ARRAYLIST
		return v;
	}
	
	/**
	 * BUSCAREMOS UN LIBRO CON EL TITULO QUE NOS HAYA PASADO EL USUARIO
	 * @return LIBRO: EL LIBRO RESULTANTE DE LA BUSQUEDA
	 */
	public Libro recuperar(){
		//PEDIMOS AL USUARIO EL ARCHIVO A BUSCAR
		String fichero = escaneaStringRelleno("Introduce el titulo exacto del libro buscado:");
		System.out.println();
		//CREAMOS LAS VARIABLES NECESARIAS PARA LA GESTION
		Libro libro = null;
		ObjectInputStream obj = null;
		try {
			//CREAMOS EL OBJETO QUE LEERA EL ARCHIVO BUSCADO SI EXISTE
			obj = new ObjectInputStream(new FileInputStream(fichero+".dat"));
			//LEEMOS EL LIBRO DEL ARCHIVO Y LO GUARDAMOS EN LA VARIABLE
			libro = (Libro) obj.readObject();
			//MOSTRAMOS EL RESULTADO POR PANTALLA
			System.out.println("El libro consultado es el siguiente:");
			System.out.println("Titulo: "+libro.getTitulo());
			System.out.println("Autor: "+libro.getAutor());
			System.out.println("Año de publicacion: "+libro.getAñoPubli());
			System.out.println("Editorial: "+libro.getEditor());
			System.out.println("Paginas: "+libro.getNumPaginas());
			System.out.println();
		} catch (FileNotFoundException e) {
			System.err.println("El archivo buscado no existe");
			System.out.println();
		} catch (IOException e) {
			System.err.println("Ha habido un error recuperando el fichero");
			System.out.println();
		} catch (ClassNotFoundException e) {
			System.err.println("Clase no encontrada");
			System.out.println();
		}
		return libro;
	}

	/**
	 * METODO QUE UTILIZAREMOS PARA CERRAR CUALQUIER CANAL QUE SE PUEDA CERRA
	 * @param obj: CANAL A CERRAR
	 */
	private void cerrarCanal(Closeable obj) {
		try {
			if(obj!=null){
				//CERRAMOS EL CANAL PASADO AL METODO
				obj.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * GUARDAMOS UNA LISTA DE LIBROS CREANDOLOS NUEVOS
	 */
	public void guardarLista(){
		//PEDIMOS EL NUMERO DE LIBROS HA GUARDAR
		int numero = escaneaNumeroLimite(15, "Intoduce el numero de libros que quieres introducir(15 como mucho)");
		System.out.println();
		//REPETIMOS LA ACCION DE GUARDAR UN NUEVO LIBRO TANTAS VECES COMO NOS HAN DICHO
		for(int x = 0;x<numero;x++){
			guardarNuevo();
		}
	}
	
	/**
	 * MODIFICAREMOS EL TITULO DE UN LIBRO EXISTENTE Y LO GUARDAREMOS CON EL NUEVO TITULO
	 */
	public void modificarTitulo(){
		//PEDIMOS EL TITULO DEL LIBRO A MODIFICAR AL USUARIO
		String titulo;
		Libro l = null;
		while(l==null){
			System.out.println("Intoduce el nombre del libro a modificar");
			l = recuperar();
		}		
		//PEDIMOS EL NUEVO TITULO PARA EL LIBRO BUSCADO
		String nuevoTitulo = escaneaStringRelleno("Intoduce el nuevo titulo del libro");
		System.out.println();
		//CREAMOS EL LIBRO CON EL NUEVO TITULO, LO GUARDAMOS Y BORRAMOS EL DEL TITULO VIEJO
		Libro libro = new Libro(nuevoTitulo, l.getAutor(), l.getAñoPubli(), l.getEditor(), l.getNumPaginas());
		guardarLibro(libro,libro.getTitulo());	
		borrarFichero(l.getTitulo());
	}
	
	/**
	 * MODIFICAREMOS EL AUTOR DE UN LIBRO DADO POR EL USUARIO
	 */
	public void modificaAutor(){
		//PEDIMOS EL TITULO DEL LIBRO A MODIFICAR AL USUARIO
		String titulo;
		Libro l = null;
		while(l==null){
			l = recuperar();
		}		
		//PEDIMOS EL NUEVO AUTOR PARA EL LIBRO BUSCADO
		String nuevoTitulo = escaneaStringRelleno("Intoduce el nuevo autor del libro");
		System.out.println();
		//CREAMOS EL NUEVO LIBRO Y LO GUARDAMOS (AL TENER EL MISMO TITULO EL ANTERIOR LO DESTRULLE)
		Libro libro = new Libro(nuevoTitulo, l.getAutor(), l.getAñoPubli(), l.getEditor(), l.getNumPaginas());
		guardarLibro(libro,libro.getTitulo());
	}
	
	/**
	 * METODO UTILIZADO PARA LEER UN NUMERO POR TECLADO
	 * @param limite: LIMITE DEL NUMERO QUE QUEREMOS
	 * @param peticion: MENSAJE QUE MOSTRAREMOS PARA HACER LA PETICION
	 * @return DEVOLVEREMOS EL NUMERO ELEGIDO
	 */
	public int escaneaNumeroLimite(int limite,String peticion){
		//CREAMOS LAS VARIABLE NECESARIAS
		int x = 0;
		boolean f = false;
		boolean g = false;
		//COMPROVAMOS QUE NO PODEMOS CONTINUAR Y DEVEMOS PEDIR UN NUEVO NUMERO (MIENTRAS LAS DOS NO ESTEN EN TRUE)
		while(g==false||f==false){
			f = false;
			g = false;
			//MOSTRAMOS LA PETICION QUE TENEMOS
			System.out.println(peticion);
			try{	
				//COMPROVAMOS QUE EL DATO PASADO ES UN NUMERO
				x = Integer.valueOf(sc.next());
				g=true;
			}catch(NumberFormatException e){
				
			}
			//COMPROVAMOS CUANDO SABEMOS QUE ES NUMERO QUE ESTA EN EL RANGO QUE QUEREMOS
			if(x>=1&&x<=limite){
				f=true;
			}else{
				//SI NO COINCIDE MOSTRAMOS QUE EL DATO ESTA MAL
				System.err.println("El dato introducido no es valido");
			}
		}
		sc.nextLine();
		//DEVOLVEMOS EL NUMERO DADO
		return x;
	}
	
	/**
	 * METODO QUE UTILIZAREMOS PARA ESCANEAR UN STRING DEL TECLADO
	 * @param peticion: MENSAGE PARA PEDIR EL STRING ADECUADO
	 * @return EL STRING DADO POR EL USUARIO
	 */
	public String escaneaStringRelleno(String peticion){
		//CREAMOS LAS VARIABLE NECESARIAS
		String x = "";
		boolean f = false;
		//COMPROVAMOS QUE HAY QUE PEDIR OTRO STRING
		while(f==false){
			f = false;
			//MOSTRAMOS LA PETICION
			System.out.println(peticion);
			//GUARDAMOS LO ESCRITO POR TECLADDO
			x = sc.nextLine();
			//COMPROVAMOS QUE EL STRING MIDE MAS DE DOS CARACTERES
			if(x.length()>=2){
				f=true;
			}else{
				System.err.println("El dato introducido no es valido");
			}
		}
		//DEVOLVEMOS EL STRING
		return x;
	}
	
	/**
	 * BORRAREMOS EL FICHERO CON EL TITULO PASADO
	 * @param titulo: FICHERO A BORRAR
	 */
	public void borrarFichero(String titulo){
		try{
			File file = new File(titulo+".dat");
			if(file.exists()){
				file.delete();
				System.out.println("El archivo ha sido borrado");
			}else{
				System.err.println("El archivo no existe");
			}
			
		}catch(Exception e){
			System.err.println("El archivo no ha podido ser borrado o encontrado "+titulo);
		}
		
	}
}
