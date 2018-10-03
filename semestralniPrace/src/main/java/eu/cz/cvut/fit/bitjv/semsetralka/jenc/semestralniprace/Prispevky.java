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
public class Prispevky {
        private List<Prispevek> prispevky = new ArrayList();

    public List<Prispevek> getCars() {
        return prispevky;
    }

    public void setCars(List<Prispevek> cars) {
        this.prispevky = cars;
}


    
    
}
