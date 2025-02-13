
#include <iostream>
using namespace std;

int main()
{
	int N1;
	int N2;

	cout << "Entra 2 numeros: ";
	cin >> N1;
	cin >> N2;

	if (N1 < N2)
	{
		cout << N1 << " es mes petit que " << N2;
	}
	else if (N1 > N2)
	{
		cout << N1 << " es mes gran que " << N2;
	}
	else
	{
		cout << N1 << " i " << N2 << " son iguals";
	}
	return 0;
}

