using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// Enumeraci�n para los estados del jugador (Enumeraciones)
public enum EstadoJugador { Inactivo, Moviendo }

public class Jugador : MonoBehaviour
{
    public EstadoJugador estadoActual = EstadoJugador.Inactivo;
    public float velocidad = 5f;

    void Update()
    {
        Mover();
    }

    void Mover()
    {
        float h = Input.GetAxis("Horizontal");
        float v = Input.GetAxis("Vertical");

        Vector3 movimiento = new Vector3(h, 0, v) * velocidad * Time.deltaTime;
        transform.Translate(movimiento);

        // Cambia estado seg�n el movimiento
        estadoActual = (movimiento != Vector3.zero) ? EstadoJugador.Moviendo : EstadoJugador.Inactivo;
    }

    // M�todo sobrecargado (Sobrecarga de m�todos)
    public void Interactuar()
    {
        Debug.Log("El jugador ha interactuado con un objeto.");
    }

    public void Interactuar(string mensaje)
    {
        Debug.Log(mensaje);
    }

    private void OnCollisionEnter(Collision other)
    {
        if (other.gameObject.CompareTag("Esfera"))
        {
            Interactuar("El jugador toc� la esfera.");
        }
    }
}
