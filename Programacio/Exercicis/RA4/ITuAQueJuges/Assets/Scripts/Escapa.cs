using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;

public class Escapa : MonoBehaviour
{
    public class EscapaDelCubo : MonoBehaviour
    {
        private GameObject cubo;  // Ahora es Transform en vez de GameObject
        private Rigidbody esferaRB; // Rigidbody de la esfera
        public float speed = 5f; // Velocidad de la esfera

        void Start()
        {
            cubo = GameObject.Find("Cube");
            esferaRB = GetComponent<Rigidbody>();
        }


        void Update()
        {
            cubo = GameObject.Find("Cube");
            esferaRB = GetComponent<Rigidbody>();

            if (cubo != null)
            {
                // Calcular dirección opuesta
                Vector3 direction = (cubo.transform.position + transform.position).normalized;
                esferaRB.AddForce(direction * speed);

                // Mover la esfera en la dirección opuesta
                transform.position += direction * speed * Time.deltaTime;
            }
        }
    }
}