//Create the Course Class
class Course {
    private String courseName;
    private String courseCode;

    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }
    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String toString() {
        return courseName + " (" + courseCode + ")";
    }
}

//Create the Student Class
import java.util.ArrayList;
import java.util.List;
class Student {
    private String studentName;
    private String studentID;
    private List<Course> registeredCourses;
    public Student(String studentName, String studentID) {
        this.studentName = studentName;
        this.studentID = studentID;
        this.registeredCourses = new ArrayList<>();
    }
    public String getStudentName() {
        return studentName;
    }
    public String getStudentID() {
        return studentID;
    }
    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
            System.out.println("Registered for course: " + course);
        } else {
            System.out.println("Already registered for this course.");
        }
    }


    public void deregisterCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            System.out.println("Deregistered from course: " + course);
        } else {
            System.out.println("You are not registered for this course.");
        }
    }
    public void viewRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered courses:");
            for (Course course : registeredCourses) {
                System.out.println("- " + course);
            }
        }
    }
}

//Create the CourseRegistrationSystem Class
import java.util.Scanner;
class CourseRegistrationSystem {
    private Student student;
    private List<Course> availableCourses;
    public CourseRegistrationSystem(Student student, List<Course> availableCourses) {
        this.student = student;
        this.availableCourses = availableCourses;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Course Registration System ---");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Deregister from a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewAvailableCourses();
                    break;
                case 2:
                    registerForCourse(scanner);
                    break;
                case 3:
                    deregisterFromCourse(scanner);
                    break;
                case 4:
                    student.viewRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAvailableCourses() {
        System.out.println("Available courses:");
        for (int i = 0; i < availableCourses.size(); i++) {
            System.out.println((i + 1) + ". " + availableCourses.get(i));
        }
    }

    private void registerForCourse(Scanner scanner) {
        viewAvailableCourses();
        System.out.print("Enter the course number to register: ");
        int courseNumber = scanner.nextInt();
        if (courseNumber > 0 && courseNumber <= availableCourses.size()) {
            student.registerCourse(availableCourses.get(courseNumber - 1));
        } else {
            System.out.println("Invalid course number.");
        }
    }

    private void deregisterFromCourse(Scanner scanner) {
        student.viewRegisteredCourses();
        System.out.print("Enter the course number to deregister: ");
        int courseNumber = scanner.nextInt();
        if (courseNumber > 0 && courseNumber <= student.registeredCourses.size()) {
            student.deregisterCourse(student.registeredCourses.get(courseNumber - 1));
        } else {
            System.out.println("Invalid course number.");
        }
    }
}

Create the Main Class
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Course> courses = Arrays.asList(
            new Course("Introduction to Programming", "CS101"),
            new Course("Data Structures", "CS102"),
            new Course("Operating Systems", "CS103"),
            new Course("Database Management", "CS104"),
            new Course("Software Engineering", "CS105")
        );
        Student student = new Student("John Doe", "S12345");
        CourseRegistrationSystem crs = new CourseRegistrationSystem(student, courses);
        crs.start();
    }
}

