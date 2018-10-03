/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri.exceptions.IllegalOrphanException;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri.exceptions.NonexistentEntityException;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Item;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Producer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author jencmart
 */
public class ProducerJpaController implements Serializable {

    public ProducerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producer producer) throws RollbackFailureException, Exception {
        if (producer.getItemCollection() == null) {
            producer.setItemCollection(new ArrayList<Item>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Item> attachedItemCollection = new ArrayList<Item>();
            for (Item itemCollectionItemToAttach : producer.getItemCollection()) {
                itemCollectionItemToAttach = em.getReference(itemCollectionItemToAttach.getClass(), itemCollectionItemToAttach.getItemId());
                attachedItemCollection.add(itemCollectionItemToAttach);
            }
            producer.setItemCollection(attachedItemCollection);
            em.persist(producer);
            for (Item itemCollectionItem : producer.getItemCollection()) {
                Producer oldProducerIdOfItemCollectionItem = itemCollectionItem.getProducerId();
                itemCollectionItem.setProducerId(producer);
                itemCollectionItem = em.merge(itemCollectionItem);
                if (oldProducerIdOfItemCollectionItem != null) {
                    oldProducerIdOfItemCollectionItem.getItemCollection().remove(itemCollectionItem);
                    oldProducerIdOfItemCollectionItem = em.merge(oldProducerIdOfItemCollectionItem);
                }
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

    public void edit(Producer producer) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Producer persistentProducer = em.find(Producer.class, producer.getProducerId());
            Collection<Item> itemCollectionOld = persistentProducer.getItemCollection();
            Collection<Item> itemCollectionNew = producer.getItemCollection();
            List<String> illegalOrphanMessages = null;
            for (Item itemCollectionOldItem : itemCollectionOld) {
                if (!itemCollectionNew.contains(itemCollectionOldItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Item " + itemCollectionOldItem + " since its producerId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Item> attachedItemCollectionNew = new ArrayList<Item>();
            for (Item itemCollectionNewItemToAttach : itemCollectionNew) {
                itemCollectionNewItemToAttach = em.getReference(itemCollectionNewItemToAttach.getClass(), itemCollectionNewItemToAttach.getItemId());
                attachedItemCollectionNew.add(itemCollectionNewItemToAttach);
            }
            itemCollectionNew = attachedItemCollectionNew;
            producer.setItemCollection(itemCollectionNew);
            producer = em.merge(producer);
            for (Item itemCollectionNewItem : itemCollectionNew) {
                if (!itemCollectionOld.contains(itemCollectionNewItem)) {
                    Producer oldProducerIdOfItemCollectionNewItem = itemCollectionNewItem.getProducerId();
                    itemCollectionNewItem.setProducerId(producer);
                    itemCollectionNewItem = em.merge(itemCollectionNewItem);
                    if (oldProducerIdOfItemCollectionNewItem != null && !oldProducerIdOfItemCollectionNewItem.equals(producer)) {
                        oldProducerIdOfItemCollectionNewItem.getItemCollection().remove(itemCollectionNewItem);
                        oldProducerIdOfItemCollectionNewItem = em.merge(oldProducerIdOfItemCollectionNewItem);
                    }
                }
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
                BigDecimal id = producer.getProducerId();
                if (findProducer(id) == null) {
                    throw new NonexistentEntityException("The producer with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Producer producer;
            try {
                producer = em.getReference(Producer.class, id);
                producer.getProducerId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producer with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Item> itemCollectionOrphanCheck = producer.getItemCollection();
            for (Item itemCollectionOrphanCheckItem : itemCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producer (" + producer + ") cannot be destroyed since the Item " + itemCollectionOrphanCheckItem + " in its itemCollection field has a non-nullable producerId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(producer);
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

    public List<Producer> findProducerEntities() {
        return findProducerEntities(true, -1, -1);
    }

    public List<Producer> findProducerEntities(int maxResults, int firstResult) {
        return findProducerEntities(false, maxResults, firstResult);
    }

    private List<Producer> findProducerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producer.class));
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

    public Producer findProducer(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producer.class, id);
        } finally {
            em.close();
        }
    }

    public int getProducerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producer> rt = cq.from(Producer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
