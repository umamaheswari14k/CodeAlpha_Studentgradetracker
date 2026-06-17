import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentGradeTrackerGUI extends JFrame {

    private JTextField rollField, nameField, javaField, pythonField, dbmsField, webField;
    private JButton addButton, searchButton, updateButton, deleteButton,
            statisticsButton, rankingButton, clearButton;

    private JTable table;
    private DefaultTableModel model;

    private StudentManager manager;

    public StudentGradeTrackerGUI() {

        manager = new StudentManager();

        setTitle("Student Grade Tracker Pro");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        inputPanel.add(new JLabel("Roll Number"));
        rollField = new JTextField();
        inputPanel.add(rollField);

        inputPanel.add(new JLabel("Student Name"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Java Marks"));
        javaField = new JTextField();
        inputPanel.add(javaField);

        inputPanel.add(new JLabel("Python Marks"));
        pythonField = new JTextField();
        inputPanel.add(pythonField);

        inputPanel.add(new JLabel("DBMS Marks"));
        dbmsField = new JTextField();
        inputPanel.add(dbmsField);

        inputPanel.add(new JLabel("Web Technology Marks"));
        webField = new JTextField();
        inputPanel.add(webField);

        add(inputPanel, BorderLayout.NORTH);

        String[] columns = {
                "Roll No", "Name", "Java", "Python", "DBMS",
                "Web Tech", "Total", "Average", "Grade", "Status"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Add Student");
        searchButton = new JButton("Search");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        statisticsButton = new JButton("Statistics");
        rankingButton = new JButton("Ranking");
        clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(statisticsButton);
        buttonPanel.add(rankingButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addStudent());
        searchButton.addActionListener(e -> searchStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        statisticsButton.addActionListener(e -> showStatistics());
        rankingButton.addActionListener(e -> showRanking());
        clearButton.addActionListener(e -> clearFields());

        setVisible(true);
    }

    private void addStudent() {
        try {
            int rollNo = Integer.parseInt(rollField.getText());
            String name = nameField.getText();

            int javaMark = Integer.parseInt(javaField.getText());
            int pythonMark = Integer.parseInt(pythonField.getText());
            int dbmsMark = Integer.parseInt(dbmsField.getText());
            int webMark = Integer.parseInt(webField.getText());

            Student student = new Student(
                    rollNo, name, javaMark, pythonMark, dbmsMark, webMark);

            manager.addStudent(student);

            refreshTable();
            clearFields();

            JOptionPane.showMessageDialog(this,
                    "Student Added Successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    private void searchStudent() {
        try {
            int rollNo = Integer.parseInt(rollField.getText());

            Student student = manager.searchStudent(rollNo);

            if (student != null) {
                JOptionPane.showMessageDialog(this,
                        "Name : " + student.getName()
                                + "\nAverage : " + String.format("%.2f", student.getAverage())
                                + "\nGrade : " + student.getGrade()
                                + "\nStatus : " + student.getStatus());
            } else {
                JOptionPane.showMessageDialog(this, "Student Not Found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Enter Valid Roll Number!");
        }
    }

    private void updateStudent() {
        try {
            int rollNo = Integer.parseInt(rollField.getText());

            boolean updated = manager.updateStudent(
                    rollNo,
                    Integer.parseInt(javaField.getText()),
                    Integer.parseInt(pythonField.getText()),
                    Integer.parseInt(dbmsField.getText()),
                    Integer.parseInt(webField.getText())
            );

            if (updated) {
                refreshTable();
                JOptionPane.showMessageDialog(this,
                        "Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Student Not Found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Data!");
        }
    }

    private void deleteStudent() {
        try {
            int rollNo = Integer.parseInt(rollField.getText());

            boolean deleted = manager.deleteStudent(rollNo);

            if (deleted) {
                refreshTable();
                JOptionPane.showMessageDialog(this,
                        "Deleted Successfully!");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Student Not Found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Roll Number!");
        }
    }

    private void showStatistics() {

        Student highest = manager.getHighestScorer();
        Student lowest = manager.getLowestScorer();

        if (highest == null) {
            JOptionPane.showMessageDialog(this,
                    "No Students Available!");
            return;
        }

        String report =
                "Total Students : " + manager.getTotalStudents()
                        + "\n\nClass Average : "
                        + String.format("%.2f", manager.getClassAverage())
                        + "\n\nHighest Scorer : "
                        + highest.getName()
                        + "\nAverage : "
                        + String.format("%.2f", highest.getAverage())
                        + "\n\nLowest Scorer : "
                        + lowest.getName()
                        + "\nAverage : "
                        + String.format("%.2f", lowest.getAverage());

        JOptionPane.showMessageDialog(this, report);
    }

    private void showRanking() {

        StringBuilder ranks = new StringBuilder();
        int rank = 1;

        for (Student s : manager.getRankList()) {
            ranks.append(rank++)
                    .append(". ")
                    .append(s.getName())
                    .append(" - ")
                    .append(String.format("%.2f", s.getAverage()))
                    .append("\n");
        }

        JOptionPane.showMessageDialog(this, ranks.toString());
    }

    private void refreshTable() {

        model.setRowCount(0);

        for (Student s : manager.getStudents()) {

            model.addRow(new Object[]{
                    s.getRollNo(),
                    s.getName(),
                    s.getJavaMark(),
                    s.getPythonMark(),
                    s.getDbmsMark(),
                    s.getWebTechMark(),
                    s.getTotal(),
                    String.format("%.2f", s.getAverage()),
                    s.getGrade(),
                    s.getStatus()
            });
        }
    }

    private void clearFields() {
        rollField.setText("");
        nameField.setText("");
        javaField.setText("");
        pythonField.setText("");
        dbmsField.setText("");
        webField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGradeTrackerGUI::new);
    }
}
