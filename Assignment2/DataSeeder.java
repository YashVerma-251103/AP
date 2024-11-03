// package redo1;
// public class PreRun { // left
//     public static void RUN(){     
//     }
// }


package Assignment2;
public class DataSeeder {

    public static void seedData() {
        // Add Admin
        Admin admin = new Admin();
        admin.set_user("admin@iiitd.ac.in", "admin123");
        admin.set_admin_id("A001");
        admin.set_name("Admin One");
        Admin.admin_db.put(admin.get_admin_id(), admin);

        // Add Professors
        Professor prof1 = new Professor();
        make_professor(prof1, "Professor Alpha","prof1@iiitd.ac.in","prof123","Prof001","mathematics");
        Professor.professor_db.put(prof1.get_professor_id(), prof1);
        Professor prof2 = new Professor();
        make_professor(prof2,"Professor Beta","prof2@iiitd.ac.in", "prof123", "Prof002", "computer science");
        Professor.professor_db.put(prof2.get_professor_id(), prof2);
        Professor prof3 = new Professor();
        make_professor(prof3,"Professor Gamma","prof3@iiitd.ac.in", "prof123", "Prof003", "mathematics");
        Professor.professor_db.put(prof3.get_professor_id(), prof3);
        Professor prof4 = new Professor();
        make_professor(prof4,"Professor Theta","prof4@iiitd.ac.in", "prof123", "Prof004", "computer science");
        Professor.professor_db.put(prof4.get_professor_id(), prof4);
        Professor prof5 = new Professor();
        make_professor(prof5,"Professor Phi","prof5@iiitd.ac.in", "prof123", "Prof005", "computer science");
        Professor.professor_db.put(prof5.get_professor_id(), prof5);

        // Add Students
        Student student1 = new Student();
        make_student(student1,"student1@uni.com", "stud123", 1001,"Student One");
        Student.student_db.put(student1.get_student_roll_number(), student1);
        Student student2 = new Student();
        make_student(student2,"student2@uni.com", "stud456", 1002 ,"Student Two");
        Student.student_db.put(student2.get_student_roll_number(), student2);
        Student student3 = new Student();
        make_student(student3,"student3@uni.com", "stud789", 1003 ,"Student Three");
        Student.student_db.put(student3.get_student_roll_number(), student3);

        // Add Course
        Course.sem_course_initializer();
        Course course1 = new Course();
        make_course(course1,"C101", "Calculus", 1, prof1, "01/10/2024");
        Course.course_db.put(course1.get_course_id(), course1);
        Course.semester_course_db.get(1).add(course1);
        Course course2 = new Course();
        make_course(course2,"C102", "Linear Algebra", 1, prof3, "01/10/2023");
        Course.course_db.put(course2.get_course_id(), course2);
        Course.semester_course_db.get(1).add(course2);
        Course course3 = new Course();
        make_course(course3,"C201", "Data Structures", 2, prof2, "01/10/2024");
        Course.course_db.put(course3.get_course_id(), course3);
        Course.semester_course_db.get(2).add(course3);
        Course course4 = new Course();
        make_course(course4,"C202", "Operating Systems", 2, prof4, "01/10/2024");
        Course.course_db.put(course4.get_course_id(), course4);
        Course.semester_course_db.get(2).add(course4);
        Course course5 = new Course();
        make_course(course5,"C301", "Artificial Intelligence", 3, prof5, "01/10/2024");
        Course.course_db.put(course5.get_course_id(), course5);
        Course.semester_course_db.get(3).add(course5);
        System.out.println("Sample data has been seeded successfully!");
    }

    static void make_professor(Professor prof, String name, String email, String password, String prof_id, String department){
        prof.set_name(name);
        prof.set_email(email);
        prof.set_password(password);
        prof.set_professor_id(prof_id);
        prof.set_department(department);
    }

    static void make_student(Student stud, String email, String password, int roll_number, String name){
        stud.set_email(email);
        stud.set_password(password);
        stud.set_student_roll_number(roll_number);
        stud.set_name(name);
    }

    static void make_course(Course course, String course_id, String course_name, int semester, Professor prof, String drop_date){
        course.set_course_id(course_id);
        course.set_course_name(course_name);
        course.set_offered_semester(semester);
        course.set_course_professor(prof);
        course.get_course_professor().set_assigned_course(course);
        course.set_course_credits(4);
        course.set_current_enrollment(0);
        course.set_enrollment_limit(0);
        course.set_drop_date(drop_date);
    }

}

