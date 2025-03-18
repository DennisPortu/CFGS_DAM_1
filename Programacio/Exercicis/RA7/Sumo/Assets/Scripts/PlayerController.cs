using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;

public class PlayerController : MonoBehaviour
{
    private Rigidbody playerRB;
    public float speed = 5.0f;
    private GameObject focalPoint;
    
    public bool hasPowerup;
    private float powerupStrength = 15.0f;

    public bool canShoot;
    public GameObject projectilePrefab;

    void Start()
    {
        playerRB = GetComponent<Rigidbody>();
        focalPoint = GameObject.Find("FocalPoint"); 
    }

    void Update()
    {
        float forwardInput = Input.GetAxis("Vertical");
        playerRB.AddForce(focalPoint.transform.forward * speed * forwardInput);

        if (canShoot && Input.GetKey(KeyCode.F))
        {
            Shoot();
        }

        if (Input.GetKey(KeyCode.Space))   
        {
            Jump();
        }
    }

    private void Shoot() 
    {
        GameObject ProjectilTemp = Instantiate(projectilePrefab, transform.position, focalPoint.transform.rotation);
        Destroy(ProjectilTemp, 3);
    }

    private void Jump()
    {
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.CompareTag("Powerup"))
        {
            hasPowerup = true;
            Destroy(other.gameObject);
            StartCoroutine(PowerupCountdownRoutine());
        }

        else if (other.CompareTag("ShootPowerup")) 
        {
            canShoot = true;
            Destroy(other.gameObject );
            StartCoroutine(ShootPowerupCountdown());
        }
    }
    
    IEnumerator PowerupCountdownRoutine() 
    {
        yield return new WaitForSeconds(7); 
        hasPowerup = false;
    }

    IEnumerator ShootPowerupCountdown() 
    {
        yield return new WaitForSeconds(7);
        canShoot = false;
    }
    
    private void OnCollisionEnter(Collision collision) 
    { 
        if (collision.gameObject.CompareTag("Enemy") && hasPowerup) 
        { 
            Rigidbody enemyRigidbody = collision.gameObject.GetComponent<Rigidbody>(); 
            Vector3 awayFromPlayer = (collision.gameObject.transform.position - transform.position); 
            Debug.Log("Player collided with " + collision.gameObject + " with powerup set to " + hasPowerup); 
            enemyRigidbody.AddForce(awayFromPlayer * powerupStrength, ForceMode.Impulse); 
        } 

        else if (collision.gameObject.CompareTag("Enemy") && canShoot) 
        {
            Rigidbody enemyRigidbody = collision.gameObject.GetComponent<Rigidbody>();
        }
    }
}
    
