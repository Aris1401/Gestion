/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Henintsoa & Hery
 */
public class PosteService {
    private int id;
    private int service;
    private String titreposte;

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

    public String getTitreposte() {
        return titreposte;
    }

    public void setTitreposte(String titreposte) {
        this.titreposte = titreposte;
    }

    public PosteService() {
    }

    public PosteService(int id, int service, String titreposte) {
        this.setId(id);
        this.setService(service);
        this.setTitreposte(titreposte);
    }
    
    
}
