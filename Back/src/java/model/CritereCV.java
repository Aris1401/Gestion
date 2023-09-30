/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import generalisationIante.BDD;

/**
 *
 * @author BEST
 */
public class CritereCV extends BDD
{
 	int id;
	int critere;
	double note ;
	int idCV ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCritere() {
        return critere;
    }

    public void setCritere(int critere) {
        this.critere = critere;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public int getIdCV() {
        return idCV;
    }

    public void setIdCV(int idCV) {
        this.idCV = idCV;
    }
///////////////////////////////////////////////////////////
    public void insertCritereCV(String  critere,double note,String idCV,boolean check,String sousCritere)
    {
        CritereBesoin critereBesoin=new CritereBesoin();
        CritereCV critereCV =new CritereCV();
         int criteree=Integer.parseInt(critere);
         int idCVv =Integer.parseInt(idCV);
         int sousCriteree=Integer.parseInt (sousCritere);
         note= critereBesoin.getValeurCritere(criteree,sousCriteree, check);
         critereCV.dontSave("id");
         critereCV.save();
        
    }
}
