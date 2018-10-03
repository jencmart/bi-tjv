/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.semsetralka.jenc.controller;

import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Typ;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jencmart
 */
@Stateless
public class TypCntroller {

   @PersistenceContext
    private EntityManager em;

    public void create(Typ entity) {
        this.em.merge(entity);
    }

    public void remove( Long id) {
     Typ t = this.find(id);
        if(t != null)
            this.em.remove(t);     
    }

    public Typ find(Long id) {
        return this.em.find(Typ.class, id);
    }

    public Collection<Typ> findAll() {
         return this.em.createNativeQuery("SELECT * FROM TYP", Typ.class).getResultList();
    }
    
    public Collection<Typ> findConstrain(String s){
     Query q =  this.em.createNativeQuery("SELECT * FROM TYP WHERE POPIS LIKE ? ", Typ.class);
     q.setParameter(1,"%"+ s +"%");
     return q.getResultList();
    }
}