#pragma once

#include<SFML/Graphics.hpp>
#include<SFML/Audio.hpp>

#include<vector>

#include"Hero.h"
#include"Enemy.h"
#include"Rocket.h"

using namespace std;
using namespace sf;

class Engine {
private:
	// Text
	Font headingFont;
	Font scoreFont;
	Text headingText;
	Text tutorialText;
	Text scoreText;
	
	// Audio
	Music bgMusic;
	
	//creem la variables per crear la finestra de joc
	Vector2f viewSize;
	VideoMode vm;
	RenderWindow window;
	
	//variable per emmagatzemar el fons
	//cel
	Texture skyTexture;
	Sprite skySprite;
	//cel
	Texture skyTexturemort;
	Sprite skySpritemort;

	//variable del personatge
	Hero Dennis;

	vector<Enemy*> enemies;
	vector<Rocket*> rockets;

	//temps entre frame
	float currentTime;
	float prevTime = 0.0f;
	
	//variables del joc.
	int score = 0;
	bool gameover = true;

	void reset();
	void init();
	void updateInput();
	void update(float dt);
	void spawnEnemy();
	void shoot();
	bool checkCollision(Sprite sprite1, Sprite sprite2);
	void draw();

public:
	// This is the constructor, called whenever a 'Game' object is created.
	// Use this to setup stuff you have to set once.
	// This should only be used for things that can't fail, to always have a defined state;
	Engine();
	
	// This is the destructor, called whenever a 'Game' object is destroyed.
	~Engine();
	
	// Load and run the game
	int run();
};