package sessions;

import model.StringRepeater;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Stateless
public class DatabaseHelperBean implements DatabaseHelper {

    EntityManagerFactory entityManagerFactory;

    @Override
    public void writeData(StringRepeater stringRepeater) {
        entityManagerFactory = Persistence.createEntityManagerFactory("data.jpa.hibernate");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(stringRepeater);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public StringRepeater findData(String sourceString, int repeatCount) {
        entityManagerFactory = Persistence.createEntityManagerFactory("JavaRush");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<StringRepeater> data = em.createQuery("SELECT * FROM STRING_REPEATER WHERE SOURCE_STRING = \'" + sourceString + "\' AND REPEAT_COUNT = " + repeatCount,
                StringRepeater.class)
                .getResultList();
        em.getTransaction().commit();
        em.close();
        if(data == null) {
            return null;
        } else {
            return data.get(0);
        }
    }
}
