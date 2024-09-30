package Assignment2;
import java.util.HashMap;
public class TAs extends Student  {
    // ta attributes
    private Integer ta_id;
    private Course teaching_course;
    private Professor course_professor;

    // Shared data structure
    protected static HashMap<Integer, TAs> ta_db = new HashMap<Integer, TAs>();

    // Setter for TA
    public void set_teaching_course(Course course){
        this.teaching_course = course;
    }
    public void set_course_professor(Professor professor){
        this.course_professor = professor;
    }
    public void set_ta_id(Integer id){
        this.ta_id = id;
    }

    // Getter for TA
    public Course get_teaching_course(){
        return this.teaching_course;
    }
    public Professor get_course_professor(){
        return this.course_professor;
    }
    public Integer get_ta_id(){
        return this.ta_id;
    }


    // Funcitonalities for smooth implementation.
    // TA maker method
    public static void ta_maker(Integer student_id, Course TA_course, Professor professor) {
        if (TAs.ta_db.containsKey(student_id)) {
            System.out.println("Student is already a TA.");
            return;
        }
        if (!Student.student_db.containsKey(student_id)) {
            System.out.println("Student does not exist.");
            return;
        }
        // may remove this.
        if(!(Student.student_db.get(student_id).completed_courses.containsKey(TA_course.get_course_id()))){
            System.out.println("Student has not completed this course. Hence can not be promoted to TA.");
            return;
        }


        // Creating TA by copying the student reference
        TAs ta = new TAs(Student.student_db.get(student_id));
        ta.set_ta_id(student_id);
        ta.set_teaching_course(TA_course);
        ta.set_course_professor(professor);
        TAs.ta_db.put(ta.get_student_roll_number(), ta);
        professor.tas.put(ta.get_student_roll_number(), ta);
        System.out.println("TA assigned successfully.");
    }
    // Constructor taking Student object
    public TAs(Student student) {
        // super();
        this.set_email(student.get_email());
        this.set_password(student.get_password());
        this.set_name(student.get_name());
        this.set_student_roll_number(student.get_student_roll_number());
        this.set_credits_registered(student.get_credits_registered());
        this.set_current_semester(student.get_current_semester());
        this.set_cgpa(student.get_cgpa());
        this.sgpas = student.sgpas;
        this.current_courses = student.current_courses;
        this.completed_courses = student.completed_courses;
        this.dropped_courses = student.dropped_courses;
        this.current_courses_pass_check = student.current_courses_pass_check;
        this.completed_course_feedbacks = student.completed_course_feedbacks;
        this.current_course_feedbacks = student.current_course_feedbacks;
    }


    // Required Functionalities
    public void view_course_details() {
        if (this.teaching_course == null) {
            System.out.println("TA is not assigned any course.");
            return;
        }
        this.teaching_course.show_details();
    }
    public void manage_student_grades(){
        while (true) {
            System.out.println("1. View enrolled students (Press 1)");
            System.out.println("2. Update student grades (Press 2)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = student_sc.nextInt();
            student_sc.nextLine();
            if (choice == 1){
                this.course_professor.view_enrolled_students();
            } else if (choice == 2) {
                this.assign_grades_to_student();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    public void assign_grades_to_student(){
        if (this.teaching_course == null) {
            System.out.println("No course assigned to this professor.");
            return;
        } else if (this.teaching_course.get_enrolled_students().isEmpty()) {
            System.out.println("No students enrolled in the assigned course.");
            return;
        }
        System.out.println("Assigning grades to students for the course: " + this.teaching_course.get_course_id());
        this.teaching_course.show_enrolled_students();
        while (true) {
            System.out.println("Enter -1 to return to the previous menu.");
            System.out.print("Do you want to assign grades to any student? (Y/N)");
            String choice = student_sc.next();
            if (choice == "-1"){
                return;
            } else if (choice.equals("Y") || choice.equals("y")) {
                this.course_professor.show_students_with_grades_assigned();
                System.out.print("Enter the student's roll number: ");
                Integer roll_number = student_sc.nextInt();
                student_sc.nextLine();
                if (Student.student_db.containsKey(roll_number)) {
                    Student student = Student.student_db.get(roll_number);
                    if (student.current_courses.containsKey(this.teaching_course.get_course_id())) {
                        System.out.print("Enter the grade: ");
                        Integer grade = student_sc.nextInt();
                        student_sc.nextLine();
                        Pair<Student,Integer> pair = new Pair<Student,Integer>(student, grade);
                        this.course_professor.assigning_grades_to_student.put(roll_number, pair);
                        System.out.println("Grade assigned successfully.");
                    } else {
                        System.out.println("Student not enrolled in this course.");
                    }
                } else {
                    System.out.println("Student not found.");
                }
            } else{
                break;
            }
        }
    }





}
