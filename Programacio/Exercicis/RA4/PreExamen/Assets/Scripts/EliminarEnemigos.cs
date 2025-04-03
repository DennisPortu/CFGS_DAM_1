using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EliminarEnemigos : MonoBehaviour
{
    private Score Score;

    // Start is called before the first frame update
    void Start()
    {
        Score = GameObject.Find("Score").GetComponent<Score>();
    }

    // Update is called once per frame
    void Update()
    {

    }

    private void OnCollisionEnter(Collision other)
    {
        if (other.gameObject.CompareTag("Enemy"))
        {
            Destroy(other.gameObject);
            Score.UpdateScore(5);
        }
    }
}
