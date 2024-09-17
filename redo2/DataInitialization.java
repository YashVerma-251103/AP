package redo2;
public class DataInitialization {

    public static void initializeData() {
        // Sample Admin
        Admin admin1 = new Admin();
        admin1.set_user("admin@university.edu", "admin123");
        admin1.set_admin_id("A001");
        admin1.set_name("Admin One");
        Admin.admin_db.put(admin1.get_admin_id(), admin1);

        // Sample Professors
        Professor prof1 = new Professor();
        prof1.set_user("prof.jones@university.edu", "profpass1");
        prof1.set_professor_id("P001");
        prof1.set_name("Dr. Jones");
        prof1.set_department("Computer Science");
        Professor.professor_db.put(prof1.get_professor_id(), prof1);

        Professor prof2 = new Professor();
        prof2.set_user("prof.smith@university.edu", "profpass2");
        prof2.set_professor_id("P002");
        prof2.set_name("Dr. Smith");
        prof2.set_department("Mathematics");
        Professor.professor_db.put(prof2.get_professor_id(), prof2);

        // Sample Students
        Student student1 = new Student();
        student1.set_user("john.doe@student.edu", "studentpass1");
        student1.set_student_roll_number(1001);
        student1.set_name("John Doe");
        student1.set_current_semester(2);
        Student.student_db.put(student1.get_student_roll_number(), student1);

        Student student2 = new Student();
        student2.set_user("jane.smith@student.edu", "studentpass2");
        student2.set_student_roll_number(1002);
        student2.set_name("Jane Smith");
        student2.set_current_semester(3);
        Student.student_db.put(student2.get_student_roll_number(), student2);

        // Sample Courses
        Course course1 = new Course();
        course1.set_course_id("CS101");
        course1.set_course_name("Introduction to Programming");
        course1.set_course_description("Basics of programming in C.");
        course1.set_syllabus("C programming, algorithms, data structures.");
        course1.set_timings("Mon-Wed-Fri 9:00 AM - 10:30 AM");
        course1.set_course_credits(4);
        course1.set_offered_semester(1);
        course1.set_enrollment_limit(30);
        course1.set_course_professor(prof1);
        Course.course_db.put(course1.get_course_id(), course1);

        Course course2 = new Course();
        course2.set_course_id("MA201");
        course2.set_course_name("Calculus II");
        course2.set_course_description("Advanced calculus.");
        course2.set_syllabus("Differentiation, integration, multivariable calculus.");
        course2.set_timings("Tue-Thu 11:00 AM - 12:30 PM");
        course2.set_course_credits(3);
        course2.set_offered_semester(2);
        course2.set_enrollment_limit(40);
        course2.set_course_professor(prof2);
        Course.course_db.put(course2.get_course_id(), course2);

        System.out.println("Data initialized successfully.");
    }
}


// give me directory structure to better store my files and still ensure smooth running of program.
// give me a well structured report documenting everything.
// give me a well structured UML diagram for this project.

