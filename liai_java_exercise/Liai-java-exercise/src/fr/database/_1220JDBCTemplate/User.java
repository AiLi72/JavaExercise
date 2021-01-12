package fr.database._1220JDBCTemplate;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private Integer english;
    private Integer math;


    public User(){}

    public User(int id, String username, String password, String sex, int english, int math) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.english = english;
        this.math = math;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", english=" + english +
                ", math=" + math +
                '}';
    }
}
