/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri.exceptions.IllegalOrphanException;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri.exceptions.NonexistentEntityException;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controllerTri.exceptions.RollbackFailureException;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Customer;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class CustomerJpaController implements Serializable {

    public CustomerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Customer customer) throws RollbackFailureException, Exception {
        if (customer.getItemCustomerCollection() == null) {
            customer.setItemCustomerCollection(new ArrayList<ItemCustomer>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<ItemCustomer> attachedItemCustomerCollection = new ArrayList<ItemCustomer>();
            for (ItemCustomer itemCustomerCollectionItemCustomerToAttach : customer.getItemCustomerCollection()) {
                itemCustomerCollectionItemCustomerToAttach = em.getReference(itemCustomerCollectionItemCustomerToAttach.getClass(), itemCustomerCollectionItemCustomerToAttach.getItemCustomerId());
                attachedItemCustomerCollection.add(itemCustomerCollectionItemCustomerToAttach);
            }
            customer.setItemCustomerCollection(attachedItemCustomerCollection);
            em.persist(customer);
            for (ItemCustomer itemCustomerCollectionItemCustomer : customer.getItemCustomerCollection()) {
                Customer oldCustomerIdOfItemCustomerCollectionItemCustomer = itemCustomerCollectionItemCustomer.getCustomerId();
                itemCustomerCollectionItemCustomer.setCustomerId(customer);
                itemCustomerCollectionItemCustomer = em.merge(itemCustomerCollectionItemCustomer);
                if (oldCustomerIdOfItemCustomerCollectionItemCustomer != null) {
                    oldCustomerIdOfItemCustomerCollectionItemCustomer.getItemCustomerCollection().remove(itemCustomerCollectionItemCustomer);
                    oldCustomerIdOfItemCustomerCollectionItemCustomer = em.merge(oldCustomerIdOfItemCustomerCollectionItemCustomer);
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

    public void edit(Customer customer) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customer persistentCustomer = em.find(Customer.class, customer.getCustomerId());
            Collection<ItemCustomer> itemCustomerCollectionOld = persistentCustomer.getItemCustomerCollection();
            Collection<ItemCustomer> itemCustomerCollectionNew = customer.getItemCustomerCollection();
            List<String> illegalOrphanMessages = null;
            for (ItemCustomer itemCustomerCollectionOldItemCustomer : itemCustomerCollectionOld) {
                if (!itemCustomerCollectionNew.contains(itemCustomerCollectionOldItemCustomer)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ItemCustomer " + itemCustomerCollectionOldItemCustomer + " since its customerId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ItemCustomer> attachedItemCustomerCollectionNew = new ArrayList<ItemCustomer>();
            for (ItemCustomer itemCustomerCollectionNewItemCustomerToAttach : itemCustomerCollectionNew) {
                itemCustomerCollectionNewItemCustomerToAttach = em.getReference(itemCustomerCollectionNewItemCustomerToAttach.getClass(), itemCustomerCollectionNewItemCustomerToAttach.getItemCustomerId());
                attachedItemCustomerCollectionNew.add(itemCustomerCollectionNewItemCustomerToAttach);
            }
            itemCustomerCollectionNew = attachedItemCustomerCollectionNew;
            customer.setItemCustomerCollection(itemCustomerCollectionNew);
            customer = em.merge(customer);
            for (ItemCustomer itemCustomerCollectionNewItemCustomer : itemCustomerCollectionNew) {
                if (!itemCustomerCollectionOld.contains(itemCustomerCollectionNewItemCustomer)) {
                    Customer oldCustomerIdOfItemCustomerCollectionNewItemCustomer = itemCustomerCollectionNewItemCustomer.getCustomerId();
                    itemCustomerCollectionNewItemCustomer.setCustomerId(customer);
                    itemCustomerCollectionNewItemCustomer = em.merge(itemCustomerCollectionNewItemCustomer);
                    if (oldCustomerIdOfItemCustomerCollectionNewItemCustomer != null && !oldCustomerIdOfItemCustomerCollectionNewItemCustomer.equals(customer)) {
                        oldCustomerIdOfItemCustomerCollectionNewItemCustomer.getItemCustomerCollection().remove(itemCustomerCollectionNewItemCustomer);
                        oldCustomerIdOfItemCustomerCollectionNewItemCustomer = em.merge(oldCustomerIdOfItemCustomerCollectionNewItemCustomer);
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
                BigDecimal id = customer.getCustomerId();
                if (findCustomer(id) == null) {
                    throw new NonexistentEntityException("The customer with id " + id + " no longer exists.");
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
            Customer customer;
            try {
                customer = em.getReference(Customer.class, id);
                customer.getCustomerId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customer with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ItemCustomer> itemCustomerCollectionOrphanCheck = customer.getItemCustomerCollection();
            for (ItemCustomer itemCustomerCollectionOrphanCheckItemCustomer : itemCustomerCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Customer (" + customer + ") cannot be destroyed since the ItemCustomer " + itemCustomerCollectionOrphanCheckItemCustomer + " in its itemCustomerCollection field has a non-nullable customerId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(customer);
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

    public List<Customer> findCustomerEntities() {
        return findCustomerEntities(true, -1, -1);
    }

    public List<Customer> findCustomerEntities(int maxResults, int firstResult) {
        return findCustomerEntities(false, maxResults, firstResult);
    }

    private List<Customer> findCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customer.class));
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

    public Customer findCustomer(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customer> rt = cq.from(Customer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
