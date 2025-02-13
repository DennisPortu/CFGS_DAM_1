#pragma once

#include <SFML/Graphics.hpp>

using namespace sf;
using namespace std;

class Enemy
{
public:
	Enemy();
	~Enemy();
	void init(string textureName, Vector2f position, float _speed);
	void update(float dt);
	Sprite getSprite();

private:
	Texture m_texture;
	Sprite m_sprite;
	Vector2f m_position;
	float m_speed;
};