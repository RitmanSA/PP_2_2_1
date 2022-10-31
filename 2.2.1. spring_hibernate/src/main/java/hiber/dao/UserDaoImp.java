package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private Session session;

   @Override
   public void add(User user) {
      session.save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      return session.createQuery("from User").getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      User user;
      try {
         Query query = session.createQuery("FROM User " +
                 "where car.model = :model " +
                 "and car.series = :series");
         query.setParameter("model", model);
         query.setParameter("series", series);
         user = (User) query.getSingleResult();
      } catch (NoResultException e) {
         user = null;
      }
      return user;
   }


}
