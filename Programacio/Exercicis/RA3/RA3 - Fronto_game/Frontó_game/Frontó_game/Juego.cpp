#include "Juego.h"

Juego::Juego()
    : ventana(VideoMode(2560, 1440), "Breakout Ball", Style::Fullscreen),
    raqueta(Vector2f(400.f, 30.f)),
    bloques(10, 18, 130.f, 40.f, 10.f),
    velocidadPelota(0.5f, -0.5f),
    pelotaEnMovimiento(false) {

    // Cargar la textura de la pelota.
    if (!texturaPelota.loadFromFile("./graphics/pelota.png")) {
        throw runtime_error("No se pudo cargar la textura de la pelota.");
    }

    // Configurar el sprite de la pelota.
    spritePelota.setTexture(texturaPelota);
    spritePelota.setPosition(1280.f, 720.f);
    spritePelota.setScale(0.10f, 0.10f);

    // Configurar la raqueta.
    raqueta.setFillColor(Color::Blue);
    raqueta.setPosition(1080.f, 1380.f);
}

void Juego::ejecutar() {
    while (ventana.isOpen()) {
        manejarEventos();
        float deltaTime = reloj.restart().asSeconds();
        actualizar(deltaTime);
        dibujar();
    }
}

void Juego::manejarEventos() {
    Event evento;
    while (ventana.pollEvent(evento)) {
        if (evento.type == Event::Closed || Keyboard::isKeyPressed(Keyboard::Escape)) {
            ventana.close();
        }
        if (evento.type == Event::KeyPressed && evento.key.code == Keyboard::Space) {
            pelotaEnMovimiento = true;
        }
    }

    if (Keyboard::isKeyPressed(Keyboard::Left) && raqueta.getPosition().x > 0) {
        raqueta.move(-0.5f, 0.f);
    }
    if (Keyboard::isKeyPressed(Keyboard::Right) && raqueta.getPosition().x + raqueta.getSize().x < 2560) {
        raqueta.move(0.5f, 0.f);
    }
}

void Juego::actualizar(float deltaTime) {
    if (pelotaEnMovimiento) {
        spritePelota.move(velocidadPelota.x * deltaTime * 1000, velocidadPelota.y * deltaTime * 1000);

        if (spritePelota.getPosition().x <= 0 || spritePelota.getPosition().x + spritePelota.getGlobalBounds().width >= 2560) {
            velocidadPelota.x = -velocidadPelota.x;
        }
        if (spritePelota.getPosition().y <= 0) {
            velocidadPelota.y = -velocidadPelota.y;
        }

        if (spritePelota.getGlobalBounds().intersects(raqueta.getGlobalBounds())) {
            velocidadPelota.y = -std::abs(velocidadPelota.y);
        }

        bloques.manejarColision(spritePelota.getGlobalBounds(), velocidadPelota);

        if (spritePelota.getPosition().y > 1440) {
            pelotaEnMovimiento = false;
            spritePelota.setPosition(1280.f, 720.f);
        }
    }
    else {
        spritePelota.setPosition(
            raqueta.getPosition().x + raqueta.getSize().x / 2 - spritePelota.getGlobalBounds().width / 2,
            raqueta.getPosition().y - spritePelota.getGlobalBounds().height
        );
    }
}

void Juego::dibujar() {
    ventana.clear(Color::Black);
    ventana.draw(spritePelota);
    ventana.draw(raqueta);
    bloques.dibujarBloques(ventana);
    ventana.display();
}
