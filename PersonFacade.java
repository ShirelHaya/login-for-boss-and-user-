package facade;

import DAO.PersonDAO;
import DAO.PersonDAODB;
import entities.User;
import entities.Person;

import java.sql.SQLException;
import java.util.List;

public class PersonFacade extends clientFacade {

    PersonDAO PD = null;
        public PersonFacade(){
            try{
                PD = new PersonDAODB();
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    @Override
    public boolean login(User user){
        try {
            if(PD.IsExistPassword(user.getPassword()))
            {
                System.out.println("the password not correct");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public static void AddPerson(Person p){
            try{
                personDAO.AddPerson(p);
            }catch(Exception e){
                e.printStackTrace();
            }
    }

    public static List<Person> GetListPerson(){
            try{
                return personDAO.GetListPerson();
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
    }

    public void showDetails(int id)  {
            try {
                Person person = PD.GetPersonById(id);
                person.toString();
            }catch(Exception e){
                e.printStackTrace();
            }

    }
    public void delete(int id) {
            try {
                PD.Delete(id);
            }catch (Exception e){
                e.printStackTrace();
            }
    }
    public void Update(Person person){
            try{
                Person p = PD.GetPersonById(person.getId());
                if(p.isMale() != person.isMale() || p.getBirthdate() != person.getBirthdate())
                    System.out.println("cant change the date of birth or gander!!!");
                else
                    PD.Update(person);
            }catch(Exception e){
                e.printStackTrace();
            }
    }

}
