/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri.exceptions.NonexistentEntityException;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Customer;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Item;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.ItemCustomer;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author jencmart
 */
public class ItemCustomerJpaController implements Serializable {

    public ItemCustomerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ItemCustomer itemCustomer) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customer customerId = itemCustomer.getCustomerId();
            if (customerId != null) {
                customerId = em.getReference(customerId.getClass(), customerId.getCustomerId());
                itemCustomer.setCustomerId(customerId);
            }
            Item itemId = itemCustomer.getItemId();
            if (itemId != null) {
                itemId = em.getReference(itemId.getClass(), itemId.getItemId());
                itemCustomer.setItemId(itemId);
            }
            em.persist(itemCustomer);
            if (customerId != null) {
                customerId.getItemCustomerCollection().add(itemCustomer);
                customerId = em.merge(customerId);
            }
            if (itemId != null) {
                itemId.getItemCustomerCollection().add(itemCustomer);
                itemId = em.merge(itemId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ItemCustomer itemCustomer) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ItemCustomer persistentItemCustomer = em.find(ItemCustomer.class, itemCustomer.getItemCustomerId());
            Customer customerIdOld = persistentItemCustomer.getCustomerId();
            Customer customerIdNew = itemCustomer.getCustomerId();
            Item itemIdOld = persistentItemCustomer.getItemId();
            Item itemIdNew = itemCustomer.getItemId();
            if (customerIdNew != null) {
                customerIdNew = em.getReference(customerIdNew.getClass(), customerIdNew.getCustomerId());
                itemCustomer.setCustomerId(customerIdNew);
            }
            if (itemIdNew != null) {
                itemIdNew = em.getReference(itemIdNew.getClass(), itemIdNew.getItemId());
                itemCustomer.setItemId(itemIdNew);
            }
            itemCustomer = em.merge(itemCustomer);
            if (customerIdOld != null && !customerIdOld.equals(customerIdNew)) {
                customerIdOld.getItemCustomerCollection().remove(itemCustomer);
                customerIdOld = em.merge(customerIdOld);
            }
            if (customerIdNew != null && !customerIdNew.equals(customerIdOld)) {
                customerIdNew.getItemCustomerCollection().add(itemCustomer);
                customerIdNew = em.merge(customerIdNew);
            }
            if (itemIdOld != null && !itemIdOld.equals(itemIdNew)) {
                itemIdOld.getItemCustomerCollection().remove(itemCustomer);
                itemIdOld = em.merge(itemIdOld);
            }
            if (itemIdNew != null && !itemIdNew.equals(itemIdOld)) {
                itemIdNew.getItemCustomerCollection().add(itemCustomer);
                itemIdNew = em.merge(itemIdNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = itemCustomer.getItemCustomerId();
                if (findItemCustomer(id) == null) {
                    throw new NonexistentEntityException("The itemCustomer with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ItemCustomer itemCustomer;
            try {
                itemCustomer = em.getReference(ItemCustomer.class, id);
                itemCustomer.getItemCustomerId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itemCustomer with id " + id + " no longer exists.", enfe);
            }
            Customer customerId = itemCustomer.getCustomerId();
            if (customerId != null) {
                customerId.getItemCustomerCollection().remove(itemCustomer);
                customerId = em.merge(customerId);
            }
            Item itemId = itemCustomer.getItemId();
            if (itemId != null) {
                itemId.getItemCustomerCollection().remove(itemCustomer);
                itemId = em.merge(itemId);
            }
            em.remove(itemCustomer);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ItemCustomer> findItemCustomerEntities() {
        return findItemCustomerEntities(true, -1, -1);
    }

    public List<ItemCustomer> findItemCustomerEntities(int maxResults, int firstResult) {
        return findItemCustomerEntities(false, maxResults, firstResult);
    }

    private List<ItemCustomer> findItemCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ItemCustomer.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ItemCustomer findItemCustomer(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItemCustomer.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItemCustomer> rt = cq.from(ItemCustomer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
