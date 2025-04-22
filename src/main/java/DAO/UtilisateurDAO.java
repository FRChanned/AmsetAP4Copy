package DAO;

import model.MySQLConnection;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UtilisateurDAO {

    private final Connection connexion;

    public UtilisateurDAO() {
        this.connexion = MySQLConnection.getConnexion(); // Instanciation du Singleton
    }

    public static Connection getConnexion() {
        return getConnexion();
    }

    public List<User> getAll() { // Récupère tout les utilisateurs
        try {
            List<User> utilisateur = new ArrayList<User>();
            String query = "SELECT * FROM utilisateur";
            PreparedStatement ps = this.connexion.prepareStatement(query);
            ResultSet res = ps.executeQuery(query);
            while (res.next()) {
                int id = res.getInt("ID");
                String nom = res.getString("NOM");
                String prenom = res.getString("PRENOM");
                String adresse_mail = res.getString("ADRESSE_MAIL");
                String identifiant = res.getString("IDENTIFIANT");
                String mot_de_passe = res.getString("MOT_DE_PASSE");

                User utilisateur2 = new User(id, nom, prenom, adresse_mail, identifiant, mot_de_passe);
                utilisateur.add(utilisateur2);
            }
            return utilisateur;
        } catch (SQLException ex) {
            return null;
        }

    }

    public User create(User utilisateur) { // Création de l'utilisateur
        try {
            String sql = "INSERT INTO utilisateur (NOM, PRENOM, ADRESSE_MAIL, IDENTIFIANT, MOT_DE_PASSE) VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.connexion.prepareStatement(sql);
            ps.setString(1, utilisateur.getNom());
            ps.setString(2, utilisateur.getPrenom());
            ps.setString(3, utilisateur.getMail());
            ps.setString(4, utilisateur.getIdentifiant());
            ps.setString(5, utilisateur.getPassword());
            ps.execute();
            return utilisateur;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "DB : Erreur lors de la création de l'utilisateur");
            return utilisateur;
        }
    }

    public User update(User utilisateur) { // Modification de l'utilisateur
        try {
            String sql = "UPDATE utilisateur SET NOM = ?, PRENOM = ?, ADRESSE_MAIL = ?, IDENTIFIANT = ?, MOT_DE_PASSE = ? WHERE ID = ?"; // Requête
            PreparedStatement ps = this.connexion.prepareStatement(sql);
            ps.setString(1, utilisateur.getNom());              // Récupération des valeurs pour la requète
            ps.setString(2, utilisateur.getPrenom());           
            ps.setString(3, utilisateur.getMail());             
            ps.setString(4, utilisateur.getIdentifiant());      
            ps.setString(5, utilisateur.getPassword());         
            ps.setInt(6, utilisateur.getId());                  
            ps.executeUpdate();     // Exécution de la requête
            return utilisateur;     // Retour de l'objet utilisateur donnée en paramètre
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "DB : Erreur lors de la modification de l'utilisateur");
            return utilisateur;
        }
    }

    public void delete(int idUser) { // Suppression de l'utilisateur
        try {
            Connection connection = this.connexion;
            String sql = "DELETE FROM utilisateur WHERE ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idUser);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "DB : Erreur lors de la suppression de l'utilisateur");
        }
    }
}
