/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author vincent
 */
public class RDV implements Comparable<RDV>, Serializable {

    private LocalDate Date;
    private LocalTime Time_d, Time_f;
    private int day, month, year;
    private int heure_d, minute_d;
    private int heure_f, minute_f;
    private String libelle;
    private Boolean rappel;

    public void ajouterDate(int jour, int mois, int annee) {
        // Lire la date au format jour/mois/année

        this.day = jour;
        this.month = mois;
        this.year = annee;
        this.Date = LocalDate.of(this.year, this.month, this.day);
    }

    public void ajouterHoraire_debut(int heure_d, int minute_d) {

        this.heure_d = heure_d;
        this.minute_d = minute_d;
        this.Time_d = LocalTime.of(this.heure_d, this.minute_d);

    }

    public void ajouterHoraire_fin(int heure_f, int minute_f) {

        this.heure_f = heure_f;
        this.minute_f = minute_f;
        this.Time_f = LocalTime.of(this.heure_f, this.minute_f);

    }

    public void ajouterHoraire_debut_Direct(LocalTime heure_d) {
        this.heure_d = heure_d.getHour();
        this.minute_d = heure_d.getMinute();
        this.Time_d = heure_d;

    }

    public void ajouterHoraire_fin_Direct(LocalTime heure_f) {

        this.Time_f = heure_f;

    }

    public void ajouterHoraire_date_Direct(LocalDate Date) {

        this.Date = Date;

    }

    public void ajouterLibelle(String libelle) {

        this.libelle = libelle;

    }

    public void ajouterRappel(Boolean rappel) {

        this.rappel = rappel;

    }

    //Retourne -1 si les RDV sont différents, 0 si leur horaire se chevauche et 1 si ils sont identiques
    public int comparerCréneauxRDVRDV(Model.RDV RDV, Model.RDV RDV1) {
        int x = -1;

        if (((RDV.getDateFormated()).equals((RDV1.getDateFormated())) == true) && ((RDV.getTime_d()).equals((RDV1.getTime_d())) == true) && ((RDV.getTime_f()).equals((RDV1.getTime_f())) == true)) { //RDV.getLibelle().equals(libelleRDV(i))
            x = 1;
        } else if (((RDV.getDateFormated()).equals((RDV1.getDateFormated())) == true) && (((((RDV.getTime_d()).compareTo((RDV1.getTime_d()))) > 0) && (((RDV.getTime_d()).compareTo((RDV1.getTime_d()))) < 0)) || ((((RDV.getTime_f()).compareTo((RDV1.getTime_f()))) < 0) && (((RDV.getTime_f()).compareTo((RDV1.getTime_f()))) > 0)))) {
            x = 0;
        }

        return x;
    }

    public String getDateFormated() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = this.Date.format(formatter);
        return formattedString;
    }

    public LocalDate getDate() {

        return this.Date;
    }

    public String getLibelle() {

        return this.libelle;
    }

    public LocalTime getTime_d() {

        return this.Time_d;
    }

    public String getTime_d_Formated() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedString = this.Time_d.format(formatter);

        return formattedString;
    }

    public String getTime_f_Formated() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedString = this.Time_f.format(formatter);

        return formattedString;
    }

    public LocalTime getTime_f() {

        return this.Time_f;
    }

    public Boolean getRapelle() {

        return this.rappel;
    }

    public void Ajouter_Rapelle_Direct(String rapel) {
        Boolean rapelle;
        if (rapel.equals("yes")) {
            rapelle = true;
        } else {

            rapelle = false;
        }

        this.rappel = rapelle;
    }

    //Affiche toutes les RDV désignés
    public String afficherRDV() {

        String libelle_RDV = getLibelle();
        String Date_RDV = getDateFormated();
        LocalTime Temps_debut = getTime_d();
        int Heure_debut = Temps_debut.getHour();
        int Minute_debut = Temps_debut.getMinute();
        LocalTime Temps_fin = getTime_f();
        int Heure_fin = Temps_fin.getHour();
        int Minute_fin = Temps_fin.getMinute();

        String monRDV = ("RDV" + "        " + Date_RDV + "        " + Heure_debut + "h" + Minute_debut + "-" + Heure_fin + "h" + Minute_fin + "\n" + "        " + libelle_RDV);
        return monRDV;

    }

    public int get_hour_d() {

        return this.heure_d;
    }

    public int get_minute_d() {

        return this.minute_d;
    }

    @Override
    public int compareTo(RDV RDV1) {
        int comparaison = this.Date.compareTo(RDV1.getDate());
        if (comparaison == 0) {

            comparaison = this.Time_d.compareTo(RDV1.Time_d);
        }
        return comparaison;
    }
}
