
#include <iostream>
#include <Windows.h>
using namespace std;


int main()
{
	char res = 's';
	string w1, w2, w3, w4, w5, w6, w7, w9;
	int w8, w10;

	do
	{
		cout << "Enter a name of country: ";
		cin >> w1;

		cout << "Enter a Family member: ";
		cin >> w2;

		cout << "Enter a name of animal: ";
		cin >> w3;

		cout << "Enter a name of planet: ";
		cin >> w4;

		cout << "Enter another name of country: ";
		cin >> w5;

		cout << "Enter a emotional adjective: ";
		cin >> w6;

		cout << "Enter another emotional adjective: ";
		cin >> w7;

		cout << "Enter a number between 2 - 10: ";
		cin >> w8;

		cout << "Enter a colour: ";
		cin >> w9;

		cout << "Enter another number between 1-24: ";
		cin >> w10;

		cout << endl;

		cout << "Loading your history...\n"; Sleep(3000);
		
		cout << endl;

		cout << "-----STORY-----";

		cout << endl;

		cout << "The story starts in some house in " << w1 << ", I am a normal child, I am  playing my\n";
		cout << "favorite video game with my " << w2 << " phone, is normal day all is good but at that\n";
		cout << "moment the ski begins to get dark, and a strange number calls me on the phone\n";
		cout << "I answer the call, and I stayed quiet, they passed a few seconds and strange man\n";
		cout << "started to talk, I'm scared, and I don't understand anything, a few seconds later i\n";
		cout << "started paying attention and i comprended this strange man, he wanted to give me a\n";
		cout << "magic " << w3 << " to save our " << w4 << " the plan consists of this strange man send me\n";
		cout << "a magical " << w3 << " but no a normally magical " << w3 << " this " << w3 << " can fly\n";
		cout << "and i could to travel " << w5 << ".\n";
		cout << endl;
		cout << "The conversation was " << w6 << " and i ask why i have to travel to " << w5 << " with a\n";
		cout << "magical " << w3 << "? The strange men responded by telling me that my slimes are\n";
		cout << "magical and that's why I had to travel. I responded, why you need my slimes? And\n";
		cout << "he sayed because the trees around the world are getting sick and with a drop of my\n";
		cout << "slime I would solve everything.\n";
		cout << endl;
		cout << "I accept the plan and this strange man sends to my home the magical " << w3 << " the\n";
		cout << "travel was so long but was so " << w7 << " but this " << w3 << " con flip around the clouds.\n";
		cout << "During the travel, the " << w3 << " started to talk to me a lot of bad pranks.\n";
		cout << endl;
		cout << w8 << " days later, we arrive to the country and i start to cough all the trees and\n";
		cout << "instantly started to growing all the trees bloomed again, and the sky turned " << w9 << ".\n";
		cout << "And in one moment  the magical" << w3 << " slaps me on my face and i wake up in my\n";
		cout << "room at " << w10 << " oclock and i thought that all was a dream, I didn't like that it was a\n";
		cout << "dream, but it was too good to be real.\n";

		cout << endl;

		cout << "Vols tornar a jugar? (s/n) ";
		cin >> res;

		if (res != 's')
		{
			cout << "joooo, pues fins un altree.";
		}

		cout << endl;
		cout << endl;

	} while (res == 's');

	return 0;
} 
