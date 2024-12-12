/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static javax.management.Query.or;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author AluCiclesGS1
 */
public class Examen {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Menu();  // Mostra el menu
        
        int opcio = 0;  // tenim la variable opcio inicialitzada en 0
        
        while (opcio != 9)  // mentres opcio sigui diferent a 9 repeteix el bucle
        {
            Scanner teclado = new Scanner (System.in);  // detecta el teclat
            System.out.println("-- Escull opcio: ");  // mostra el text
            opcio = teclado.nextInt(); // opcio s'actualitzara per lo que entris amb elteclat
            seleccio(opcio);  // seleccióna opció
        }
    }

    private static void Menu() {
        System.out.println("************** Menu *************");
        System.out.println("*********************************");
        System.out.println("**   1.- Jugador menys nivell  **");
        System.out.println("*********************************");
        System.out.println("**    2.- Crear nou jugador    **");
        System.out.println("*********************************");
        System.out.println("**    3.- Nom contra correu    **");
        System.out.println("*********************************");
        System.out.println("**    4.- Comprova cuentas     **");
        System.out.println("*********************************");
    }

    private static void seleccio(int opcio) throws Exception {
        switch(opcio){
            case 1:
                exercici1();  // amb el numero 1 va al exercici 1
                break;
            case 2:
                exercici2();  // amb el numero 2 va al exercici 2
                break;
            case 3:
                exercici3();  // amb el numero 3 va al exercici 3
                break;
            case 4:
                exercici4(); // amb el numero 4 va al exercici 4
                break;
            default:  // surt del switch
                break;
        }
    }
    
    public static Document obrirFitxerXML(String fitxerXML) throws Exception {
        File fxml = new File(fitxerXML);  // Crea un objecte de tipus "File" que representa el fitxer que volem lleguir
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();  // Utilitzarem la classe DocuemntBuildeFactor per crear una instancia per gestionar l'acces al fitxe XML
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  // Creem el constructor per obrir un XML
        Document doc = (Document) dBuilder.parse(fxml);  //Indiquem quin XML obrirem per obter el document necessari per llegir
        doc.getDocumentElement().normalize();  //Revisem el doc xml per veure si esta ben formulat
        return doc; //Returnem el doc amb el contingut de l'XML
    }

    private static void exercici1() throws Exception {
        Document doc = obrirFitxerXML("./Jugadors.xml");  // Anem a buscar el nostre arxiu xml
        int JugMenysNivell = 100;  // Definim la variable per guardar el jugador amb menys nivell
        NodeList listJugs = doc.getElementsByTagName("jugador");  // Guardem una llista nodelist per guardar els jugadors
        Element NomJug = null;  // Definim el nom del jugador inicialitzat a nul
        for (int i = 0; i < listJugs.getLength(); i++) {  
            Element jug = (Element) listJugs.item(i);
            int Nivell = Integer.parseInt(jug.getElementsByTagName("nivell").item(0).getTextContent());  // Si el nivell te una allargada mes gran que 0
            if (Nivell < JugMenysNivell) {  // Si l'anterior jugadorte menys nivell
                JugMenysNivell = Nivell;  // guarde el nivell en la variable JugMenysNivell
                NomJug = jug;  // guarda el nom del jugador amb menys nivell en la variable NomJug
            }
        }
        System.out.println("Jugador amb menys nivell es:\n"); 
        System.out.println("Nom: " + NomJug.getElementsByTagName("nom").item(0).getTextContent()); // Mostra el nom del jugador
        System.out.println("Es nivell: " + JugMenysNivell);  // Mostra el nivell
        System.out.println("Atac: " + NomJug.getElementsByTagName("atac").item(0).getTextContent()); // Mostra l'atac
        System.out.println("Or: " + NomJug.getElementsByTagName("or").item(0).getTextContent());  // Mostra l'or
        System.out.println("Defensa: " + NomJug.getElementsByTagName("defensa").item(0).getTextContent());  // Mostra la defensa
        System.out.println("Magia: " + NomJug.getElementsByTagName("magia").item(0).getTextContent() + "\n");  // Mostra la magia
        System.out.println("Inventari \n");
        
        NodeList objectes = NomJug.getElementsByTagName("objecte");
        for (int y = 0; y < objectes.getLength(); y++) {  // Fa un bucle per llegir tants objectes com hi hagi
            Element objecte = (Element) objectes.item(y);
            System.out.println("Nom: " + objecte.getElementsByTagName("nomObj").item(0).getTextContent());  // Mostra el nom del objecte
            System.out.println("Tipus: " + objecte.getElementsByTagName("tipusObj").item(0).getTextContent());  // Mostra el tipus del objecte
            System.out.println("Nivell: " + objecte.getElementsByTagName("nivellObj").item(0).getTextContent());  // Mostra el nivell del objecte
            System.out.println("\n");
        }
    }

    private static void exercici2() throws Exception {
        Document doc = obrirFitxerXML("./Jugadors.xml");  // Anem a buscar el nostre arxiu xml

        Scanner entrada = new Scanner(System.in);  // Fem que guardi lo que escribim  

        Node root = doc.getDocumentElement();  

        Element jugador = doc.createElement("jugador");

        Element nom = doc.createElement("nom"); // Fem que afegueixi l'element Nom
        System.out.println("Entra el nom del jugador: ");  // Mostrem el text per que afegueixi el nom
        String nomIn = entrada.nextLine();   // Guardem com a nomIn el que entrem en el teclat
        nom.setTextContent(nomIn);

        Element oro = doc.createElement("or");  // Fem que afegueixi l'element or
        System.out.println("Entra l'or del jugador: ");  // Mostrem el text per que afegueixi el or
        String orIn = entrada.nextLine();  // Guardem com a orIn el que entrem en el teclat
        oro.setTextContent(orIn);  

        Element nivell = doc.createElement("nivell");  // Fem que afegueixi l'element nivell
        System.out.println("Entra el nivell del jugador: ");  // Mostrem el text per que afegueixi el nivell
        String nivellIn = entrada.nextLine();  // Guardem com a nivellIn el que entrem en el teclat
        nivell.setTextContent(nivellIn);

        Element vida = doc.createElement("vida");  // Fem que afegeixi l'element vida
        System.out.println("Entra la vida del jugador: ");  // Mostrem el text per que afegueixi la vida
        String vidaIn = entrada.nextLine();  // Guardem com a vidaIn el que entreme en el teclat
        vida.setTextContent(vidaIn);

        Element atac = doc.createElement("atac");  // Fem que afegeixi l'element atac
        System.out.println("Entra l'atac del jugador: ");  // Mostrem el text per que afegueixi l'atac
        String atacIn = entrada.nextLine();  // guardem com a atacIn el que entrem en el teclat
        atac.setTextContent(atacIn);

        Element defensa = doc.createElement("defensa");  // Fem que afegeixi l'element defensa
        System.out.println("Entra la defensa del jugador: ");  // Mostrem el text per que afegueixi la defensa
        String defensaIn = entrada.nextLine();  // guardem com a atacIn el que entrem en el teclat
        defensa.setTextContent(defensaIn);

        Element magia = doc.createElement("magia");  // Fem que afegeixi l'element defensa
        System.out.println("Entra la magia del jugador: ");  // Mostrem el text per que afegueixi la defensa
        String magiaIn = entrada.nextLine();  // guardem com a defensaIn el que entrem en el teclat
        magia.setTextContent(magiaIn);

        Element inventari = doc.createElement("inventari");

        Element objecte = doc.createElement("Objecte");

        Element nomObj = doc.createElement("nomObj");  // Fem que afegeixi l'element nomObj
        System.out.println("Entra el nom del objecte: ");  // Mostrem el text per que afegueixi el nomObj
        String nomObjIn = entrada.nextLine();  // guardem com a nomObjIn el que entrem en el teclat
        nomObj.setTextContent(nomObjIn);

        Element tipusObj = doc.createElement("tipusObj");  // Fem que afegeixi l'element tipusObj
        System.out.println("Entra el tipus del objecte: ");  // Mostrem el text per que afegueixi el tipusObj
        String tipusObjIn = entrada.nextLine();  // guardem com a tipusObjIn el que entrem en el teclat
        tipusObj.setTextContent(tipusObjIn);

        Element nivellObj = doc.createElement("nivellObj");  // Fem que afegeixi l'element nivellObj
        System.out.println("Entra el nivell del objecte: ");  // Mostrem el text per que afegueixi el nivellObj
        String nivellObjIn = entrada.nextLine();  // guardem com a nivellObjIn el que entrem en el teclat
        nivellObj.setTextContent(nivellObjIn);

        inventari.appendChild(objecte);

        root.appendChild(jugador);

        jugador.appendChild(nom);
        jugador.appendChild(oro);
        jugador.appendChild(nivell);
        jugador.appendChild(vida);
        jugador.appendChild(atac);
        jugador.appendChild(defensa);
        jugador.appendChild(magia);

        objecte.appendChild(nomObj);
        objecte.appendChild(tipusObj);
        objecte.appendChild(nivellObj);
        
        guardarXML(doc, "./Jugadors.xml");
    }

    private static void guardarXML(Document doc, String ruta) throws TransformerException {
        Transformer tFormer = TransformerFactory.newInstance().newTransformer();  // transforma el fitxer de doc a xml
        tFormer.setOutputProperty(OutputKeys.METHOD, "xml");  // ho transforma
        Source source = new DOMSource(doc);  // transforma a doc
        Result result = new StreamResult(new File(ruta)); // el resultata al directori ruta
        tFormer.transform(source, result);  // transforma doc al directori ruta
    }

    private static void exercici3() throws IOException {
        FileWriter cuentas = new FileWriter("./cuentas.txt", true); // Aqui li diem que crei un fitxer .txt en ./ que es digui cuentas
        Scanner entrada = new Scanner(System.in);  // Aqui diem que el teclat es diu entrada
        String textUsu = "";  // Crea la variable textUsu
        String textCont = ""; // Crea la variable textCont
        String textCorr = ""; // Crea la variable textCorr
        
        System.out.println("Introdueix un ENTER (espai en blanc) per finalitzar"); // Ens mostra el text
        
        while (true) { // Aqui li diem que mentres sigui verdader no finalitzi
            System.out.println("Introdueix nom d'usuari "); // Diem que mostri per pantalla el text
            textUsu = entrada.nextLine(); // Introduim amb el teclat el valor i es guarda en la variable textUsu
           
            System.out.println("Introdueix contrasenya d'usuari "); // Diem que mostri per pantalla el text
            textCont = entrada.nextLine(); // Introduim amb el teclat el valor i es guarda en la variable textCon
            
            System.out.println("Introdueix correu al usuari ");
            textCorr = entrada.nextLine();
            
            if (textUsu.isBlank() || textCont.isBlank()) { // Aqui diem que si las variables textUsu o textCont estan vuides
            System.out.println("Finalitzant entrada de dades."); // Que mostri el text
            break; // I surti del bulce
            }
           
            cuentas.write( textUsu + ":" + textCont + ":" + textCorr + "\n"); // Diem que escrigi en el arxiu cuentas textUsu:textCont (el que correspongi a cada variable)

            System.out.println("Guardado correctamente :)"); // Diem que mostri el text
        } 
        
        cuentas.close(); // I que tanqui el fitxer
    }

    private static void exercici4() throws FileNotFoundException {
        FileReader fr = new FileReader("./cuentas.txt");
        BufferedReader buff = new BufferedReader (fr);
        
        
    }
    
    
    
    
    
}
