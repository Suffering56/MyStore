package beans;

import entities.Users;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import managers.UsersManager;

@ManagedBean(name = "category")
@SessionScoped
public class Category {

    public String save() {
        for (Users user : usersList) {
            usersManager.replaceUser(user);
        }
        usersList = usersManager.getAllUsers();
        return null;
    }

    public String refresh() {
        usersList = usersManager.getAllUsers();
        return "category";
    }
    
    public String extractParam(String param) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        return map.get(param);
    }

    public List<Users> getUsersList() {
        if (usersList == null) {
            usersList = usersManager.getAllUsers();
        }
        return usersList;
    }

    @EJB
    private UsersManager usersManager;
    private List<Users> usersList;
}
