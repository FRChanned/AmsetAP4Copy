/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
                this.ajoutDialog.setVisible(true);
                break;
            case "deleteUser":
                // Retourne l'option choisie par l'utilisateur en int.
                int confirm = JOptionPane.showConfirmDialog(
                        this.view,
                        this.view.message("Voulez-vous supprimer cet utilisateur ?"),
                        "Confirmation de suppression",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );

                // Si l'utilisateur à cliqué sur "YES" alors le delete s'effectue.
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        int selectedUserId = this.view.getSelectedId(); // Retourne l'utilisateur sélectionné dans la table.
                        this.userListModel.delete(selectedUserId); // Supprime l'utilisateur selon l'id de l'utilisateur sélectionné.

                        JOptionPane.showMessageDialog(this.view, "Utilisateur supprimé avec succès.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        JOptionPane.showMessageDialog(this.view, "Aucun utilisateur sélectionné.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this.view, "Aucun utilisateur sélectionné");
                    }
                }
                // Sinon la suppression est annulée.
                else {
                    JOptionPane.showMessageDialog(this.view, "Suppression annulée.");
                }
                break;
            case "openModifDialog": // Affiche le dialog avec toute les données de l'utilisateur dedans.
                modifDialog.setId(this.view.getSelectedId());
                modifDialog.setNom(this.view.getSelectedNom());
                modifDialog.setPrenom(this.view.getSelectedPrenom());
                modifDialog.setEmail(this.view.getSelectedEmail());
                modifDialog.setIdentifiant(this.view.getSelectedIdentifiant());
                modifDialog.setPassword(this.view.getSelectedPassword());

                modifDialog.setVisible(true);
                break;
            case "updateUser": // Effectue la méthode update dans le model userList qui va exécuter la méthode delete de UtilisateurDAO avec les données fournies.
                userListModel.update(
                        modifDialog.getId(),
                        modifDialog.getNom(),
                        modifDialog.getPrenom(),
                        modifDialog.getEmail(),
                        modifDialog.getIdentifiant(),
                        modifDialog.getPassword());

                modifDialog.setVisible(false);
                break;

            case "validNewUser":
                userListModel.create(
                        ajoutDialog.getNom(),
                        ajoutDialog.getPrenom(),
                        ajoutDialog.getEmail(),
                        ajoutDialog.getIdentifiant(),
                        ajoutDialog.getPassword()
                );

                ajoutDialog.setVisible(false);

                break;
        }
    }
}
