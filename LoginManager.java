package login;

/*//import .CompaniesDAO;
import dao.CompaniesDBDAO;
import dao.CustomersDAO;
import dao.CustomersDBDAO;
import entities.LoginDetails;
import entities.User;
import facade.AdminFacade;
import facade.ClientFacade;
import facade.PersonFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;
import facade.adminFacade;
import login.Credentials;*/

import entities.User;
import facade.PersonFacade;
import facade.adminFacade;
import facade.clientFacade;

import java.sql.SQLException;

public class LoginManager {

    private static LoginManager instance = null;
    private LoginManager(){

    }
    public static LoginManager getInstance(){
        if (instance==null)
            instance=new LoginManager();
        return instance;
    }
    private static final String ADMIN_EMAIL = "admin@admin.com";
    private static final String ADMIN_PASSWORD = "admin";
    private clientFacade clientFacade=null;
    public clientFacade  login(User credentials, Role role) throws SQLException, ClassNotFoundException {
        if (role.equals(Role.ADMIN)) {
            clientFacade = new adminFacade();
            if (clientFacade.login(credentials)) {
                return clientFacade;
            }
        }
        if (role.equals(Role.PERSON)) {
            clientFacade = new PersonFacade(){
            };
            if (clientFacade.login(credentials)) {
                return clientFacade;
            }
        }
        return null;
    }

}