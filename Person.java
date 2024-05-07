package entities;

import java.time.LocalDate;

public class Person {
    private Integer id;
    private String name;
    private Integer age;
    private boolean isMale;
    private LocalDate birthdate;
    private User user;


    public Person(String name, Integer age, boolean isMale, LocalDate birthdate, User user) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.birthdate = birthdate;
        this.user = user;
    }

    public Person() {
        this.name = "name";
        this.age = 0;
        this.isMale = false;
        this.birthdate = LocalDate.ofEpochDay(2023/12/12);
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isMale() {
        return isMale;
    }


    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isMale=" + isMale +
                ", birthdate=" + birthdate  +
                ", user=" + user +
                '}';
    }
}
