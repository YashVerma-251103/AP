package Assignment1;
import java.util.*;;


public class Course {
    public Scanner sc = new Scanner(System.in);

    private String code;
    private String title;
    private Professor professor;
    private Integer credit;
    private ArrayList<Course> prerequisites;
    private String timings;
    void set_professor(Professor professor){
        this.professor = professor;
    }
    


    // got from professor
    private String syllabus;
    private Integer enrollment_limit;
    private ArrayList<Student> enrolled_students;
    void set_enrollment_limit(Integer limit){
        enrollment_limit = limit;
    }
    
    
    // thought that this would be useful
    private Integer enrollment_count = 0;
    Course create_course(){
        return new Course();
    }
    void Set_Course(Professor prof){
        // set the course details
        this.professor=prof;
        System.out.print("Enter the course code: ");
        this.code=sc.nextLine();
        System.out.print("Enter the course title: ");
        this.title=sc.nextLine();
        System.out.print("Enter the course credit: ");
        this.credit=sc.nextInt();
        System.out.print("Enter the course enrollment limit: ");
        this.enrollment_limit=sc.nextInt();
        this.prerequisites=new ArrayList<Course>();
        this.enrolled_students=new ArrayList<Student>();
        // set timings, syllabus, enrollment_limit
    }
    void add_prerequisite(Course course){
        prerequisites.add(course);
        System.out.println("Prerequisite added");
    }
    void remove_prerequisite(Course course){
        prerequisites.remove(course);
        System.out.println("Prerequisite removed");
    }
    void view_prerequisites(){
        System.out.println("Prerequisites for "+this.code+" : "+this.title+"\n");
        for (Course course : prerequisites) {
            System.out.println(course.code+" : "+course.title);
        }
        System.out.println();
    }
    void update_syllabus(){
        System.out.print("Enter the new syllabus: ");
        this.syllabus=sc.nextLine();
        System.out.println("Syllabus updated");
    }
}
