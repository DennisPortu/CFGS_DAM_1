#pragma once

class Critter
{
public:
	Critter(int hambre = 5, int aburrimiento = 5);
	void hablar();
	void comer(int comida = 2);
	void jugar(int diversion = 2);
	void PasaTiempo(int tiempo = 1);
	void dormir(int comida = 8, int diversion = 8);

private:
	int m_hambre;
	int m_aburrimiento;
	int GetMood() const;
};