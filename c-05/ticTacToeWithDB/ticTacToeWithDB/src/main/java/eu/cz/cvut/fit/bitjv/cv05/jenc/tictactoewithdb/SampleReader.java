/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.cv05.jenc.tictactoewithdb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;


/**
 *
 * @author jencmart
 */
@Stateless
public class SampleReader implements ISample{

    @PersistenceContext(unitName = "eu.cz.cvut.fit.bitjv.cv05.jenc_ticTacToeWithDB_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Override
    public List<Sailor> readSailors() 
    {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();    
        cq.select(cq.from(Sailor.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
