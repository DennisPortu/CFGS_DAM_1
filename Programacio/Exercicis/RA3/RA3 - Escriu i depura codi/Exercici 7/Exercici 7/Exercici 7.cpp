
#include <iostream>
using namespace std;

int main()
{
    int dni;
    int poslet;
    char letcorr;
    char let;
    char lislet[] = { 't','r','w','a','g','m','y','f','p','d','x','b','n','j','z','s','q','v','h','l','c','k','e' };

    cout << "Entra el teu NUMERO de dni : ";
    cin >> dni;

    cout << "Entra la teva LLETRA del teu dni: ";
    cin >> let;

    poslet = dni % 23;
    letcorr = lislet[poslet];

    if (letcorr == let)
    {
        cout << "El DNI es correcte";
    }
    else
    {
        cout << "El DNI es incorrecte ";
    }
    return 0;
}

