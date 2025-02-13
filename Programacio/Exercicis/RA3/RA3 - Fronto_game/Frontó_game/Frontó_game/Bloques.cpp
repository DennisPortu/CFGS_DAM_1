#include "Bloques.h"

Bloques::Bloques(int filas, int columnas, float ancho, float alto, float espacio)
    : filasBloques(filas), columnasBloques(columnas), anchoBloque(ancho), altoBloque(alto), espacioBloques(espacio) {
    crearBloques();
}

void Bloques::crearBloques() {
    for (int i = 0; i < filasBloques; i++) {
        for (int j = 0; j < columnasBloques; j++) {
            RectangleShape bloque(Vector2f(anchoBloque, altoBloque));
            bloque.setFillColor(Color::White);
            bloque.setPosition(
                50.f + j * (anchoBloque + espacioBloques),
                50.f + i * (altoBloque + espacioBloques)
            );
            bloques.push_back(bloque);
        }
    }
}

void Bloques::dibujarBloques(RenderWindow& ventana) {
    for (auto& bloque : bloques) {
        ventana.draw(bloque);
    }
}

bool Bloques::manejarColision(const FloatRect& pelotaBounds, Vector2f& velocidadPelota) {
    for (size_t i = 0; i < bloques.size(); ++i) {
        if (pelotaBounds.intersects(bloques[i].getGlobalBounds())) {
            velocidadPelota.y = -velocidadPelota.y;
            bloques.erase(bloques.begin() + i);
            return true;
        }
    }
    return false;
}
