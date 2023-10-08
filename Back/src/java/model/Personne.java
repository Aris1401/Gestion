/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import generalisationIante.BDD;
import java.sql.Date;
import java.util.ArrayList;

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
    int contact;
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

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
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

    public Personne(int id, String nom, String prenom, String email, String motDePasse, Date dateNaissance, int sexe, int contact, int profil) {
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
    public ArrayList<Personne> allPersonne()
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
           p.setContact(Integer.parseInt(personneBDD.get(i)[7]));
           p.setProfil(Integer.parseInt(personneBDD.get(i)[8]));
         personnes.add(p);
       }
       return personnes;  
    }
///////////////////////////////////////////////////////    
    public int Login (String email,String motDePasse)
    {
        int  valeur=0;
        ArrayList<Personne> allPersonne=this.allPersonne();
        for(int i=0;i<allPersonne.size();i++)
        {
            if(allPersonne.get(i).email=="ok" && allPersonne.get(i).motDePasse=="ok")
            {
               valeur=0;
            }
            else
            {
                valeur=1;
            }
        }
        
        return valeur;
    }
 ////////////////////////////////////////////////////////

    
}
