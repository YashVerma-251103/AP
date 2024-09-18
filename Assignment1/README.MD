#Project Overview
This project allows Students, Professors, and Admins, to manage various Courses offered by the IIITD. In Short its management system implemented in Java. It demonstrates several Object-Oriented Programming (OOP) concepts including inheritance, encapsulation, abstraction, and polymorphism as well as its relationships including aggregation, association, inheritance, dependancy, and composition. The system manages students, professors, and courses, providing functionality for course registration, academic progress tracking, and complaint management.

Link for UML Diagram: [UML Diagram](https://miro.com/app/board/uXjVLd338TU=/?share_link_id=727113134233)

Link for GitHub Repo: [GitHub Repo for Assignment 1](https://github.com/YashVerma-251103/AP/tree/1542971eb65dd4581da8d43cf3943583f0b20d30/Assignment1)

Brief Description of system.
In the package there are 3 user files (Admin.java, Profesor.java, and Student.java). User.java and CommonUser.java are structure files providing basic structure as well as baice funcitonalities for these 3 user files. Pair.java is the Data structure file which was reaquired to store associated values together. Complaint.java and Course.java are the functionalities that we requried by the Question. DataSeeder.java is the basic data feeder and sample object initializer used by the main ERP.java file.

How to run the program.
Make sure that all the 10 files in package are in good health and are compiling without any issues. If this is ensured, compile and run the ERP.java file to run the system. Please strictly follow the instructions prompted by the program otherwise there is a high chance of getting an error.

Assumptions
Some major assumption to keep in mind:
1. Regarding Passing Semester and assigning Grades: Here Professor assigns the grades to student in his own course. And Admin upgrades the semester of students if marks are assigned for all courses for the current semester.
2. Regarding GPAs : SGPA is the average of the grades recieved during that particular semester. CGPA is the average if the SGPAs till last semester of the student.
3. Professor and Course Association: Each professor only teaches one course and has full authority to manage the course properties. 



OOP Concepts Applied
1. Inheritance: Student and Professor classes inherit from the CommonUser class. This allows them to reuse code and maintain a consistent user interface.
2. CommonUser implements User, which defines the common behavior for all user types.
3. Encapsulation: Attributes of classes such as Student, Course, and Professor are private and can only be accessed or modified through public getter and setter methods.
4. Abstraction: The User interface provides a level of abstraction by defining methods without specifying how they are implemented. This allows different user types to implement the interface in their own way.
5. Polymorphism: The User interface allows different user types (like Student and Professor) to be used interchangeably where User is expected.


OOP Relationships
1. Aggregation: Course objects are aggregated in Student and Professor classes. For example, a student has multiple courses, but the existence of a course is independent of a student.
2. Association: Student and Course have an association relationship. A student can register for and drop multiple courses, and courses can have multiple students enrolled.
3. Inheritance: Student and Professor inherit from CommonUser, demonstrating inheritance by extending the base class and inheriting its properties and methods.
4. Dependency: Student and Professor classes depend on Course to perform operations such as enrolling or dropping courses. This is a one-way dependency where changes in Course can affect Student and Professor.
5. Composition: Student class contains multiple instances of HashMap to manage courses, grades, and completed courses. These HashMap instances are integral to the student’s functionality and lifecycle.
