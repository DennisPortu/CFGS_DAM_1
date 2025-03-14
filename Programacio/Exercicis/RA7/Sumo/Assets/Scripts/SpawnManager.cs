using System.Collections;
using System.Collections.Generic;
using System.Runtime.CompilerServices;
using UnityEngine;

public class SpawnManager : MonoBehaviour
{
    public GameObject[] enemyPrefab;
    private float spawnRange = 9;
    int WaveNumber = 1;

    void Start()
    {
        SpawnEnemy(WaveNumber);
    }

    void SpawnEnemy(int WaveNumber) 
    {
        int enemyRandom = Random.Range(0, 3);
        for (int i = 0; i < WaveNumber; i++) 
        {
            int readEnemy = Random.Range(0, 3);
            Instantiate(enemyPrefab[enemyRandom], GenerateSpawnPosition(), enemyPrefab[enemyRandom].transform.rotation);
        }
    }

    private Vector3 GenerateSpawnPosition()
    {
        float spawnPosX = Random.Range(-spawnRange, spawnRange);
        float spawnPosZ = Random.Range(-spawnRange, spawnRange);
        Vector3 randomPos = new Vector3(spawnPosX, 5, spawnPosZ);
        return randomPos;
    }

    int enemyCount;

    void Update()
    {
        enemyCount = FindObjectsOfType<Enemy>().Length;
        if (enemyCount == 0)
        {
            SpawnEnemy(WaveNumber);
        }
    }
}
