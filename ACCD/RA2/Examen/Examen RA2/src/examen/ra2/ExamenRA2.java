package examen.ra2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author AluCiclesGS1
 */
public class ExamenRA2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       Menu();  // Mostra el menu
        
        int opcio = 0;  // tenim la variable opcio inicialitzada en 0
        
        while (opcio != 9) {  // mentres opcio sigui diferent a 9 repeteix el bucle
            Scanner teclado = new Scanner (System.in);  // detecta el teclat
            System.out.println("-- Escull opcio: ");  // mostra el text
            opcio = teclado.nextInt();  // opcio s'actualitzara per lo que entris amb elteclat
            seleccio(opcio);  // seleccióna opció
        }
    }
    
    private static int ultimIdCartes() throws SQLException {
        int id = 0;
        Connection connection = null;
        // Database connect
        // Conetamos con la base de datos
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/clash", "postgres", "accedir");

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM cartes order by id ASC");
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } 
        
        catch (SQLException ex) {
            System.err.print("Excepcio de SQL: ");
            System.err.print(ex.getMessage());
        }
        
        connection.close();
        return id;
    }

    private static void Menu() {
        // El menu que es veura
        System.out.println("***************** Menu ****************");
        System.out.println("***************************************");
        System.out.println("**        1. Afegir cartes           **");
        System.out.println("***************************************");
        System.out.println("**     2. Veure cartes jugador       **");
        System.out.println("***************************************");
        System.out.println("**           9.- SORTIR              **");
        System.out.println("***************************************");
    }

    private static void seleccio(int opcio) throws SQLException {
        switch(opcio){ // Fem un switc per escollir la opció del menu
            case 1:
                exercici1();  // amb el numero 1 va al exercici 1
                break;
            case 2:
                exercici2();  // amb el numero 2 va al exercici 2
                break;
            default:  // surt del switch
                break;
        }
    }

    private static void exercici1() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // busca aquest driver
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex); // si no el trova mostra aixo
        }

        Connection connection = null;
        
        // Database connect
        // Conectamos con la base de datos
        connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/clash",
                "postgres", "accedir");
   
        Scanner entrada = new Scanner(System.in); // creem l'entrada del teclat
        
        // Llista els jugadors disponibles
        System.out.println("\nLlista de jugadors disponibles:");
        String queryJugadors = "SELECT id, nom FROM jugadors ORDER BY id ASC";
        PreparedStatement stmtJugadors = connection.prepareStatement(queryJugadors);
        ResultSet rs = stmtJugadors.executeQuery();

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + " - Nom: " + rs.getString("nom"));
        }
        
        System.out.println("\nQuin a quin jugador li vols afegir la carta? ");
        String idjug = entrada.nextLine();
        System.out.println("Entra el nom de la carta: ");
        String nom = entrada.nextLine();
        System.out.println("Entra el nivell (1-14): ");
        String nivell = entrada.nextLine();
        System.out.println("Entra la raresa de la carta: ");
        String raresa = entrada.nextLine();
        
        int id = ultimIdCartes() + 1; // Sumem 1 a id per cambiar d'usuari
        PreparedStatement insertCarta = connection.prepareStatement("INSERT INTO cartes (id, idjug, nom, nivell, raresa) VALUES (?, ?, ?, ?, ?) "); // creem els espais
        
        // Definim els espais
        insertCarta.setInt(1, id);
        insertCarta.setInt(2, Integer.parseInt(idjug));
        insertCarta.setString(3, nom);
        insertCarta.setInt(4, Integer.parseInt(nivell));
        insertCarta.setString(5, raresa);
        insertCarta.executeUpdate();
        
        int rowsUpdated = insertCarta.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Les dades de la carta s'han afegit correctament.\n");
        } else {
            System.out.println("No s'ha pogut afegir la carta.");
        }
        
        connection.close(); // tenca la connexio
    }

    private static void exercici2() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // busca aquest driver
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex); // si no el trova mostra aixo
        }

        Connection connection = null;
        
        // Database connect
        // Conectamos con la base de datos
        connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/clash",
                "postgres", "accedir");
   
        Scanner entrada = new Scanner(System.in); // creem l'entrada del teclat
        
        // Llista els jugadors disponibles
        System.out.println("\nLlista de jugadors disponibles:");
        String queryJugadors = "SELECT id, nom FROM jugadors ORDER BY id ASC";
        PreparedStatement stmtJugadors = connection.prepareStatement(queryJugadors);
        ResultSet rs = stmtJugadors.executeQuery();

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + " - Nom: " + rs.getString("nom"));
        }

        System.out.println("De quin jugador vols veure les seves cartes? ");
        int resposta = entrada.nextInt();
        entrada.nextLine(); // Consumeix la línia restant
        
        // Mostra les cartes
        String selectCartes = "SELECT * FROM cartes WHERE idjug = ?";
        PreparedStatement selectStmt = connection.prepareStatement(selectCartes);
        selectStmt.setInt(1, resposta);
        ResultSet rsCartes = selectStmt.executeQuery();
        
        while (rsCartes.next()){
            System.out.println("ID: " + rsCartes.getInt("idjug") + " - Nom: " + rsCartes.getString("nom"));
        }
    }
}

