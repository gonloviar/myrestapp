/*
 * License here...
 */
package com.gonloviar.myRestApp.dominio;

/**
 *
 * @author yotas
 */
public class Usuario {
    
    
    private int id;
    private String nombre;
    private String apellido;

    
//  getters and setters
    
    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
}

