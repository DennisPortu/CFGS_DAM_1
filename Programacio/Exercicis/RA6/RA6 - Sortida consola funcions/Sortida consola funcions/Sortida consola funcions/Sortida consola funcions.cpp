#include <iostream>
#include <cmath> // Para usar sqrt

using namespace std;

// Función para calcular el factorial de un número
/// <summary>
/// Aquesta funció ens diu calcula el valor factorial del numero que hem indicat
/// </summary>
/// <param name="n">La n representa el umero que hem indicat</param>
/// <returns>Retorna el valor de el calcul</returns>

int factorial(int n) {
    if (n < 0) return -1; // No definido para números negativos
    int result = 1;
    for (int i = 1; i <= n; ++i) {
        result *= i;
    }
    return result;
}

// Función para comprobar si un número es primo
/// <summary>
/// Aquesta funció ens diu si el numero que hem indicat es primer o no.
/// </summary>
/// <param name="n">La n representa el numero que hem indicat</param>
/// <returns>Retorna cert o fals dependen si es primer o no</returns>

bool esPrimo(int n) {
    if (n <= 1) return false; // No es primo si es menor o igual a 1
    for (int i = 2; i <= sqrt(n); ++i) { // Comprobamos hasta la raíz cuadrada de n
        if (n % i == 0) return false; // Si es divisible por cualquier número, no es primo
    }
    return true; // Si no es divisible por nada, es primo
}

int main() {
    int nFactorial;
    cout << "Entra un numero factorial: ";
    cin >> nFactorial;

    cout << "El factorial de " << nFactorial << " es: " << factorial(nFactorial) << endl;

    int nPrimo;
    cout << "Entra un numero primo: ";
    cin >> nPrimo;

    if (esPrimo(nPrimo)) {
        cout << nPrimo << " es un numero primo." << endl;
    }
    else {
        cout << nPrimo << " no es un numero primo." << endl;
    }
    return 0;
}