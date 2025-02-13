
#include <iostream>
using namespace std;

int main()
{
	int Edat;

	cout << "Introdueix la teva edat: ";
	cin >> Edat;

	cout << endl;

	bool major = Edat >= 18;

	if (major)
	{
		cout << "Ets major d'edat";
	}

	else
	{
		cout << "No ets major d'edat";
	}
	return 0;
}
