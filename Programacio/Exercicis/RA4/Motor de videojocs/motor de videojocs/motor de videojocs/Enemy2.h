#pragma once

#include <SFML/Graphics.hpp>

using namespace sf;
using namespace std;

class Enemy2
{
public:
	Enemy2();
	~Enemy2();
	void init(string textureName, Vector2f position, float speed, string texturemort);
	void update(float dt);
	Sprite getSprite();
	void swaptexture(bool gameover);

private:
	Texture m_texture;
	Sprite m_sprite;
	Texture m_texturemort;
	Vector2f m_position;
	float m_speed;
};