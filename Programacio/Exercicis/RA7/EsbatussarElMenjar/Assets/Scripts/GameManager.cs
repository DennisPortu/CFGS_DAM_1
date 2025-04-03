using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.SocialPlatforms.Impl;

public class GameManager : MonoBehaviour
{
    private DificultyButton DificultyButton;
    public List<GameObject> targets;
    private float spawnRate = 1.0f;
    private int score;
    private int vides = 3;
    public TextMeshProUGUI scoreText;
    public TextMeshProUGUI videsText;
    public TextMeshProUGUI gameoverText;
    public bool isGameActive = true;

    void Start()
    {
        StartCoroutine(SpawnTarget());
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

    IEnumerator SpawnTarget()
    {
        while (isGameActive)
        {
            yield return new WaitForSeconds(spawnRate);
            int index = Random.Range(0, targets.Count);
            Instantiate(targets[index]);
        }
    }

    void Update()
    {
        
    }

    public void gameover() 
    {
        isGameActive = false;
        gameoverText.gameObject.SetActive(true);
    }
}
