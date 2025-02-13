package dmm_ra2_p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.sound.midi.SoundbankResource;
import java.sql.PreparedStatement;


public class DMM_RA2_P1 {

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

    private static int ultimId() throws SQLException {
        int id = 0;
        Connection connection = null;
        // Database connect
        // Conetamos con la base de datos
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/clash", "postgres", "accedir");
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM jugadors order by id ASC");
            while(rs.next()){
                id = rs.getInt("id");
            }
        }
        catch (SQLException ex){
            System.err.print("Excepcio de SQL: ");
            System.err.print(ex.getMessage());
        }
        connection.close();
        return id;
    }
    
    private static int ultimIdPartides() throws SQLException {
        int id = 0;
        Connection connection = null;
        // Database connect
        // Conetamos con la base de datos
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/clash", "postgres", "accedir");

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM partides order by id ASC");
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            System.err.print("Excepcio de SQL: ");
            System.err.print(ex.getMessage());
        }
        connection.close();
        return id;
    }

    private static void Menu() throws SQLException {
        // El menu que es veura
        System.out.println("***************** Menu ****************");
        System.out.println("***************************************");
        System.out.println("**  1.- Llistar jugadors i partides  **");
        System.out.println("***************************************");
        System.out.println("**       2.- Afegir un jugador       **");
        System.out.println("***************************************");
        System.out.println("**     3.- Actualitza un jugador     **");
        System.out.println("***************************************");
        System.out.println("**      4.- Afegir una partida       **");
        System.out.println("***************************************");
        System.out.println("**      5.- Elimina un jugador       **");
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
            case 3:
                exercici3();  // amb el numero 3 va al exercici 3
                break;
            case 4:
                exercici4(); // amb el numero 4 va al exercici 4
                break;
            case 5:
                exercici5(); // amb el numero 5 va al exercici 5
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

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM jugadors"); // Mostra els jugadors

        while (rs.next()) { // Mostra les dades dels jugadors y les partides respectives del mateix
            System.out.println("JUGADOR \n"); 
            
            System.out.println("Id Jugador: " + rs.getString("id"));

            System.out.println("Nom Jugador: " + rs.getString("nom"));

            System.out.println("Nivell Jugador: " + rs.getString("nivell"));

            System.out.println("Copes Jugador: " + rs.getString("copes"));

            System.out.println("Oro Jugador: " + rs.getString("oro"));

            System.out.println("Gemes Jugador: " + rs.getString("gemes"));

            System.out.println("Estrelles Jugador: " + rs.getString("estrelles"));
            System.out.println("\n");

            int id = rs.getInt("id");

            String query = "SELECT * FROM partides WHERE id_jug_1 = " + id + " OR id_jug_2 = " + id;
            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query);
            
            System.out.println("PARTIDES \n");
            
            while (rs2.next()) {
                System.out.println("Resultat: " + rs2.getString("resultat"));
            }
            System.out.println("\n");
        }
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
        System.out.println("Entra el nom del jugador: ");
        String nom = entrada.nextLine();
        System.out.println("Entra el nivell (1-14): ");
        String nivell = entrada.nextLine();
        System.out.println("Entra les copes: ");
        String copes = entrada.nextLine();
        System.out.println("Entra el oro: ");
        String oro = entrada.nextLine();
        System.out.println("Entra les gemes: ");
        String gemes = entrada.nextLine();
        System.out.println("Entra les estrelles: ");
        String estrelles = entrada.nextLine();
        
        int id = ultimId() + 1; // Sumem 1 a id per cambiar d'usuari
        PreparedStatement insertJug = connection.prepareStatement("INSERT INTO jugadors (id, nom, nivell, copes, oro, gemes, estrelles) VALUES (?, ?, ?, ?, ?, ?, ?) "); // creem els espais

        // Definim els espais
        insertJug.setInt(1, id);
        insertJug.setString(2, nom);
        insertJug.setInt(3, Integer.parseInt(nivell));
        insertJug.setInt(4, Integer.parseInt(copes));
        insertJug.setInt(5, Integer.parseInt(oro));
        insertJug.setInt(6, Integer.parseInt(gemes));
        insertJug.setInt(7, Integer.parseInt(estrelles));
        insertJug.executeUpdate();
                
        connection.close(); // tenca la connexio
    }

    private static void exercici3() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // Registra el driver
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex); // Mostra error si no troba el driver
        }

        Connection connection = null;

        // Connecta amb la base de dades
        connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/clash",
                "postgres", "accedir");

        Scanner entrada = new Scanner(System.in);

        // Demana el nom del jugador a modificar
        System.out.println("Entra el nom del jugador que vols modificar: ");
        String nomJugador = entrada.nextLine();

        // Verifica si l'usuari existeix
        String selectQuery = "SELECT * FROM jugadors WHERE nom = ?";
        PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
        selectStmt.setString(1, nomJugador);
        ResultSet rs = selectStmt.executeQuery();

        if (rs.next()) {
            System.out.println("Jugador trobat. Les dades actuals són:");
            System.out.println("Nom: " + rs.getString("nom"));
            System.out.println("Nivell: " + rs.getInt("nivell"));
            System.out.println("Copes: " + rs.getInt("copes"));
            System.out.println("Oro: " + rs.getInt("oro"));
            System.out.println("Gemes: " + rs.getInt("gemes"));
            System.out.println("Estrelles: " + rs.getInt("estrelles"));

            // Demana les noves dades
            System.out.println("\nEntra les noves dades del jugador (deixa buit per mantenir l'actual):");

            System.out.println("Nom: ");
            String nom = entrada.nextLine();
            if (nom.isEmpty()) {
                nom = rs.getString("nom");
            }

            System.out.println("Nivell (1-14): ");
            String nivellInput = entrada.nextLine();
            int nivell = nivellInput.isEmpty() ? rs.getInt("nivell") : Integer.parseInt(nivellInput);

            System.out.println("Copes: ");
            String copesInput = entrada.nextLine();
            int copes = copesInput.isEmpty() ? rs.getInt("copes") : Integer.parseInt(copesInput);

            System.out.println("Oro: ");
            String oroInput = entrada.nextLine();
            int oro = oroInput.isEmpty() ? rs.getInt("oro") : Integer.parseInt(oroInput);

            System.out.println("Gemes: ");
            String gemesInput = entrada.nextLine();
            int gemes = gemesInput.isEmpty() ? rs.getInt("gemes") : Integer.parseInt(gemesInput);

            System.out.println("Estrelles: ");
            String estrellesInput = entrada.nextLine();
            int estrelles = estrellesInput.isEmpty() ? rs.getInt("estrelles") : Integer.parseInt(estrellesInput);

            // Actualitza les dades
            String updateQuery = "UPDATE jugadors SET nom = ?, nivell = ?, copes = ?, oro = ?, gemes = ?, estrelles = ? WHERE nom = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
            updateStmt.setString(1, nom);
            updateStmt.setInt(2, nivell);
            updateStmt.setInt(3, copes);
            updateStmt.setInt(4, oro);
            updateStmt.setInt(5, gemes);
            updateStmt.setInt(6, estrelles);
            updateStmt.setString(7, nomJugador);

            int rowsUpdated = updateStmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Dades del jugador actualitzades correctament.");
            } else {
                System.out.println("No s'ha pogut actualitzar el jugador.");
            }
        } else {
            System.out.println("Jugador amb nom '" + nomJugador + "' no trobat.");
        }

        connection.close(); // Tanca la connexió
    }

    private static void exercici4() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // Registra el driver
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex); // Mostra error si no troba el driver
        }

        Connection connection = null;

        // Connecta amb la base de dades
        connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/clash",
                "postgres", "accedir");

        Scanner entrada = new Scanner(System.in);

        // Llista els jugadors
        System.out.println("Llista de jugadors disponibles:");
        String queryJugadors = "SELECT id, nom FROM jugadors ORDER BY id ASC";
        PreparedStatement stmtJugadors = connection.prepareStatement(queryJugadors);
        ResultSet rs = stmtJugadors.executeQuery();

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + " - Nom: " + rs.getString("nom"));
        }

        // Selecciona el primer jugador
        System.out.println("\nEntra l'ID del primer jugador:");
        int idJug1 = entrada.nextInt();
        entrada.nextLine(); // Consumeix la línia restant

        // Selecciona el segon jugador
        System.out.println("Entra l'ID del segon jugador:");
        int idJug2 = entrada.nextInt();
        entrada.nextLine(); // Consumeix la línia restant

        if (idJug1 == idJug2) {
            System.out.println("Error: Els dos jugadors no poden ser el mateix.");
            connection.close();
            return;
        }

        // Demana el resultat de la partida
        System.out.println("Entra el resultat de la partida (per exemple, '3-1'):");
        String resultat = entrada.nextLine();

        // Demana el temps de la partida
        System.out.println("Entra el temps de la partida (per exemple, '2:30'):");
        String temps = entrada.nextLine();

        // Demana el tipus de partida
        System.out.println("Entra el tipus de partida (per exemple, 'Lliga'):");
        String tipus = entrada.nextLine();

        // Afegeix la partida a la base de dades
        String insertPartida = "INSERT INTO partides (id_jug_1, id_jug_2, resultat, temps, tipus, id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStmt = connection.prepareStatement(insertPartida);
        insertStmt.setInt(1, idJug1);
        insertStmt.setInt(2, idJug2);
        insertStmt.setString(3, resultat);
        insertStmt.setString(4, temps);
        insertStmt.setString(5, tipus);
        insertStmt.setInt(6, ultimIdPartides() + 1);

        int rowsInserted = insertStmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Partida afegida correctament!");
        } 
        
        else {
            System.out.println("No s'ha pogut afegir la partida.");
        }

        connection.close(); // Tanca la connexió
    }

    private static void exercici5() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // Registra el driver
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex); // Mostra error si no troba el driver
            return;
        }

        Connection connection = null;

        // Connecta amb la base de dades
        connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/clash",
                "postgres", "accedir");

        Scanner entrada = new Scanner(System.in);

        // Llista els jugadors disponibles
        System.out.println("Llista de jugadors disponibles:");
        String queryJugadors = "SELECT id, nom FROM jugadors ORDER BY id ASC";
        PreparedStatement stmtJugadors = connection.prepareStatement(queryJugadors);
        ResultSet rs = stmtJugadors.executeQuery();

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + " - Nom: " + rs.getString("nom"));
        }

        // Demana quin jugador es vol eliminar
        System.out.println("\nEntra l'ID del jugador que vols eliminar:");
        int idJugador = entrada.nextInt();
        entrada.nextLine(); // Consumeix la línia restant

        // Verifica si l'ID existeix
        String selectJugador = "SELECT nom FROM jugadors WHERE id = ?";
        PreparedStatement selectStmt = connection.prepareStatement(selectJugador);
        selectStmt.setInt(1, idJugador);
        ResultSet rsJugador = selectStmt.executeQuery();

        if (rsJugador.next()) {
            String nomJugador = rsJugador.getString("nom");
            System.out.println("Estàs segur que vols eliminar el jugador '" + nomJugador + "'? (s/n):");
            String confirmacio = entrada.nextLine();

            if (confirmacio.equalsIgnoreCase("s")) {
                // Elimina el jugador
                String deleteJugador = "DELETE FROM jugadors WHERE id = ?";
                PreparedStatement deleteStmt = connection.prepareStatement(deleteJugador);
                deleteStmt.setInt(1, idJugador);

                int rowsDeleted = deleteStmt.executeUpdate();
                
                if (rowsDeleted > 0) {
                    System.out.println("El jugador '" + nomJugador + "' ha estat eliminat correctament.");
                } 
                
                else {
                    System.out.println("No s'ha pogut eliminar el jugador.");
                }
            } 
            
            else {
                System.out.println("El jugador no ha estat eliminat.");
            }
        } 
        
        else {
            System.out.println("No s'ha trobat cap jugador amb l'ID especificat.");
        }

        connection.close(); // Tanca la connexió
    }
}