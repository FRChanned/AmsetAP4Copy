/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.User;
import model.UserListModel;
import view.MainView;
import view.ModifDialog;
import view.AjoutDialog;
import view.DeleteDialog;
import view.AjoutDialog;

/**
 *
 * @author m.perot
 */
public class MainControl implements PropertyChangeListener {

    private MainView view;
    private ModifDialog modifDialog;
    private UserListModel userListModel;
    private AjoutDialog ajoutDialog;
//    private UtilisateurDAO 

    public MainControl(MainView v) {
        this.view = v;
        this.view.addPropertyChangeListener(this);
        this.userListModel = new UserListModel();
        this.view.setTableModel(userListModel);

        this.ajoutDialog = new AjoutDialog(this.view, true);
        this.ajoutDialog.addPropertyChangeListener(this);

        this.modifDialog = new ModifDialog(this.view, true);
        this.modifDialog.addPropertyChangeListener(this);

    }

    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "ajoutUser":
                this.ajoutDialog.setDefaultTextfieldData();
                this.ajoutDialog.setVisible(true);
                break;
            case "deleteUser":
                Integer selectedUserId = this.view.getSelectedId(); // Retourne l'id de l'utilsiateur sélectionné

                if (selectedUserId == null) {
                    try {
                        throw new Exception("Aucun utilisateur sélectionné");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        JOptionPane.showMessageDialog(this.view, "Aucun utilisateur sélectionné.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this.view, "Aucun utilisateur sélectionné");
                    }
                    break;
                } 
                // Retourne l'option choisie par l'utilisateur en int.
                int confirm = JOptionPane.showConfirmDialog(
                        this.view,
                        this.view.message("Voulez-vous supprimer cet utilisateur ?"),
                        "Confirmation de suppression",
                        JOptionPane.YES_NO_OPTION
                );

                // Si l'utilisateur à cliqué sur "YES" alors le delete s'effectue.
                if (confirm == JOptionPane.YES_OPTION) {
                    this.userListModel.delete(selectedUserId); // Supprime l'utilisateur selon l'id de l'utilisateur sélectionné.
                    JOptionPane.showMessageDialog(this.view, "Utilisateur supprimé avec succès.");
                } // Sinon la suppression est annulée.
                else {
                    JOptionPane.showMessageDialog(this.view, "Suppression annulée.");
                }
                break;

            case "openModifDialog": // Affiche le dialog avec toute les données de l'utilisateur dedans.
                try {
                    modifDialog.setId(this.view.getSelectedId());
                    modifDialog.setNom(this.view.getSelectedNom());
                    modifDialog.setPrenom(this.view.getSelectedPrenom());
                    modifDialog.setEmail(this.view.getSelectedEmail());
                    modifDialog.setIdentifiant(this.view.getSelectedIdentifiant());
                    modifDialog.setPassword(this.view.getSelectedPassword());
                    modifDialog.setVisible(true);
                } catch (ArrayIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(this.view, "Aucun utilisateur sélectionné.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this.view, "Aucun utilisateur sélectionné");
                }
                break;

            case "updateUser": // Effectue la méthode update dans le model userList qui va exécuter la méthode delete de UtilisateurDAO avec les données fournies.
                if (Arrays.equals(modifDialog.getPassword(), modifDialog.getVerifPassword())) {
                    userListModel.update(
                            modifDialog.getId(),
                            modifDialog.getNom(),
                            modifDialog.getPrenom(),
                            modifDialog.getEmail(),
                            modifDialog.getIdentifiant(),
                            String.valueOf(modifDialog.getPassword()));

                    modifDialog.setVisible(false);
                } else {
                    try {
                        throw new Exception("Mots de passes non identiques");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this.view, "Mots de passes non identiques");
                    }
                }
                break;

            case "validNewUser":
                if (Arrays.equals(ajoutDialog.getPassword(), ajoutDialog.getPasswordVerif())) {
                    userListModel.create(
                            ajoutDialog.getNom(),
                            ajoutDialog.getPrenom(),
                            ajoutDialog.getEmail(),
                            ajoutDialog.getIdentifiant(),
                            String.valueOf(ajoutDialog.getPassword())
                    );
                    ajoutDialog.setVisible(false);
                } else {
                    try {
                        throw new Exception("Mots de passes non identiques");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this.view, "Mots de passes non identiques");
                    }
                }

                break;
        }
    }
}
