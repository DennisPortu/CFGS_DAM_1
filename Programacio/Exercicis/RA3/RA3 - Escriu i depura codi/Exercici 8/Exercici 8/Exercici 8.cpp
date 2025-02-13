
#include <iostream>
using namespace std;

int main()
{
    cout << "********** Menu **********\n";
    cout << "**======================**\n";
    cout << "**      1. Facil        **\n";
    cout << "**======================**\n";
    cout << "**      2. Mitja        **\n";
    cout << "**======================**\n";
    cout << "**     3. Dificil       **\n";
    cout << "**======================**\n";
    
    int n1 = 1;

    while (n1 != 4)
    {
        cout << "Escull una dificulat: ";
        cin >> n1;
        if (n1 == 1)
        {
            cout << "Has escollit dificultat facil\n";
        }
        else if (n1 == 2)
        {
            cout << "Has escollit dificultat mitja\n";
        }
        else if (n1 == 3)
        {
            cout << "Has escollit dificultat dificil\n";
        }
        else
        {
            cout << "Has escollit una opcio no valida\n";
        }


        char resp;
        char n = 'n';
        char s = 's';

        cout << "Vols tornar a jugar? s/n: ";
        cin >> resp;

        if (resp == n)
        {
            n1 = 4;
            cout << "Gracies per jugar.";
        }
        else
        {
            cout << "********** Menu **********\n";
            cout << "**======================**\n";
            cout << "**      1. Facil        **\n";
            cout << "**======================**\n";
            cout << "**      2. Mitja        **\n";
            cout << "**======================**\n";
            cout << "**     3. Dificil       **\n";
            cout << "**======================**\n";
        }
    }
}
