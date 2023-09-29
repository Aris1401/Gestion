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
public class Besoin extends BDD
{
    	int id;
	int service ;
	String  description;
	String titre ;
	double volumeTaches;
	double tauxJourHomme ;
	Date dateBesoin;
	Date dateFin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getVolumeTaches() {
        return volumeTaches;
    }

    public void setVolumeTaches(double volumeTaches) {
        this.volumeTaches = volumeTaches;
    }

    public double getTauxJourHomme() {
        return tauxJourHomme;
    }

    public void setTauxJourHomme(double tauxJourHomme) {
        this.tauxJourHomme = tauxJourHomme;
    }

    public Date getDateBesoin() {
        return dateBesoin;
    }

    public void setDateBesoin(Date dateBesoin) {
        this.dateBesoin = dateBesoin;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
//////////////////////////////////////////////////////////////       
    public ArrayList<Besoin> allPersonne()
   {
      Besoin besoin = new Besoin();
      ArrayList<String[]> besoinsBDD =besoin.select();
      ArrayList<Besoin> besoins= new ArrayList<>();
      for(int i=0;i< besoinsBDD.size();i++)
      {
          Besoin b = new Besoin();
          b.setId(Integer.parseInt(besoinsBDD.get(i)[0]));
          b.setService(Integer.parseInt(besoinsBDD.get(i)[1]));
          b.setDescription(String.valueOf(besoinsBDD.get(i)[2]));
          b.setTitre(String.valueOf(besoinsBDD.get(i)[3]));
          b.setVolumeTaches(Double.parseDouble(besoinsBDD.get(i)[4]));
          b.setTauxJourHomme(Integer.parseInt(besoinsBDD.get(i)[5]));
          b.setDateBesoin(Date.valueOf(besoinsBDD.get(i)[6]));
          b.setDateFin(Date.valueOf(besoinsBDD.get(i)[7]));

        besoins.add(b);
      }
      return besoins;  
   } 


}
