/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.cv05.jenc.tictactoewithdb;

import java.util.List;
import javax.ejb.Local;
//import static org.eclipse.persistence.jaxb.TypeMappingInfo.ElementScope.Local;

/**
 *
 * @author jencmart
 */
@Local
public interface ISample 
{
    List<Sailor> readSailors();
}
