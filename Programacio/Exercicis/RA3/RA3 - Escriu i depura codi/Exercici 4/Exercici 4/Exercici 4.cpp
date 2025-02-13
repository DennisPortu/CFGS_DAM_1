
#include <iostream>
using namespace std;

int main()
{
    int seg;
    int min;
    int h;

    cout << "Cuants segonts ha durat la partida: ";
    cin >> seg;

    min = seg / 60;
    h = min / 60;

    cout << "La partida ha durat " << h << " hores, " << min << " minuts i " << seg << " segonts.";
    return 0;
}

