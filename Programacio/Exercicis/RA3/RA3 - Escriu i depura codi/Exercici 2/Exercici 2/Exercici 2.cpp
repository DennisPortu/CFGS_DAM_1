

#include <iostream>
using namespace std;

int main()
{
	int Num;
	int Res;

	cout << "Escull un Numero: ";
	cin >> Num;

	Res = Num % 2;

	cout << endl;

	if (Res == 0)
	{
		cout << "El numero es par";
	}

	else
	{
		cout << "El numero es impar";
	}
	return 0;
}

