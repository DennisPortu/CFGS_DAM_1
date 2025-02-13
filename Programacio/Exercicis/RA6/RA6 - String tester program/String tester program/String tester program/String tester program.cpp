
#include <iostream>
using namespace std;

int main()
{
	string frase1, frase2, frase3, frasecom, frase4;
	char car;
	int pos = 0;

	cout << "La frase es: ";
	cin >> frase1;
	cin >> frase2;
	cin >> frase3;

	frasecom = frase1 + " " + frase2 + " " + frase3;
	
	int len = frasecom.length();
	
	while (pos < len)
	{
		car = frasecom[pos];
		cout << "En la posicion " << pos << " se encuentra el caracter: " << car << endl;
		++pos;
	}
	
	cout << "Escriu una paraula per buscarla al text: ";
	cin >> frase4;


}
