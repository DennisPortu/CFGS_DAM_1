using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// Enumeración para los estados del jugador (Enumeraciones)
public enum EstadoJugador { Inactivo, Moviendo }

public class Jugador : MonoBehaviour
{
    // Estado actual del jugador, inicializado como Inactivo
    public EstadoJugador estadoActual = EstadoJugador.Inactivo;
    // Velocidad de movimiento del jugador
    public float velocidad = 5f;

    // Método llamado una vez por frame
    void Update()
    {
        // Llama al método Mover para manejar el movimiento del jugador
        Mover();
    }

    // Método para manejar el movimiento del jugador
    void Mover()
    {
        // Obtiene la entrada del usuario para el movimiento horizontal y vertical
        float h = Input.GetAxis("Horizontal");
        float v = Input.GetAxis("Vertical");

        // Crea un vector de movimiento basado en la entrada del usuario, la velocidad y el tiempo
        Vector3 movimiento = new Vector3(h, 0, v) * velocidad * Time.deltaTime;
        // Aplica el movimiento al transform del jugador
        transform.Translate(movimiento);

        // Cambia el estado del jugador según si se está moviendo o no
        estadoActual = (movimiento != Vector3.zero) ? EstadoJugador.Moviendo : EstadoJugador.Inactivo;
    }

    // Método sobrecargado para interactuar sin parámetros
    public void Interactuar()
    {
        // Muestra un mensaje en la consola indicando que el jugador ha interactuado con un objeto
        Debug.Log("El jugador ha interactuado con un objeto.");
    }

    // Método sobrecargado para interactuar con un mensaje específico
    public void Interactuar(string mensaje)
    {
        // Muestra el mensaje proporcionado en la consola
        Debug.Log(mensaje);
    }

    // Método llamado cuando el jugador colisiona con otro objeto
    private void OnCollisionEnter(Collision other)
    {
        // Verifica si el objeto con el que colisionó tiene la etiqueta "Esfera"
        if (other.gameObject.CompareTag("Esfera"))
        {
            // Llama al método Interactuar con un mensaje específico
            Interactuar("El jugador tocó la esfera.");
        }
    }
}
