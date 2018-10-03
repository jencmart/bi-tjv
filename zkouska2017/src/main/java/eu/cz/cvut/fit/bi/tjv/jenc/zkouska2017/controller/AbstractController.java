/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controller;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Customer;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jencmart
 */

public abstract class AbstractController<T> {
    
    public abstract void create(T entity);
    
    public abstract void remove(BigDecimal id);
    
    public abstract T find(BigDecimal id);

    public abstract Collection<T> findAll();
    
    public abstract Collection<T> findByName(String s);
    
}
