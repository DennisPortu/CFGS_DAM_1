#ifndef JUEGO_H
#define JUEGO_H

#include <SFML/Graphics.hpp>
#include "Bloques.h"

using namespace sf;
using namespace std;

class Juego {
public:
    Juego();
    void ejecutar();

private:
    void manejarEventos();
    void actualizar(float deltaTime);
    void dibujar();

    RenderWindow ventana;
    Texture texturaPelota; // Textura de la pelota.
    Sprite spritePelota;   // Sprite de la pelota.
    RectangleShape raqueta;
    Bloques bloques;
    Vector2f velocidadPelota;
    bool pelotaEnMovimiento;
    Clock reloj;
};

#endif // JUEGO_H
