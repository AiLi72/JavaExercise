package fr.database._1126_JDBC;

import java.util.Date;

// ⚠️ emp类是用来封装emp表数据的JavaBean ：依次获取表格中的各项数据，用来封装成对象
public class emp {
    private int id;
    private  String name;
    private  int gender;
    private int salaire;
    private Date join_date;
    private int dept_id;

    public emp(int id, String name, int gender, int salaire, Date join_date, int dept_id) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.salaire = salaire;
        this.join_date = join_date;
        this.dept_id = dept_id;
    }

    @Override   //改写toString方法，到时候好输出
    public String toString() {
        return "emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", salaire=" + salaire +
                ", join_date=" + join_date +
                ", dept_id=" + dept_id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }
}
