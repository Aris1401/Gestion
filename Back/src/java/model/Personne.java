/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import generalisationIante.BDD;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BEST
 */
public class Personne extends BDD
{
    int id;
    String nom;
    String prenom;
    String email;
    String motDePasse;
    Date dateNaissance;
    int sexe;
    String contact;
    int profil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getProfil() {
        return profil;
    }

    public void setProfil(int isAdmin) {
        this.profil = isAdmin;
    }

    public Personne() {
    }

    public Personne(int id, String nom, String prenom, String email, String motDePasse, Date dateNaissance, int sexe, String contact, int profil) {
        this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setEmail(email);
        this.setMotDePasse(motDePasse);
        this.setDateNaissance(dateNaissance);
        this.setSexe(sexe);
        this.setContact(contact);
        this.setProfil(profil);
    }
    
    
///////////////////////////////////////////////////////
    public static ArrayList<Personne> allPersonne()
    {
       Personne personne = new Personne();
       ArrayList<String[]> personneBDD =personne.select();
       ArrayList<Personne> personnes= new ArrayList<>();
       for(int i=0;i< personneBDD.size();i++)
       {
           Personne p=new Personne();
           p.setId(Integer.parseInt(personneBDD.get(i)[0]));
           p.setNom(String.valueOf(personneBDD.get(i)[1]));
           p.setPrenom(String.valueOf(personneBDD.get(i)[2]));
           p.setEmail(String.valueOf(personneBDD.get(i)[3]));
           p.setMotDePasse(String.valueOf(personneBDD.get(i)[4]));
           p.setDateNaissance(Date.valueOf(personneBDD.get(i)[5]));
           p.setSexe(Integer.parseInt(personneBDD.get(i)[6]));
           p.setContact((personneBDD.get(i)[7]));
           p.setProfil(Integer.parseInt(personneBDD.get(i)[8]));
           System.out.println(p);
         personnes.add(p);
       }
       return personnes;  
    }
///////////////////////////////////////////////////////    
    public static Compte Login (String email,String motDePasse)
    {
        Compte compte = new Compte();
        
        ArrayList<Personne> allPersonne= Personne.allPersonne();
        for(int i=0;i<allPersonne.size();i++)
        {
            if(allPersonne.get(i).getEmail().equals(email))
            {
                if (allPersonne.get(i).getMotDePasse().equals(motDePasse)) {
                    compte.setPersonne(allPersonne.get(i));
                    compte.setProfil(allPersonne.get(i).getProfil());
                    
                    // Obtenir le profil
                    Profil profil = null;
                    try {
                        profil = Profil.getProfil(compte.getProfil());
                    } catch (Exception ex) {
                        Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    compte.setRang(profil.getRang()); // A faire
                    compte.setNom(profil.getNom()); // A faire
                    compte.setValide(1);
                    
                } else {
                    compte.setValide(-1);
                    compte.setError("Mot de passe incorrect");
                }
                
                break;
            }
        }
        
//        compte.setError(email);
        if (compte.getValide() == 0) compte.setError("Email ou mot de passe incorrect");
        
        return compte;
    }
 ////////////////////////////////////////////////////////

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(dateNaissance);

        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", dateNaissance=" + formattedDate +
                ", sexe=" + sexe +
                ", contact='" + contact + '\'' +
                ", profil=" + profil +
                '}';
    }
}
