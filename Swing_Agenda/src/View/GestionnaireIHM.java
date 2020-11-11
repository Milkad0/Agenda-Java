package View;

import static Controller.Controleur.Afficher_RDV_critere;
import static Controller.Controleur.AfficherRDVentreDeuxDates;
import static Controller.Controleur.CreerAgenda;
import static Controller.Controleur.ModifierRDV;
import static Controller.Controleur.Sauvegarder;
import static Controller.Controleur.SupprimerRDV;
import static Controller.Controleur.ViderAgenda;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;

public class GestionnaireIHM extends JFrame implements ActionListener {

    Model.Agenda Agenda;
    ArrayList<Model.Agenda> ListeAgenda = new ArrayList();
    Timer t = new Timer();
    private JButton b1;
    private JButton b2;
    private ButtonGroup bg = new ButtonGroup();
    private JRadioButton jr1 = new JRadioButton("Radio 1");
    private JRadioButton jr2 = new JRadioButton("Radio 2");

    private JLabel label = new JLabel("Un JTextField");
    private JButton create = new JButton("Creer Agenda");
    private JButton open = new JButton("Ouvrir Agenda");
    private JButton add_rdv = new JButton("Ajouter RDV");
    private JButton modif_rdv = new JButton("Modifier RDV");
    private JButton supp_rdv = new JButton("Supprimer RDV");
    private JButton supp_all = new JButton("Supprimer tous les RDV");
    private JButton save = new JButton("Sauvegarder Agenda");
    private JButton filtre1 = new JButton("Filtrer entre 2 dates");
    private JButton filtre2 = new JButton("Filtrer sur criteres");
    private JButton Stop_Filtre = new JButton("Supprimer tous les filtres");
    private JButton filtre = new JButton("Filtres");
    private JTextArea texte = new JTextArea("");
    private JPanel Pop_up_accueil = new JPanel();
    private List textArea = new List();
    private List listagenda = new List();
    private JPanel container = new JPanel();
    private JFormattedTextField Entre_date = new JFormattedTextField();
    private JFormattedTextField Entre_h_d = new JFormattedTextField();
    private JFormattedTextField Entre_h_f = new JFormattedTextField();
    private JFormattedTextField Libelle = new JFormattedTextField();
    private JFormattedTextField Modif_date = new JFormattedTextField();
    private JFormattedTextField Modif_h_d = new JFormattedTextField();
    private JFormattedTextField Modif_h_f = new JFormattedTextField();
    private JFormattedTextField Modif_Libelle = new JFormattedTextField();
    private JFormattedTextField Create_Agenda = new JFormattedTextField();
    private JFormattedTextField Entre_date1 = new JFormattedTextField();
    private JFormattedTextField Entre_date2 = new JFormattedTextField();
    private JFormattedTextField Entre_jour = new JFormattedTextField();
    private JFormattedTextField Entre_mois = new JFormattedTextField();
    private JFormattedTextField Entre_anne = new JFormattedTextField();
    private JCheckBox rapelle = new JCheckBox();
    private JCheckBox Modif_rapelle = new JCheckBox();
    private JLabel date_rdv = new JLabel("Date :");
    private JLabel date_rdv1 = new JLabel("Date 1 :");
    private JLabel date_rdv2 = new JLabel("Date 2 :");
    private JLabel heure_d = new JLabel("Heure debut :");
    private JLabel heure_f = new JLabel("Heure fin :");
    private JLabel libelle = new JLabel("Libelle :");
    private JLabel Rapelle = new JLabel("Rapelle :");
    private JLabel date = new JLabel("Date");
    private JLabel agenda_label = new JLabel("Agenda");
    private JLabel time = new JLabel("time");
    private JLabel jour_libel = new JLabel("Jour :");
    private JLabel mois_libel = new JLabel("Mois :");
    private JLabel annee_libel = new JLabel("Annee :");
    private JLabel Create_agenda = new JLabel("Choisir un nom d'agenda non existant : ");
    private JButton valider_RDV = new JButton("Valider");
    private JButton valider_Modif_RDV = new JButton("Valider");
    private JButton valider_Agenda = new JButton("Valider");
    private JButton valider_filtre1 = new JButton("Valider");
    private JButton valider_filtre2 = new JButton("Valider");
    private JButton Annuler = new JButton("Annuler");

    private JFrame ModifRDV = new JFrame();

    public void IHM1() {
        //set Jframe principal

        bg.add(jr1);
        bg.add(jr2);
        this.setTitle("Ma première fenêtre Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1250, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));

        AllAgenda();
        //set POP_UP_ACUUEIL
        //set Jpannel
        JPanel top = new JPanel();

        top.setLayout(null);
        top.setBackground(Color.LIGHT_GRAY);
        top.setSize(1000, 800);
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
        String formattedString = localDate.format(formatter);
        Font font = new Font("Arial", Font.BOLD, 40);
        Font font2 = new Font("Arial", Font.BOLD, 20);
        date.setText(formattedString);
        date.setFont(font);
        date.setForeground(Color.white);
        agenda_label.setText("Agenda");
        agenda_label.setFont(font2);
        agenda_label.setBounds(50, 260, 300, 50);
        //set boutton
        create.setBounds(50, 150, 150, 50);
        open.setBounds(50, 200, 150, 50);
        add_rdv.setBounds(260, 50, 150, 50);
        modif_rdv.setBounds(410, 50, 150, 50);
        supp_rdv.setBounds(560, 50, 150, 50);
        supp_all.setBounds(710, 50, 170, 50);
        save.setBounds(880, 50, 170, 50);
        filtre.setBounds(1050, 50, 150, 50);
        date.setBounds(30, 50, 200, 50);
        textArea.setBounds(260, 150, 935, 600);
        listagenda.setBounds(50, 300, 150, 200);

        //ActionListener
        create.removeActionListener(this);
        open.removeActionListener(this);
        add_rdv.removeActionListener(this);
        modif_rdv.removeActionListener(this);
        supp_rdv.removeActionListener(this);
        supp_all.removeActionListener(this);
        save.removeActionListener(this);
        filtre.removeActionListener(this);

        create.addActionListener(this);
        open.addActionListener(this);
        add_rdv.addActionListener(this);
        modif_rdv.addActionListener(this);
        supp_rdv.addActionListener(this);
        supp_all.addActionListener(this);
        save.addActionListener(this);
        filtre.addActionListener(this);

        top.add(label);
        top.add(create);
        top.add(open);
        top.add(textArea);
        top.add(listagenda);
        top.add(add_rdv);
        top.add(modif_rdv);
        top.add(supp_rdv);
        top.add(supp_all);
        top.add(save);
        top.add(filtre);
        top.add(date);
        top.add(agenda_label);
        this.add(top);
        this.setVisible(true);

    }

    public void Creer_RDV() {
        JFrame creeRDV = new JFrame();

        creeRDV.setTitle("Creer RDV");
        creeRDV.setSize(1000, 100);
        creeRDV.setLocationRelativeTo(null);
        creeRDV.setResizable(false);

        JPanel top = new JPanel();
        try {
            MaskFormatter date = new MaskFormatter("##/##/####");
            MaskFormatter heure = new MaskFormatter("##:##");
            Entre_date = new JFormattedTextField(date);
            Entre_h_d = new JFormattedTextField(heure);
            Entre_h_f = new JFormattedTextField(heure);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Font police = new Font("Arial", Font.BOLD, 14);
        Entre_date.setFont(police);
        Entre_date.setPreferredSize(new Dimension(150, 30));

        Entre_h_d.setFont(police);
        Entre_h_d.setPreferredSize(new Dimension(150, 30));

        Entre_h_f.setFont(police);
        Entre_h_f.setPreferredSize(new Dimension(150, 30));

        Libelle.setFont(police);
        Libelle.setPreferredSize(new Dimension(150, 30));

        top.add(date_rdv);
        top.add(Entre_date);
        top.add(heure_d);
        top.add(Entre_h_d);
        top.add(heure_f);
        top.add(Entre_h_f);
        top.add(libelle);
        top.add(Libelle);
        top.add(Rapelle);

        top.add(rapelle);

        top.add(valider_RDV);

        valider_RDV.removeActionListener(this);
        valider_RDV.addActionListener(this);
        rapelle.addActionListener(this);
        creeRDV.add(top);
        creeRDV.setVisible(true);
    }

    public void Creer_Agenda() {
        JFrame creeRDV = new JFrame();

        creeRDV.setTitle("Agenda");
        creeRDV.setSize(500, 100);
        creeRDV.setLocationRelativeTo(null);
        creeRDV.setResizable(false);
        JPanel top = new JPanel();

        Font police = new Font("Arial", Font.BOLD, 14);
        Create_Agenda.setFont(police);
        Create_Agenda.setPreferredSize(new Dimension(150, 30));

        top.add(Create_agenda);
        top.add(Create_Agenda);

        top.add(valider_Agenda);

        valider_Agenda.removeActionListener(this);
        valider_Agenda.addActionListener(this);
        creeRDV.add(top);
        creeRDV.setVisible(true);

    }

    public void Modifier_RDV() {
        int index = textArea.getSelectedIndex();

        ModifRDV.getContentPane().removeAll();
        ModifRDV.repaint();
        ModifRDV.setTitle("Modifier RDV");
        ModifRDV.setSize(1000, 100);
        ModifRDV.setLocationRelativeTo(null);
        ModifRDV.setResizable(false);

        JPanel top = new JPanel();
        try {
            MaskFormatter date = new MaskFormatter("##/##/####");
            MaskFormatter heure = new MaskFormatter("##:##");
            Modif_date = new JFormattedTextField(date);
            Modif_h_d = new JFormattedTextField(heure);
            Modif_h_f = new JFormattedTextField(heure);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String Str_Libelle = this.Agenda.Get_RDV(index).getLibelle();
        String Str_Date = this.Agenda.Get_RDV(index).getDateFormated();
        String Str_Time_d = this.Agenda.Get_RDV(index).getTime_d_Formated();
        String Str_Time_f = this.Agenda.Get_RDV(index).getTime_f_Formated();
        Boolean boo_Rapelle = this.Agenda.Get_RDV(index).getRapelle();

        Font police = new Font("Arial", Font.BOLD, 14);
        Modif_date.setFont(police);
        Modif_date.setPreferredSize(new Dimension(150, 30));
        Modif_date.setText(Str_Date);

        Modif_h_d.setFont(police);
        Modif_h_d.setPreferredSize(new Dimension(150, 30));
        Modif_h_d.setText(Str_Time_d);

        Modif_h_f.setFont(police);
        Modif_h_f.setPreferredSize(new Dimension(150, 30));
        Modif_h_f.setText(Str_Time_f);

        Modif_Libelle.setFont(police);
        Modif_Libelle.setPreferredSize(new Dimension(150, 30));
        Modif_Libelle.setText(Str_Libelle);
        Modif_rapelle.setSelected(boo_Rapelle);

        top.add(date_rdv);
        top.add(Modif_date);
        top.add(heure_d);
        top.add(Modif_h_d);
        top.add(heure_f);
        top.add(Modif_h_f);
        top.add(libelle);
        top.add(Modif_Libelle);
        top.add(Rapelle);

        top.add(Modif_rapelle);

        top.add(valider_Modif_RDV);

        valider_Modif_RDV.removeActionListener(this);
        valider_Modif_RDV.addActionListener(this);
        rapelle.removeActionListener(this);
        rapelle.addActionListener(this);
        ModifRDV.add(top);
        ModifRDV.setVisible(true);

    }

    public void Choix_Filtre() {
        JFrame ChoixFiltre = new JFrame();

        ChoixFiltre.setTitle("Filtre");
        ChoixFiltre.setSize(500, 100);
        ChoixFiltre.setLocationRelativeTo(null);
        ChoixFiltre.setResizable(false);
        JPanel top = new JPanel();

        this.filtre1.setBounds(50, 150, 150, 50);
        this.filtre2.setBounds(50, 150, 150, 50);
        Stop_Filtre.setBounds(50, 150, 150, 50);

        top.add(filtre1);
        top.add(filtre2);
        top.add(Stop_Filtre);
        filtre1.removeActionListener(this);
        filtre1.addActionListener(this);
        filtre2.removeActionListener(this);
        filtre2.addActionListener(this);
        Stop_Filtre.removeActionListener(this);
        Stop_Filtre.addActionListener(this);

        ChoixFiltre.add(top);
        ChoixFiltre.setVisible(true);

    }

    public void Filtre2dates() {
        JFrame Filtre2 = new JFrame();

        Filtre2.setTitle("Filtre");
        Filtre2.setSize(1000, 100);
        Filtre2.setLocationRelativeTo(null);
        Filtre2.setResizable(false);
        JPanel top = new JPanel();
        try {
            MaskFormatter date = new MaskFormatter("##/##/####");
            Entre_date1 = new JFormattedTextField(date);
            Entre_date2 = new JFormattedTextField(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Font police = new Font("Arial", Font.BOLD, 14);
        Entre_date1.setFont(police);
        Entre_date1.setPreferredSize(new Dimension(150, 30));
        Entre_date2.setFont(police);
        Entre_date2.setPreferredSize(new Dimension(150, 30));

        top.add(date_rdv1);
        top.add(Entre_date1);
        top.add(date_rdv2);
        top.add(Entre_date2);

        top.add(valider_filtre1);

        valider_filtre1.removeActionListener(this);
        valider_filtre1.addActionListener(this);
        rapelle.addActionListener(this);
        Filtre2.add(top);
        Filtre2.setVisible(true);

    }

    public void FiltreCritere() {
        JFrame Filtre1 = new JFrame();

        Filtre1.setTitle("Creer RDV");
        Filtre1.setSize(1000, 100);
        Filtre1.setLocationRelativeTo(null);
        Filtre1.setResizable(false);
        JPanel top = new JPanel();

        try {
            MaskFormatter date = new MaskFormatter("####");
            MaskFormatter jour_mois = new MaskFormatter("##");
            Entre_anne = new JFormattedTextField(date);
            Entre_jour = new JFormattedTextField(jour_mois);
            Entre_mois = new JFormattedTextField(jour_mois);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Font police = new Font("Arial", Font.BOLD, 14);
        Entre_anne.setFont(police);
        Entre_anne.setPreferredSize(new Dimension(150, 30));

        Entre_jour.setFont(police);
        Entre_jour.setPreferredSize(new Dimension(150, 30));

        Entre_mois.setFont(police);
        Entre_mois.setPreferredSize(new Dimension(150, 30));

        top.add(jour_libel);
        top.add(Entre_jour);
        top.add(mois_libel);
        top.add(Entre_mois);
        top.add(annee_libel);
        top.add(Entre_anne);

        top.add(valider_filtre2);

        valider_filtre2.removeActionListener(this);
        valider_filtre2.addActionListener(this);
        Filtre1.add(top);
        Filtre1.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == this.create) {
            Creer_Agenda();

        } else if (source == this.valider_Agenda) {
            traiterChoixCreerAgenda();

        } else if (source == this.open) {
            if (this.ListeAgenda.size() == 0) {
                JOptionPane.showMessageDialog(null, "Aucun agenda n'est répertorié.", "Sauvegarde", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.Agenda = traiterChoixOuvrirAgenda();
                AllRDV(this.Agenda);
                check_rapelle();
            }
        } else if (source == this.add_rdv) {
            traiterChoixAjouterRDV(this.Agenda);

        } else if (source == this.modif_rdv) {
            Modifier_RDV();

        } else if (source == this.supp_rdv) {
            traiterChoixSupprimerRDV(this.Agenda);
        } else if (source == this.supp_all) {
            traiterChoixSupprimerTousRDV(this.Agenda);
        } else if (source == this.save) {
            traiterChoixSauvegarderEtFermer(this.Agenda);
            JOptionPane.showMessageDialog(null, "Agenda sauvegardé.", "Sauvegarde", JOptionPane.INFORMATION_MESSAGE);
        } else if (source == this.filtre1) {
            Filtre2dates();

        } else if (source == this.filtre2) {
            FiltreCritere();
        } else if (source == this.filtre) {

            Choix_Filtre();
        } else if (source == this.valider_RDV) {
            traiterChoixCreerRDV();
        } else if (source == this.valider_Modif_RDV) {
            traiterChoixModifierRDV();
            ModifRDV.dispose();
        } else if (source == this.valider_filtre1) {
            traiterChoixAfficherRDV_Entre2Dates(this.Agenda);
        } else if (source == this.Stop_Filtre) {
            AllRDV(this.Agenda);
        } else if (source == this.valider_filtre2) {
            traiterFiltrerCritere();
        }

    }
//Recupere tous les Agenda existant pous les afficher dans la list
    public void AllAgenda() {

        listagenda.removeAll();
        listagenda.setFont(new Font("Serif", Font.ITALIC, 16));
        String Txt = "q";
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < ListeAgenda.size(); i++) {

            int nombre_caract_recup = 4;

            int taille_agenda = ListeAgenda.get(i).GetNomAgenda().length();
            if (ListeAgenda.get(i).GetNomAgenda().length() < 4) {

            } else {
                Txt = ListeAgenda.get(i).GetNomAgenda().substring(taille_agenda - nombre_caract_recup, taille_agenda);
            }
            if ((Txt.equals(".dat")) == false) {

                listagenda.add(this.ListeAgenda.get(i).GetNomAgenda());

            } else {

                int taille_agenda2 = this.ListeAgenda.get(i).GetNomAgenda().length();
                listagenda.add(this.ListeAgenda.get(i).GetNomAgenda().substring(0, taille_agenda2 - 4));
            }

        }
    }
//Recupere tous les RDV existant pous les afficher dans la list
    public void AllRDV(Model.Agenda Agenda) {
        textArea.removeAll();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < Agenda.GetTaille(); i++) {

            textArea.add(Agenda.Get_RDV(i).afficherRDV());

        }
        if (Agenda.Return_Liste_RDV().isEmpty()) {
            String text = "Aucun RDV dans cet agenda.";
            textArea.add(text);
        }
    }

    public void traiterChoixCreerRDV() {

        System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
        String libel = Libelle.getText();
        String[] heure_dd = Entre_h_d.getText().split(":");
        String[] heure_ff = Entre_h_f.getText().split(":");
        int[] intArray_d = new int[heure_ff.length];
        int[] intArray_f = new int[heure_ff.length];

        for (int i = 0; i < 2; i++) {
            String numberAsString = heure_dd[i];
            String numberAsStringg = heure_ff[i];
            intArray_d[i] = Integer.parseInt(numberAsString);
            intArray_f[i] = Integer.parseInt(numberAsStringg);
            System.out.println(intArray_f[i]);
            System.out.println(intArray_d[i]);
        }

        String[] Date = Entre_date.getText().split("/");
        int[] intArray_date = new int[Date.length];
        for (int i = 0; i < 3; i++) {
            String numberAsString_date = Date[i];
            intArray_date[i] = Integer.parseInt(numberAsString_date);
        }
        Boolean rap = (rapelle.isSelected());
        Controller.Controleur.CreerRDV(this.Agenda, intArray_date[0], intArray_date[1], intArray_date[2], intArray_d[0], intArray_d[1], intArray_f[0], intArray_f[1], libel, rap);
        AllRDV(Agenda);

    }

    public void traiterChoixCreerAgenda() {
        String nom_agenda = Create_Agenda.getText();
        CreerAgenda(this.ListeAgenda, nom_agenda);
        AllAgenda();

    }

    public void charger_agenda(ArrayList<Model.Agenda> All_agenda) {

        this.ListeAgenda = All_agenda;
    }

    public Model.Agenda traiterChoixOuvrirAgenda() {

        int index = listagenda.getSelectedIndex();
        Model.Agenda agenda = ListeAgenda.get(index);
        return agenda;
    }

    public void traiterChoixAfficherRDV_Entre2Dates(Model.Agenda Agenda) {
        ArrayList<Model.RDV> tab = new ArrayList();
        String[] Date1 = Entre_date1.getText().split("/");
        String[] Date2 = Entre_date2.getText().split("/");
        int[] intArray_date1 = new int[Date1.length];
        int[] intArray_date2 = new int[Date2.length];
        for (int i = 0; i < 3; i++) {
            String numberAsString_date1 = Date1[i];
            String numberAsString_date2 = Date2[i];
            intArray_date1[i] = Integer.parseInt(numberAsString_date1);
            intArray_date2[i] = Integer.parseInt(numberAsString_date2);
        }

        tab = AfficherRDVentreDeuxDates(Agenda, intArray_date1[0], intArray_date1[1], intArray_date1[2], intArray_date2[0], intArray_date2[1], intArray_date2[2]);

        if (tab.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun RDV trouvé entre ces deux dates.", "Filtre", JOptionPane.INFORMATION_MESSAGE);
        } else {
            textArea.removeAll();
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < tab.size(); i++) {

                textArea.add(tab.get(i).afficherRDV());
            }
            JOptionPane.showMessageDialog(null, "Filtre appliqué.", "Filtre", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void traiterFiltrerCritere() {
        ArrayList<Model.RDV> tab = new ArrayList();
        String jour = Entre_jour.getText();
        String mois = Entre_mois.getText();
        String annee = Entre_anne.getText();

        int intjour;
        int intmois;
        int intannee;

        intjour = Integer.parseInt(jour);
        System.out.println(intjour);
        intmois = Integer.parseInt(mois);
        intannee = Integer.parseInt(annee);

        tab = Afficher_RDV_critere(Agenda, intjour, intmois, intannee);
        if (tab.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun RDV trouvé.", "Filtre", JOptionPane.INFORMATION_MESSAGE);
        } else {
            textArea.removeAll();
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < tab.size(); i++) {

                textArea.add(tab.get(i).afficherRDV());
            }
            JOptionPane.showMessageDialog(null, "Filtre appliqué.", "Filtre", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void traiterChoixAjouterRDV(Model.Agenda Agenda) {

        Creer_RDV();

    }

    public void traiterChoixModifierRDV() {
        int index = textArea.getSelectedIndex();
        System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
        String libel = Modif_Libelle.getText();
        String[] heure_dd = Modif_h_d.getText().split(":");
        String[] heure_ff = Modif_h_f.getText().split(":");
        int[] intArray_d = new int[heure_ff.length];
        int[] intArray_f = new int[heure_ff.length];

        for (int i = 0; i < 2; i++) {
            String numberAsString = heure_dd[i];
            String numberAsStringg = heure_ff[i];
            intArray_d[i] = Integer.parseInt(numberAsString);
            intArray_f[i] = Integer.parseInt(numberAsStringg);
        }

        String[] Date = Modif_date.getText().split("/");
        int[] intArray_date = new int[Date.length];
        for (int i = 0; i < 3; i++) {
            String numberAsString_date = Date[i];
            intArray_date[i] = Integer.parseInt(numberAsString_date);
        }
        Boolean rap = (Modif_rapelle.isSelected());
        ModifierRDV(this.Agenda, intArray_date[0], intArray_date[1], intArray_date[2], intArray_d[0], intArray_d[1], intArray_f[0], intArray_f[1], libel, rap, index);
        AllRDV(this.Agenda);

    }

    public void traiterChoixSupprimerRDV(Model.Agenda Agenda) {
        int index = textArea.getSelectedIndex();

        SupprimerRDV(Agenda, index);
        AllRDV(Agenda);
    }

    public void traiterChoixSupprimerTousRDV(Model.Agenda Agenda) {
        ViderAgenda(Agenda);
        AllRDV(Agenda);
    }

    public void traiterChoixSauvegarderEtFermer(Model.Agenda Agenda) {
        try {
            Sauvegarder(Agenda);
        } catch (Exception ex) {
            Logger.getLogger(GestionnaireIHM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void traiterChoixQuitter() {
        String reponse;

        do {
            System.out.println("Voulez-vous vraiment quitter ? (yes/no)");
            Scanner scan = new Scanner(System.in);
            reponse = scan.nextLine();
        } while ((!reponse.equals("yes")) && (!reponse.equals("no")));

        if (reponse.equals("yes")) {
            System.out.println("Arrêt en cours...");
            System.out.println("OFF");
            System.exit(1);
        } else {

        }
    }

    public void check_rapelle() {
        t = new Timer();

        t.schedule(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
System.out.println("kokokokokok");
                LocalTime temps_today_15 = LocalTime.now().plusMinutes(15);
                int heure_temps_today_15 = temps_today_15.getHour();
                int minute_temps_today_15 = temps_today_15.getMinute();
                LocalTime Heure_15 = LocalTime.of(heure_temps_today_15, minute_temps_today_15);

                //répéter chaque minute 
                for (int i = 0; i < Agenda.GetTaille(); i++) {
                    if (Agenda.Get_RDV(i).getRapelle() == true) {

                        if ((Agenda.Get_RDV(i).getTime_d().equals(Heure_15) == true) && (((Agenda.Get_RDV(i).getDate().compareTo(LocalDate.now())) == 0) || ((Agenda.Get_RDV(i).getDate().compareTo(LocalDate.now().plusDays(1))) == 0))) {

                            JOptionPane.showMessageDialog(null, "Votre RDV : " + Agenda.Get_RDV(i).getLibelle() + " est dans 15 min", "Rapelle", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            // System.out.println("erreur");
                        }
                    }

                }
            }
        }, 0, 60000);
    }
}
