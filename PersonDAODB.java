package DAO;

import dbManager.connectionPool;
import entities.Person;
import entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonDAODB implements PersonDAO {
    private connectionPool cp;
    public PersonDAODB() throws SQLException, ClassNotFoundException {
        cp = connectionPool.getInstance();
    }

    @Override
    public boolean IsPersonExistsById(Integer id) throws SQLException {
        ResultSet res = cp.runGetQuery("select * from persons where id =" + id);
        return res.next();
    }
    @Override
    public boolean IsPersonExistsByUser(User user) throws  SQLException{
        ResultSet res = cp.runGetQuery("select * from persons where userName ==" + user.getUserName() + "and password =="+ user.getPassword());
        return res.next();
    }
    @Override
    public boolean AddPerson(Person p) throws SQLException{
        try {
            if(IsPersonExistsById(p.getId()) && p.getAge()>0 && p.getAge()<120){
                String sql = "insert into person.persons (name, age, isMale, birthdate, userName, password) values ('"+p.getName()+"',"+p.getAge()+","+p.isMale()+",'"+p.getBirthdate()+"','"+p.getUser().getUserName()+"','"+p.getUser().getPassword()+"')";
                Integer id =  cp.runInsertQuerry(sql);
                if(id == -1)
                    return false;
                return true;
            }
            else
                return false;
        }catch(SQLException e){
            throw e;
        }
    }

    @Override
    public void Update(Person person) throws SQLException{

        String query = "UPDATE person.persons\n" +
                "SET name = '"+person.getName()+"', age ="+person.getAge()+", isMale = "+person.isMale()+", birthdate = '"+person.getBirthdate()+"', userName = '"+person.getUser().getUserName()+"', password = '"+person.getUser().getPassword()+"'\n" +
                "WHERE id = "+person.getId()+";";
        cp.runUpdateQuerry(query);
    }
    @Override
    public void Delete(Integer id) throws SQLException{
        cp.runDeleteQuerry("DELETE FROM persons WHERE id = "+id);
    }

    @Override
    public Person GetPersonById(Integer id) throws SQLException{
        ResultSet res = cp.runGetQuery("select * from persons where id =" + id);
        res.next();
        User u = new User(res.getString(6),res.getString(7));
        Person p = new Person(res.getString(2),res.getInt(3),res.getBoolean(4),res.getDate(5).toLocalDate() ,u);
        p.setId(res.getInt(1));
        return p;

    }

    @Override
    public Person GetPersonByUser(User user)throws SQLException{
        ResultSet res = cp.runGetQuery("select * from persons where userName =" + user.getUserName()+" and password = "+user.getPassword());
        res.next();
        User u = new User(res.getString(6),res.getString(7));
        Person p = new Person(res.getString(2),res.getInt(3),res.getBoolean(4),res.getDate(5).toLocalDate() ,u);
        p.setId(res.getInt(1));
        return p;

    }

    @Override
    public List<Person> GetListPerson() throws SQLException{
        List<Person> personList = new ArrayList<>();
        ResultSet res = cp.runGetQuery("select * from persons");
        while (res.next()){
            User u = new User(res.getString(6),res.getString(7));
            Person p = new Person(res.getString(2),res.getInt(3),res.getBoolean(4),res.getDate(5).toLocalDate() ,u);
            p.setId(res.getInt(1));
            personList.add(p);
        }
        return personList;
    }

    @Override
    public Person GetPersonBtbirthdate(LocalDate date) throws SQLException{
        ResultSet res = cp.runGetQuery("select * from persons where birthdate = " +java.sql.Date.valueOf(date));
        res.next();
        User u = new User(res.getString(6),res.getString(7));
        Person p = new Person(res.getString(2),res.getInt(3),res.getBoolean(4),res.getDate(5).toLocalDate() ,u);
        p.setId(res.getInt(1));
        return p;
    }

    @Override
//    public Boolean IsExistPassword(String password){
//        try {
//            List<Person> lp = GetListPerson();
//            for (Person p:lp) {
//                if (password == p.getUser().getPassword())
//                    return true;
//            }
//            return false;
//        }catch (Exception e){
//            throw e;
//        }
//
//    }
    public Boolean IsExistPassword(String password) throws SQLException{
        try {
            List<Person> lp = GetListPerson();
            for (Person p:lp) {
                if (password == p.getUser().getPassword())
                    return true;
            }
            return false;
        }catch (SQLException e){
            throw e;
        }

    }



}
