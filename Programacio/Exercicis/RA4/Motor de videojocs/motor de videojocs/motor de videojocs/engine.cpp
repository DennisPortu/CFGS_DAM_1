#include "engine.h"
#include "Enemy.h"
#include "Rocket.h"

// This is the constructor, called whenever a 'Game' object is init.
Engine::Engine() {
	// Get the screen resolution and create an SFML window and View
	//mides de la finestra
	viewSize.x = 1920;
	viewSize.y = 1080;
	
	//construïm la finestra de joc.
	window.create(VideoMode(viewSize.x, viewSize.y),
		"Joc Heroi Dennis",
		Style::Default);
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

	
	// Load font
	headingFont.loadFromFile("Assets/fonts/SnackerComic.ttf");
	scoreFont.loadFromFile("Assets/fonts/arial.ttf");
	
	// Set Heading Text
	headingText.setFont(headingFont);
	headingText.setString("Tiny Bazooka");
	headingText.setCharacterSize(84);
	headingText.setFillColor(Color::Red);
	FloatRect headingbounds = headingText.getLocalBounds();
	headingText.setOrigin(headingbounds.width / 2, headingbounds.height / 2);
	headingText.setPosition(Vector2f(viewSize.x * 0.5f, viewSize.y * 0.10f));
	// Set Score Text

	// Tutorial Text
	tutorialText.setFont(scoreFont);
	tutorialText.setString("Press Down Arrow to Fire and Start Game, Up Arrow to Jump");
	tutorialText.setCharacterSize(35);
	tutorialText.setFillColor(Color::Red);
	FloatRect tutorialbounds = tutorialText.getLocalBounds();
	tutorialText.setOrigin(tutorialbounds.width / 2, tutorialbounds.height / 2);
	tutorialText.setPosition(Vector2f(viewSize.x * 0.5f, viewSize.y * 0.20f));
	
	// Audio
	bgMusic.openFromFile("Assets/audio/bgMusic.ogg");
	bgMusic.play();
	
	//inicialitzem el jugador
	Dennis.init("Assets/graphics/heroi.png", Vector2f(200, 880), 130);
	//inicialitzem els nombre aleatoris.
	srand((int)time(0));
}

void Engine::updateInput() {
	Event event;
	// while there are pending events...
		while (window.pollEvent(event)) {
			if (event.type == Event::KeyPressed) { //revisem les tecles premudes
				if (event.key.code == Keyboard::Up) {//personatge sala
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
}

void Engine::update(float dt) {
	//actualitzem la posició del personatge
	Dennis.update(dt);
	
	//modifiquem el temps per sabre el que ha passat
	currentTime += dt;
	
	// Spawn Enemies
	if (currentTime >= prevTime + 1.125f) {
		spawnEnemy();
		prevTime = currentTime;
	}

	// Update Enemies
	for (Enemy* enemy : enemies) {
		enemy->update(dt);
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
				break;
			}
		}
	}
	// Check collision between Rocket and Enemies
}

void Engine::draw() {
	// netejem la pantalla
	window.clear(Color::Red);
	
	//dibuixem elements
	window.draw(skySprite);
	window.draw(Dennis.getSprite());
	for (Enemy* enemy : enemies) {
		window.draw(enemy->getSprite());
	}
	for (Rocket* rocket : rockets) {
		window.draw(rocket->getSprite());
	}

	//mostrem text depenent si estem jugant o no
	if (gameover) {
		window.draw(headingText);
		window.draw(tutorialText);
	}
	else {
		window.draw(scoreText);
	}
	
	//enviem a la pantalla.
	window.display();
}

void Engine::reset() {
	score = 0;
	currentTime = 0.0f;
	prevTime = 0.0;
	scoreText.setString("Score: 0");
}

void Engine::spawnEnemy() {
	int randLoc =	 rand() % 3;
	Vector2f enemyPos;
	float speed;
	switch (randLoc) 
	{
		case 0: enemyPos = Vector2f(viewSize.x, viewSize.y * 0.8f);
			speed = -300; break;
		case 1: enemyPos = Vector2f(viewSize.x, viewSize.y * 0.51f);
			speed = -450; break;
		case 2: enemyPos = Vector2f(viewSize.x, viewSize.y * 0.2f);
			speed = -550; break;
		default: printf("incorrect y value \n"); break;
	}
	Enemy* enemy = new Enemy();
	enemy->init("Assets/graphics/enemy.png", enemyPos, speed); 
	enemies.push_back(enemy);
}

void Engine::shoot() {
	Rocket* rocket = new Rocket();
	rocket->init("Assets/graphics/rocket.png", Dennis.getSprite().getPosition(),1000);
	rockets.push_back(rocket);
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
