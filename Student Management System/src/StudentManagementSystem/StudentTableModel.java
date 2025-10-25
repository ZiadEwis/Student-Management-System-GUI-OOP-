package StudentManagementSystem;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    private final String[] cols = {"ID","Full Name","Age","Gender","Department","GPA"};
    private List<Student> data;

    public StudentTableModel(List<Student> data) {
        this.data = data;
    }

    public void setData(List<Student> newData) {
        this.data = newData;
        fireTableDataChanged();
    }

    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int c) { return cols[c]; }

    @Override
    public Object getValueAt(int r, int c) {
        Student s = data.get(r);
        switch (c) {
            case 0: return s.getStudentID();
            case 1: return s.getFullName();
            case 2: return s.getAge();
            case 3: return s.getGender();
            case 4: return s.getDepartment();
            case 5: return s.getGPA();
        }
        return null;
    }

    public Student getStudentAt(int row) { return data.get(row); }
}

