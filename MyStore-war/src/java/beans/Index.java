package beans;

import entities.Users;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import managers.UsersManager;

@ManagedBean(name = "index")
@SessionScoped
public class Index {

    public List<Users> getUsersList() {
        if (usersList == null) {
            usersList = usersManager.getData();
        }
        return usersList;
    }
    
    @EJB
    private UsersManager usersManager;
    private List<Users> usersList;
}