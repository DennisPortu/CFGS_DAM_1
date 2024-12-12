/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmm_ra1_p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.Random;

/**
 *
 * @author AluCiclesGS1
 */
public class DMM_RA1_P1 {

    public static void main(String[] args) throws IOException {
        
        menu();
        int opcio = 0;
        Scanner teclado = new Scanner(System.in); //Creem objecte de escáner o lectura

        while (opcio != 9) {
            System.out.println("  --Escull opció: ");
            opcio = teclado.nextInt(); //cridem metode nextInt per llegar el numero i guardar a opcio.

            Seleccio(opcio); //Executem exercici escollit.
        }

    }

    private static void menu() {
        System.out.println("*********************************");
        System.out.println("************** MENU *************");
        System.out.println("*|-----------------------------|*");
        System.out.println("*|   1.- Comprova fitxer       |*");
        System.out.println("*|-----------------------------|*");
        System.out.println("*|   2.- Llista d'arxius       |*");
        System.out.println("*|-----------------------------|*");
        System.out.println("*|   3.- Crear arxiu TXT       |*");
        System.out.println("*|-----------------------------|*");
        System.out.println("*|  4.- Mostrar contingut      |*");
        System.out.println("*|-----------------------------|*");
        System.out.println("*| 5.- Crear directori i arxiu |*");
        System.out.println("*|-----------------------------|*");
        System.out.println("*|        9.- Sortir           |*");
        System.out.println("*|-----------------------------|*");
        System.out.println("*********************************");

    }

    private static void Seleccio(int opcio) throws IOException {
        switch (opcio) {

            case 1:
                exercici1();
                break;
            case 2:
                exercici2();
                break;
            case 3:
                exercici3();
                break;
            case 4:
                exercici4();
                break;
            case 5:
                exercici5();
                break;
            case 6:
                //exercici6();
                break;
            case 9:
                //exercici9();
                break;
            default:
                break;

        }

    }

    private static void exercici1() {
        File f1 = new File("./prova/nop.txt");
        File f2 = new File ("C:\\Windows\\notepad.exe");
        
        if (f1.exists()) {
            System.out.println("El fitcher \"./prova/nop.txt\" existeix!.");
        } 
        
        else {
            System.out.println("El fitcher \"./prova/nop.txt\" NO existeix!.");
        }
        
        if (f2.exists()) {
            System.out.println("El fitcher \"C:\\Windows\\notepad.exe\" existeix!");
        }
        
        else {
            System.out.println("El fitcher \"C:\\Windows\\notepad.exe\" NO existeix!");
        }
    }
      
    private static void exercici2() {
        File carpeta = new File ("C:\\Windows\\");
        String[] llista =carpeta.list();
        for (String nom:llista){
            System.out.println(nom);
        }
    }

    private static void exercici3() throws IOException {
        FileWriter arxiu = new FileWriter ("./arxiu1.txt");
        FileWriter arxiu2 = new FileWriter ("./arxiu2.txt");
        
        arxiu.write ("Aquest és el primer escrit al meu arxiu");
        arxiu.close();
        System.out.println("S'ha creat el fitxer correctament.");
        
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("Escriu un text: ");
        arxiu2.write (teclado.next() );
        System.out.println("S'ha creat correctament ☻");
    }

    private static void exercici4() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("./arxiu1.txt");
        BufferedReader buf = new BufferedReader(fr);
        String linia = buf.readLine();
        while (linia != null) {
            System.out.println(linia);
            linia = buf.readLine();
        }

        FileReader fr2 = new FileReader("./arxiu2.txt");
        BufferedReader buf2 = new BufferedReader(fr2);
        String linia2 = buf2.readLine();
        while (linia2 != null) {
            System.out.println(linia2);
            linia2 = buf2.readLine();
        }
    }

    
    public static String textAleatori() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 7) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    private static void exercici5() throws IOException {
        String pichaNom = textAleatori()+".txt";
        File dennis = new File ("./" + textAleatori());
        dennis.mkdir();
        File picha = new File(dennis , pichaNom);
        if (!picha.exists()) {
            picha.createNewFile();
        } 
        else {
            System.out.println("aquest directori ja existeix");
        } 
    }
}
 