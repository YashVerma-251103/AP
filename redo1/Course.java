package redo1;

import java.util.*;
public class Course { // made -- test left
    private static Scanner sc = new Scanner(System.in);

    // Attributes
    private String course_id;
    private String course_name;
    private String course_description;
    private String syllabus;
    private String timings;
    private Professor course_professor;
    private Integer course_credits;
    private Integer offered_semester;
    private Integer enrollment_limit;
    private Integer current_enrollment;

    
    // Storing and Sharing DataBase
    protected static HashMap<String, Course> course_db = new HashMap<String, Course>();
    protected static HashMap<Integer, ArrayList<Course>> semester_course_db = new HashMap<Integer, ArrayList<Course>>();
    private HashMap<Integer, Student> enrolled_students = new HashMap<Integer, Student>();
    private ArrayList<Course> course_prerequisites = new ArrayList<Course>();
    private ArrayList<Course> prerequist_of_courses = new ArrayList<Course>();


    // Getters
    public String get_course_id() {
        return course_id;
    }
    public String get_course_name() {
        return course_name;
    }   
    public String get_course_description() {
        return course_description;
    }   
    public String get_syllabus() {
        return syllabus;
    }
    public String get_timings() {
        return timings;
    }
    public Professor get_course_professor() {
        return course_professor;
    }
    public Integer get_course_credits() {
        return course_credits;
    }
    public Integer get_offered_semester() {
        return offered_semester;
    }
    public Integer get_enrollment_limit() {
        return enrollment_limit;
    }
    public Integer get_current_enrollment() {
        return current_enrollment;
    }
    

    // Setters
    public void set_course_id(String course_id) {
        this.course_id = course_id;
    }
    public void set_course_name(String course_name) {
        this.course_name = course_name;
    }
    public void set_course_description(String course_description) {
        this.course_description = course_description;
    }
    public void set_syllabus(String syllabus) {
        this.syllabus = syllabus;
    }
    public void set_timings(String timings) {
        this.timings = timings;
    }
    public void set_course_professor(Professor course_professor) {
        this.course_professor = course_professor;
    }
    public void set_course_credits(Integer course_credits) {
        this.course_credits = course_credits;
    }
    public void set_offered_semester(Integer offered_semester) {
        this.offered_semester = offered_semester;
    }
    public void set_enrollment_limit(Integer enrollment_limit) {
        this.enrollment_limit = enrollment_limit;
    }
    public void set_current_enrollment(Integer current_enrollment) {
        this.current_enrollment = current_enrollment;
    }


    /* Functions I need for smooth implementation */
    // Static Functions
    public static Course create_course(Professor assigned_prof) {
        Course course = new Course();
        course.set_course();
        if (course_db.containsKey(course.course_id)) {
            System.out.println("Course already exists.");
            return null;
        } else {
            course.set_course_professor(assigned_prof);
            assigned_prof.set_assigned_course(course);
            course_db.put(course.course_id, course);
            if (semester_course_db.containsKey(course.offered_semester)) {
                semester_course_db.get(course.offered_semester).add(course);
            } else {
                ArrayList<Course> courses = new ArrayList<Course>();
                courses.add(course);
                semester_course_db.put(course.offered_semester, courses);
            }
            System.out.println("Course added successfully.");
        }
        return course;
    }
    public static void display_all_courses() {
        for (Course course : course_db.values()) {
            System.out.println("Course ID: " + course.course_id + " | Course Name: " + course.course_name);
        }
    }
    public static void display_courses_by_semester(Integer semester) {
        if (semester_course_db.containsKey(semester)) {
            for (Course course : semester_course_db.get(semester)) {
                System.out.println("Course ID: " + course.course_id + " | Course Name: " + course.course_name);
            }
        } else {
            System.out.println("No courses offered in this semester.");
        }
    }
    public static void display_course_details(String course_id) {
        if (course_db.containsKey(course_id)) {
            Course course = course_db.get(course_id);
            course.show_details();
        } else {
            System.out.println("Course not found.");
        }
    }
    public static void delete_course(String course_id) {
        if (course_db.containsKey(course_id)) {
            Course course = course_db.get(course_id);
            course.course_professor.set_assigned_course(null);
            for (Student student : course.enrolled_students.values()) {
                student.current_courses.remove(course_id);
                // student.completed_courses.remove(course_id); // they will still get credits for that so no need to remove from this list.
                student.set_credits_registered(student.get_credits_registered() - course.course_credits);
            }
            for (Course prereq : course.prerequist_of_courses) {
                prereq.course_prerequisites.remove(course);
            }
            course_db.remove(course_id);
            semester_course_db.get(course.offered_semester).remove(course);
            
            System.out.println("Course deleted successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }
    public static void update_course(String course_id) {
        if (course_db.containsKey(course_id)) {
            Course course = course_db.get(course_id);
            while (true) {
                System.out.println("Enter -1 to return to previous menu!\n");
                System.out.println("What do you want to update?");
                System.out.println("1. Update Course Name (Press 1)");
                System.out.println("2. Update Course ID (Press 2)");
                System.out.println("3. Update Course Description (Press 3)");
                System.out.println("4. Update Syllabus (Press 4)");
                System.out.println("5. Update Timings (Press 5)");
                System.out.println("6. Update Course Credits (Press 6)");
                System.out.println("7. Update Offered Semester (Press 7)");
                System.out.println("8. Update Enrollment Limit (Press 8)");
                System.out.println("9. Update Prerequisites (Press 9)");
                System.out.println("Enter your choice: ");
                Integer choice = sc.nextInt();
                if (choice == -1) {
                    return;
                } else if (choice == 1) {
                    System.out.println("Enter new course name: ");
                    course.set_course_name(sc.nextLine());
                } else if (choice == 2) {
                    System.out.println("Enter new course id: ");
                    String prev_id = course.get_course_id();
                    course.set_course_id(sc.nextLine());
                    course_db.remove(prev_id);
                    course_db.put(course.get_course_id(), course);
                    for (Student student : course.enrolled_students.values()) {
                        student.current_courses.remove(prev_id);
                        student.current_courses.put(course.get_course_id(), course);
                    }
                    Student.changes_in_completed_courses(prev_id, course.get_course_id());
                } else if (choice == 3) {
                    System.out.println("Enter new course description: ");
                    course.set_course_description(sc.nextLine());
                } else if (choice == 4) {
                    System.out.println("Enter new syllabus: ");
                    course.set_syllabus(sc.nextLine());
                } else if (choice == 5) {
                    System.out.println("Enter new timings: ");
                    course.set_timings(sc.nextLine());
                } else if (choice == 6) {
                    System.out.println("Enter new course credits: ");
                    Integer prev_credits = course.get_course_credits();
                    course.set_course_credits(sc.nextInt());
                    for (Student student : course.enrolled_students.values()) {
                        student.set_credits_registered(student.get_credits_registered() - prev_credits + course.get_course_credits());
                    }
                } else if (choice == 7) {
                    System.out.println("Enter new offered semester: ");
                    Integer prev_sem=course.get_offered_semester();
                    course.set_offered_semester(sc.nextInt());
                    semester_course_db.get(prev_sem).remove(course);
                    if (semester_course_db.containsKey(course.get_offered_semester())) {
                        semester_course_db.get(course.get_offered_semester()).add(course);
                    } else {
                        ArrayList<Course> courses = new ArrayList<Course>();
                        courses.add(course);
                        semester_course_db.put(course.get_offered_semester(), courses);
                    }
                } else if (choice == 8) {
                    System.out.println("Enter new enrollment limit: ");
                    course.set_enrollment_limit(sc.nextInt());
                } else if (choice == 9) {
                    course.show_prerequisites();
                    while (true) {
                        System.out.println("Enter -1 to return to previous menu!\n");
                        System.out.println("Do you want to add or remove prerequisites?");
                        System.out.println("1. Add prerequisites (Press 1)");
                        System.out.println("2. Remove prerequisites (Press 2)");
                        System.out.println("Enter your choice: ");
                        Integer choice2 = sc.nextInt();
                        if (choice2 == 1) {
                            System.out.println("Enter the course id of the prerequisite: ");
                            String prereq_id = sc.nextLine();
                            if (course_db.containsKey(prereq_id)) {
                                course.add_prerequisite(course_db.get(prereq_id));
                                course_db.get(prereq_id).add_prerequisite_of(course);
                            } else {
                                System.out.println("Course not found.");
                            }
                        } else if (choice2 == 2) {
                            System.out.println("Enter the course id of the prerequisite: ");
                            String prereq_id = sc.nextLine();
                            if (course_db.containsKey(prereq_id)) {
                                course.remove_prerequisite(course_db.get(prereq_id));
                                course_db.get(prereq_id).remove_prerequisite_of(course);
                            } else {
                                System.out.println("Course not found.");
                            }
                        } else if (choice2 == -1) {
                            break;
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    }
                }else {
                    System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    // Course specific functions
    public void set_course() {
        System.out.println("Enter course id: ");
        this.set_course_id(sc.nextLine());
        System.out.println("Enter course name: ");
        this.set_course_name(sc.nextLine());
        System.out.println("Enter course description: ");
        this.set_course_description(sc.nextLine());
        System.out.println("Enter syllabus: ");
        this.set_syllabus(sc.nextLine());
        System.out.println("Enter timings: ");
        this.set_timings(sc.nextLine());
        System.out.println("Enter course credits: ");
        this.set_course_credits(sc.nextInt());
        System.out.println("Enter offered semester: ");
        this.set_offered_semester(sc.nextInt());
        System.out.println("Enter enrollment limit: ");
        this.set_enrollment_limit(sc.nextInt());
        this.set_current_enrollment(0);
    } 
    public void enroll_student(Student student) {
        if (this.enrolled_students.containsKey(student.get_student_roll_number())) {
            System.out.println("Student already enrolled in this course.");
        } else if (this.current_enrollment >= this.enrollment_limit) {
            System.out.println("Course is full. Cannot enroll more students.");
        } else{
            this.enrolled_students.put(student.get_student_roll_number(), student);
            this.current_enrollment++;
            student.current_courses.putIfAbsent(this.course_id, this);
            student.set_credits_registered(student.get_credits_registered() + this.course_credits);
            System.out.println("Student enrolled successfully.");
        }
    }
    public void drop_student(Student student) {
        if (this.enrolled_students.containsKey(student.get_student_roll_number())) {
            this.enrolled_students.remove(student.get_student_roll_number());
            this.current_enrollment--;
            student.current_courses.remove(this.course_id);
            student.set_credits_registered(student.get_credits_registered() - this.course_credits);
            System.out.println("Student dropped successfully.");
        } else {
            System.out.println("Student not enrolled in this course.");
        }
    }
    public void add_prerequisite(Course course) {
        this.course_prerequisites.add(course);
    }
    public void add_prerequisite_of(Course course) {
        this.prerequist_of_courses.add(course);
    }
    public void remove_prerequisite(Course course) {
        this.course_prerequisites.remove(course);
    }
    public void remove_prerequisite_of(Course course) {
        this.prerequist_of_courses.remove(course);
    }
    public void show_prerequisites() {
        if (this.course_prerequisites.size() == 0) {
            System.out.println("No prerequisites for this course.");
            return;
        }
        System.out.println("Prerequisites: ");
        for (Course course : this.course_prerequisites) {
            System.out.println("Course ID: " + course.get_course_id() + " | Course Name: " + course.get_course_name());
        }
    }
    public void show_enrolled_students() {
        if (this.current_enrollment == 0) {
            System.out.println("No students enrolled in this course.");
            return;
        }
        System.out.println("Enrolled Students: ");
        for (Student student : this.enrolled_students.values()) {
            System.out.println("Student Roll Number: " + student.get_student_roll_number() + " | Student Name: " + student.get_name());
        }
    }
    public void show_details(){
        System.out.println("Course ID: " + this.course_id);
        System.out.println("Course Name: " + this.course_name);
        System.out.println("Course Description: " + this.course_description);
        System.out.println("Syllabus: " + this.syllabus);
        System.out.println("Timings: " + this.timings);
        System.out.println("Course Credits: " + this.course_credits);
        System.out.println("Offered Semester: " + this.offered_semester);
        System.out.println("Enrollment Limit: " + this.enrollment_limit);
        System.out.println("Current Enrollment: " + this.current_enrollment);
        this.show_enrolled_students();
        this.show_prerequisites();
    }
}
