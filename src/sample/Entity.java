package src.sample;

public class Entity {
    private String name;
    private String surname;
    private int age;
    private boolean employed;
    private boolean married;
    private int monthSalary;
    private int numOfChildren;

    public Entity(String name, String surname, int age, boolean employed,
                  boolean married, int monthSalary, int numOfChildren) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.employed = employed;
        this.married = married;
        this.monthSalary = monthSalary;
        this.numOfChildren = numOfChildren;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public int getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(int monthSalary) {
        this.monthSalary = monthSalary;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", employed=" + employed +
                ", married=" + married +
                ", monthSalary=" + monthSalary +
                ", numOfChildren=" + numOfChildren +
                '}';
    }
}
