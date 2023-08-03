
package com.egg.noticias.entidades;

import java.util.ArrayList;
import javax.persistence.Entity;

@Entity
public class Periodista extends Usuario{
    
    private ArrayList misNoticias;
    private Integer sueldo;

    public Periodista() {
    }



    public ArrayList getMisNoticias() {
        return misNoticias;
    }

    public void setMisNoticias(ArrayList misNoticias) {
        this.misNoticias = misNoticias;
    }

    public Integer getSueldo() {
        return sueldo;
    }

    public void setSueldo(Integer sueldo) {
        this.sueldo = sueldo;
    }
    
    
    
}
