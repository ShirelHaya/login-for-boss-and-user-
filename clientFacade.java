package facade;
import DAO.PersonDAO;
import DAO.PersonDAODB;
import entities.User;
import java.sql.SQLException;

public abstract class clientFacade {
    protected static PersonDAO personDAO = null;

    protected clientFacade(){
        try{
            personDAO = new PersonDAODB();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public abstract boolean login(User user);
    //public abstract boolean UpdateNewPassword(String password, User user) throws SQLException;
    //public abstract boolean CheckingPassword(String password);



}
