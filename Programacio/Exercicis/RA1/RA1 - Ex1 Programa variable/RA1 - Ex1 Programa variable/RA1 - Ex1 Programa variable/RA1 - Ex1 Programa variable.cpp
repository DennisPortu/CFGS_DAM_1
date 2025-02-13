// RA1 - Ex1 Programa variable.cpp : Este archivo contiene la función "main". La ejecución del programa comienza y termina ahí.
//




#include <iostream>
using namespace std;

int main()
{
    // Exercici 1;
    
    int peus = 0, polzades = 0;

    cout << "Exercici 1\n";

    cout << endl;

    cout << "Peus " << peus << endl;
    cout << "Polzades " << polzades << endl;

    cout << endl << "--------------------------\n";
    cout << endl;

    // Exercici 2;

    int n1, n2, n3, n4, n5, n6;

   // cout << n1 << n2 << n3 << n4 << n5 << n6 << endl

    cout << "Exercici 2\n";

    cout << endl;

    cout << endl << "--------------------------\n";
    cout << endl;

    // No es mostra res ja que no tenim definides les variables

    // Exercici 3; 

    int ftemp;
    double ctemp;

    cout << "Exercici 3\n";

    cout << endl;

    cout << "Convercio de Fahrenheit a Celcius\n";
    cout << "Introdueix la temperatura en Fahrenheit\n";
    
    cin >> ftemp;

    ctemp = ((ftemp - 32) / 1.8);

    cout << "Aquesta es la temperatura en Celcius " << ctemp << endl;

    cout << endl << "--------------------------\n";
    cout << endl;

    return 0;
}

// Ejecutar programa: Ctrl + F5 o menú Depurar > Iniciar sin depurar
// Depurar programa: F5 o menú Depurar > Iniciar depuración

// Sugerencias para primeros pasos: 1. Use la ventana del Explorador de soluciones para agregar y administrar archivos
//   2. Use la ventana de Team Explorer para conectar con el control de código fuente
//   3. Use la ventana de salida para ver la salida de compilación y otros mensajes
//   4. Use la ventana Lista de errores para ver los errores
//   5. Vaya a Proyecto > Agregar nuevo elemento para crear nuevos archivos de código, o a Proyecto > Agregar elemento existente para agregar archivos de código existentes al proyecto
//   6. En el futuro, para volver a abrir este proyecto, vaya a Archivo > Abrir > Proyecto y seleccione el archivo .sln
