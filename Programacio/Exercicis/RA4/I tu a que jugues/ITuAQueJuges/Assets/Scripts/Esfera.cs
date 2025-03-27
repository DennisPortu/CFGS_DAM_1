using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// Clase base (Herencia)
public class ObjetoInteractivo : MonoBehaviour
{
    public virtual void Interactuar()
    {
        Debug.Log("Interacción con un objeto base.");
    }
}

// Clase derivada (Polimorfismo y Override)
public class Esfera : ObjetoInteractivo
{
    public override void Interactuar()
    {
        Debug.Log("¡La esfera ha sido tocada!");
    }

    private void OnCollisionEnter(Collision other)
    {
        if (other.gameObject.CompareTag("Cubo"))
        {
            Interactuar();
        }
    }
}



