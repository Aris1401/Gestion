/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Henintsoa & Hery
 */
public class ConfigConge {
    private int id;
    private String nomconfig;
    private String configvalue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomconfig() {
        return nomconfig;
    }

    public void setNomconfig(String nomconfig) {
        this.nomconfig = nomconfig;
    }

    public String getConfigvalue() {
        return configvalue;
    }

    public void setConfigvalue(String configvalue) {
        this.configvalue = configvalue;
    }

    public ConfigConge() {
        
    }

    public ConfigConge(int id, String nomconfig, String configvalue) {
        this.setId(id);
        this.setNomconfig(nomconfig);
        this.setConfigvalue(configvalue);
    }    
}
