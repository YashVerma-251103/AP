package Assignment1;

import java.util.*;

public class Professor extends User {
    /*
     * Required Functionalities
     * manage courses
     * view enrolled students
     */

    Scanner sc = new Scanner(System.in);

    // Personal Information
    private String name;

    // Attributes
    private Integer professor_id;
    private String office_hours; // add timings, days, and place.

    // Storing and Shared Datas -- may need to change it to protected
    private static HashMap<Integer, Professor> Professors = new HashMap<Integer, Professor>();
    // private HashMap<String, Course> currently_assigned_courses = new
    // HashMap<String, Course>();
    private ArrayList<Course> currently_assigned_courses = new ArrayList<Course>();

    // Creating Professor
    static Professor create_professor() { // made - test left
        Professor professor = new Professor();
        professor.set_details();
        if (Professors.containsKey(professor.professor_id)) {
            System.out.println("Professor already exists");
            return null;
        }
        Professors.put(professor.professor_id, professor);
        System.out.println("Professor created successfully");
        return professor;
    }

    void set_details() { // made - test left
        // Personal Information
        System.out.print("Enter Professor's name: ");
        this.name = sc.nextLine();
        System.out.print("Enter Professor's office hours: ");
        this.office_hours = sc.nextLine();
        System.out.println("Enter Professor's ID: ");
        this.professor_id = sc.nextInt();

        // Login Information
        System.out.println("Enter Professor's email: ");
        this.set_email(sc.nextLine());
        System.out.println("Enter Professor's password: ");
        this.set_password(sc.nextLine());
    }

    // Getters and Setters
    String get_name() {
        return name;
    }

    void set_name(String name) {
        this.name = name;
    }

    // functions that will help me to implement everything easily
    /*
     * functions that i need to implement
     * 1. he should be able to view the enrolled students
     * 2. he should be able to assign grades to the students
     * 3. he should be able to update the following details of the course
     * - syllabus
     * - credits
     * - enrollment limit
     * - prerequisites
     * - class timings
     * 4. he should be able to modify his office hours.
     */
    void update_office_timings() { // made - test left
        System.out.println("Enter new office hours: ");
        this.office_hours = sc.nextLine();
    }

    void view_currently_assigned_courses() { // made - test left
        for (Course course : currently_assigned_courses) {
            System.out.println(course.get_code() + " : " + course.get_title());
        }
        System.out.println();
    }

    // Required Functionalities
    void manage_courses() {
        // Only read and update course
        // addition and deletion of course is handled by admin
        while (true) {
            System.out.println("Enter -1 to return to previous menu");
            System.out.println(
                    "1. View currently assigned courses (press 1)\n2. See course details (press 2)\n3. Uppdate course details (press 3)\n4. Update office timings (Press 4)");
            Integer choice = sc.nextInt();
            if (choice == -1) {
                return;
            } else if (choice == 1) { // made - test left
                this.view_currently_assigned_courses();
            } else if (choice == 2) {
                // this.view_course_details();
            } else if (choice == 3) {
                Course course_to_update = null;
                while (true) {
                    System.out.println("Enter the course code for updation: ");
                    String updating_course_code = sc.nextLine();
                    for (Course course : currently_assigned_courses) {
                        if (course.get_code().equals(updating_course_code)) {
                            course_to_update = course;
                            break;
                        }
                    }
                    if (course_to_update==null) {
                        System.out.println("Course not found");
                        break;
                    } else {
                        Course.update_course_details(course_to_update);
                        break;
                    }
                }

            } else if (choice == 4) { // made - test left
                this.update_office_timings();
            } else {
                System.out.println("Invalid Input");
            }
        }
    }

    void view_enrolled_students() {
    }
}