
public class MainParser {

	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.parseFicheroXml("ejemplo.xml");
		parser.paseDocument();
		parser.print();
	}

}
