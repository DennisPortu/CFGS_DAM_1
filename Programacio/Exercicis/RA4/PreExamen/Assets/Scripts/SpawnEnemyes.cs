using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawnEnemyes : MonoBehaviour
{
    public GameObject[] Enemies;
    private Rigidbody EnemyRB;
    private float Range = 5f;
    public int EnemyCount = 0;
    public int MaxEnemies = 5;
    private int WaveNumber;

    void Start()
    {
        
    }

    void Update()
    {
        EnemyCount = GameObject.FindGameObjectsWithTag("Enemy").Length;


        if (EnemyCount == 0)
        {
            SpawnEnemy();
        }
    }

    private void SpawnEnemy() 
    {
        
        int RandomNum = Random.Range(0, MaxEnemies);

        for (int i = 0; i < RandomNum; i++)
        {
            int EnemyRandom = Random.Range(0, MaxEnemies);
            Instantiate(Enemies[EnemyRandom], GenerateSpawnPosition(), Quaternion.identity);
        }
    }

    private Vector3 GenerateSpawnPosition()
    {
        float spawnPosX = Random.Range(-Range, Range);
        float spawnPosZ = Random.Range(-Range, Range);
        Vector3 randomPos = new Vector3(spawnPosX, 5, spawnPosZ);
        return randomPos;
    }

}
