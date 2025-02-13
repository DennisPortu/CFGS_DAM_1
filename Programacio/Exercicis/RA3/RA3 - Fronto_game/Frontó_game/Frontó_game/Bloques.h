#ifndef BLOQUES_H
#define BLOQUES_H

#include <SFML/Graphics.hpp>
#include <vector>

using namespace sf;
using namespace std;

class Bloques {
public:
    Bloques(int filas, int columnas, float ancho, float alto, float espacio);
    void crearBloques();
    void dibujarBloques(RenderWindow& ventana);
    bool manejarColision(const FloatRect& pelotaBounds, Vector2f& velocidadPelota);

private:
    int filasBloques;
    int columnasBloques;
    float anchoBloque;
    float altoBloque;
    float espacioBloques;
    vector<RectangleShape> bloques;
};

#endif // BLOQUES_H
