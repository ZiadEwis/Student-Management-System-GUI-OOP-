import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
/**
 *
 * @author ERR0R
 */
public class StudentManager {
    private int nextID=9000;
    private List<Student> students= new ArrayList<>();
    
    public void setNextID(int nextID){
        this.nextID=nextID;
    }
    public int getNextID(){
        return nextID;
    }
    public int generateID(){
        setNextID(getNextID()+1);
        return getNextID();
    }
    public void addStudent(String fullName, int age, String gender, String department, float gpa){
        Student student= new Student(generateID(),fullName,age,gender,department,gpa);
        students.add(student);
    }
    public void viewStudents(){
        if(students.isEmpty())
            System.out.println("No students available!");
        else{
            for(Student i: students){
                System.out.println(i.getStudentID()+" - "+
                        i.getFullName()+" - "+i.getAge()+" - "+
                        i.getGender()+" - "+i.getDepartment()+
                        " - "+i.getGPA());
            }
        }
    }
    public Student searchStudent(int studentID){
        for(Student i:students){
            if(i.getStudentID()==studentID){
                return i;
            }
        }
        System.out.println("Student was not found");
        return null;
    }
    public void deleteStudent(int studentID){
        boolean flag=false;
        for(Student i:students){
            if(i.getStudentID()==studentID){
                students.remove(i);
                flag=true;
            }
        }
        if(flag)
            System.out.println("Student removed successfully!");
        else
            System.out.println("Student was not found");
    }
    public void updateStudent(int studentID,String fullName, int age, String gender, String department, float gpa){
        Student i=searchStudent(studentID);
        if (i != null) {
            i.setFullName(fullName);
            i.setAge(age);
            i.setGender(gender);
            i.setDepartment(department);
            i.setGPA(gpa);
            System.out.println("Student removed successfully!");
        }
        else
            System.out.println("Student was not found");
    }
    public void loadFiles(){
         students = new ArrayList<>();
    try {
        File file = new File("students.txt");
        if (!file.exists()) return;
        Scanner r = new Scanner(file);
        while (r.hasNextLine()) {
            String[] data = r.nextLine().split(","); //puit the data in array line by line
            int id = Integer.parseInt(data[0]);
            String name = data[1];
            int age = Integer.parseInt(data[2]);
            String gender = data[3];
            String dept = data[4];
            float gpa = Float.parseFloat(data[5]);
            Student s = new Student(id, name, age, gender, dept, gpa); // we creat e the stuednt
            students.add(s);
            nextID = id + 1;// preparing for add student
        }
        r.close();
    } catch (IOException e) {
        System.out.println("Can't load the file.");
    }
    }
    public void saveFiles(){
         try {
        FileWriter w = new FileWriter("students.txt");
        for (Student s : students) {
            w.write(s.getStudentID() + "," + s.getFullName() + "," + s.getAge() + "," +
                         s.getGender() + "," + s.getDepartment() + "," + s.getGPA() + "\n");
        }
        w.close();
    } 
         catch (IOException e) {
        System.out.println("Eroor occured while saving data!!");
    }
    }
    
    
}
