#include "Enemy.h"

using namespace std;
using namespace sf;

Sprite Enemy::getSprite()
{
	return m_sprite;
}

void Enemy::swaptexture(bool gameover)
{
	if (gameover) {
		m_sprite.setTexture(m_texturemort);
	}

	else
	{
		m_sprite.setTexture(m_texture);
	}
}

Enemy::Enemy  ()
{
}

Enemy::~Enemy()
{
}

void Enemy::init(string textureName, Vector2f position, float speed, string texturemort)
{
	m_position = position;

	m_speed = speed;

	// Load a Texture
	m_texture.loadFromFile(textureName.c_str());
	m_texturemort.loadFromFile(texturemort.c_str());

	// Create Sprite and Attach a Texture
	m_sprite.setTexture(m_texture);
	m_sprite.setScale(1.9, 2.1);
	m_sprite.setPosition(m_position);
	m_sprite.setOrigin(m_texture.getSize().x / 2,
		m_texture.getSize().y / 2);
}

void Enemy::update(float dt)
{
	m_sprite.move(m_speed * dt, 0);
}

