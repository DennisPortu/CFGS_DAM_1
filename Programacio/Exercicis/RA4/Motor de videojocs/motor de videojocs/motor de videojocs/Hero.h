#pragma once

#include "SFML\Graphics.hpp"

using namespace sf;
using namespace std;
	
class Hero {
public:
	Hero();
	~Hero();
	void init(string textureName, Vector2f position, float mass, string texturemort);
	void update(float dt);
	void jump(float velocity);
	Sprite getSprite();
	void swaptexture(bool gameover);
	void mover(float speed);
private:
	Texture m_texture;
	Sprite m_sprite;
	Texture m_texturemort;
	Vector2f m_position;
	int m_force;
	int jumpCount = 0;
	float m_mass;
	float m_velocity;
	const float m_gravity = 24.0f;
	bool m_grounded;
};