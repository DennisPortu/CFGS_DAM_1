using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.SceneManagement;

public class GameManager : MonoBehaviour
{
    public List<GameObject> targets;
    public float spawnRate = 1f;
    private int score;
    private int vides = 3;
    public TextMeshProUGUI scoreText;
    public TextMeshProUGUI videsText;
    public TextMeshProUGUI gameoverText;
    public bool isGameActive = false;
    public GameObject BotoReiniciar;

    void Start()
    {
        UpdateScore(0);
    }

    public void UpdateScore(int scoreToAdd)
    {
        score += scoreToAdd;
        scoreText.text = "Score: " + score;
    }

    public void UpdateVides(int videsToAdd)
    {
        vides += videsToAdd;
        videsText.text = "Vides: " + vides;

        if (vides == 0)
        {
            gameover();
        }
    }

    public IEnumerator SpawnTarget()
    {
        while (isGameActive == true)
        {
            yield return new WaitForSeconds(spawnRate);
            int index = Random.Range(0, targets.Count);
            Instantiate(targets[index]);
        }
    }

    void Update()
    {

    }

    public void ReiniciarJuego()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
    }

    public void gameover()
    {
        isGameActive = false;
        gameoverText.gameObject.SetActive(true);
        BotoReiniciar.SetActive(true);
    }
}
