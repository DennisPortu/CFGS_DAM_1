/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmm_ra1_p3;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.Scanner;
import java.util.Set;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;


/**
 *
 * @author AluCiclesGS1
 */
public class DMM_RA1_p3 {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Menu();  // Mostra el menu
        
        int opcio = 0;  // tenim la variable opcio inicialitzada en 0
        
        while (opcio != 9) {  // mentres opcio sigui diferent a 9 repeteix el bucle
            Scanner teclado = new Scanner (System.in);  // detecta el teclat
            System.out.println("-- Escull opcio: ");  // mostra el text
            opcio = teclado.nextInt();  // opcio s'actualitzara per lo que entris amb elteclat
            seleccio(opcio);  // seleccióna opció
        }
    }

    private static void Menu() throws IOException {
        System.out.println("************** Menu *************");
        System.out.println("*********************************");
        System.out.println("**   1.- Informació Partides   **");
        System.out.println("*********************************");
        System.out.println("**    2.- Jug amb mes copes    **");
        System.out.println("*********************************");
        System.out.println("**     3.- Temp max i min      **");
        System.out.println("*********************************");
        System.out.println("**    4.- Afegir jugador       **");
        System.out.println("*********************************");
        System.out.println("**         9.- SORTIR          **");
        System.out.println("*********************************");
    }
    
    private static void seleccio(int opcio) throws Exception {
        switch(opcio){ // Fem un switc per escollir la opció del menu
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
        Document doc = obrirFitxerXML("./clash.xml"); // Anem a buscar el nostre arxiu xml
        NodeList jugs = doc.getElementsByTagName("Jugador");  // Fem una llista anomenada jugs de tots els nodes amb el nom Jugador 
        for (int i = 0; i < jugs.getLength(); i++) {  // Fem un bucle que ens llegueixi tot els elements
            Element jug = (Element) jugs.item(i);  // Fem que la nostra variable "i" del bucle sigui els elements de la llista jugs
            System.out.println("Nom: " + jug.getElementsByTagName("Nom").item(0).getTextContent());  // Ens mostra per pantalla el nom del jugador
            System.out.println("\tNivell: " + jug.getElementsByTagName("Nivell").item(0).getTextContent());  // Ens mostra per pantalla el nivell del jugador
            System.out.println("\tCopes: " + jug.getElementsByTagName("Copes").item(0).getTextContent());  // Ens mostra per pantalla les copes del jugador
            System.out.println("\tOro: " + jug.getElementsByTagName("Oro").item(0).getTextContent());  // Ens mostra per pantalla l'or del jugador
            System.out.println("\tGemes: " + jug.getElementsByTagName("Gemes").item(0).getTextContent());  // Ens mostra les gemes del jugador
            System.out.println("\tEstrelles: " + jug.getElementsByTagName("Estrelles").item(0).getTextContent());  // Ens mostra les estrelles del jugador

            NodeList partidas = jug.getElementsByTagName("Partida");  // Fem una llista anomenada partides de tots els nodes amb el nom Partida
            for (int y = 0; y < partidas.getLength(); y++) {  // Fem un bucle que ens llegeixi tot els elements
                Element partida = (Element) partidas.item(y);  // Fem que la  nostra variable "y" del bucle que sigui els elements de la llista partidas
                System.out.println("\t\tPartida: " + (y));  // Ens mostra el numero de partida que esta llegint
                System.out.println("\t\t\tData: " + partida.getElementsByTagName("Data").item(0).getTextContent());  // Ens mostra per pantalla la data de la partida
                System.out.println("\t\t\tResultat: " + partida.getElementsByTagName("Resultat").item(0).getTextContent());  // Ens mostra per pantalla el resultat de la partida
                System.out.println("\t\t\tDurada: " + partida.getElementsByTagName("Durada").item(0).getTextContent());  // Ens mostra per pantalla la durada de la partida
                System.out.println("\t\t\tTipus: " + partida.getElementsByTagName("Tipus").item(0).getTextContent());  // Ens mostra per pantalla el tipus de la partida
            }
        }
    }

    private static void exercici2() throws Exception {
        Document doc = obrirFitxerXML("./clash.xml");  // Anem a buscar el nostre arxiu xml
        int copMax = 0;  // Definim la variable per guardar las copas maximes
        NodeList listJugs = doc.getElementsByTagName("Jugador");  // Guardem una llista nodelist per guardar els jugadors
        Element jugMax = null;  // Definim la variable per guardar el nom del jugador
        for (int i = 0; i < listJugs.getLength(); i++) {  
            Element jug = (Element) listJugs.item(i);  
            int cop = Integer.parseInt(jug.getElementsByTagName("Copes").item(0).getTextContent());  // guarde les copes en la vaiable cop
            if (cop > copMax) { // si la anterior copes maximes es mes petita que les copes acutals
                copMax = cop;  // guarda les copes actuals en la variable copMax
                jugMax = jug;  // guarda el nom del jugador en la variable jugMax
             }
        }
        System.out.println("Jugador amb mes copes es " + jugMax.getElementsByTagName("Nom").item(0).getTextContent());  // Mostra el text i el nom del jugador
        System.out.println("Te " + copMax);  // Mostra el text i les copes
    }

    private static void exercici3() throws Exception {
        Document doc = obrirFitxerXML("./meteo2015.xml"); // Anem a buscar el nostre arxiu xml
        double tempMax = 0; // Definim la variable per guardar la temperatura maxima
        double tempMin = 100; // Definim la variable per guardar la temperatura minima
        NodeList listTempsMax = doc.getElementsByTagName("element"); // Guardem una llista nodelist per guardar les temperatures
        Element diaMax = null; // Definim el dia de temperatura maxima inicialitzat en nul
        Element diaMin = null; // Definim el dia de la temperatura minima inicialitzat en nul
        for (int i = 0; i < listTempsMax.getLength(); i++) {
            Element dia = (Element) listTempsMax.item(i); 
            if (dia.getElementsByTagName("tmax").getLength() > 0) {  // Si la tmax te una allargada mes gran que 0 
                double temp = NumberFormat.getInstance().parse(dia.getElementsByTagName("tmax").item(0).getTextContent()).doubleValue();  // guarda la temperatura en la variable 
                if (temp > tempMax) {  // Si la temperatura es major a la temperatura maxima anterior la guarda
                    tempMax = temp;  // guarda el contingut de la variable tempMax la variable temp
                    diaMax = dia;  // guarda el contingun en diaMax el contingut de la variable dia
                }
            }
            if (dia.getElementsByTagName("tmin").getLength() > 0) {  // Si la temperatura minima te una longitud mes gran que 0 
                double temp = NumberFormat.getInstance().parse(dia.getElementsByTagName("tmin").item(0).getTextContent()).doubleValue();  // guarda la temperatura en la variable temp
                if (temp < tempMin) {  // si la temperatura es menor a la ultima temperatura minima 
                    tempMin = temp;  // guarda el contingut de la vairable temp en la variabe tempMin
                    diaMin = dia;  // guarda el contingut de la variable dia en la variable diaMin
                }
            }
        }
        
        System.out.println("El dia amb mes calor va ser: " + diaMax.getElementsByTagName("fecha").item(0).getTextContent());  // Mostra el dia que va tindre major temperatura
        System.out.println("Van ser: " + tempMax + " graus.");  // Mostra el text i la temperatura 
        System.out.println("El dia amb menys calor va ser: " + diaMin.getElementsByTagName("fecha").item(0).getTextContent());  // Mostra el dia que va tindre menys temperatura
        System.out.println("Van ser: " + tempMin + " graus.\n");  // Mostra el text i la temperatura
        
        Document doc1 = obrirFitxerXML("./meteo2016.xml");  // Anem a buscar el nostre arxiu xml
        tempMax = 0;  // Reutilitzem la variable per guardar la temperatura maxima
        tempMin = 100;  // Reutilitzem la variable per guardar la temperatura minima
        NodeList listTempsMax1 = doc1.getElementsByTagName("element");  // Guardem una llista nodelist per guardar les temperatures
        diaMax = null;  // Reutilitzem la variable per guardar la temperatura maxima
        diaMin = null;  // Reutilitzem la variable per guardar la temperatura minima
        for (int i = 0; i < listTempsMax1.getLength(); i++) {
            Element dia = (Element) listTempsMax1.item(i);  
            if (dia.getElementsByTagName("tmax").getLength() > 0) {  // si la tmax te una allargada mes gran que 0
                double temp = NumberFormat.getInstance().parse(dia.getElementsByTagName("tmax").item(0).getTextContent()).doubleValue();  // guarda la temperatura en la variable 
                if (temp > tempMax) {  // si la temperatura actual es mes gra que la temperatua maxima anterior
                    tempMax = temp;  // guarda el contingut de la variable temp en la variable tempMax
                    diaMax = dia;  // guarda el contingut de la variable dia en la varible diaMax
                }
            }
            if (dia.getElementsByTagName("tmin").getLength() > 0) {  // Si la temperatura minima te una longitud mes gran que 0 
                double temp = NumberFormat.getInstance().parse(dia.getElementsByTagName("tmin").item(0).getTextContent()).doubleValue();   // guarda la temperatura en la variable temp
                if (temp < tempMin) {   // si la temperatura es menor a la ultima temperatura minima 
                    tempMin = temp;  // guarda el contingut de la vairable temp en la variabe tempMin
                    diaMin = dia;  // guarda el contingut de la variable dia en la variable diaMin
                }
            }
        }  
        System.out.println("El dia amb mes calor va ser: " + diaMax.getElementsByTagName("fecha").item(0).getTextContent());   // Mostra el dia que va tindre major temperatura
        System.out.println("Van ser: " + tempMax + " graus.");  // Mostra el text i la temperatura 
        System.out.println("El dia amb menys calor va ser: " + diaMin.getElementsByTagName("fecha").item(0).getTextContent());   // Mostra el dia que va tindre menys temperatura
        System.out.println("Van ser: " + tempMin + " graus.\n");  // Mostra el dia que va tindre menys temperatura
        
        Document doc2 = obrirFitxerXML("./meteo2017.xml");   // Anem a buscar el nostre arxiu xml
        tempMax = 0;  // Reutilitzem la variable per guardar la temperatura maxima
        tempMin = 100;  // Reutilitzem la variable per guardar la temperatura minima
        NodeList listTempsMax2 = doc2.getElementsByTagName("element");  // Guardem una llista nodelist per guardar les temperatures
        diaMax = null;  // Reutilitzem la variable per guardar la temperatura maxima
        diaMin = null;  // Reutilitzem la variable per guardar la temperatura minima
        for (int i = 0; i < listTempsMax2.getLength(); i++) {
            Element dia = (Element) listTempsMax2.item(i);
            if (dia.getElementsByTagName("tmax").getLength() > 0) {  // si la tmax te una allargada mes gran que 0
                double temp = NumberFormat.getInstance().parse(dia.getElementsByTagName("tmax").item(0).getTextContent()).doubleValue();  // si la tmax te una allargada mes gran que 0
                if (temp > tempMax) {  // si la temperatura actual es mes gra que la temperatua maxima anterior
                    tempMax = temp;  // guarda el contingut de la variable temp en la variable tempMax
                    diaMax = dia;  // guarda el contingut de la variable dia en la varible diaMax
                }
            }
            if (dia.getElementsByTagName("tmin").getLength() > 0) {  
                double temp = NumberFormat.getInstance().parse(dia.getElementsByTagName("tmin").item(0).getTextContent()).doubleValue();  // si la tmax te una allargada mes gran que 0
                if (temp < tempMin) {  // si la temperatura es menor a la ultima temperatura minima 
                    tempMin = temp;  // guarda el contingut de la vairable temp en la variabe tempMin
                    diaMin = dia;  // guarda el contingut de la variable dia en la variable diaMin
                }
            }
        }
        
        System.out.println("El dia amb mes calor va ser: " + diaMax.getElementsByTagName("fecha").item(0).getTextContent());   // Mostra el dia que va tindre major temperatura
        System.out.println("Van ser: " + tempMax + " graus.");  // Mostra el text i la temperatura 
        System.out.println("El dia amb menys calor va ser: " + diaMin.getElementsByTagName("fecha").item(0).getTextContent());  // Mostra el dia que va tindre menys temperatura
        System.out.println("Van ser: " + tempMin + " graus.");  // Mostra el dia que va tindre menys temperatura
        
    }

    private static void exercici4() throws Exception {
       Document doc = obrirFitxerXML("./clash.xml");  // Obrim el fitxer clash.xml 
       
       Scanner entrada = new Scanner (System.in);  // Fem que guardi lo que escribim  
       
       Node root = doc.getDocumentElement();  // 
       
       Element jugador = doc.createElement("Jugador");  // Fem que afegueixi l'element Jugador
       
       Element nom = doc.createElement("Nom");  // Fem que afegueixi l'element Nom
       System.out.println("Entra el nom del jugador: ");  // Mostrem el text per que afegueixi el nom
       String nomIn = entrada.nextLine();  // Guardem com a nomIn el que entrem en el teclat
       nom.setTextContent(nomIn);  // Afegim a l'element Nom el text de nomIn
       
       Element nivell = doc.createElement("Nivell");  // Fem que afegueixi l'element Nom
       System.out.println("Entra el nivell del jugador: ");  // Mostrem el text per que afegueixi el nivell
       String nivellIn = entrada.nextLine();  // Guardem com a nivellIn el que entrem en el teclat
       nivell.setTextContent(nivellIn);  // Afegim a l'element nivell el text de nivellIn
       
       Element copes = doc.createElement("Copes");  // Fem que afegueixi l'element Copes
       System.out.println("Entra les copes del jugador: ");  // Mostrem el text per que afegueixi el copes
       String copesIn = entrada.nextLine();  // Guardem com a copesIn el que entrem en el teclat
       copes.setTextContent(copesIn);  // Afegim a l'element copes el text de copesIn
       
       Element oro = doc.createElement("Oro");  // Fem que afegueixi l'element Oro
       System.out.println("Entra l'or del jugador: ");  // Mostrem el text per que afegueixi el oro
       String oroIn = entrada.nextLine();  // Guardem com a oroIn el que entrem en el teclat
       oro.setTextContent(oroIn);  // Afegim a l'element oro el text de oroIn
       
       Element gemes = doc.createElement("Gemes");  // Fem que afegueixi l'element Gemes
       System.out.println("Entra les gemes del jugador: ");  // Mostrem el text per que afegueixi el gemes
       String gemesIn = entrada.nextLine();  // Guardem com a gemesIn el que entrem en el teclat
       gemes.setTextContent(gemesIn);  // Afegim a l'element gemes el text de gemesIn
       
       Element estrelles = doc.createElement("Estrelles");  // Fem que afegueixi l'element Estrelles
       System.out.println("Entra les estrelles del jugador: ");  // Mostrem el text per que afegueixi el estrelles
       String estrellesIn = entrada.nextLine();  // Guardem com a estrellesIn el que entrem en el teclat
       estrelles.setTextContent(estrellesIn);  // Afegim a l'element estrelles el text de estrellesIn
       
       Element partides = doc.createElement("Partides");  // Fem que afegueixi l'element Partides
       
       int par = 0;  // creem la variable par per contar les partides
       String res="si";  // creem la variable res per la resposta
        do {
           par ++;  // sumem 1 a la partida
            
           Element partida = doc.createElement("Partida " + par);  // Fem que afegueixi l'element Partida
      
           Element data = doc.createElement("Data");  // Fem que afegueixi l'element Data
           System.out.println("Entra la data de la partida(Any-Mes-Dia): ");  // Mostrem el text per que afegueixi el data
           String dataIn = entrada.nextLine();  // Guardem com a dataIn el que entrem en el teclat
           data.setTextContent(dataIn);  // Afegim a l'element data el text de dataIn

           Element resultat = doc.createElement("Resultat");  // Fem que afegueixi l'element Resultat
           System.out.println("Entra el resultat de la partida (1-1, 2-3...): ");  // Mostrem el text per que afegueixi el resultat
           String resultatIn = entrada.nextLine();  // Guardem com a resultatIn el que entrem en el teclat
           resultat.setTextContent(resultatIn);  // Afegim a l'element data el text de resultatIn

           Element durada = doc.createElement("Durada");  // Fem que afegueixi l'element Durada
           System.out.println("Entra la durada de la partida (Min:Segs): ");  // Mostrem el text per que afegueixi el durada
           String duradaIn = entrada.nextLine();  // Guardem com a duradaIn el que entrem en el teclat
           durada.setTextContent(duradaIn);  // Afegim a l'element durada el text de duradaIn

           Element tipus = doc.createElement("Tipus");  // Fem que afegueixi l'element Tipus
           System.out.println("Entra el tipus de partida(Lliga, Amistós, Torneig...): ");  // Mostrem el text per que afegueixi el tipus
           String tipusIn = entrada.nextLine();  // Guardem com a tipusIn el que entrem en el teclat
           tipus.setTextContent(tipusIn);  // Afegim a l'element durada el text de tipusIn
            
           System.out.println("Vols afegir una altre partida?(si/no)");  // Mostrem el text per que afegueixi la resposta
           res = entrada.nextLine();  // Guardem com a res el que entrem en el teclat
           
           partides.appendChild(partida);  // partida sera l'element fill de partides
       
           partida.appendChild(data);  // data sera l'element fill de partida
           partida.appendChild(resultat);  // resultat sera l'element fill de partida
           partida.appendChild(durada);  // durada sera l'element fill de partida
           partida.appendChild(tipus);  // tipus sera l'element fill de partida
        } while (res.equals("si"));
        
       root.appendChild(jugador);  // jugador sera l'element fill de root       
       
       jugador.appendChild(nom);  // nom sera l'element fill de jugador
       jugador.appendChild(nivell);  // nivell sera l'element fill de jugador
       jugador.appendChild(copes);  // copes sera l'element fill de jugador
       jugador.appendChild(oro);  // oro sera l'element fill de jugador
       jugador.appendChild(gemes);  // gemes sera l'element fill de jugador
       jugador.appendChild(estrelles);  // estrelles sera l'element fill de jugador 
       jugador.appendChild(partides);  // partides sera l'element fill de jugador
       
       guardarXML(doc, "./clash.xml");  // guardem el nostre fitxer xml amb el nom "clash.xml"
    }

    private static void guardarXML(Document doc, String ruta) throws TransformerConfigurationException, TransformerException {
        Transformer tFormer = TransformerFactory.newInstance().newTransformer();  // transforma el fitxer de doc a xml
        tFormer.setOutputProperty(OutputKeys.METHOD, "xml");  // ho transforma
        Source source = new DOMSource(doc);  // transforma a doc
        Result result = new StreamResult(new File(ruta)); // el resultata al directori ruta
        tFormer.transform(source, result);  // transforma doc al directori ruta
    }
}
