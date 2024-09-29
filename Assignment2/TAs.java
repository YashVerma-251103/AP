package Assignment2;
import java.util.HashMap;
public class TAs extends Student  {
    // ta attributes
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

    // Getter for TA
    public Course get_teaching_course(){
        return this.teaching_course;
    }
    public Professor get_course_professor(){
        return this.course_professor;
    }

    // @Override
    // protected Object clone() throws CloneNotSupportedException {
    //     TAs cloned = (TAs) super.clone();
    //     // Deep copy of address
    //     return cloned;
    // }


    public static void ta_maker(Integer student_id, Course TA_course, Professor professor) {
        if (TAs.ta_db.containsKey(student_id)) {
            System.out.println("Student is already a TA.");
            return;
        }
        // // make a student a TA
        // if (!Student.student_db.containsKey(student_id)) {
        //     System.out.println("Student does not exist.");
        //     return;
        // }
        // Student student = Student.student_db.get(roll_number);
        // TAs ta = new TAs();
        // ta.set_name(student.get_name());
        // ta.set_email(student.get_email());
        // ta.set_password(student.get_password());
        // ta.set_roll_number(student.get_roll_number());
        // ta.set_teaching_course(TA_course);
        // ta.set_course_professor(professor);
        // TAs.ta_db.put(ta.get_roll_number(), ta);
        // professor.tas.put(ta.get_roll_number(), ta);
        // System.out.println("TA assigned successfully.");
    
        // Clone the student object to TA object
        TAs ta = new TAs();
        ta = (TAs) Student.student_db.get(student_id).clone();

        ta.set_teaching_course(TA_course);
        ta.set_course_professor(professor);
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
