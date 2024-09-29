package Assignment2;

import java.util.HashMap;
import java.util.Scanner;

public class Student extends CommonUser { // Left

    public static Scanner student_sc = new Scanner(System.in);

    // Personal Information
    private String name;
    private Integer student_roll_number;

    // Attributes
    private static Integer credit_limit = 20;
    private Integer credits_registered = 0;
    private Integer current_semester = 1;
    private Float cgpa = 0.0f;

    // Storing and Sharing DataBase
    protected static HashMap<Integer, Student> student_db = new HashMap<Integer, Student>();
    protected HashMap<String, Course> current_courses = new HashMap<String, Course>();
    protected HashMap<String, Course> dropped_courses = new HashMap<String, Course>();
    protected HashMap<String, Pair<Course, Integer>> completed_courses = new HashMap<String, Pair<Course, Integer>>();
    private Float sgpas[] = new Float[8];
    protected HashMap<String, Pair<Course, Boolean>> current_courses_pass_check = new HashMap<String, Pair<Course, Boolean>>();

    // Getters
    public String get_name() {
        return name;
    }

    public Integer get_student_roll_number() {
        return student_roll_number;
    }

    public Integer get_current_semester() {
        return current_semester;
    }

    public Integer get_credits_registered() {
        return credits_registered;
    }

    public Integer get_credit_limit() {
        return credit_limit;
    }

    public Float get_cgpa() {
        return cgpa;
    }

    // Setters
    public void set_name(String name) {
        this.name = name;
    }

    public void set_student_roll_number(Integer student_roll_number) {
        this.student_roll_number = student_roll_number;
    }

    public void set_current_semester(Integer current_semester) {
        this.current_semester = current_semester;
    }

    public void set_cgpa(Float cgpa) {
        this.cgpa = cgpa;
    }

    public void set_sgpa(Integer sem, Float sgpa) {
        this.sgpas[sem - 1] = sgpa;
    }

    public void set_credits_registered(Integer credits_registered) {
        this.credits_registered = credits_registered;
    }

    // methods for easy implementations
    public void calculate_cgpa() {
        if (this.current_semester > 1) {
            Float cgpa = 0.0f;
            Integer sems = 0;
            for (int i = 0; i < (this.current_semester) - 1; i++) {
                if (this.sgpas[i] != null) {
                    cgpa += this.sgpas[i];
                    sems++;
                }
            }
            this.set_cgpa((Float) (cgpa / sems));
        } else {
            this.set_cgpa(0.0f);
        }
    }

    public void calculate_sgpa(int sem) {
        if (this.current_semester > 1) {
            Float sgpa = 0.0f;
            Integer courses = 0;
            for (Pair<Course, Integer> course_grade_pair : this.completed_courses.values()) {
                if (course_grade_pair.getFirst().get_offered_semester() == sem) {
                    sgpa += course_grade_pair.getSecond();
                    courses++;
                }
            }
            sgpa = (Float) (sgpa / courses);
            this.set_sgpa(sem, sgpa);
        } else {
            this.set_sgpa(sem, 0.0f);
        }
    }

    public void set_student() {
        System.out.println("Enter the following details: ");
        System.out.print("Enter the name of the student:");
        this.set_name(student_sc.nextLine());
        System.out.print("Enter the roll number of the student:");
        this.set_student_roll_number(Integer.parseInt(student_sc.nextLine()));

        // Login Info Setting
        System.out.print("Enter Student's email:");
        this.set_email(student_sc.nextLine());
        System.out.print("Enter Student's password:");
        this.set_password(student_sc.nextLine());

    }

    public void view_registered_courses() {
        if (this.current_courses.isEmpty()) {
            System.out.println("No courses registered.");
            return;
        }
        System.out.println("Registered Courses: ");
        for (Course course : this.current_courses.values()) {
            System.out
                    .println("Course ID : " + course.get_course_id() + " | Course Name : " + course.get_course_name());
        }
    }

    public void show_details() {
        System.out.println("Name: " + this.name);
        System.out.println("Roll Number: " + this.student_roll_number);
        System.out.println("Email: " + this.get_email());
        System.out.println("Current Semester: " + this.current_semester);
        System.out.println("CGPA: " + this.cgpa);
        System.out.println("Credits Registered for this semester: " + this.credits_registered);
    }

    public void show_completed_courses() {
        if (this.completed_courses.isEmpty()) {
            System.out.println("No courses completed.");
            return;
        }
        System.out.println("Completed Courses: ");
        for (Pair<Course, Integer> course_grade_pair : this.completed_courses.values()) {
            System.out.println("Course ID : " + course_grade_pair.getFirst().get_course_id() + " | Course Name : "
                    + course_grade_pair.getFirst().get_course_name() + " | Grade: " + course_grade_pair.getSecond());
        }
    }

    public void update_detials() {
        System.out.println("Enter -1 to return to previous menu!");
        System.out.println("What do you want to update?");
        System.out.println("1. Email (press 1)");
        System.out.println("2. Name (press 2)");
        System.out.println("3. Roll Number (press 3)");
        System.out.println("4. Current Semester (press 4)");
        System.out.println("5. Grades (press 5)");
        System.out.println("6. SPGAs (press 6)");
        System.out.println("7. CGPA (press 7)");
        System.out.print("Enter your choice: ");
        Integer update_choice = student_sc.nextInt();
        if (update_choice == -1) {
            return;
        } else {
            Student student = student_db.get(student_roll_number);
            if (update_choice == 1) {
                System.out.print("Enter the new Student Email: ");
                String new_email = student_sc.next();
                student.set_email(new_email);
            } else if (update_choice == 2) {
                System.out.print("Enter the new Student Name: ");
                student.set_name(student_sc.nextLine());
            } else if (update_choice == 3) {
                System.out.print("Enter the new Student Roll Number: ");
                Integer new_roll_number = student_sc.nextInt();
                student.set_student_roll_number(new_roll_number);
                student_db.remove(student_roll_number);
                student_db.put(new_roll_number, student);
                for (Course course : student.current_courses.values()) {
                    course.correct_student_roll_number(student_roll_number, new_roll_number);
                }
            } else if (update_choice == 4) {
                System.out.print("Enter the new Student Semester: ");
                Integer new_semester = student_sc.nextInt();
                student.set_current_semester(new_semester);
                for (Course course : student.current_courses.values()) {
                    course.drop_student(student);
                }
                student.current_courses.clear();
                student.credits_registered = 0;
            } else if (update_choice == 5) {
                student.show_completed_courses();
                System.out.print("Enter the course id to update grade: ");
                String course_id = student_sc.next();
                if (student.completed_courses.containsKey(course_id)) {
                    Pair<Course, Integer> course_grade_pair = student.completed_courses.get(course_id);
                    System.out.print("Enter the new grade: ");
                    Integer new_grade = student_sc.nextInt();
                    course_grade_pair.setSecond(new_grade);
                    student.calculate_sgpa(course_grade_pair.getFirst().get_offered_semester());
                    student.calculate_cgpa();
                } else {
                    System.out.println("Course not found.");
                }
            } else if (update_choice == 6) {
                System.out.print("Enter the Semester for which you want to update the SGPA: ");
                Integer sem = student_sc.nextInt();
                System.out.print("Enter the new Student SGPA: ");
                Float new_sgpa = student_sc.nextFloat();
                student.set_sgpa(sem, new_sgpa);
                student.calculate_cgpa();
            } else if (update_choice == 7) {
                System.out.print("Enter the new Student CGPA: ");
                Float new_cgpa = student_sc.nextFloat();
                student.set_cgpa(new_cgpa);
            } else {
                System.out.println("Invalid Input");
            }
        }
    }

    public boolean prereq_check(Course course_to_check) {
        if (course_to_check.get_prereq_List().isEmpty()) {
            return true;
        }
        for (Course prereqs : course_to_check.get_prereq_List()) {
            if (!this.completed_courses.containsKey(prereqs.get_course_id())) {
                return false;
            }
        }
        return true;
    }

    public static void changes_in_completed_courses(String old_course_id, String new_course_id) {
        for (Student student : student_db.values()) {
            if (student.completed_courses.containsKey(old_course_id)) {
                Pair<Course, Integer> course_grade_pair = student.completed_courses.get(old_course_id);
                student.completed_courses.remove(old_course_id);
                student.completed_courses.put(new_course_id, course_grade_pair);
            } else if (student.dropped_courses.containsKey(old_course_id)) {
                Course course = student.dropped_courses.get(old_course_id);
                student.dropped_courses.remove(old_course_id);
                student.dropped_courses.put(new_course_id, course);
            }
        }
    }

    public static Student create_student() {
        Student student = new Student();
        student.set_student();
        if (student_db.containsKey(student.student_roll_number)) {
            System.out.println("Student already exists.");
            return null;
        } else {
            student_db.put(student.student_roll_number, student);
            System.out.println("Student added successfully.");
        }
        return student;
    }

    public static void show_all_students() {
        System.out.println("Current Students : ");
        for (Student student : student_db.values()) {
            System.out.println("Name : " + student.name + " | Roll Number : " + student.student_roll_number);
        }
        System.out.println();
    }

    public void remove_student() {
        student_db.remove(this.student_roll_number);
        for (Course course : this.current_courses.values()) {
            if (course.get_course_professor().assigning_grades_to_student.containsKey(this.student_roll_number)) {
                course.get_course_professor().assigning_grades_to_student.remove(this.student_roll_number);
            }
            course.drop_student(this);
        }
        System.out.println("Student removed successfully.");
    }

    public void view_dropped_courses() {
        if (this.dropped_courses.isEmpty()) {
            System.out.println("No courses dropped.");
            return;
        }
        System.out.println("Dropped Courses: ");
        for (Course course : this.dropped_courses.values()) {
            System.out
                    .println("Course ID : " + course.get_course_id() + " | Course Name : " + course.get_course_name());
        }
    }

    // public void get_to_next_semester(Boolean printing) {
    //     if (this.current_courses.size() == this.current_courses_pass_check.size()) {
    //         this.current_semester++;
    //         this.current_courses.clear();
    //         this.current_courses_pass_check.clear();
    //         this.dropped_courses.clear();
    //         this.credits_registered = 0;
    //         this.calculate_sgpa(this.current_semester - 1);
    //         this.calculate_cgpa();
    //         if (printing) {
    //             System.out.println("Semester ended successfully.");
    //         }
    //         return;
    //     }
    //     if (printing) {
    //         System.out.println("Grade not assigned to all the courses.");
    //     }
    // }

    public static void pass_all_eligible_students() {
        for (Student student : student_db.values()) {
            if (student.current_courses.size() == student.current_courses_pass_check.size()) {
                // for (Pair<Course,Boolean> course_pass_check :
                // student.current_courses_pass_check.values()) {
                // if (course_pass_check.getSecond()) {
                // student.completed_courses.put(course_pass_check.getFirst().get_course_id(),
                // new Pair<Course,Integer>(course_pass_check.getFirst(),10));
                // } else {
                // student.dropped_courses.put(course_pass_check.getFirst().get_course_id(),
                // course_pass_check.getFirst());
                // }
                // }
                student.get_to_next_semester(false);
            }
        }
    }

    // Required functionalities
    public void view_available_courses() {
        System.out.println("Available Courses: ");
        Course.display_courses_by_semester(this.current_semester);
        System.out.print("Would you like to see details of any course? (y/n)");
        String choice = student_sc.next();
        if (choice.equals("y") || choice.equals("Y")) {
            System.out.print("Enter the course id: ");
            String course_id = student_sc.next();
            Course.display_course_details(course_id);
        }
        return;
    }

    // public void register_course() {
    //     if (this.credits_registered < credit_limit) {
    //         Course.display_courses_by_semester(this.current_semester);
    //         System.out.print("Enter the course id: ");
    //         String course_id = student_sc.next();
    //         if (Course.course_db.containsKey(course_id)) {
    //             Course course = Course.course_db.get(course_id);
    //             if (course.get_offered_semester() == this.current_semester) {
    //                 if (this.prereq_check(course)) {
    //                     course.enroll_student(this);
    //                 } else {
    //                     System.out.println("Requirements for the course not met!");
    //                 }
    // 
    //             } else {
    //                 System.out.println("Course not offered in this semester.");
    //             }
    //         } else {
    //             System.out.println("Course not found.");
    //         }
    //     } else {
    //         System.out.println("Credit limit reached.");
    //     }
    // }

    public void drop_course() {
        if (this.current_courses.size() > 0) {
            System.out.println("Registered Courses: ");
            for (Course course : this.current_courses.values()) {
                System.out.println(
                        "Course Id : " + course.get_course_id() + " | Course Name : " + course.get_course_name());
            }
            System.out.print("Enter the course id to delete: ");
            String course_id = student_sc.next();
            if (this.current_courses.containsKey(course_id)) {
                Course course = this.current_courses.get(course_id);
                course.drop_student(this);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("No courses registered.");
        }
    }

    public void track_academic_progress() {
        System.out.println("Academic Progress: ");
        System.out.println("CGPA: " + this.cgpa);
        System.out.println("Your current semester is: " + this.current_semester);
        System.out.println("Currently Enrolled Courses: ");
        for (Course course : this.current_courses.values()) {
            System.out
                    .println("Course ID : " + course.get_course_id() + " | Course Name : " + course.get_course_name());
        }
        for (int i = 0; i < this.current_semester; i++) {
            System.out.println("Semester " + (i + 1) + " SGPA: " + this.sgpas[i]);
            System.out.println("Completed Courses: ");
            for (Pair<Course, Integer> course_grade_pair : this.completed_courses.values()) {
                if (course_grade_pair.getFirst().get_offered_semester() == i + 1) {
                    System.out.println("Course ID : " + course_grade_pair.getFirst().get_course_id()
                            + " | Course Name : " + course_grade_pair.getFirst().get_course_name() + " | Grade: "
                            + course_grade_pair.getSecond());
                }
            }
        }
    }

    public void submit_complaint() {
        System.out.print("Enter the complaint: ");
        String complaint = student_sc.nextLine();
        // Structure ==
        // HashMap<student_roll_number,HashMap<Pair<Status,Complaint_id>,Pair<Complaint,Response>>>
        // Adding complaint to the database
        Complaint.create_complaint(this, complaint);
        // Complaint my_complaint = Complaint.create_complaint(this, complaint);

        System.out.println("Complaint submitted successfully.");
    }

    public void see_complaint() {
        Complaint.view_all_complaints(this);
    }

    public void see_status_of_particular_complaint() {
        System.out.print("Enter the complaint id: ");
        Integer complaint_id = student_sc.nextInt();
        if (Complaint.complaint_db.containsKey(complaint_id)) {
            Complaint.complaint_db.get(complaint_id).view_complaint();
        } else {
            System.out.println("Complaint not found.");
        }
    }

    public void view_schedule() {
        if (this.current_courses.isEmpty()) {
            System.out.println("No courses enrolled.");
            return;
        }

        System.out.println("Current Schedule:");
        for (String courseId : this.current_courses.keySet()) {
            Course course = Course.course_db.get(courseId);
            if (course != null) {
                System.out.println("Course ID: " + courseId);
                System.out.println("Course Name: " + course.get_course_name());
                System.out.println("Course Timing: " + course.get_timings());
                System.out.println("Instructor: " + course.get_course_professor().get_name());
                System.out.println();
            }
        }
    }

    // Additions
    private Boolean pending_feedbacks = false;
    protected HashMap<String, Pair<Course,Feedback<Object>>> completed_course_feedbacks = new HashMap<>();
    protected HashMap<String, Pair<Course,Feedback<Object>>> current_course_feedbacks = new HashMap<>();


    public Boolean check_for_pending_feedbacks() {
        if (this.current_semester==1) {
            this.pending_feedbacks = false;
            return false;
        } // can not give feedbacks for the first semester.
        if(completed_courses.size() == completed_course_feedbacks.size()){
            this.pending_feedbacks = false;
            return false;
        }
        this.pending_feedbacks = true;
        return true;
    }

    public void register_course() {
        if (this.check_for_pending_feedbacks()) {
            System.out.println("You have pending feedbacks to fill.\n");
            return;
        }
        if (this.credits_registered < credit_limit) {
            Course.display_courses_by_semester(this.current_semester);
            System.out.print("Enter the course id: ");
            String course_id = student_sc.next();
            if (Course.course_db.containsKey(course_id)) {
                Course course = Course.course_db.get(course_id);
                if (course.get_offered_semester() == this.current_semester) {
                    if (this.prereq_check(course)) {
                        course.enroll_student(this);
                    } else {
                        System.out.println("Requirements for the course not met!");
                    }

                } else {
                    System.out.println("Course not offered in this semester.");
                }
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Credit limit reached.");
        }
    }

    public void get_to_next_semester(Boolean printing) {
        if (this.current_courses.size() == this.current_courses_pass_check.size()) {
            for (Pair<Course,Boolean> course_pass_check : this.current_courses_pass_check.values()) {
                    this.completed_courses.put(course_pass_check.getFirst().get_course_id(), new Pair<Course,Integer>(course_pass_check.getFirst(),course_pass_check.getSecond()));
                    this.current_course_feedbacks.put(course_pass_check.getFirst().get_course_id(), new Pair<Course,Feedback<Object>>(course_pass_check.getFirst(),null));
            }
            this.current_semester++;
            this.current_courses.clear();
            this.current_courses_pass_check.clear();
            this.dropped_courses.clear();
            this.credits_registered = 0;
            this.calculate_sgpa(this.current_semester - 1);
            this.calculate_cgpa();
            if (printing) {
                System.out.println("Semester ended successfully.");
            }
            return;
        }
        if (printing) {
            System.out.println("Grade not assigned to all the courses.");
        }
    }

    public void add_feeback() {
        if (this.current_course_feedbacks.isEmpty()) {
            System.out.println("No courses to give feedback for.");
            return;
        }
        System.out.println("\nCourses to give feedback for: ");
        for (Pair<Course,Feedback<Object>> pair : current_course_feedbacks.values()) {
            if (pair.getSecond() != null) {
                continue
            }
            Course course = pair.getFirst();
            System.out.println("Course ID: " + course.get_course_id() + " | Course Name: " + course.get_course_name());
        }
        System.out.print("\nEnter -1 to return to previous menu\nEnter Course ID: ");
        String course_id = student_sc.next();
        Boolean feedback_given = false;
        Pair<Course,Feedback<Object>> selected_pair = null;
        if (course_id.equals("-1")){
            return;
        } else if (this.current_course_feedbacks.containsKey(course_id)) {
            if (this.current_course_feedbacks.get(course_id).getSecond() != null) {
                System.out.println("Feedback already given for this course.");
                return;
            }
            selected_pair = this.current_course_feedbacks.get(course_id);
            while (!feedback_given){
                System.out.println("\nEnter -1 to go to previous menu\nPress 1 to give feedback in the form of a string.");
                System.out.println("Press 2 to give feedback in the form of a rating. (1-10)");
                System.out.print("Enter your choice: ");
                Integer choice = student_sc.nextInt();
                student_sc.nextLine();
                Feedback<Object> feedback_object = new Feedback<>();
                if (choice == 1) {
                    System.out.print("Enter your feedback: ");
                    String feedback = student_sc.nextLine();
                    feedback_object.set_feedback(feedback);
                    selected_pair.setSecond(feedback_object);
                    feedback_given = true;
                    break;
                } else if (choice == 2) {
                    System.out.print("Enter your rating: ");
                    Integer rating = student_sc.nextInt();
                    student_sc.nextLine();
                    feedback_object.set_feedback(rating);
                    selected_pair.setSecond(feedback_object);
                    feedback_given = true;
                    break;
                } else if(choice == -1){        
                    return;
                }else {
                    System.out.println("Invalid Input.");
                    continue;
                }
            }   
        } else{
            System.out.println("You have not completed this.");
        }
        if (feedback_given) {
            Course course = selected_pair.getFirst();
            this.completed_course_feedbacks.put(course.get_course_id(), selected_pair);
            this.current_course_feedbacks.remove(course.get_course_id());
        }
    }

    public void view_feedback_as_prof(String course_id){
        if (this.completed_course_feedbacks.containsKey(course_id)) {
            System.out.println(this.completed_course_feedbacks.get(course_id));
            return;
        }
        System.out.println("Student did not complete this course.");
    }
}
