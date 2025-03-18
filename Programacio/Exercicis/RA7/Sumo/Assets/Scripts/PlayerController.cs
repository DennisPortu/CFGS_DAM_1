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

    public float jumpForce = 10.0f;

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

        Debug.Log("Player Y Position: " + transform.position.y);

        if (Input.GetKeyDown(KeyCode.Space) && Mathf.Abs(transform.position.y) < 0.1f) 
        {
            Debug.Log("Jumping");
            Jump();
        }
    }

    private void Shoot()
    {
        GameObject ProjectilTemp = Instantiate(projectilePrefab, transform.position, focalPoint.transform.rotation);
        Destroy(ProjectilTemp, 3);
        canShoot = false;
        StartCoroutine(FireCooldown());
    }

    private void Jump()
    {
        playerRB.AddForce(Vector3.up * jumpForce, ForceMode.Impulse);
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
            Destroy(other.gameObject);
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

    IEnumerator FireCooldown()
    {
        yield return new WaitForSeconds(0.5f);
        canShoot = true;
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