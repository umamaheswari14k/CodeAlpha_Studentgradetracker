public class Student {

    private int rollNo;
    private String name;

    private int javaMark;
    private int pythonMark;
    private int dbmsMark;
    private int webTechMark;

    public Student(int rollNo, String name,
                   int javaMark,
                   int pythonMark,
                   int dbmsMark,
                   int webTechMark) {

        this.rollNo = rollNo;
        this.name = name;
        this.javaMark = javaMark;
        this.pythonMark = pythonMark;
        this.dbmsMark = dbmsMark;
        this.webTechMark = webTechMark;
    }

    // Getters

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public int getJavaMark() {
        return javaMark;
    }

    public int getPythonMark() {
        return pythonMark;
    }

    public int getDbmsMark() {
        return dbmsMark;
    }

    public int getWebTechMark() {
        return webTechMark;
    }

    // Update Marks

    public void setMarks(int javaMark,
                         int pythonMark,
                         int dbmsMark,
                         int webTechMark) {

        this.javaMark = javaMark;
        this.pythonMark = pythonMark;
        this.dbmsMark = dbmsMark;
        this.webTechMark = webTechMark;
    }

    // Total

    public int getTotal() {

        return javaMark +
               pythonMark +
               dbmsMark +
               webTechMark;
    }

    // Average

    public double getAverage() {

        return getTotal() / 4.0;
    }

    // Grade

    public String getGrade() {

        double avg = getAverage();

        if (avg >= 90)
            return "A+";

        else if (avg >= 80)
            return "A";

        else if (avg >= 70)
            return "B";

        else if (avg >= 60)
            return "C";

        else if (avg >= 50)
            return "D";

        else
            return "F";
    }

    // Pass / Fail

    public String getStatus() {

        if (getAverage() >= 50)
            return "PASS";
        else
            return "FAIL";
    }
}