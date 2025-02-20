package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
      sessionFactory.getCurrentSession().save(user.getCar());
   }


   @Override
   public User getUserByCar(String model, int series){
       return  (User) sessionFactory.getCurrentSession()
               .createQuery("select u from User u join u.car c where c.model = :model and c.series = :series")
               .setParameter("model",model)
               .setParameter("series",series)
               .getSingleResult();

   }


   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
