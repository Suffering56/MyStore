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

    public List<Users> getData() {
        return manager.createNamedQuery("Users.findAll").getResultList();
    }
}
