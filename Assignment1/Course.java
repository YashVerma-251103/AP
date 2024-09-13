package Assignment1;

import java.util.*;;

public class Course {
    public Scanner sc = new Scanner(System.in);
    // Thought that this might come handy while working with the course.
    private Integer corresponding_semester;

    // Attributes
    private Professor professor;
    private String code;
    private String title;
    private String timings;
    private String syllabus;
    private Integer credit;
    private Integer enrollment_limit;
    private Integer enrollment_count = 0;

    // Storing and Shared Datas
    private ArrayList<Student> enrolled_students;
    private ArrayList<Course> prerequisites;
    private static HashMap<Integer, ArrayList<Course>> Courses = new HashMap<Integer, ArrayList<Course>>();

    // Required Functionalities
    void set_professor(Professor professor) {
        this.professor = professor;
    }

    void set_enrollment_limit(Integer limit) {
        enrollment_limit = limit;
    }

    Course create_course(Professor prof) {
        Course course = new Course();
        course.Set_Course(prof);
        return course;
    }

    Course Set_Course(Professor prof) { // something left need to check the if block -- also add the checks before creation
        Course newcourse = create_course(prof);
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
        Courses.get(corresponding_semester).add(newcourse); //check this line.
        return newcourse;
    }

    void show_course_list(Integer semester) {
        // may need to add a check for student prerequisits
        System.out.println("Courses offered in Semester " + semester);
        for (Course course : Courses.get(semester)) {
            System.out.println(course.code + " : " + course.title);
        }
        System.out.println();
    }

    void view_course() { // made - test left
        System.out.println("Course Code: " + this.code);
        System.out.println("Course Title: " + this.title);
        System.out.println("Course Credit: " + this.credit);
        System.out.println("Course offered in Semester: " + this.corresponding_semester);
        System.out.println("Course Enrollment Limit: " + this.enrollment_limit);
        System.out.println("Course Timings: " + this.timings);
        System.out.println("Course Syllabus: " + this.syllabus);
        System.out.println("Course Professor: " + this.professor.get_name());
        System.out.println("Course Enrollment Count: " + this.enrollment_count);
        System.out.println("Course Prerequisites: ");
        for (Course course : prerequisites) {
            System.out.println(course.code + " : " + course.title);
        }
        System.out.println();
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
