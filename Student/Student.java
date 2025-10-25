public class Student {
    private int studentID;
    private String fullName;
    private int age;
    private String gender;
    private String department;
    private float gpa;
    public Student(int studentID, String fullName, int age, String gender, String department, float gpa){
        setStudentID(studentID);
        setFullName(fullName);
        setAge(age);
        setGender(gender);
        setDepartment(department);
        setGPA(gpa);
    }
    
    //setters
    
    public void setStudentID(int studentID){
        this.studentID=studentID;
    }
    public void setFullName(String fullName){
        this.fullName=fullName;
    }
    public void setAge(int age){
        if(age>0){
        this.age=age;
        }
    }
    public void setGender(String gender){
        if(gender.equals("male")||gender.equals("female")){
        this.gender=gender;
        }
    }
    public void setDepartment(String department){
        this.department=department;
    }
    public void setGPA(float gpa){
        if(gpa<=4&&gpa>0){
        this.gpa=gpa;
        }
    }
    
    //getters
    public int getStudentID(){
        return studentID;
    }
    public String getFullName(){
        return fullName;
    }
    public int getAge(){
        return age;
    }
    public String getGender(){
        return gender;
    }
    public String getDepartment(){
        return department;
    }
    public float getGPA(){
        return gpa;
    }
}
