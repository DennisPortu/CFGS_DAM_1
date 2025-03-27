using System.Collections;
using System.Collections.Generic;
using System.Drawing;
using Unity.VisualScripting;
using UnityEditor;
using UnityEngine;
using UnityEngine.UI;



public class Escogedificultad : MonoBehaviour
{
    
    public GameObject suelo;
    public GameObject menu;
    public GameObject camara;

    public Button Facil;
    public Button Medio;
    public Button Dificil;

    private void Start()
    {
        menu.SetActive(true);

        Facil.onClick.AddListener(SetFacil);
        Medio.onClick.AddListener(SetMedio);
        Dificil.onClick.AddListener(SetDificil);
    }

    public void SetFacil()
    {
        Debug.Log("Facil");
        suelo.transform.localScale = new Vector3 (2f, 1f, 2f);
        camara.transform.position = new Vector3(0, 10, -14);
        menu.SetActive(false);
    }

    public void SetMedio()
    {
        Debug.Log("Medio");
        suelo.transform.localScale = new Vector3(1.5f, 1f, 1.5f);
        camara.transform.position = new Vector3(0, 8, -10);
        menu.SetActive(false);
    }

    public void SetDificil()
    {
        Debug.Log("Dificil");
        suelo.transform.localScale = new Vector3(1f, 1f, 1f);
        camara.transform.position = new Vector3(0, 5, -6);
        menu.SetActive(false);
    }
}
