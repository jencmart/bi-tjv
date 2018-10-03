/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.cv5.orm.fitormdemo;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jpavlicek
 */

//Jakeho typu ma byt trida, aby mohla implementovat lokalni rozhrani a byla Stateless beanou??
public class SampleReader {

  
    //Vsimete si, jak si pomoci unitName volame Persitence objekt (to jmeno je na vas, muzete si jej pojmenovat jak chete). Dle tohoto jmena splikacni server dohleda konfiguraci
    @PersistenceContext(unitName = "eu.cz.fit.bitjv.cv5.orm_FitORMDemo_war_1.0-SNAPSHOTPU")
    private EntityManager em;
 
    
 
    public  readSailors() {
           CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sailor.class));
            Query q = em.createQuery(cq);
          return q.getResultList();
    }

    
    
    
}
