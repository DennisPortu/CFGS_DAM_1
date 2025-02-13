
#include <iostream>
#include <SFML/Graphics.hpp>
#include <sstream>
#include <SFML/Audio.hpp>
using namespace sf;

// Variable maxima y minima de la pantalla
const int Xmax = 1920;
const int Ymax = 1080;

struct CloudStructure {
    Sprite dibuix;
    bool Active;
    float Speed;
};

// Left or Right
enum class side { LEFT, RIGHT, NONE };

// Numero de ramas
const int num_branches = 6;


void updateBranches(int seed, side branchPositions[]);

int main()
{
    // Tamaño de pantalla
    VideoMode vm(Xmax, Ymax);
    RenderWindow window(vm, "Timber!!!", Style::Fullscreen);
    //**********************************************************

    // Creas el Fondo
    Texture TBackground;
    TBackground.loadFromFile("./graphics/background.png");

    Sprite SBackground;
    SBackground.setTexture(TBackground);
    //**********************************************************

    // Creas el arbol
    Texture TTree;
    TTree.loadFromFile("./graphics/tree.png");

    Sprite STree;
    STree.setTexture(TTree);
    STree.setPosition(810, -72);
    STree.setScale(1, 1.1);
    //**********************************************************

    // Creas la abeja
    Texture TBee;
    TBee.loadFromFile("./graphics/bee.png");

    Sprite SBee;
    SBee.setTexture(TBee);
    SBee.setPosition(700, 500);

    bool beeActive = false;
    float beeSpeed = 0.0f;
    //**********************************************************

    // Creas la nube
    Texture TCloud;
    TCloud.loadFromFile("./graphics/cloud.png");

    CloudStructure spriteCloud[3];

    for (int i = 0; i < 3; i++)
    {
        spriteCloud[i].dibuix.setTexture(TCloud);
        spriteCloud[i].dibuix.setPosition(0, i * 250);
        spriteCloud[i].Active = false;
        spriteCloud[i].Speed = 0.3f;
    }
    //**********************************************************

    // Juego en Pausa
    bool paused = true;
    //**********************************************************

    // Letras
    int score = 0;
    Text scoreText;
    Text messageText;

    Font font;
    font.loadFromFile("fonts/KOMIKAP_.TTF");

    messageText.setFont(font);
    scoreText.setFont(font);

    messageText.setString("Press Enter to start!");
    scoreText.setString("Score = 0");

    messageText.setCharacterSize(75);
    scoreText.setCharacterSize(100);

    messageText.setFillColor(Color::White);
    scoreText.setFillColor(Color::White);

    FloatRect textRect = messageText.getLocalBounds();
    messageText.setOrigin(textRect.left + textRect.width / 2.0f, textRect.top + textRect.height / 2.0f);
    messageText.setPosition(Xmax / 2.0f, Ymax / 2.0f);
    scoreText.setPosition(20, 20);
    //**********************************************************

    // Barra de tiempo
    RectangleShape timeBar;
   
    float timeBarStartWidth = 1920;
    float timeBarHeight = 80;
    
    timeBar.setSize(Vector2f(timeBarStartWidth, timeBarHeight));
    timeBar.setFillColor(Color::Red);
    timeBar.setPosition(0, 980);
    
    Time gameTimeTotal;
   
    float timeRemaining = 6.0f;
    float timeBarWidthPerSecond = timeBarStartWidth / timeRemaining;
    //**********************************************************

    // Rama
    Sprite branches[num_branches];
    side branchPositions[num_branches];
    
    Texture TBranch;
    TBranch.loadFromFile("graphics/branch.png");

    Sprite SBranch;
    SBranch.setTexture(TBranch);
    for (int i = 0; i < num_branches; i++)
    {
        branches[i].setTexture(TBranch);
        branches[i].setPosition(-2000, -2000);

        branches[i].setOrigin(220, 20);
    }
    //**********************************************************

    // Input del Jugador
    bool acceptInput = false;
    //**********************************************************

    // rip
    Texture TRip;
    TRip.loadFromFile("graphics/rip.png");

    Sprite SRip;
    SRip.setTexture(TRip);
    //**********************************************************

    // Jugador
    Texture TPlayer;
    TPlayer.loadFromFile("graphics/player.png");

    Sprite SPlayer;
    SPlayer.setTexture(TPlayer);

    side playerSide = side::LEFT;
    branchPositions[3] = side::NONE;
    
    // Jugador Dreta
    Texture TPlayerR;
    TPlayerR.loadFromFile("graphics/player left.png");

    Sprite SPlayerR;
    SPlayerR.setTexture(TPlayerR);

    side playerRSide = side::RIGHT;
    branchPositions[3] = side::NONE;
    //**********************************************************

    // Jugador Esquerra
    Texture TPlayerL;
    TPlayerL.loadFromFile("graphics/player right.png");

    Sprite SPlayerL;
    SPlayerL.setTexture(TPlayerL);

    side playerLSide = side::LEFT;
    branchPositions[3] = side::NONE;
    //**********************************************************

    // Axe Derecha
    Texture TAxeR;
    TAxeR.loadFromFile("graphics/axe right.png");

    Sprite SAxeR;
    SAxeR.setTexture(TAxeR);
    const float AXE_POSITION_RIGHT = 1100;
    //**********************************************************

    // Axe Izquierda
    Texture TAxeL;
    TAxeL.loadFromFile("graphics/axe left.png");

    Sprite SAxeL;
    SAxeL.setTexture(TAxeL);
    const float AXE_POSITION_LEFT = 650;
    //**********************************************************
    
    // Trozo de tronco
    Texture TLog;
    TLog.loadFromFile("graphics/log.png");

    Sprite SLog;
    SLog.setTexture(TLog);

    bool logActive = false;
    float logSpeedX = 1000;
    float logSpeedY = 1500;

    SLog.setScale(1, 1.1);
    //**********************************************************

    // Declarar el texto para FPS
    Text fpsText;
    fpsText.setFont(font);
    fpsText.setCharacterSize(50);
    fpsText.setFillColor(Color::White);
    fpsText.setPosition(Xmax - 300, 20); 
    //**********************************************************

    // Sonido Picar
    SoundBuffer SBPicar;
    SBPicar.loadFromFile("sound/picar.wav");
    Sound SPicar;
    SPicar.setBuffer(SBPicar);
    //**********************************************************

    // Sonido Morir
    SoundBuffer SBMorir;
    SBMorir.loadFromFile("sound/morir.wav");
    Sound SMorir;
    SMorir.setBuffer(SBMorir);
    //**********************************************************

    // Sonido Musica
    SoundBuffer SBMusica;
    SBMusica.loadFromFile("sound/musica.wav");
    Sound SMusica;
    SMusica.setBuffer(SBMusica);
    //**********************************************************

    // Sonido tiempo
    SoundBuffer SBTiempo;
    SBTiempo.loadFromFile("sound/tiempo.wav");
    Sound STiempo;
    STiempo.setBuffer(SBTiempo);
    //**********************************************************

    // Mientras la ventana esta abierta (que haga lo que hay dentro)
    Clock clock;
    srand((int)time(0) * 10);

    while (window.isOpen())
    {
        /*
        ****************************************
        Handle the players input
        ****************************************
        */
            
        Event event;
       while (window.pollEvent(event))
       {
            if (event.type == Event::KeyReleased && !paused)
            {
                                acceptInput = true;
                SAxeR.setPosition(2000, SAxeR.getPosition().y);
                SAxeL.setPosition(2000, SAxeL.getPosition().y);
            }
        }

        if (Keyboard::isKeyPressed(Keyboard::Escape))
        {
            window.close();
        }

       if (Keyboard::isKeyPressed(Keyboard::Return))
       {
           paused = false;

           SMusica.play();

           SPlayerR.setPosition(3000, 3000);
           SPlayerL.setPosition(3000, 3000);

           score = 0;
           timeRemaining = 6;

           for (int i = 1; i < num_branches; i++)
           {
               branchPositions[i] = side::NONE;
           }

           SRip.setPosition(675, 2000);

           SPlayer.setPosition(580, 690);

           acceptInput = true;
       }

       if (acceptInput)
       {
           if (Keyboard::isKeyPressed(Keyboard::Right))
           {
               playerSide = side::RIGHT;

               SPicar.play();
               
               SPlayerL.setPosition(3000, 3000);
               SPlayer.setPosition(3000, 3000);
               
               score ++;

               timeRemaining += (2 / score) + .15;
               SAxeR.setPosition(AXE_POSITION_RIGHT, 690);
               SPlayerR.setPosition(1200, 690);

               updateBranches(score, branchPositions);

               SLog.setPosition(815, 627);
               logSpeedX = -5000;
               logActive = true;
               acceptInput = false;
           }

           if (Keyboard::isKeyPressed(Keyboard::Left))
           {
               playerSide = side::LEFT;

               SPicar.play();
               
               SPlayerR.setPosition(3000, 3000);
               SPlayer.setPosition(3000, 3000);
               
               score++;

               timeRemaining += (2 / score) + .15;
               SAxeL.setPosition(AXE_POSITION_LEFT,690);
               SPlayerL.setPosition(650, 690);

               updateBranches( score, branchPositions);

               SLog.setPosition(810, 627);
               logSpeedX = 5000;
               logActive = true;
               acceptInput = false;
           }

       }

        /*
        ****************************************
        Update the scene
        ****************************************
        */

       if (!paused)
       {
           Time dt = clock.restart();

           for (int i = 0; i < 3; i++) {

               if (!spriteCloud[i].Active)
               {
                   spriteCloud[i].Speed = (rand() % 200);
                   spriteCloud[i].dibuix.setPosition(-200, (rand() % 10) + i * 150);
                   spriteCloud[i].Active = true;
               }

               else
               {
                   spriteCloud[i].dibuix.setPosition(spriteCloud[i].dibuix.getPosition().x + (spriteCloud[i].Speed * dt.asSeconds()),
                       spriteCloud[i].dibuix.getPosition().y);
                   if (spriteCloud[i].dibuix.getPosition().x > Xmax)
                   {
                       spriteCloud[i].Active = false;
                   }
               }
           }

           // Update the score text
           std::stringstream ss;
           ss << "Score = " << score;
           scoreText.setString(ss.str());

           timeRemaining -= dt.asSeconds();
           timeBar.setSize(Vector2f(timeBarWidthPerSecond * timeRemaining, timeBarHeight));



           if (timeRemaining <= 0.0f)
           {
               paused = true;

               messageText.setString("SE TE ACABO EL TIEMPO!!!");

               STiempo.play();

               FloatRect textRect = messageText.getLocalBounds();
               messageText.setOrigin(textRect.left + textRect.width / 2.0f, textRect.top + textRect.height / 2.0f);
               messageText.setPosition(1920 / 2.0f, 1080 / 2.0f);
           }

           for (int i = 0; i < num_branches; i++)
           {
               float height = i * 150;

               if (branchPositions[i] == side::LEFT)
               {
                   branches[i].setPosition(610, height);
                   branches[i].setRotation(180);
               }

               else if (branchPositions[i] == side::RIGHT)
               {
                   branches[i].setPosition(1315, height);
                   branches[i].setRotation(0);
               }

               else
               {
                   branches[i].setPosition(3000, height);
               }
           }

           if (logActive)
           {
               SLog.setPosition(
                   SLog.getPosition().x + (logSpeedX * dt.asSeconds()),
                   SLog.getPosition().y - (logSpeedY * dt.asSeconds())
               );

               if (SLog.getPosition().x < -100 || SLog.getPosition().x > 2000)
               {
                   logActive = false;
                   SLog.setPosition(810, 627);
               }
           }
       }

       if (branchPositions[5] == playerSide)
       {
           paused = true;
           acceptInput = false;

           if (playerSide == side::LEFT)
           {
               SRip.setPosition(525, 770);
               SMorir.play();
           }
           else
           {
               SRip.setPosition(1200, 770);
               SMorir.play();
           }
           

           SPlayer.setPosition(2000, 660);
           SPlayerL.setPosition(2000, 660);
           SPlayerR.setPosition(2000, 660);
           SAxeL.setPosition(2000, 660);
           SAxeR.setPosition(2000, 660);

           messageText.setString("APLATSAOOO!!!");

           FloatRect textRect = messageText.getLocalBounds();
           messageText.setOrigin(textRect.left + textRect.width / 2.0f,
                                 textRect.top + textRect.height / 2.0f);
           messageText.setPosition(1920 / 2.0f, 1080 / 2.0f);
       }

       Time dt = clock.restart();
       float fps = 1.0f / dt.asSeconds(); 
       std::stringstream ss;
       ss << "FPS: " << static_cast<int>(fps);
       fpsText.setString(ss.str());

        /*
        ****************************************
        Draw the scene
        ****************************************
        */

        window.draw(SBackground);

        for (int i = 0; i < 3; i++) {
            window.draw(spriteCloud[i].dibuix);
        }

        for (int i = 0; i < num_branches; i++)
        {
            window.draw(branches[i]);
        }

        window.draw(STree);

        window.draw(SLog);

        window.draw(SAxeR);

        window.draw(SPlayer);

        window.draw(SPlayerR);

        window.draw(SPlayerL);

        window.draw(SRip);

        window.draw(SAxeL);

        window.draw(SBee);

        window.draw(scoreText);

        if (paused)
        {
            window.draw(messageText);
        }

        window.draw(timeBar);

        window.draw(fpsText);

        window.display();
        
    }
    return 0;
}

void updateBranches(int seed, side branchPositions[])
{
    for (int j = num_branches-1; j > 0; j--)
    {
        branchPositions[j] = branchPositions[j - 1];
    }

    srand((int)time(0) + seed);
    int r = (rand() % 5);
    switch (r)
    {
    case 1:
        branchPositions[0] = side::LEFT;
        break;
    case 2:
        branchPositions[0] = side::RIGHT;
        break;
    default:
        branchPositions[0] = side::NONE;
        break;
    }
}

