
import java.util.Scanner;

class Student {

    private int StudentId;
    private int age;
    private String name;
    private String dept;

    public Student(int StudentId, String name, int age, String dept) {
        this.StudentId = StudentId;
        this.age = age;
        this.name = name;
        this.dept = dept;
    }

    public String getName() {
        return this.name;
    }

    public String getDept() {
        return this.dept;
    }

    public int getStudentId() {
        return this.StudentId;
    }

    public int getAge() {
        return this.age;
    }

    public String toString() {
        return "\nStudent Id :" + StudentId +
                "\nName       :" + name +
                "\nAge        :" + age +
                "\nDept       :" + dept;
    }
}

class StudentRecordSystem {
    private Student[] student = new Student[100];
    private int count;

    public StudentRecordSystem() {
        count = 0;
    }

    public void addStudent(Student s) {
        student[count] = s;
        count++;
    }

    public void displayAllStudents() {
        if(count == 0)
        {
            System.out.println("No data.Try adding data first...");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(student[i]);
        }
    }

    public Student getStudent(int id) {
        int i;
        for (i = 0; i < count; i++) {
            if (student[i].getStudentId() == id) {
                return student[i];
            }
        }
        return null;
    }

}

public class StudentRecordMGMT {

    public static void main(String[] args) {
        StudentRecordSystem system = new StudentRecordSystem();
        Scanner scanner = new Scanner(System.in);

        char controller = 'y';

        do {
            System.out.println("\n\n\t\tStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Student by Id");
            System.out.println("3. Display All Students");
            System.out.println("4. Exit");
            System.out.print("Option :");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.print("\n\nEnter Student Id : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name :");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age : ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Dept :");
                    String dept = scanner.nextLine();
                    Student s = new Student(id, name, age, dept);
                    system.addStudent(s);
                    break;
                case 2:
                    System.out.print("Enter Id : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    s = system.getStudent(id);
                    if(s == null)
                    {
                        System.out.println("No data found....");
                    }
                    else{
                        System.out.println(s);
                    }
                    break;
                case 3:
                    system.displayAllStudents();
                    break;
                case 4:
                    controller = 'n';
                    break;
                default:
                    System.out.println("\n\n!!Invalid Option");
            }
        } while (controller == 'y');
        scanner.close();
    }
}