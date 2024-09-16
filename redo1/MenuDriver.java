package redo1;

public class MenuDriver { // left{
    public static void main(String[] args) {
    Admin ad = Admin.create_admin();
    Student st = Student.create_student();
    Professor pr = Professor.create_professor();
    Course cr = Course.create_course(pr);
    Complaint cm = Complaint.create_complaint(st,"complaint");}}

