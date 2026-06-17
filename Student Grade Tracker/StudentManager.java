import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentManager {

    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    // Add Student

    public void addStudent(Student student) {
        students.add(student);
    }

    // Get All Students

    public ArrayList<Student> getStudents() {
        return students;
    }

    // Search Student

    public Student searchStudent(int rollNo) {

        for(Student s : students) {

            if(s.getRollNo() == rollNo) {
                return s;
            }
        }

        return null;
    }

    // Delete Student

    public boolean deleteStudent(int rollNo) {

        Student student = searchStudent(rollNo);

        if(student != null) {

            students.remove(student);
            return true;
        }

        return false;
    }

    // Update Student

    public boolean updateStudent(int rollNo,
                                 int javaMark,
                                 int pythonMark,
                                 int dbmsMark,
                                 int webTechMark) {

        Student student = searchStudent(rollNo);

        if(student != null) {

            student.setMarks(
                    javaMark,
                    pythonMark,
                    dbmsMark,
                    webTechMark
            );

            return true;
        }

        return false;
    }

    // Total Students

    public int getTotalStudents() {

        return students.size();
    }

    // Class Average

    public double getClassAverage() {

        if(students.isEmpty())
            return 0;

        double total = 0;

        for(Student s : students) {

            total += s.getAverage();
        }

        return total / students.size();
    }

    // Highest Scorer

    public Student getHighestScorer() {

        if(students.isEmpty())
            return null;

        Student highest = students.get(0);

        for(Student s : students) {

            if(s.getAverage() >
                    highest.getAverage()) {

                highest = s;
            }
        }

        return highest;
    }

    // Lowest Scorer

    public Student getLowestScorer() {

        if(students.isEmpty())
            return null;

        Student lowest = students.get(0);

        for(Student s : students) {

            if(s.getAverage() <
                    lowest.getAverage()) {

                lowest = s;
            }
        }

        return lowest;
    }

    // Ranking

    public ArrayList<Student> getRankList() {

        ArrayList<Student> rankList =
                new ArrayList<>(students);

        Collections.sort(
                rankList,
                Comparator.comparingDouble(
                        Student::getAverage
                ).reversed()
        );

        return rankList;
    }
}