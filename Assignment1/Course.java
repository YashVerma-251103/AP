package Assignment1;

import java.util.*;;

public class Course {
    private Scanner sc = new Scanner(System.in);
    // Thought that this might come handy while working with the course.
    private Integer corresponding_semester;

    // Attributes
    private Professor professor;
    private Character grade;
    private String code;
    private String title;
    private String timings;
    private String syllabus;
    private Integer credit;
    private Integer enrollment_limit;
    private Integer enrollment_count = 0;

    // Storing and Shared Datas
    protected ArrayList<Student> enrolled_students;
    protected ArrayList<Course> prerequisites;
    protected static HashMap<String, Course> course_bank = new HashMap<String, Course>();
    protected static HashMap<Integer, ArrayList<Course>> sem_course_bank = new HashMap<Integer, ArrayList<Course>>();

    // Setters
    void set_professor(Professor professor) {
        this.professor = professor;
    }
    void set_enrollment_limit(Integer limit) {
        enrollment_limit = limit;
    }
    void set_credits(Integer credit) {
        this.credit = credit;
    }
    // void set_timings(String timings) {
    //     this.timings = timings;
    // }
    void set_syllabus(String syllabus) {
        this.syllabus = syllabus;
    }
    void set_enrollment_count(Integer count) {
        enrollment_count = count;
    }
    void set_prerequisites(ArrayList<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }
    void set_grade(Character grade) {
        this.grade = grade;
    }
    
    // Getters
    String get_code() {
        return this.code;
    }
    String get_title() {
        return this.title;
    }
    Integer get_enrollment_count() {
        return this.enrollment_count;
    }
    Integer get_enrollment_limit() {
        return this.enrollment_limit;
    }
    Character get_grade() {
        return this.grade;
    }
    Integer get_credits() {
        return this.credit;
    }
    Integer get_semester() {
        return this.corresponding_semester;
    }

    // Functionalities that i may require to implement everything easily
    void increment_enrollment_count() {
        this.enrollment_count++;
    }
    void decrement_enrollment_count() {
        this.enrollment_count--;
    }
    void enroll_student(Student student) {
        this.enrolled_students.add(student);
        this.enrollment_count++;
    }
    void denroll_student(Student student) {
        this.enrolled_students.remove(student);
        this.enrollment_count--;

    }
    // Static Functions -- will not be used for specific course.
    static Course create_course(Professor prof) {
        Course course = new Course();
        course.set_course(prof);
        if(sem_course_bank.containsKey(course.corresponding_semester)){
            if(sem_course_bank.get(course.corresponding_semester).contains(course)){
                System.out.println("Course already exists");
                return null;
            }
            else{
                sem_course_bank.get(course.corresponding_semester).add(course);
            }
        }
        return course;
    }
    static void show_course_list(Integer semester, Student student) { // made - test left
        System.out.println("Courses offered in Semester " + semester+" to you");
        for (Course course : sem_course_bank.get(semester)) {
            if(student.check_prerequisites(course)){
                System.out.println(course.code + " : " + course.title);
            }
        }
        System.out.println();
    }
    static void show_course_list(Integer semester) { // made for profs and admins - test left
        System.out.println("Courses offered in Semester " + semester);
        for (Course course : sem_course_bank.get(semester)) {
            System.out.println(course.code + " : " + course.title);
        }
        System.out.println();
    }
    static void view_course(Course course_to_show) { // made - test left
        System.out.println("Course Code: " + course_to_show.code);
        System.out.println("Course Title: " + course_to_show.title);
        System.out.println("Course Credit: " + course_to_show.credit);
        System.out.println("Course offered in Semester: " + course_to_show.corresponding_semester);
        System.out.println("Course Enrollment Limit: " + course_to_show.enrollment_limit);
        System.out.println("Course Timings: " + course_to_show.timings);
        System.out.println("Course Syllabus: " + course_to_show.syllabus);
        System.out.println("Course Professor: " + course_to_show.professor.get_name());
        System.out.println("Course Enrollment Count: " + course_to_show.enrollment_count);
        System.out.println("Course Prerequisites: ");
        for (Course course : course_to_show.prerequisites) {
            System.out.println(course.code + " : " + course.title);
        }
        System.out.println();
    }


    // Non-Static Functions -- will be used for specific course.
    void set_course(Professor prof) { // something left need to check the if block -- also add the checks before creation
        // set the course details
        this.professor = prof;
        System.out.print("Enter the course code: ");
        this.code = sc.nextLine();
        System.out.print("Enter the course title: ");
        this.title = sc.nextLine();
        System.out.print("Enter the course credit: ");
        this.credit = sc.nextInt();
        System.out.print("Enter the course offered in Semester(1-8): ");
        this.corresponding_semester = sc.nextInt();
        System.out.print("Enter the course enrollment limit: ");
        this.enrollment_limit = sc.nextInt();
        this.prerequisites = new ArrayList<Course>();
        System.out.println("Do you want to create prerequisite list now? (Y/N)");
        String choice = sc.next();
        if (choice.equals("Y") || choice.equals("y")) {
            // need to add a way to add prerequisites to form the prerequisites list
            this.add_prerequisites(prerequisites, false);
        }
        this.enrolled_students = new ArrayList<Student>();
        // set timings, syllabus, enrollment_limit
    }
    void add_single_prerequisite(Course course) { // made - test left
        this.prerequisites.add(course);
        System.out.println("Prerequisites added");
    }
    void add_prerequisites(ArrayList<Course> course, Boolean print) { // made - test left
        this.prerequisites.addAll(course);
        if (print) {
            System.out.println("Prerequisites added");
        }
    }
    void remove_prerequisite(Course course) { // made - test left
        prerequisites.remove(course);
        System.out.println("Prerequisite removed");
    }
    void view_prerequisites() { // made - test left
        System.out.println("Prerequisites for " + this.code + " : " + this.title + "\n");
        for (Course course : prerequisites) {
            System.out.println(course.code + " : " + course.title);
        }
        System.out.println();
    }
    void update_syllabus() { // made - test left
        System.out.print("Enter the new syllabus: ");
        this.syllabus = sc.nextLine();
        System.out.println("Syllabus updated");
    }
    
    
}
