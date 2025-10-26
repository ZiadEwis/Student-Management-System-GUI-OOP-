package StudentManagementSystem;

public class Student {
    private int studentID;
    private String fullName;
    private int age;
    private String gender;
    private String department;
    private double gpa;

    public Student(int studentID, String fullName, int age, String gender, String department, double gpa){
        this.studentID = studentID;
        this.fullName = fullName;
        this.age = age;
        setGender(gender);
        this.department = department;
        this.gpa = gpa;
    }

    // Getters & Setters
    public int getStudentID() { return studentID; }
    public void setStudentID(int studentID) { this.studentID = studentID; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getAge() { return age; }
    public void setAge(int age) { if(age>0) this.age=age; }

    public String getGender() { return gender; }
    public void setGender(String gender){
        if (gender == null) this.gender="Not Specified";
        else if (gender.equals("Male") || gender.equals("Female")) this.gender=gender;
        else this.gender="Other";
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department=department; }

    public double getGPA() { return gpa; }
    public void setGPA(double gpa) { if(gpa>=0 && gpa<=4) this.gpa=gpa; }
}
