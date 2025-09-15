//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {//TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        universitySystem uni = new universitySystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("--- University Course Registration System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Add Course");
            System.out.println("4. Remove Course");
            System.out.println("5. Enroll Student in Course");
            System.out.println("6. Remove Student from Course");
            System.out.println("7. Undo Last Action");
            System.out.println("8. Redo Last Action");
            System.out.println("9. Print Students in a Course");
            System.out.println("10. Print Courses of a Student");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID to add: ");
                    int studentIdAdd = scanner.nextInt();
                    uni.addStudent(studentIdAdd);
                    break;
                case 2:
                    System.out.print("Enter Student ID to remove: ");
                    int studentIdRemove = scanner.nextInt();
                    uni.removeStudent(studentIdRemove);
                    break;
                case 3:
                    System.out.print("Enter Course ID to add: ");
                    int courseIdAdd = scanner.nextInt();
                    uni.addCourse(courseIdAdd);
                    break;
                case 4:
                    System.out.print("Enter Course ID to remove: ");
                    int courseIdRemove = scanner.nextInt();
                    uni.removeCourse(courseIdRemove);
                    break;
                case 5:
                    System.out.print("Enter Student ID to enroll: ");
                    int studentIdEnroll = scanner.nextInt();
                    System.out.print("Enter Course ID to enroll in: ");
                    int courseIdEnroll = scanner.nextInt();
                    uni.enrollstudentincourse(studentIdEnroll, courseIdEnroll);
                    break;
                case 6:
                    System.out.print("Enter Student ID to remove from course: ");
                    int studentIdUnenroll = scanner.nextInt();
                    System.out.print("Enter Course ID to remove enrollment from: ");
                    int courseIdUnenroll = scanner.nextInt();
                    uni.removestudentfromcourse(studentIdUnenroll, courseIdUnenroll);
                    break;
                case 7:
                    uni.undo();
                    break;
                case 8:
                    uni.redo();
                    break;
                case 9:
                    System.out.print("Enter Course ID to list students: ");
                    int courseIdPrint = scanner.nextInt();
                    uni.PrintStudentsinCourse(courseIdPrint);
                    break;
                case 10:
                    System.out.print("Enter Student ID to list courses: ");
                    int studentIdPrint = scanner.nextInt();
                    uni.printCoursesOfStudent(studentIdPrint);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    }



