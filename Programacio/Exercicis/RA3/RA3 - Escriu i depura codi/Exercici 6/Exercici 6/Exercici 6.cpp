
#include <iostream>
using namespace std;

int main()
{
    int n1;

    cout << "Quina posicio has quedat: ";
    cin >> n1;

    if (n1 <= 10)
    {
        cout << "Estas entre els 10 primers";
    }
    else if (n1 > 10 && n1 <= 100)
    {
        cout << "Estas entre els 100 primers";
    }
    else if (n1 > 100 && n1 <= 1000)
    {
        cout << "Estas entre els 1000 primers";
    }
    else
    {
        cout << "No estas ni entre els 1000 primers";
    }
    return 0;
}

