package StudentManagementSystem;

public class ValidationHelper {

    public static boolean isValidName(String s){
        if(s==null || s.isEmpty()) return false;
        for(char c: s.toCharArray()){
            if(!Character.isLetter(c) && c!=' ') return false;
        }
        return true;
    }

    public static boolean isValidDepartment(String s){
        return isValidName(s);
    }

    public static String validateStudent(String name, String ageStr, String dept, String gpaStr){
        if(name.isEmpty() || ageStr.isEmpty() || dept.isEmpty() || gpaStr.isEmpty())
            return "Please fill all fields.";

        if(!isValidName(name)) return "Name can only contain letters and spaces";
        if(!isValidDepartment(dept)) return "Department can only contain letters and spaces";

        try{
            int age = Integer.parseInt(ageStr);
            if(age<1 || age>150) return "Age must be between 1 and 150";
        } catch(NumberFormatException e){ return "Age must be an integer"; }

        try{
            double gpa = Double.parseDouble(gpaStr);
            if(gpa<0 || gpa>4) return "GPA must be between 0.0 and 4.0";
        } catch(NumberFormatException e){ return "GPA must be numeric"; }

        return null;
    }
}
