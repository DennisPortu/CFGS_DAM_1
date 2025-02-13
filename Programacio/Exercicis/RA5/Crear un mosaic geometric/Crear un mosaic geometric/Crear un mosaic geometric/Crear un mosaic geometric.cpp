
#include <iostream>
#include <SFML/Graphics.hpp>  // Importem les llibreries
using namespace sf;

const int Xmax = 1920; // Mida X de la pantalla
const int Ymax = 1080; // Mida Y de la pantalla

int main()
{
	VideoMode vm(Xmax, Ymax);
	RenderWindow window(vm, "Mosaic geometric", Style::Fullscreen); // Definim la mida de la pantalla

	RectangleShape rect(Vector2f(50.0f, 300.0f));  // Recntangle 1 amb mida X i Y
	rect.setFillColor(Color::Yellow); // Color del recntangle
	rect.setPosition(0, 0); // Posicio

	RectangleShape rect2(Vector2f(1000.0f, 290.0f)); // Recntangle 2 amb mida X i Y
	rect2.setFillColor(Color::Red); // Color del rectangle
	rect2.setPosition(70, 10); // Posicio
	
	RectangleShape rect3(Vector2f(480.0f, 290.0f));  // Recntangle 3 amb mida X i Y
	rect3.setFillColor(Color::White); // Color del rectangle
	rect3.setPosition(1090, 10); // Posicio

	RectangleShape rect4(Vector2f(310.0f, 300.0f)); // Recntangle 4 amb mida X i Y
	rect4.setFillColor(Color::White); // Color del rectangle
	rect4.setPosition(1590, -150); // Posicio

	RectangleShape rect5(Vector2f(310.0f, 250.0f)); // Recntangle 5 amb mida X i Y
	rect5.setFillColor(Color::Yellow); // Color del rectangle
	rect5.setPosition(1590, 170); // Posicio

	RectangleShape rect6(Vector2f(50.0f, 500.0f)); // Recntangle 6 amb mida X i Y
	rect6.setFillColor(Color::White); // Color del rectangle
	rect6.setPosition(0, 320); // Posicio

	RectangleShape rect7(Vector2f(1000.0f, 300.0f));  // Recntangle 7 amb mida X i Y
	rect7.setFillColor(Color::Blue); // Color del rectangle
	rect7.setPosition(70, 320); // Posicio

	RectangleShape rect8(Vector2f(150.0f, 180.0f));  // Recntangle 8 amb mida X i Y
	rect8.setFillColor(Color::White); // Color del rectangle
	rect8.setPosition(1590, 440); // Posicio

	RectangleShape rect9(Vector2f(1000.0f, 100.0f)); // Recntangle 9 amb mida X i Y
	rect9.setFillColor(Color::White); // Color del rectangle
	rect9.setPosition(70, 640); // Posicio

	RectangleShape rect10(Vector2f(480.0f, 300.0f)); // Recntangle 10 amb mida X i Y
	rect10.setFillColor(Color::Red); // Color del rectangle
	rect10.setPosition(1090, 640); // Posicio

	RectangleShape rect11(Vector2f(150.0f, 300.0f)); // Recntangle 11 amb mida X i Y
	rect11.setFillColor(Color::Red); // Color del rectangle
	rect11.setPosition(1590, 640); // Posicio

	RectangleShape rect12(Vector2f(600.0f, 310.0f)); // Recntangle 12 amb mida X i Y
	rect12.setFillColor(Color::White); // Color del rectangle
	rect12.setPosition(70, 760); // Posicio

	RectangleShape rect13(Vector2f(1210.0f, 110.0f)); // Recntangle 13 amb mida X i Y
	rect13.setFillColor(Color::Blue); // Color del rectangle
	rect13.setPosition(690, 960); // Posicio

	while (window.isOpen()) // Mentre la finestra estigui oberta repeteix el bucle
	{
		if (Keyboard::isKeyPressed(Keyboard::Escape)) // Si es prem la tecla "Escape" 
		{
			window.close(); // Tenca la pantalla
		}

		window.clear(Color::Black); // Neteija la pantalla de color negre

		window.draw(rect); // Dibuixa el rectangle 1
		window.draw(rect2); // Dibuixa el rectangle 2
		window.draw(rect3); // Dibuixa el rectangle 3
		window.draw(rect4); // Dibuixa el rectangle 4
		window.draw(rect5); // Dibuixa el rectangle 5
		window.draw(rect6); // Dibuixa el rectangle 6
		window.draw(rect7); // Dibuixa el rectangle 7
		window.draw(rect8); // Dibuixa el rectangle 8
		window.draw(rect9); // Dibuixa el rectangle 9
		window.draw(rect10); // Dibuixa el rectangle 10
		window.draw(rect11); // Dibuixa el rectangle 11
		window.draw(rect12); // Dibuixa el rectangle 12
		window.draw(rect13); // Dibuixa el rectangle 13

		window.display(); // Mostra-ho per pantalla
	}
	return 0;
}
