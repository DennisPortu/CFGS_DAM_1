using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class DificultyButton : MonoBehaviour
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
        gameManager.isGameActive = false;
    }

    void SetDificultad() 
    {
        Debug.Log(gameObject.name + " ha sido clicado");
        menu.SetActive(false);
        gameManager.isGameActive = true;

    }
}
