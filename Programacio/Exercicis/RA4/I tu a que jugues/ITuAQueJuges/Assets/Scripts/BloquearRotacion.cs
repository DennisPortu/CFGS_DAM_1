using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BloquearRotacion : MonoBehaviour
{
    private Rigidbody rb;

    void Start()
    {
        rb = GetComponent<Rigidbody>();
        rb.freezeRotation = true; // Bloquea la rotaci�n
    }
}