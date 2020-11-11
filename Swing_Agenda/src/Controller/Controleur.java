package Controller;


import View.GestionnaireIHM;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vincent
 */
public class Controleur extends Thread {


  public static void main(final String argv[]) {
      
 View();
     
  }
  
  public void Afficher_rapelle(){
   
     JOptionPane.showConfirmDialog (null,"This is the message","truc",JOptionPane.OK_CANCEL_OPTION);
    
}
    public static void CreerAgenda(ArrayList ListeAgenda,String Nom_agenda) {
        Model.Agenda Agenda;
        Agenda = new Model.Agenda();
        

       
        if(ComparerNom(ListeAgenda, Nom_agenda) == true){
            JOptionPane.showMessageDialog (null,"L'agenda "+Nom_agenda+" existe deja.","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
        }else{
             Agenda.ajouterNom(Nom_agenda);
        ListeAgenda.add(Agenda);
        JOptionPane.showMessageDialog (null,"L'agenda " + Nom_agenda + " a bien été enregistré.","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
        
            
        }
    }

    public static void CreerRDV(Model.Agenda agenda, int jour, int mois, int annee,int heure_debut, int minute_debut, int heure_fin,int miunte_fin,String libelle,boolean rapelle ) {
        Model.RDV RDV;
        RDV = new Model.RDV();
        boolean correct_date, correct_heure_d, correct_heure_f;
        
       correct_date = AjouterDate(RDV,jour,mois,annee);
        correct_heure_d = AjouterHoraire_debut(RDV,heure_debut,minute_debut);
        correct_heure_f = AjouterHoraire_fin(RDV,heure_fin,miunte_fin,heure_debut,minute_debut);
        AjouterLibelle(RDV,libelle);
        AjouterRappel(RDV,rapelle);

        if(correct_date==true&&correct_heure_d==true&&correct_heure_f==true){
        int rep = agenda.PositionRDV(RDV);
        if (rep == -2) {
            agenda.ajouterRDV(RDV);
              JOptionPane.showMessageDialog (null,"Votre RDV a bien été pris en compte","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
        } else if (rep == -1) {
            JOptionPane.showMessageDialog (null,"Le RDV créé dépasse sur un autre RDV.","ERROR",JOptionPane.WARNING_MESSAGE);
        } else {
             JOptionPane.showMessageDialog (null,"Le RDV créé existe déjà.","ERROR",JOptionPane.WARNING_MESSAGE);
        }
        }else{
             System.out.println("information incorrect");
            
        }

    }
    


    public static boolean AjouterDate(Model.RDV RDV,int jour,int mois, int annee) {

 
        boolean bissextile, correct = false;

        
   

// Déterminer si la date est correcte
            if (jour >= 1 && jour <= 31 && mois >= 1 && mois <= 12) {
                correct = true;
            } else if ((mois == 2) && (jour >= 29)) {
                bissextile = (annee % 400 == 0)
                        || ((annee % 100 != 0) && (annee % 4 == 0));
                correct = (jour == 29) && bissextile;
            } else if ((mois <= 7) && (jour == 31)) {
                correct = (mois % 2 == 1);
            } else if ((mois >= 8) && (jour == 31)) {
                correct = (mois % 2 == 0);
            }
// Si date incorrecte, la mettre à zéro
            if (jour == 0 || mois == 0 || annee == 0) {
                correct = false;
            }
            System.out.println(correct);
     if(correct==true){
       RDV.ajouterDate(jour, mois, annee);
       System.out.println("Votre date a bien été prise en compte");
     }
     return correct;

    }


    public static boolean AjouterHoraire_debut(Model.RDV RDV,int heure_d,int minute_d) {

        
boolean correct = true;
        

          

        if (heure_d < 0 || minute_d < 0 || heure_d >= 24 || minute_d >= 60) {
            correct = false;
        }else{
        RDV.ajouterHoraire_debut(heure_d, minute_d);
        System.out.println("Votre horaire de dbbut a bien été prise en compte");
        }
return correct;
    }

    public static boolean AjouterHoraire_fin(Model.RDV RDV,int heure_f,int minute_f,int heure_d,int minute_d) {
boolean correct = true;
    
     //  LocalTime Time_d = LocalTime.of(heure_d,minute_d);
     System.out.println(heure_d);
     System.out.println(heure_f);
        
         if(heure_f<heure_d){
            JOptionPane.showMessageDialog (null,"Heure de fin anterieur a heure de debut.","ERROR",JOptionPane.WARNING_MESSAGE);
            correct = false;
         }    

        if(heure_f < 0 || minute_f < 0 || heure_f >= 24 || minute_f >= 60){
            
            JOptionPane.showMessageDialog (null,"Heure de fin incorrect","ERROR",JOptionPane.WARNING_MESSAGE);
             correct = false;
       
        }else if(correct==true){
            RDV.ajouterHoraire_fin(heure_f, minute_f);
          
        
        }

        

        return correct;
    }

    public static void AjouterLibelle(Model.RDV RDV, String commentaire) {

    

        RDV.ajouterLibelle(commentaire);
    }

    public static void AjouterRappel(Model.RDV RDV,Boolean rapelle) {

        RDV.ajouterRappel(rapelle);
    }

    public static void SupprimerRDV(Model.Agenda agenda, int index) {

        Model.RDV RDV_a_supprimer;
        RDV_a_supprimer = new Model.RDV();

        RDV_a_supprimer = agenda.Get_RDV(index);

        int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez-vous vraiment supprimer le RDV ?","Warning",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
if(dialogResult == JOptionPane.YES_OPTION){
  agenda.supprimerRDV(RDV_a_supprimer);
  JOptionPane.showMessageDialog (null,"Le RDV a été supprimé.","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
}else{
    JOptionPane.showMessageDialog (null,"Le RDV n'a pas été supprimé.","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
}
       

    }

    public static void ModifierRDV(Model.Agenda agenda,int jour, int mois, int annee,int heure_debut, int minute_debut, int heure_fin,int miunte_fin,String libelle,boolean rap,int index) {
        String reponse;

        Model.RDV RDV_a_modifier;
        RDV_a_modifier = new Model.RDV();
        
        int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez-vous vraiment modifier le RDV ?","Warning",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
if(dialogResult == JOptionPane.YES_OPTION){
  agenda.modifierRDV(jour,  mois,  annee, heure_debut,  minute_debut,  heure_fin, miunte_fin,libelle,rap,index);

}else{
    JOptionPane.showMessageDialog (null,"Le RDV n'a pas été modifié.","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
}



    }

    public static void ViderAgenda(Model.Agenda agenda) {

        
                int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez-vous vraiment vider l'agenda ?","Warning",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
if(dialogResult == JOptionPane.YES_OPTION){
  agenda.viderAgenda();
  JOptionPane.showMessageDialog (null,"L'agenda a été vidé.","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
}else{
    JOptionPane.showMessageDialog (null,"L'agenda n'a pas été vidé.","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
}
        

    }

    public static ArrayList <Model.RDV> AfficherRDVentreDeuxDates(Model.Agenda Agenda,int jour1,int mois1,int annee1,int jour2,int mois2,int annee2) {
        boolean bissextile;
        ArrayList <Model.RDV> tab = new  ArrayList();
        boolean correct1 = true;
        boolean correct2 = true;
        LocalDate Date1 = LocalDate.of(1001, 10, 10);
        LocalDate Date2 = LocalDate.of(1001, 10, 10);
 System.out.println("1");

// Déterminer si la date est correcte
         
                    if (jour1 >= 1 && jour1 <= 31 && mois1 >= 1 && mois1 <= 12) {
                    correct1 = true;
                } else if ((mois1 == 2) && (jour1 >= 29)) {
                    bissextile = (annee1 % 400 == 0)
                            || ((annee1 % 100 != 0) && (annee1 % 4 == 0));
                    correct1 = (jour1 == 29) && bissextile;
                } else if ((mois1 <= 7) && (jour1 == 31)) {
                    correct1 = (mois1 % 2 == 1);
                } else if ((mois1 >= 8) && (jour1 == 31)) {
                    correct1 = (mois1 % 2 == 0);
                }
// Si date incorrecte, la mettre à zéro
                if (jour1== 0 || mois1 == 0 || annee1 == 0) {
                    correct1 = false;
                }
                   System.out.println("2"); 
                if (jour2 >= 1 && jour2 <= 31 && mois2 >= 1 && mois2 <= 12) {
                   correct2 = true;
                } else if ((mois2 == 2) && (jour2 >= 29)) {
                    bissextile = (annee2 % 400 == 0)
                            || ((annee2 % 100 != 0) && (annee2 % 4 == 0));
                    correct2 = (jour2 == 29) && bissextile;
                } else if ((mois2 <= 7) && (jour2 == 31)) {
                    correct2 = (mois2 % 2 == 1);
                } else if ((mois2 >= 8) && (jour2 == 31)) {
                    correct2 = (mois2 % 2 == 0);
                }
// Si date incorrecte, la mettre à zéro
                if (jour2 == 0 || mois2 == 0 || annee2 == 0) {
                    correct2 = false;
                }
         


           
                if(correct1==false){
                    
                    JOptionPane.showMessageDialog (null,"Date 1 incorrect","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
                }else{
                
            
                Date1 = LocalDate.of(annee1, mois1, jour1);
                JOptionPane.showMessageDialog (null,"Votre date de début a bien été prise en compte","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
               
            } 
                 System.out.println("3");
                 if(correct2==false){
                    
                    JOptionPane.showMessageDialog (null,"Date 2 incorrect","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
                } else {
                Date2 = LocalDate.of(annee2, mois2, jour2);
                JOptionPane.showMessageDialog (null,"Votre date de fin a bien été prise en compte","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
            }
                
                
        

        if(correct2==false||correct1==false){
            
        }else{
         tab = Agenda.AfficherRDVEntreDeuxDates(Date1, Date2);
        return tab;
        }
        return tab;
    }

   

    

    

    public static boolean ComparerNom(ArrayList<Model.Agenda> ListeAgenda, String Nom) {
        Boolean Present = false;

        for (int i = 0; i < ListeAgenda.size(); i++) {
            if ((Nom.equals(ListeAgenda.get(i).GetNomAgenda())) == true) {
                Present = true;
            }

        }

        return Present;
    }
    public static ArrayList <Model.RDV> Afficher_RDV_critere (Model.Agenda Agenda,int jour,int mois,int annee) {
        ArrayList <Model.RDV> tab = new  ArrayList();
        
        if((jour!=-1)&&(mois!=-1)&&(annee!=-1)){
           LocalDate Date = LocalDate.of(annee, mois, jour);
           tab =  Agenda.AfficherRDV_jour(Date);
        }else if((mois!=-1)&&(annee!=-1)){
            tab = Agenda.AfficherRDV_Mois(mois, annee);
        }else if((annee!=-1)){
              tab = Agenda.AfficherRDV_Annee(jour);
        }else{
            
            
        }
        return tab;
        
        
    }
    
    
    
    public static void View (){

       GestionnaireIHM IHM;
        IHM = new GestionnaireIHM();
        IHM.charger_agenda(Charger_agenda());
        IHM.IHM1();
        
        


    }

           
 public static void Sauvegarder(Model.Agenda agenda) throws Exception {
   String  monFichier = "Agenda\\" + agenda.GetNomAgenda() + ".dat";
FileOutputStream fos;
ObjectOutputStream oos;
fos = new FileOutputStream(monFichier);
oos = new ObjectOutputStream(fos);
oos.writeObject(agenda);
oos.flush();
oos.close();

       /* int taille_agenda = agenda.GetNomAgenda().length();
        int nombre_caract_recup = 4;
        String Txt = agenda.GetNomAgenda().substring(taille_agenda - nombre_caract_recup, taille_agenda);
        String monFichier;
        int i;

        if ((Txt.equals(".txt")) == false) {

            monFichier = "C:\\Users\\Vincent\\Documents\\NetBeansProjects\\Agenda\\Agenda\\" + agenda.GetNomAgenda() + ".txt";
            

        } else {

            monFichier = "C:\\Users\\Vincent\\Documents\\NetBeansProjects\\Agenda\\Agenda\\" + agenda.GetNomAgenda();
        }
        //System.out.println(monFichier);

        try {

// Créer un objet java.io.FileWriter avec comme argument le nom du
//fichier dans lequel enregsitrer
            FileWriter lu = new FileWriter(monFichier);
            BufferedWriter out = new BufferedWriter(lu);
// Mettre le flux en tampon (en cache)
            for (i = 0; i < agenda.GetTaille(); i++) {
                String Texte_fichier = agenda.libelleRDV(i) + ";" + agenda.DateRDV(i) + ";" + agenda.String_Horaire_debut_RDV(i) + ";" + agenda.String_Horaire_fin_RDV(i) + ";" + agenda.String_Rapelle_RDV(i) + "\r\n";
                out.write(Texte_fichier);
                //out.newLine();
            }
//Balancer dans le flux le contenu de la zone de texte
            out.close(); // Fermer le flux (c'est
//toujours mieux de le fermer explicitement)
        } catch (IOException er) {;
        }*/

    }

    public static ArrayList<Model.Agenda> Charger_agenda() {
        ArrayList<Model.Agenda> All_agenda = new ArrayList();
        String Chemin_fichier = "Agenda";

        try {

            final Collection<File> all = new ArrayList<File>();
            TrouverFichierTexte(new File(Chemin_fichier), all, ".dat");

            for (File file : all) {
                
                Model.Agenda agenda;
                agenda = new Model.Agenda();
                String NomFichier = file.getName();
                //String Nom_fichier_sans_txt[] = NomFichier.split(".");
                agenda.ajouterNom(NomFichier);

                String monFichier = Chemin_fichier + "\\" + NomFichier;
                System.out.println(monFichier);
                
                FileInputStream fis;
                ObjectInputStream ois;
                fis = new FileInputStream(monFichier);
                System.out.println("1"+fis);
                ois = new ObjectInputStream(fis);
                System.out.println("2"+ois);
                Model.Agenda obj = (Model.Agenda) ois.readObject();
                System.out.println("3"+obj);
                ois.close();
      
                All_agenda.add(obj);
         
            }
            
               /* InputStream flux = new FileInputStream(monFichier);
                InputStreamReader lecture = new InputStreamReader(flux);
                BufferedReader buff = new BufferedReader(lecture);

                String ligne;

                while ((ligne = buff.readLine()) != null) {
                    Model.RDV RDV;
                    RDV = new Model.RDV();

                    String str = ligne;
                    String tab[] = str.split(";");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

                    RDV.ajouterLibelle(tab[0]);
                    RDV.ajouterHoraire_date_Direct(LocalDate.parse(tab[1], formatter));
                    RDV.ajouterHoraire_debut_Direct(LocalTime.parse(tab[2]));
                    RDV.ajouterHoraire_fin_Direct(LocalTime.parse(tab[3]));
                    RDV.Ajouter_Rapelle_Direct(tab[4]);

                    agenda.ajouterRDV(RDV);

                }
                All_agenda.add(agenda);
                buff.close();
            }*/
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return All_agenda;
    }

    private static void TrouverFichierTexte(File file, Collection<File> all, final String extension) {
        //Liste des fichiers correspondant a l'extension souhaitee
        final File[] children = file.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return f.getName().endsWith(extension);
            }
        }
        );
        if (children != null) {
            //Pour chaque fichier recupere, on appelle a nouveau la methode
            for (File child : children) {
                all.add(child);
                TrouverFichierTexte(child, all, extension);
            }
        }
    }

}

   


 
    




