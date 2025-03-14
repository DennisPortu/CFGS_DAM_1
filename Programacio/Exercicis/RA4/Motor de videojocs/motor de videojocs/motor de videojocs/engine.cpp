#include <iostream>
#include "engine.h"
#include "Rocket.h"
#include <sstream>

using namespace sf;
using namespace std;

int score = 0;
std::stringstream ss;

// This is the constructor, called whenever a 'Game' object is init.
Engine::Engine() {
	// Get the screen resolution and create an SFML window and View
	// Mides de la finestra
	viewSize.x = 1920;
	viewSize.y = 1080;
	
	//construïm la finestra de joc.
	window.create(VideoMode(viewSize.x, viewSize.y),
		"Joc Heroi Dennis",
		Style::Fullscreen);
}

// This is the destructor, called whenever a 'Game' object is destroyed.
Engine::~Engine() {
}

// Load and run the game
int Engine::run() {
	Clock clock;
	init();
	while (window.isOpen()) {
		updateInput();
		Time dt = clock.restart();
		if (!gameover) {
			update(dt.asSeconds());
		}
		draw();
	}
	return 0;
}

void Engine::init() {
	//carregam les imatges del fons
	skyTexture.loadFromFile("Assets/graphics/sky.png");
	skySprite.setTexture(skyTexture);
	skyTexturemort.loadFromFile("Assets/graphics/skymort.png");
	skySpritemort.setTexture(skyTexturemort);


	
	// Load font
	headingFont.loadFromFile("Assets/fonts/BeaufortforLOL-Heavy.ttf");
	scoreFont.loadFromFile("Assets/fonts/BeaufortforLOL-Heavy.ttf");
	
	// Set Heading Text
	headingText.setFont(headingFont);
	headingText.setString("Fizz vs Kayn");
	headingText.setCharacterSize(84);
	headingText.setFillColor(Color::Yellow);
	FloatRect headingbounds = headingText.getLocalBounds();
	headingText.setOrigin(headingbounds.width / 2, headingbounds.height / 2);
	headingText.setPosition(Vector2f(viewSize.x * 0.5f, viewSize.y * 0.10f));
	
	// Set Score Text
	scoreText.setFont(scoreFont);
	ss << "Score: " << score;
	scoreText.setString(ss.str());
	scoreText.setCharacterSize(85);
	scoreText.setFillColor(Color::Yellow);
	scoreText.setPosition(20, 20);

	// Tutorial Text
	tutorialText.setFont(scoreFont);
	tutorialText.setString("Pulsa la flecha pa abajo pa empezar");
	tutorialText.setCharacterSize(35);
	tutorialText.setFillColor(Color::Yellow);
	FloatRect tutorialbounds = tutorialText.getLocalBounds();
	tutorialText.setOrigin(tutorialbounds.width / 2, tutorialbounds.height / 2);
	tutorialText.setPosition(Vector2f(viewSize.x * 0.5f, viewSize.y * 0.20f));
	
	// Audio
	bgMusic.openFromFile("Assets/audio/bgMusic.wav");
	bgMusic.setVolume(5);
	bgMusic.play();

	m.openFromFile("Assets/audio/m.wav");
	m.setVolume(5);
	
	//inicialitzem el jugador
	Dennis.init("Assets/graphics/heroi.png", Vector2f(200, 880), 130, "Assets/graphics/heroimort.png");
	
	//inicialitzem els nombre aleatoris.
	srand((int)time(0));
}

void Engine::updateInput() {
	Event event;
	// while there are pending events...
		while (window.pollEvent(event)) {
			if (event.type == Event::KeyPressed) { //revisem les tecles premudes
				if (event.key.code == Keyboard::Up) {//personatge salta
					Dennis.jump(1500.f);
				
				}
				
				if (event.key.code == Keyboard::Down) {
					if (gameover) {//si no joguem llavors engeguem el joc.
						gameover = false;
						reset();
					}
				}
			}

			if (event.type == Event::KeyPressed) { //revisem les tecles premudes
				if (event.key.code == Keyboard::Space) {//personatge sala
					shoot();
				}
			}
			
			//condicions per tancar el programa
			if (event.key.code == Keyboard::Escape || event.type == Event::Closed)
			window.close();
		}

		if (Keyboard::isKeyPressed(Keyboard::Right)) {
			Dennis.mover(0.3);
		}
		if (Keyboard::isKeyPressed(Keyboard::Left)) {
			Dennis.mover(0.3);
		}
}


void Engine::update(float dt) {
	//actualitzem la posició del personatge
	Dennis.update(dt);
	
	//modifiquem el temps per sabre el que ha passat
	currentTime += dt;
	
	// Spawn Enemies
	if (currentTime >= prevTime + 1.125f) {
		spawnEnemy();
		spawnEnemy2();
		prevTime = currentTime;
	}

	// Update Enemies
	for (Enemy* enemy : enemies) {
		enemy->update(dt);
	}

	for (Enemy2* enemy2 : enemies2) {
		enemy2->update(dt);
	}
	
	//Morir
	for (int i = 0; i < enemies.size(); i++)
	{
		Enemy* enemy = enemies[i];
		enemy->update(dt);

		if (checkCollision(enemy->getSprite(), Dennis.getSprite()))
		{
			gameover = true;
			enemies.erase(enemies.begin() + i);
		}

		else if (enemy->getSprite().getPosition().x < -150)
		{
			delete enemy;
			enemies.erase(enemies.begin() + i);
		}
	}

	for (int i = 0; i < enemies2.size(); i++)
	{
		Enemy2* enemy2 = enemies2[i];
		enemy2->update(dt);

		if (checkCollision(enemy2->getSprite(), Dennis.getSprite()))
		{
			gameover = true;
			enemies2.erase(enemies2.begin() + i);
		}

		else if (enemy2->getSprite().getPosition().y > 2300)
		{
			delete enemy2;
			enemies2.erase(enemies2.begin() + i);
		}
	}

	// Update rockets
	for (int i = 0; i < rockets.size(); i++) {
		Rocket* rocket = rockets[i];
		rocket->update(dt);
		for (int j = 0; j < enemies.size(); j++) {
			if (checkCollision(rocket->getSprite(), enemies[j]->getSprite())) {
				delete rockets[i];
				rockets.erase(rockets.begin() + i);
				delete enemies[j];
				enemies.erase(enemies.begin() + j);
				score += 1;
				ss.str("");
				ss.clear();
				ss << "Score: " << score;
				scoreText.setString(ss.str());
				break;
			}
		}
	}

	for (int i = 0; i < rockets.size(); i++) {
		Rocket* rocket = rockets[i];
		rocket->update(dt);
		for (int j = 0; j < enemies2.size(); j++) {
			if (checkCollision(rocket->getSprite(), enemies2[j]->getSprite())) {
				delete rockets[i];
				rockets.erase(rockets.begin() + i);
				delete enemies2[j];
				enemies2.erase(enemies2.begin() + j);
				score += 1;
				ss.str("");
				ss.clear();
				ss << "Score: " << score;
				scoreText.setString(ss.str());
				break;
			}
		}
	}
	// Check collision between Rocket and Enemies
}

void Engine::draw() {

	Dennis.swaptexture(gameover);

	// netejem la pantalla
	window.clear(Color::Yellow);
	
	// dibuixem elements
	if (gameover) {
		window.draw(skySpritemort);

		window.draw(headingText);
		window.draw(tutorialText);
	}
	else {
		window.draw(skySprite);

		window.draw(scoreText);
	}
	
	window.draw(Dennis.getSprite());

	for (Enemy* enemy : enemies) {
		enemy->swaptexture(gameover);
		window.draw(enemy->getSprite());
	}

	for (Enemy2* enemy2 : enemies2) {
		enemy2->swaptexture(gameover);
		window.draw(enemy2->getSprite());
	}

	for (Rocket* rocket : rockets) {
		window.draw(rocket->getSprite());
	}

	//mostrem text depenent si estem jugant o no

	//enviem a la pantalla.
	window.display();
}

void Engine::spawnEnemy() {
	int randLoc = rand() % 3;

	switch (randLoc) 
	{
		case 0: enemyPos = Vector2f(viewSize.x, viewSize.y * 0.8f);
			speed = -350; break;
		case 1: enemyPos = Vector2f(viewSize.x, viewSize.y * 0.51f);
			speed = -350; break;
		case 2: enemyPos = Vector2f(viewSize.x, viewSize.y * 0.2f);
			speed = -350; break;
		default: printf("incorrect y value 1\n"); break;
	}
	Enemy* enemy = new Enemy();
	enemy->init("Assets/graphics/enemy.png", enemyPos, speed, "Assets/graphics/enemymort.png");
	enemies.push_back(enemy);
}

void Engine::spawnEnemy2() {
	int randLoc = rand() % 3;

	switch (randLoc)
	{
	case 0: enemy2Pos = Vector2f(200, -150);
			speed2 = 150; break;
	case 1: enemy2Pos = Vector2f(650, -150);
			speed2 = 150; break;
	case 2: enemy2Pos = Vector2f(950, -150);
		speed2 = 150; break;
	default: printf("incorrect y value 2\n"); break;
	}
	Enemy2* enemy2 = new Enemy2();
	enemy2->init("Assets/graphics/enemy2.png", enemy2Pos, speed2, "Assets/graphics/enemy2mort.png");
	enemies2.push_back(enemy2);
}

void Engine::shoot() {
	Rocket* rocket = new Rocket();
	rocket->init("Assets/graphics/rocket.png", Dennis.getSprite().getPosition(),900);
	rockets.push_back(rocket);
	m.play();
}

bool Engine::checkCollision(Sprite sprite1, Sprite sprite2) {
	FloatRect shape1 = sprite1.getGlobalBounds();
	FloatRect shape2 = sprite2.getGlobalBounds();

	if (shape1.intersects(shape2)) {
		return true;
	}
	else {
		return false;
	}
}

void Engine::reset() {
		score = 0;
		currentTime = 0.0f;
		prevTime = 0.0;
		scoreText.setString("Score: 0");

	for (Enemy* enemy : enemies) {
		delete(enemy);
	}

	for (Enemy2* enemy2 : enemies2) {
		delete(enemy2);
	}

	for (Rocket* rocket : rockets) {
		delete(rocket);
	}
	enemies.clear();
	enemies2.clear();
	rockets.clear();
}

