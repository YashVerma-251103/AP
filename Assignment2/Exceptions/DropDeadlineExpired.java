package Assignment2.Exceptions;
import Assignment2.Course;

public class DropDeadlineExpired extends Exception{
    public DropDeadlineExpired(Course Course){
        super("Date to Drop the couse"+Course.get_course_name()+" has passed.");
    }
}
