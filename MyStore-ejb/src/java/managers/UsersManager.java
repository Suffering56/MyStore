package managers;

import entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UsersManager {

    @PersistenceContext(unitName = "MyStore-ejbPU")
    private EntityManager manager;

    public List<Users> getAllUsers() {
        return manager.createNamedQuery("Users.findAll").getResultList();
    }
    
    public Users getUserById(int id) {
        return (Users) manager.createNamedQuery("Users.findByUserID").setParameter("userID", id).getSingleResult();
    }

    public Users addUser(Users newUser) {
        manager.persist(newUser);
        return newUser;
    }

    public Users replaceUser(Users newUser) {
        manager.merge(newUser);
        return newUser;
    }
}
