using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Moviment : MonoBehaviour
{
    public float speed = 10f;

    void Start()
    {
        
    }

    void Update()
    {
        Movimiento();
    }

    private void Movimiento() 
    {
        Vector3 movement = new Vector3(Input.GetAxis("Horizontal"), 0,Input.GetAxis("Vertical"));
        transform.Translate(movement * speed * Time.deltaTime);
    }


}
