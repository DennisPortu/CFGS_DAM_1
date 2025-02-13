#pragma once

#include "SFML\Graphics.hpp"

using namespace sf;

class Hero {
public:
	Hero();
	~Hero();
	void init(std::string textureName, sf::Vector2f position, float mass);
	void update(float dt);
	void jump(float velocity);
	Sprite getSprite();
private:
	Texture m_texture;
	Sprite m_sprite;
	Vector2f m_position;
	int m_force;
	int jumpCount = 0;
	float m_mass;
	float m_velocity;
	const float m_gravity = 24.0f;
	bool m_grounded;

};