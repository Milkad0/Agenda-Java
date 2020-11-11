/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Controller.Controleur.AjouterDate;
import static Controller.Controleur.AjouterHoraire_debut;
import static Controller.Controleur.AjouterHoraire_fin;
import static Controller.Controleur.AjouterLibelle;
import static Controller.Controleur.AjouterRappel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author vincent
 */
public class Agenda implements Serializable {

    ArrayList<Model.RDV> agenda = new ArrayList();
    String nom;

    public void ajouterRDV(Model.RDV RDV) {

        agenda.add(RDV);
    }

    public void ajouterNom(String nom) {

        this.nom = nom;
    }
//Supprime le RDV désigné

    public void supprimerRDV(Model.RDV RDV) {
        int position_rdv = PositionRDV(RDV);
        if (position_rdv >= 0) {
            agenda.remove(position_rdv);
            System.out.println("Le RDV a été supprimé.");

        } else {
            System.out.println("RDV non présent dans l'agenda.");
        }
    }

    public void modifierRDV(int jour, int mois, int annee, int heure_debut, int minute_debut, int heure_fin, int miunte_fin, String libelle, boolean rap, int index) {

        Model.RDV RDV_modif;
        RDV_modif = new Model.RDV();
        RDV_modif = this.agenda.get(index);
        Model.RDV RDV_test;
        RDV_test = new Model.RDV();

        boolean correct_date, correct_heure_d, correct_heure_f;

        correct_date = AjouterDate(RDV_test, jour, mois, annee);
        correct_heure_d = AjouterHoraire_debut(RDV_test, heure_debut, minute_debut);
        correct_heure_f = AjouterHoraire_fin(RDV_test, heure_fin, miunte_fin, heure_debut, minute_debut);
        AjouterLibelle(RDV_test, libelle);
        AjouterRappel(RDV_test, rap);

        if (correct_date == true && correct_heure_d == true && correct_heure_f == true) {
            int rep = PositionRDV(RDV_test);
            if (rep == -2) {
                AjouterDate(RDV_modif, jour, mois, annee);
                AjouterHoraire_debut(RDV_modif, heure_debut, minute_debut);
                AjouterHoraire_fin(RDV_modif, heure_fin, miunte_fin, heure_debut, minute_debut);
                AjouterLibelle(RDV_modif, libelle);
                AjouterRappel(RDV_modif, rap);
                JOptionPane.showMessageDialog(null, "Le RDV a été modifié.", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
            } else if (rep == -1) {
                JOptionPane.showMessageDialog(null, "Le RDV modifié dépasse sur un autre RDV.", "ERROR", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Le RDV modifié existe déja.", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            System.out.println("information incorrect");

        }
    }
//Supprime tous les RDV de l'agenda.

    public void viderAgenda() {

        agenda.clear();

    }

    public void lireNomFichierAgenda() {

    }

    //Retourne -2 si le RDV n'est pas dans l'agenda, -1 si le RDV est superposé à un autre RDV, sinon retourne la position du RDV dans l'agenda.
    public int PositionRDV(Model.RDV RDV) {
        int x = -2;
        for (int i = 0; i < agenda.size(); i++) {
            if (((RDV.getDateFormated()).equals((agenda.get(i).getDateFormated())) == true) && ((RDV.getTime_d()).equals((agenda.get(i).getTime_d())) == true) && ((RDV.getTime_f()).equals((agenda.get(i).getTime_f())) == true)) { //RDV.getLibelle().equals(libelleRDV(i))
                x = i;
            } else if (((RDV.getDateFormated()).equals((agenda.get(i).getDateFormated())) == true) && (((((RDV.getTime_d()).compareTo(agenda.get(i).getTime_d())) > 0) && (((RDV.getTime_d()).compareTo(agenda.get(i).getTime_f())) < 0)) || ((((RDV.getTime_f()).compareTo(agenda.get(i).getTime_f())) < 0) && (((RDV.getTime_f()).compareTo(agenda.get(i).getTime_d())) > 0))) || ((((RDV.getTime_d()).compareTo(agenda.get(i).getTime_d())) < 0) && (((RDV.getTime_f()).compareTo(agenda.get(i).getTime_f())) > 0))) {
                x = -1;
            }
        }
        return x;
    }

    public int GetTaille() {

        return this.agenda.size();
    }

    public String GetNomAgenda() {

        return this.nom;
    }

    public String libelleRDV(int i) {
        String libelle = (agenda.get(i).getLibelle());
        return libelle;
    }

    public String DateRDV(int i) {
        String DateRdv = (agenda.get(i).getDateFormated());
        return DateRdv;
    }

    public LocalTime Horaire_debut_RDV(int i) {
        LocalTime Horaire_d = (agenda.get(i).getTime_d());
        return Horaire_d;
    }

    public String String_Horaire_debut_RDV(int i) {
        String Horaire_d = (agenda.get(i).getTime_d_Formated());
        return Horaire_d;
    }

    public LocalTime Horaire_fin_RDV(int i) {
        LocalTime Horaire_f = (agenda.get(i).getTime_f());
        return Horaire_f;
    }

    public String String_Horaire_fin_RDV(int i) {
        String Horaire_f = (agenda.get(i).getTime_f_Formated());
        return Horaire_f;
    }

    public Boolean Rapelle_RDV(int i) {
        Boolean Rapp = (agenda.get(i).getRapelle());
        return Rapp;
    }

    public String String_Rapelle_RDV(int i) {
        String rapelle;

        if (Rapelle_RDV(i) == true) {

            rapelle = "yes";
        } else {

            rapelle = "no";
        }

        return rapelle;
    }

    public Model.RDV Get_RDV(int i) {
        Model.RDV RDV_1;
        RDV_1 = agenda.get(i);

        return RDV_1;
    }

    public ArrayList<Model.RDV> AfficherRDVEntreDeuxDates(LocalDate Date1, LocalDate Date2) {
        ArrayList<Model.RDV> tab = new ArrayList();

        for (int i = 0; i < agenda.size(); i++) {

            if (((agenda.get(i).getDate().compareTo(Date1)) > 0) && ((agenda.get(i).getDate().compareTo(Date2)) < 0)) {

                tab.add(agenda.get(i));
            }

        }
        Collections.sort(tab);

        return tab;

    }

    public void AfficherRDV_Aujourdhui() {
        int compteur = 0;
        for (int i = 0; i < agenda.size(); i++) {

            if (((agenda.get(i).getDate().compareTo(LocalDate.now())) == 0)) {
                agenda.get(i).afficherRDV();
            } else {

                compteur++;
            }

            if (compteur == agenda.size()) {

                System.out.println("Aucun RDV trouvé pour aujourd'hui");
            }
        }

    }

    public ArrayList<Model.RDV> AfficherRDV_jour(LocalDate Date1) {
        ArrayList<Model.RDV> tab = new ArrayList();
        int compteur = 0;
        System.out.println(Date1);
        for (int i = 0; i < agenda.size(); i++) {
            System.out.println(agenda.get(i).afficherRDV());
            if (agenda.get(i).getDate().equals(Date1)) {
                tab.add(agenda.get(i));
            } else {

                compteur++;
            }

            if (compteur == agenda.size()) {

            }
        }
        return tab;
    }

    public ArrayList<Model.RDV> AfficherRDV_Mois(int month, int year) {
        int compteur = 0;
        ArrayList<Model.RDV> tab = new ArrayList();

        for (int i = 0; i < agenda.size(); i++) {

            if (((agenda.get(i).getDate().getMonthValue() == (month)) && (agenda.get(i).getDate().getYear() == (year)))) {
                tab.add(agenda.get(i));
            } else {

                compteur++;
            }

            if (compteur == agenda.size()) {
                JOptionPane.showMessageDialog(null, "Aucun RDV trouvé pour ce mois", "ERROR", JOptionPane.WARNING_MESSAGE);

            }
        }
        return tab;
    }

    public ArrayList<Model.RDV> AfficherRDV_Annee(int year) {
        ArrayList<Model.RDV> tab = new ArrayList();
        int compteur = 0;
        for (int i = 0; i < agenda.size(); i++) {

            if (((agenda.get(i).getDate().getYear() == (year)))) {
                tab.add(agenda.get(i));
            } else {

                compteur++;
            }

            if (compteur == agenda.size()) {
                JOptionPane.showMessageDialog(null, "Aucun RDV trouvé pour cette année", "ERROR", JOptionPane.WARNING_MESSAGE);

            }
        }
        return tab;
    }

    public ArrayList<Model.RDV> Return_Liste_RDV() {

        return this.agenda;
    }

}
