// MathClient.cpp
// compile with: cl /EHsc MathClient.cpp /link MathLibrary.lib

#include <iostream> 
#include <DennisMath.h>

using namespace std;

int main()
{
    double a;
    int b = 0;
    string res;

    do
    {
        cout << "Afegueix els 2 numeros que vols calcular (no 0 ni negatius):\nNum a: --> ";
        cin >> a;
        cout << "Num b: --> ";
        cin >> b;

        if (a <= 0 || b <= 0)
        {
            cout << "Numero no valid\n";
        }

        else
        {
            cout << "a + b = " <<
                DennisMath::Arithmetic::Add(a, b) << std::endl;
            cout << "a - b = " <<
                DennisMath::Arithmetic::Subtract(a, b) << std::endl;
            cout << "a * b = " <<
                DennisMath::Arithmetic::Multiply(a, b) << std::endl;
            cout << "a / b = " <<
                DennisMath::Arithmetic::Divide(a, b) << std::endl;
        }

        cout << "Vols tornar a repetir?\n--> ";
        cin >> res;

    } while (res != "no");
    
    return 0;
}