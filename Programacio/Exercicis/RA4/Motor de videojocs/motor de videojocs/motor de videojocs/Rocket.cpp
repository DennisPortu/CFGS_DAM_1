#include "Rocket.h"

using namespace sf;
using namespace std;

Sprite Rocket::getSprite()
{
	return m_sprite;
}

Rocket::Rocket()
{
}

Rocket::~Rocket()
{
}

void Rocket::init(string textureName, Vector2f position, float _speed)
{
	m_position = position;
	m_speed = _speed;
	// Load a Texture
	m_texture.loadFromFile(textureName.c_str());

	// Create Sprite and Attach a Texture
	m_sprite.setTexture(m_texture);
	m_sprite.setScale(0.3, 0.3);
	m_sprite.setPosition(m_position);
	m_sprite.setOrigin(m_texture.getSize().x / 2,
		m_texture.getSize().y / 2);
}

void Rocket::update(float dt)
{
	m_sprite.move(m_speed * dt, 0);
}

