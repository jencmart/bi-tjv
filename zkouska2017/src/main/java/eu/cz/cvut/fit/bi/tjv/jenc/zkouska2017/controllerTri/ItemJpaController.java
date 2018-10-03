/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri.exceptions.IllegalOrphanException;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri.exceptions.NonexistentEntityException;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri.exceptions.RollbackFailureException;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Item;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Producer;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.ItemCustomer;
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
public class ItemJpaController implements Serializable {

    public ItemJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Item item) throws RollbackFailureException, Exception {
        if (item.getItemCustomerCollection() == null) {
            item.setItemCustomerCollection(new ArrayList<ItemCustomer>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Producer producerId = item.getProducerId();
            if (producerId != null) {
                producerId = em.getReference(producerId.getClass(), producerId.getProducerId());
                item.setProducerId(producerId);
            }
            Collection<ItemCustomer> attachedItemCustomerCollection = new ArrayList<ItemCustomer>();
            for (ItemCustomer itemCustomerCollectionItemCustomerToAttach : item.getItemCustomerCollection()) {
                itemCustomerCollectionItemCustomerToAttach = em.getReference(itemCustomerCollectionItemCustomerToAttach.getClass(), itemCustomerCollectionItemCustomerToAttach.getItemCustomerId());
                attachedItemCustomerCollection.add(itemCustomerCollectionItemCustomerToAttach);
            }
            item.setItemCustomerCollection(attachedItemCustomerCollection);
            em.persist(item);
            if (producerId != null) {
                producerId.getItemCollection().add(item);
                producerId = em.merge(producerId);
            }
            for (ItemCustomer itemCustomerCollectionItemCustomer : item.getItemCustomerCollection()) {
                Item oldItemIdOfItemCustomerCollectionItemCustomer = itemCustomerCollectionItemCustomer.getItemId();
                itemCustomerCollectionItemCustomer.setItemId(item);
                itemCustomerCollectionItemCustomer = em.merge(itemCustomerCollectionItemCustomer);
                if (oldItemIdOfItemCustomerCollectionItemCustomer != null) {
                    oldItemIdOfItemCustomerCollectionItemCustomer.getItemCustomerCollection().remove(itemCustomerCollectionItemCustomer);
                    oldItemIdOfItemCustomerCollectionItemCustomer = em.merge(oldItemIdOfItemCustomerCollectionItemCustomer);
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

    public void edit(Item item) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Item persistentItem = em.find(Item.class, item.getItemId());
            Producer producerIdOld = persistentItem.getProducerId();
            Producer producerIdNew = item.getProducerId();
            Collection<ItemCustomer> itemCustomerCollectionOld = persistentItem.getItemCustomerCollection();
            Collection<ItemCustomer> itemCustomerCollectionNew = item.getItemCustomerCollection();
            List<String> illegalOrphanMessages = null;
            for (ItemCustomer itemCustomerCollectionOldItemCustomer : itemCustomerCollectionOld) {
                if (!itemCustomerCollectionNew.contains(itemCustomerCollectionOldItemCustomer)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ItemCustomer " + itemCustomerCollectionOldItemCustomer + " since its itemId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (producerIdNew != null) {
                producerIdNew = em.getReference(producerIdNew.getClass(), producerIdNew.getProducerId());
                item.setProducerId(producerIdNew);
            }
            Collection<ItemCustomer> attachedItemCustomerCollectionNew = new ArrayList<ItemCustomer>();
            for (ItemCustomer itemCustomerCollectionNewItemCustomerToAttach : itemCustomerCollectionNew) {
                itemCustomerCollectionNewItemCustomerToAttach = em.getReference(itemCustomerCollectionNewItemCustomerToAttach.getClass(), itemCustomerCollectionNewItemCustomerToAttach.getItemCustomerId());
                attachedItemCustomerCollectionNew.add(itemCustomerCollectionNewItemCustomerToAttach);
            }
            itemCustomerCollectionNew = attachedItemCustomerCollectionNew;
            item.setItemCustomerCollection(itemCustomerCollectionNew);
            item = em.merge(item);
            if (producerIdOld != null && !producerIdOld.equals(producerIdNew)) {
                producerIdOld.getItemCollection().remove(item);
                producerIdOld = em.merge(producerIdOld);
            }
            if (producerIdNew != null && !producerIdNew.equals(producerIdOld)) {
                producerIdNew.getItemCollection().add(item);
                producerIdNew = em.merge(producerIdNew);
            }
            for (ItemCustomer itemCustomerCollectionNewItemCustomer : itemCustomerCollectionNew) {
                if (!itemCustomerCollectionOld.contains(itemCustomerCollectionNewItemCustomer)) {
                    Item oldItemIdOfItemCustomerCollectionNewItemCustomer = itemCustomerCollectionNewItemCustomer.getItemId();
                    itemCustomerCollectionNewItemCustomer.setItemId(item);
                    itemCustomerCollectionNewItemCustomer = em.merge(itemCustomerCollectionNewItemCustomer);
                    if (oldItemIdOfItemCustomerCollectionNewItemCustomer != null && !oldItemIdOfItemCustomerCollectionNewItemCustomer.equals(item)) {
                        oldItemIdOfItemCustomerCollectionNewItemCustomer.getItemCustomerCollection().remove(itemCustomerCollectionNewItemCustomer);
                        oldItemIdOfItemCustomerCollectionNewItemCustomer = em.merge(oldItemIdOfItemCustomerCollectionNewItemCustomer);
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
                BigDecimal id = item.getItemId();
                if (findItem(id) == null) {
                    throw new NonexistentEntityException("The item with id " + id + " no longer exists.");
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
            Item item;
            try {
                item = em.getReference(Item.class, id);
                item.getItemId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The item with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ItemCustomer> itemCustomerCollectionOrphanCheck = item.getItemCustomerCollection();
            for (ItemCustomer itemCustomerCollectionOrphanCheckItemCustomer : itemCustomerCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Item (" + item + ") cannot be destroyed since the ItemCustomer " + itemCustomerCollectionOrphanCheckItemCustomer + " in its itemCustomerCollection field has a non-nullable itemId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Producer producerId = item.getProducerId();
            if (producerId != null) {
                producerId.getItemCollection().remove(item);
                producerId = em.merge(producerId);
            }
            em.remove(item);
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

    public List<Item> findItemEntities() {
        return findItemEntities(true, -1, -1);
    }

    public List<Item> findItemEntities(int maxResults, int firstResult) {
        return findItemEntities(false, maxResults, firstResult);
    }

    private List<Item> findItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Item.class));
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

    public Item findItem(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Item.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Item> rt = cq.from(Item.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
