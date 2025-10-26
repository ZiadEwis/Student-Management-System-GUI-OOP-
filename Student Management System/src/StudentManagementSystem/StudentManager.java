package StudentManagementSystem;

import java.io.*;
import java.util.*;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private File dataFile;

    public StudentManager(String filename) {
        this.dataFile = new File(filename);
        loadFiles();
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public void addStudent(Student student){
        students.add(student);
        saveFiles();
    }

    public void deleteStudent(int studentID){
        boolean removed = students.removeIf(s -> s.getStudentID() == studentID);
        if(removed) saveFiles();
    }

    public void updateStudent(Student updated){
        for (int i=0;i<students.size();i++){
            if(students.get(i).getStudentID()==updated.getStudentID()){
                students.set(i, updated);
                saveFiles();
                return;
            }
        }
    }

    public Student searchStudent(int studentID){
        return students.stream().filter(s -> s.getStudentID() == studentID).findFirst().orElse(null);
    }

    public List<Student> searchByName(String query){
        query = query.toLowerCase();
        List<Student> result = new ArrayList<>();
        for(Student s: students){
            if(s.getFullName().toLowerCase().contains(query)) result.add(s);
        }
        return result;
    }

    public int generateUniqueID(){
        return students.stream().mapToInt(Student::getStudentID).max().orElse(9000) + 1;
    }

    public void loadFiles(){
        students.clear();
        try {
            if(!dataFile.exists()) return;
            Scanner r = new Scanner(dataFile);
            while(r.hasNextLine()){
                String[] d = r.nextLine().split(",");
                if(d.length<6) continue;
                int id = Integer.parseInt(d[0]);
                String name = d[1];
                int age = Integer.parseInt(d[2]);
                String gender = d[3];
                String dept = d[4];
                double gpa = Double.parseDouble(d[5]);
                students.add(new Student(id,name,age,gender,dept,gpa));
            }
            r.close();
        } catch(IOException e){
            System.out.println("Can't load file: "+e.getMessage());
        }
    }

    public void saveFiles(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))){
            for(Student s: students){
                bw.write(s.getStudentID()+","+s.getFullName()+","+s.getAge()+","+
                        s.getGender()+","+s.getDepartment()+","+s.getGPA());
                bw.newLine();
            }
        } catch(IOException e){
            System.out.println("Error saving file: "+e.getMessage());
        }
    }
}
