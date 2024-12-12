/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex.pkg2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;


/**
 *
 * @author AluCiclesGS1
 */
public class Ex2 {

   
    public static void main(String[] args) throws IOException { // Aqui comenÃ§a l'int main
        Menu(); // Diem que cuan comenci el programa lo primer que ens mostri sigui el menu
        
        int opcio = 0; // Definim la variable opcio inicialitzada a 0
        
        while(opcio != 9){ // Aqui diem que mentres la variable opcio sigui diferent a 9 continui en el bucle 
            Scanner teclado = new Scanner(System.in); // Aixo es per que ens detecti lo que entrem amb el teclat
            System.out.println("-- Escull opcio: "); // Diem que mostri per pantalla el text Escull opcio
            opcio = teclado.nextInt(); // Aqui diem que guardi en la variable opcio el que introduim en la linia anterior
            Seleccio(opcio); // I diem al programa que el que tinguem guardat en la variable opcio vagi al apartat seleccio i miri que fa
        }
    }

    private static void Menu() throws IOException { // Aquest es el menu que mostra
        System.out.println("**************MENU*************");
        System.out.println("**   1.- Entra nom i usuari  **");
        System.out.println("*******************************");
        System.out.println("** 2.- Comprova nom i usuari **");
        System.out.println("*******************************");
        System.out.println("**        9.- SORTIR         **");
        System.out.println("*******************************");
    }

    private static void Seleccio(int opcio) throws IOException { // El codi arriba aqui i diu que ell te la variable opcio amb un valor i que el te que comprovar
        switch(opcio){ // El codi veu que es un switch i aixo vol dir que el que tinguem en la variable opcio es per escollir un cas
             case 1:
                exercici1();
                break;
            case 2:
                exercici2();
                break;
            case 3:
                //exercici3();
                break;
            case 4:
                //exercici4();
                break;
            case 5:
                //exercici5();
                break;
            case 6:
                //exercici6();
                break;
            case 9:
                //exercici9();
                break;
            default:
                break; // Si la variable opcio te el numero 9, ens mostra el text Moltes gracies i surt
        }                                                     //  del programa perque la variable opcio es un 9 i si es un 9 ha de sortir del bucle de main 
    }

    private static void exercici1() throws IOException { // Si la variable opcio tenia el valor 1 ve al exercici1 i fara el que diu
        FileWriter cuentas = new FileWriter("./cuentas.txt", true); // Aqui li diem que crei un fitxer .txt en ./ que es digui cuentas
        Scanner entrada = new Scanner(System.in);  // Aqui diem que el teclat es diu entrada
        String textUsu = "";  // Crea la variable textUsu
        String textCont = ""; // Crea la variable textCont
        
        System.out.println("Introdueix un ENTER (espai en blanc) per finalitzar"); // Ens mostra el text
        
        while (true) { // Aqui li diem que mentres sigui verdader no finalitzi
            System.out.println("Introdueix nom d'usuari "); // Diem que mostri per pantalla el text
            textUsu = entrada.nextLine(); // Introduim amb el teclat el valor i es guarda en la variable textUsu
           
            System.out.println("Introdueix contrasenya d'usuari "); // Diem que mostri per pantalla el text
            textCont = entrada.nextLine(); // Introduim amb el teclat el valor i es guarda en la variable textCon
            
            if (textUsu.isBlank() || textCont.isBlank()) { // Aqui diem que si las variables textUsu o textCont estan vuides
            System.out.println("Finalitzant entrada de dades."); // Que mostri el text
            break; // I surti del bulce
            }
           
            cuentas.write( textUsu + ":" + textCont + "\n"); // Diem que escrigi en el arxiu cuentas textUsu:textCont (el que correspongi a cada variable)

            System.out.println("Guardado correctamente :)"); // Diem que mostri el text
        } 
        
        cuentas.close(); // I que tanqui el fitxer
    }


    
     private static void exercici2() throws IOException { // Si la variable opcio tenia el valor 2 ve al exercici2 i fara el que diu
        Scanner entrada = new Scanner(System.in); // Aqui diem que el teclat es diu entrada
        FileReader fr = new FileReader("./cuentas.txt"); // Diem que llegueixi el fixer cuentas.txt 
        BufferedReader buff = new BufferedReader(fr); // Aqui diem que llegueixi el contingut del que esta llegin el file reader (la linia anterior)
        
        System.out.println("Introdueix Usuari: "); // Diem que mostri el text
        String usuari = entrada.nextLine(); // I que lo que entrem amb el teclat ho guardi en la variable usuari

        System.out.println("Introdueix Contrasenya: "); // Diem que mostri el text
        String contrasenya = entrada.nextLine(); // I que lo que entrem amb el teclat ho guardi en la variable contrasenya
        
        String linia = ""; // Li diguem que cada linia que llegueixi la guardi en la variable linia
       
        boolean credencialesCorrectas = false; // Afegim que credencials correctes estigui inicialitzat a fals
        
         while ((linia = buff.readLine()) != null) { // Aqui diguem que mentres linia sigui diferent a nul que continui
             System.out.println("Leyendo linia: " + linia); // Aqui diem que mostri el text mes lo que hi ha en la variable linia
             System.out.println("Comparando con: " + (usuari + ":" + contrasenya)); // Aqui diem que mostre el text mes lo que hi ha en la variabel usuari mes : mes contrasenya
             if (linia.equals(usuari + ":" + contrasenya)) { // Aqui diem que si linia es igual a usuari:contrasenya
                 credencialesCorrectas = true; // Canvi la variable credencialsCorrectes a true
                 System.out.println("Credencials correctes"); // I que mostri el text
                 break; // I surti del bucle
             }
         }

        if (!credencialesCorrectas) { // Aqui diem que lo que no sigui credencialsCorrectes
                System.out.println("Credencials incorrectes"); // Mostri el seguent text
            }
        
        buff.close(); // Tenca el buffer reader
        fr.close(); // Tenca el file reader
    }
} // Acaba el codi