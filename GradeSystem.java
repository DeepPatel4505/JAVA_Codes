import java.util.Scanner;

class Student {
    private int studentId;
    private String name;

    public Student(int id, String name) {
        this.studentId = id;
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }
}

class Grade {
    private int studentId;
    private int courseId;
    private char grade;

    public Grade(int studentId, int courseId, char grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public char getGrade() {
        return grade;
    }
}

class GradingSystem {
    private Student[] students;
    private Grade[] grades;
    private int[] courseCredits;
    private int studentCount;
    private int gradeCount;

    public GradingSystem(int maxStudents, int maxGrades, int courseCapacity) {
        students = new Student[maxStudents];
        grades = new Grade[maxGrades];
        courseCredits = new int[courseCapacity];
        studentCount = 0;
        gradeCount = 0;
    }

    public void addStudent(Student student) {
        students[studentCount] = student;
        studentCount++;
    }

    public void addGrade(Grade grade) {
        grades[gradeCount] = grade;
        gradeCount++;
    }

    public void addCourseCredits(int courseID, int credits) {
        if (courseID < courseCredits.length) {
            courseCredits[courseID] = credits;
        } else {
            System.out.println("Invalid course ID.");
        }
    }

    private int gradeToPoints(char grade) {
        switch (grade) {
            case 'A': return 4;
            case 'B': return 3;
            case 'C': return 2;
            case 'D': return 1;
            case 'F': return 0;
            default: return 0;
        }
    }

    public double calculateGPA(int studentId) {
        int totalPoints = 0;
        int totalCredits = 0;
        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentId() == studentId) {
                int courseId = grades[i].getCourseId();
                int credits = courseCredits[courseId];
                totalCredits += credits;
                totalPoints += gradeToPoints(grades[i].getGrade()) * credits;
            }
        }

        if(totalPoints>0)
        {
            return (double) totalPoints/totalCredits;
        }
        else{
            return 0;
        }
    }

    

    public void printGradeReport(int studentId) {
        System.out.println("Grade Report for Student ID: " + studentId);
        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentId() == studentId) {
                System.out.println("Course ID: " + grades[i].getCourseId() + ", Grade: " + grades[i].getGrade());
            }
        }
        double gpa = calculateGPA(studentId);
        System.out.println("GPA: " + gpa);
    }
}

public class GradeSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GradingSystem gradingSystem = new GradingSystem(100, 100, 100);
        String name;
        int id, courseId;
        char grade;
        int option;

        boolean controller = true;

        gradingSystem.addCourseCredits(1, 3);
        gradingSystem.addCourseCredits(2, 2);
        gradingSystem.addCourseCredits(3, 2);
        gradingSystem.addCourseCredits(4, 4);
        gradingSystem.addCourseCredits(5, 1);

        do{
            System.out.println("\n\n\t\tGrading System");
            System.out.println("1 : Add Student");
            System.out.println("2 : Add Grade");
            System.out.println("3 : Print Grade Report");
            System.out.println("4 : Exit");
            System.out.print("Option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter student ID (0-99): ");
                    id = scanner.nextInt();
                    System.out.print("Enter student name: ");
                    name = scanner.next();
                    Student student = new Student(id, name);
                    gradingSystem.addStudent(student);
                    break;
                case 2:
                    System.out.print("Enter student ID (0-99): ");
                    id = scanner.nextInt();
                    System.out.print("Enter course ID (0-99): ");
                    courseId = scanner.nextInt();
                    System.out.print("Enter grade (A-F): ");
                    grade = scanner.next().charAt(0);
                    Grade newGrade = new Grade(id, courseId, grade);
                    gradingSystem.addGrade(newGrade);
                    break;
                case 3:
                    System.out.print("Enter student ID (0-99): ");
                    id = scanner.nextInt();
                    gradingSystem.printGradeReport(id);
                    break;
                case 4:
                    controller = false;
                    default:
                    System.out.println("Invalid option. Please try again.");
                }
            }while(controller);
            scanner.close();
    }
}