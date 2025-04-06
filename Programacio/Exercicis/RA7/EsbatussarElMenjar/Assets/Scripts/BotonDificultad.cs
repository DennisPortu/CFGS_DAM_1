using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class BotonDificultad : MonoBehaviour
{
    private GameManager gameManager;
    public Button boto;
    public GameObject menu;
    public int dificultad;

    // Start is called before the first frame update
    void Start()
    {
        menu.SetActive(true);

        boto.onClick.AddListener(SetDificultad);
        gameManager = GameObject.Find("Game Manager").GetComponent<GameManager>();
    }

    void SetDificultad()
    {
        Debug.Log(gameObject.name + " ha sido clicado");
        menu.SetActive(false);
        gameManager.isGameActive = true;
        
        switch (dificultad)
        {
            case 1:
                gameManager.spawnRate = 2f;
                break;
            case 2:
                gameManager.spawnRate = 1f;
                break;
            case 3:
                gameManager.spawnRate = 0.5f;
                break;
            default:
                break;
        }

        gameManager.StartCoroutine(gameManager.SpawnTarget());
    }
}
