# Assignment 2 - ERP System

## Introduction

This project is a University Management System that simulates the management of courses, students, professors, teaching assistants (TAs), and administrative tasks. The system is designed using object-oriented principles, generic programming, and robust exception handling to ensure smooth operation and maintainability.

### [GitHub Repo](https://github.com/YashVerma-251103/AP/tree/eb2123f9765d5de6635c4208d2be034ee715457f/Assignment2)

## Generic Programming

### Pair Class

The `Pair` class is a generic class that holds a pair of objects. It is defined as follows:

```java
public class Pair<T1, T2> {
    private T1 first;
    private T2 second;

    public Pair() {
        this.first = null;
        this.second = null;
    }

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 first, T2 second) {
        return new Pair<>(first, second);
    }

    // Getters, Setters, toString, hashCode, equals methods
}
```
This class stores pairs of related objects, such as a student and their grade, making the code more flexible and reusable.

## Object-Oriented Classes
### Main Classes
1. User Interface
    - User: An interface that defines the basic methods for a user (get_email, get_password, set_email, set_password).
2. CommonUser Class
    - CommonUser: Implements the User interface and provides common attributes and methods for all users.
3. Admin Class
    - Admin: Extends CommonUser and adds attributes and methods specific to administrative tasks.
4. Professor Class
    - Professor: Extends CommonUser and adds attributes and methods specific to professors, such as managing courses and assigning grades.
5. Student Class
    - Student: Extends CommonUser and adds attributes and methods specific to students, such as registering for courses and tracking academic progress.
6. TAs Class
    - TAs: Extends Student and adds attributes and methods specific to teaching assistants.

### Inheritance and Interfaces
- The CommonUser class implements the User interface, ensuring that all user types have common methods.
- The Admin, Professor, Student, and TAs classes extend CommonUser, inheriting its attributes and methods while adding their own specific functionalities.

## Exception Handling
### Custom Exceptions
1. CourseFull Exception
    - Thrown when a course reaches its maximum capacity.
```java
  public class CourseFull extends Exception {
    public CourseFull(Course course) {
        super("Course " + course.get_course_name() + " has reached its maximum capacity.");
    }
}
```
2. InvalidLogin Exception
    - Thrown when a user provides invalid login credentials.
  ```java
  public class InvalidLogin extends Exception {
    public InvalidLogin(String user) {
        super("Invalid Login Credentials for " + user);
    }
}
```
3. DropDeadlineExpired Exception
    - Thrown when a student tries to drop a course after the deadline.
```java
public class DropDeadlineExpired extends Exception {
    public DropDeadlineExpired(Course course) {
        super("Date to Drop the course " + course.get_course_name() + " has passed.");
    }
}
```
### Usage in Code
  - These custom exceptions are used throughout the code to handle specific error conditions, providing meaningful error messages and ensuring the program can handle unexpected situations gracefully.

