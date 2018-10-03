/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.cz.fit.bitjv.cv5.orm.fitormdemo;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jpavlicek
 */
//Vsimete si, jak je anotovane rozhrani
@Local
public interface ISample {
    public List<Sailor> readSailors();
    
}
