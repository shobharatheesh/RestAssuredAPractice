package com.udemy.April082025;

//POJO ---> Plain old java object
//This is the java class that maintains data in the form of object .mostly this is used for request Payload

public class StudentsPOJO {

    //encapsulation --This is pojo class i.e it will maintain data in the form of object

    String name;
    String location;
    String phone;
    String[] courses;

    public String[] getCourses() {
        return courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
