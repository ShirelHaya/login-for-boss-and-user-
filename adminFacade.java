package facade;
import DAO.PersonDAO;
import DAO.PersonDAODB;
import entities.Person;
import entities.User;

import java.sql.SQLException;
import java.util.List;

public class adminFacade extends clientFacade {
    PersonDAO personDAO;

    public adminFacade(){
        try {
            personDAO = new PersonDAODB();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean login(User user){
        try{
            if(user.getPassword() == "admin" && user.getUserName() == "admin@admin.com")
                return true;
            else
                return false;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void addPerson(Person person){
        try {
            if(!(personDAO.IsPersonExistsById(person.getId())) && !personDAO.IsExistPassword(person.getUser().getPassword()))
            {
                personDAO.AddPerson(person);
                PrintPersonsStartFirstLetter(person.getName().charAt(0));
            }

        }catch (Exception e){
           e.printStackTrace();
        }
    }

    public boolean CheckingPassword(String password){
        try{
            if(password.length() >=4 && !password.contains("$") &&!password.contains("="))
                return true;
            return false;
        }catch(Exception e){
            e.printStackTrace();
        }
        return  false;
    }

    public boolean UpdateNewPassword(String password, User user){
        try{
            Person person = null;
            if(personDAO.IsPersonExistsByUser(user)==true){
                person = personDAO.GetPersonByUser(user);
                if (CheckingPassword(password))
                {
                    user.setPassword(password);
                    personDAO.Update(person);
                }
                return true;
            }
            return false;
        }catch(Exception e){
            e.printStackTrace();
        }
       return false;

    }
    public void PrintPersonsStartFirstLetter(char c){
        try{
            List<Person> _persons = personDAO.GetListPerson();
            for (Person p:
                    _persons) {
                if(!p.getName().startsWith(String.valueOf(c)))
                    System.out.println(p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}

