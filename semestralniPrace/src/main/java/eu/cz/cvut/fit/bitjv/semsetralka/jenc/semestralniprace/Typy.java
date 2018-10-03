/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.semsetralka.jenc.semestralniprace;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jencmart
 */
public class Typy {
    private List<Typ> roles = new ArrayList();

    public List<Typ> getDrivers() {
        return roles;
    }

    public void setDrivers(List<Typ> drivers) {
        this.roles = drivers;
    }   
    
}
