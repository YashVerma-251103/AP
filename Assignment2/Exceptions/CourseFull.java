package Assignment2.Exceptions;
import Assignment2.Course;

public class CourseFull extends Exceptions {
    public CourseFull(Course Course){
        super("Course " + Course.get_course_name(); + " has reached its maximum capacity.");
    }
}
