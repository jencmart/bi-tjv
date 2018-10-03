/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.service;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controller.AbstractController;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controller.CustomerController;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jencmart
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract AbstractController getEntityManager();

    public void create(T entity) {
        getEntityManager().create(entity);
    }

    public void remove(BigDecimal id) {
        getEntityManager().remove(id);
    }

    public T find(BigDecimal id) {
        return (T) getEntityManager().find(id);
    }

    public Collection<T> findAll() 
    {
         return getEntityManager().findAll();
    }

    public Collection<T> findByName(String s)
    {
        return getEntityManager().findByName(s);
    }
    
}
