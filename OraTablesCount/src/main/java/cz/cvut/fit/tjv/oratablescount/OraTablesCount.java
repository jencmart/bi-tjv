package cz.cvut.fit.tjv.oratablescount;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ondrej Guth
 */
@Named(value = "oraTablesCount")
@Dependent
@Stateless
public class OraTablesCount {    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Query number of tables in user's database
     * @return number of tables
     */
    public int getTablesCount() {
        final Query q = em.createNativeQuery("SELECT COUNT(*) FROM USER_TABLES");
        final BigDecimal r = (BigDecimal)q.getSingleResult();
        return r.intValue();
    }
    
}
