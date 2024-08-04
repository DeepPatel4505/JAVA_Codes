
import java.util.Scanner;

class Course {
    private int courseId;
    private String courseName;
    private int credit;

    public Course(int courseId, String courseName, int credit) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public int getCredit() {
        return this.credit;
    }

    public String toString() {
        return  courseId +" "+ courseName +" "+ credit;
    }
}

class Enrollment {
    // Taking maximum 100 students and max 7 courses
    private int[][] studentCourses = new int[100][7];
    private int[] count = new int[100];

    public void enroll(int studentId, int courseId) {
        if (count[studentId] > 7) {
            // if no_of_courses excced 7
            System.out.println("Max number of courses ENROLLED!!");
        } else {
            // if in limit
            int no_Courses = count[studentId];
            studentCourses[studentId][no_Courses] = courseId;
            count[studentId]++;
        }
    }

    public void drop(int studentId, int courseId) {
        // search loc of course
        int del_Index = 0;
        int i;
        for (i = 0; i < count[studentId]; i++) {
            if (studentCourses[studentId][i] == courseId) {
                del_Index = i;
                break;
            }
        }
        // if no data found
        if (i == count[studentId]) {
            System.out.println("No such course found...");
            return;
        }

        // setting courseid to 0
        studentCourses[studentId][del_Index] = 0;

        // deleting and bringing rest of courses one step forward
        for (i = del_Index; i < count[studentId] - 1; i++) {
            studentCourses[studentId][i] = studentCourses[studentId][i + 1];
        }

        studentCourses[studentId][count[studentId] - 1] = 0; // clear the last course
        count[studentId]--;

    }

    public void getEnrolledCourses(int studentId, Course catelog[]) {
        System.out.println("Courses enrolled by " + studentId + " :");
        System.out.print("id--Name--Credit\n");
        for (int i = 0; i < count[studentId]; i++) {
            int courseId = studentCourses[studentId][i];
            for (int j = 0; j < catelog.length; j++) {
                if (catelog[j].getCourseId() == courseId) {
                    System.out.println(catelog[j]);
                }
            }
        }
    }
}

public class CourseEnrollment {

    public static void printAvailCourses(Course cate[]) {
        System.out.println("\nAvailable Courses : ");
        System.out.print("id--Name--Credit\n");
        for (int i = 0; i < cate.length; i++) {
            System.out.println(cate[i]);
        }
    }

    public static void main(String[] args) {

        // pre-defined as nothing was mentioned
        Course[] courseCatelog = {
                new Course(111, "Maths", 4),
                new Course(112, "Java", 3),
                new Course(113, "DSA", 3),
                new Course(114, "COA", 3),
                new Course(115, "HS", 2),
                new Course(116, "SGP", 2),
                new Course(117, "UI/UX", 1)
        };

        Enrollment enrollment = new Enrollment();

        Scanner scanner = new Scanner(System.in);
        boolean controller = true;

        do {
            System.out.println("\t\tCourse Management");
            System.out.println("1. Enroll in Course");
            System.out.println("2. Drop Course");
            System.out.println("3. View Enrolled Courses");
            System.out.println("4. Exit");
            System.out.print("Option :");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    printAvailCourses(courseCatelog);
                    System.out.print("Enter student ID :");
                    int studId = scanner.nextInt();
                    System.out.print("Enter course ID :");
                    int courseId = scanner.nextInt();
                    enrollment.enroll(studId, courseId);
                    break;
                case 2:
                    System.out.print("Enter student ID :");
                    studId = scanner.nextInt();
                    System.out.print("Enter course ID :");
                    courseId = scanner.nextInt();
                    enrollment.drop(studId, courseId);
                    break;
                case 3:
                    System.out.print("Enter student ID :");
                    studId = scanner.nextInt();
                    enrollment.getEnrolledCourses(studId, courseCatelog);
                    break;
                case 4:
                    controller = false;
                    break;
                default:
                    break;
            }
        } while (controller);
    }
}
