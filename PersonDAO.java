package DAO;

import entities.Person;
import entities.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface PersonDAO {
    public boolean IsPersonExistsById(Integer id) throws SQLException;
    public boolean IsPersonExistsByUser(User user) throws  SQLException;
    public boolean AddPerson(Person p) throws SQLException;
    public void Update(Person person) throws SQLException;
    public void Delete(Integer id) throws SQLException;
    public Person GetPersonById(Integer id) throws SQLException;
    public Person GetPersonByUser(User user) throws SQLException;
    public List<Person> GetListPerson() throws SQLException;
    public Person GetPersonBtbirthdate(LocalDate date) throws SQLException;
    public Boolean IsExistPassword(String password) throws SQLException;
    //public Boolean IsExistPassword(String password);

}
