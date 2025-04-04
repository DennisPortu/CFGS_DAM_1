#include "Hero.h"

using namespace std;
using namespace sf;

Sprite Hero::getSprite() 
{ 
	return m_sprite; 
}

void Hero::swaptexture(bool gameover)
{
	if (gameover){
		m_sprite.setTexture(m_texturemort);
	}

	else 
	{
		m_sprite.setTexture(m_texture);
	}
}

Hero::Hero()
{
}

Hero::~Hero()
{
}

void Hero::init(string textureName, Vector2f position, float mass, string texturemort)
{
	m_position = position;
	m_mass = mass;
	m_grounded = false;
	
	// Load a Texture
	m_texture.loadFromFile(textureName.c_str());	
	m_texturemort.loadFromFile(texturemort.c_str());

	// Create Sprite and Attach a Texture
	m_sprite.setTexture(m_texture);
	m_sprite.setPosition(200, 880);
	m_sprite.setOrigin(m_texture.getSize().x / 2, m_texture.getSize().y / 2);
	m_sprite.setScale(0.7, 0.7);
}

void Hero::update(float dt) 
{
	m_velocity -= m_mass * m_gravity * dt;
	m_position.y -= m_velocity * dt;
	m_sprite.setPosition(m_position);
	
	if (m_position.y >= 880) 
	{
		m_position.y = 880;
		m_velocity = 0;
		m_grounded = true;
		jumpCount = 0;
	}
}

void Hero::jump(float velocity)
{
	if (jumpCount < 2) {
		jumpCount++;
		m_velocity = velocity;
		m_grounded = false;
	}
}

void Hero::mover(float speed) 
{
	if (Keyboard::isKeyPressed(Keyboard::Right))
	{
		m_position.x += speed;
	}
	else if (Keyboard::isKeyPressed(Keyboard::Left))
	{
		m_position.x -= speed;
	}
	m_sprite.setPosition(m_position);
}
