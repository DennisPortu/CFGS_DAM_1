#include <iostream>
#include <cstdlib>
using namespace std;

int main()
{
    srand(time(NULL));

    int Num1, Num2, intents = 0, intentsRob = 0, Anum = rand() %100+1, ini = 1, fin = 100, 
        Ranum = ini + rand() + fin; 
    
    cout << "Adivina mi numero del 1 al 100: ";

    do{
        intents++;
        
        cin >> Num1;

        if (Num1 > Anum)
        {
            cout << "El Numero es GRANDE.\n";
        }
        else if (Num1 < Anum)
        {
            cout << "El Numero es PEQUENO\n";
        }
        else
        {
            cout << "Has adivinado el numero MUI BIEEEN!!!\n";
            break;
        }

        cout << "Intenta otra vez: ";
;
    } while (Num1 != Anum);

    cout << "Que divertidoooo, ahora yo\n";

    cout << "Pon tu numero secreto sshh!!: ";
    cin >> Num2;
    
    Ranum;

    do
    {
        intentsRob++;

        cout << "El numero es? " << Ranum;

        if (Ranum > Num2)
        {
            cout << "El numero es? " << Ranum;
        }
        else if (Ranum < Num2)
        {
            cout << "El numero es? " << Ranum;
        }
        else
        {
            cout << "Lo he adivinado??? Siiii?? OLEEEEEE!!!";
            break;
        }
    } while (Num2 != Ranum );
    
    cout << "El Numero era " << Anum << " y lo has conseguido en " << intents << " intentos.\n";
    cout << "El Numero del Robot es " << Num2 << " y lo ha conseguido en " << intentsRob << " intentos.\n";

    return 0;
}
