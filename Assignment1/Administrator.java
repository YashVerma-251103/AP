package Assignment1;

import java.util.*;

public class Administrator extends User {
    /*
     * Required Functionalities
     * manage course catalog
     * manage student records
     * assign professors to courses
     * handle complaints
     */
    static Scanner sc = new Scanner(System.in);

    // Personal Information
    private String name;

    // Attributes
    private Integer admin_id;

    // Storing and Shared Datas -- may need to change it to protected
    private static HashMap<Integer, Administrator> Administrators = new HashMap<Integer, Administrator>();

    // Functionalities that i may require to implement everything easily

    // Required Functionalities
    void manage_course_catalog() { // made - test left
        // view, add, and delete courses
        while (true) {
            System.out.println("Enter -1 to return to the previous menu");
            System.out.println("1. View Courses (press 1)\n2. Add Course (press 2)\n3. Delete Course (press 3)");
            Integer choice = sc.nextInt();
            if (choice == -1) {
                return;
            } else if (choice == 1) { // made - test left
                System.out.println("Course Catalog:");
                Course.course_bank.forEach((key, value) -> {
                    System.out.println("Course Code : " + key + " | Course Title : " + value.get_title());
                });
                System.out.println("Wish to view the details of any particular course?\nIf yes, enter the course code. Else, enter -1");
                String course_code_to_view = sc.next();
                if (course_code_to_view.equals("-1")) {
                    continue;
                } else {
                    Course course = Course.course_bank.get(course_code_to_view);
                    System.out.println("Course Code : " + course.get_code());
                    System.out.println("Course Title : " + course.get_title());
                    System.out.println("Course Professor : " + course.get_Professor().get_name());
                    System.out.println("Course Syllabus : " + course.get_syllabus());
                    System.out.println("Course Credits : " + course.get_credits());
                    System.out.println("Course Enrollment Limit : " + course.get_enrollment_limit());
                    System.out.println("Course Enrollment Count : " + course.get_enrollment_count());
                    // System.out.println("Course Timings : "+course.timings); // need to make this.
                    System.out.println("Course Prerequisites : ");
                    course.prerequisites.forEach((prerequisite) -> {
                        System.out.println("Course Code : " + prerequisite.get_code() + " | Course Title : "
                                + prerequisite.get_title());
                    });
                }
            } else if (choice == 2) { // made - test left
                // System.out.println("Enter the Professor ID for to assign the new course to: ");
                // Integer prof_id = sc.nextInt();
                // Professor prof = Professor.Professors.get(prof_id);
                // if(prof == null){
                //     System.out.println("No Professor found with the given ID");
                //     continue;
                // } else{
                //     Course newly_created_course=Course.create_course(prof);
                // }
                Professor prof = assign_professors_to_courses();
                if (prof == null) {
                    continue;
                } else {
                    Course newly_created_course = Course.create_course(prof);
                }

            } else if (choice == 3){ // made - test left
                System.out.println("Enter the course code to delete the course: ");
                String course_code_to_delete = sc.next();
                Course course = Course.course_bank.get(course_code_to_delete);
                if(course == null){
                    System.out.println("No course found with the given code");
                    continue;
                } else{
                    Course.delete_course(course);
                }
            } else{
                System.out.println("Invalid Input");
            }

        }
    }
    static Professor assign_professors_to_courses() { // made - test left
        // assign professors to courses
        System.out.println("Enter Professor expertise (Mention Department): ");
        String expertise = sc.next();
        expertise = expertise.toLowerCase();
        ArrayList<Professor> profs = Professor.expertise_based_list_of_profs(expertise);
        if(profs.size() == 0){
            System.out.println("No Professor found with the given expertise");
        } else {
            System.out.println("Professors with the given expertise: ");
            for(Professor prof : profs){
                System.out.println("Professor ID : "+prof.get_prof_id()+" | Professor Name : "+prof.get_name());
            }
        }
        while (true){
            System.out.println("Enter -1 to return to the previous menu");
            System .out.println("Enter the Professor ID for to assign the new course to: ");
            Integer prof_id = sc.nextInt();
            if (prof_id == -1){
                return null;
            }
            Professor prof = Professor.Professors.get(prof_id);
            if(prof == null){
                System.out.println("No Professor found with the given ID");
            } else{
                return prof;
            }
        }
    }
    void manage_student_records() {
        // view and update student records, grades, and personal information
        Student.show_all_students();
        while (true) {
            System.out.println("Enter -1 to return to the previous menu");
            System.out.println("Enter the Student Roll Number to view the details of the student: ");
            Integer student_roll_number = sc.nextInt();
            if (student_roll_number == -1) {
                return;
            }
            else{
                Integer student_found = Student.show_student_details(student_roll_number);
                if(student_found == 0){
                    continue;
                }
                System.out.println("Do you want to update the student details? (Y/N)");
                String choice = sc.next();
                if (choice.equals("Y") || choice.equals("y")) {
                    // System.out.println("Enter the new Student Name: ");
                    // String new_name = sc.next();
                    // student.set_name(new_name);
                    // System.out.println("Enter the new Student Email: ");
                    // String new_email = sc.next();
                    // student.set_email(new_email);
                    // System.out.println("Enter the new Student Department: ");
                    // String new_department = sc.next();
                    // student.set_department(new_department);
                    // System.out.println("Enter the new Student Semester: ");
                    // Integer new_semester = sc.nextInt();
                    // student.set_semester(new_semester);
                    // System.out.println("Enter the new Student CGPA: ");
                    // Double new_cgpa = sc.nextDouble();
                    // student.set_cgpa(new_cgpa);
                    System.out.println("Enter -1 to return to previous menu!\nWhat do you want to update?\n1. Name (press 1)\n2. Email (press 2)\n3. Roll Number (press 3)\n4. Current Semester (press 4)\n5. Grade (press 5)\n6. SPGAs (press 6)\n7. CGPA (press 7)\n8. Current Courses (press 8)");
                    Integer update_choice = sc.nextInt();
                    if(update_choice == -1){
                        return;
                    } else{
                        Student student = Student.Students.get(student_roll_number);
                        if(update_choice == 1){
                            System.out.println("Enter the new Student Name: ");
                            String new_name = sc.next();
                            student.set_name(new_name);
                        } else if(update_choice == 2){
                            System.out.println("Enter the new Student Email: ");
                            String new_email = sc.next();
                            student.set_email(new_email);
                        } else if(update_choice == 3){
                            System.out.println("Enter the new Student Roll Number: ");
                            Integer new_roll_number = sc.nextInt();
                            student.set_roll_number(new_roll_number);
                        } else if(update_choice == 4){
                            System.out.println("Enter the new Student Semester: ");
                            Integer new_semester = sc.nextInt();
                            student.set_current_semester(new_semester);
                        } else if(update_choice == 5){
                            System.out.println("Enter the new Student Grade: ");
                            Integer new_grade = sc.nextInt();
                            student.set_grade(new_grade);
                        } else if(update_choice == 6){
                            System.out.println("Enter the Semester for which you want to update the SGPA: ");
                            Integer sem = sc.nextInt();
                            System.out.println("Enter the new Student SGPA: ");
                            Float new_sgpa = sc.nextFloat();
                            student.set_sgpa(sem,new_sgpa);
                            student.calculate_cgpa();
                        } else if(update_choice == 7){
                            System.out.println("Enter the new Student CGPA: ");
                            Float new_cgpa = sc.nextFloat();
                            student.set_cgpa(new_cgpa);
                        } else if(update_choice == 8){
                            System.out.println("Enter the Course Code for which you want to update the grade: ");
                            String course_code = sc.next();
                            

                        } else{
                            System.out.println("Invalid Input");
                        }
                    }
                } else {
                    continue;
                }
    }
}
}


    void handle_complaints() { // Will do it later
        // view and respond to complaints
        // status of complaints should be updated as well
        // Pending
        // Resolved
        // Add filter to view the complaints based on the status or date
        // provide resolution for the complaints

        // when assign grades move the course from current_courses to completed_courses
        // when making a new course and student keep in mind the null condition.
    }
}
