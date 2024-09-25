package Assignment2;

import java.util.HashMap;
import java.util.Scanner;

public class Complaint { // made -- testing left
    public static Scanner complaint_sc = new Scanner(System.in);
    // Attributes
    private static Integer current_complaint_id = 0;
    private Integer complaint_id;
    private Student student;
    private String complaint;
    private String final_response;
    private Boolean status;
    private Admin admin;
    // private Pair<String,String> conversation_thread = new Pair<>();
    // private ArrayList<Pair<String,String>> conversation_thread = new ArrayList<Pair<String,String>>();

    // Storage and Shared DataBase
    protected static HashMap<Integer,Complaint> complaint_db = new HashMap<Integer,Complaint>();
    // Structure == HashMap<Complaint_id,Complaint>
    
    // Getters
    public static Integer get_current_complaint_id() {
        return current_complaint_id;
    }
    public Integer get_complaint_id() {
        return complaint_id;
    }
    public Student get_student() {
        return student;
    }
    public String get_complaint() {
        return complaint;
    }
    public String get_final_response() {
        return final_response;
    }
    public Boolean get_status() {
        return status;
    }
    public Admin get_admin() {
        return admin;
    }
    // public ArrayList<Pair<String,String>> get_conversation_thread() {
    //     return this.conversation_thread;
    // }
    
    // Setters
    public void set_complaint_id(Integer complaint_id) {
        this.complaint_id = complaint_id;
    }
    public void set_student(Student student) {
        this.student = student;
    }
    public void set_complaint(String complaint) {
        this.complaint = complaint;
    }
    public void set_response(String response) {
        this.final_response = response;
    }
    public void set_status(Boolean status) {
        this.status = status;
    }
    public void set_admin(Admin admin) {
        this.admin = admin;
    }

    // Functions for easy implementation
    public static Complaint create_complaint(Student student, String complaint){
        Complaint new_complaint = new Complaint();
        new_complaint.set_complaint(student,complaint);
        return new_complaint;
    }
    public static void view_all_complaints(){
        System.out.println("Line checkS");
        for (Complaint complaint : complaint_db.values()) {
            complaint.view_complaint();
        }
    }
    public static void view_all_complaints(Boolean resolved){
        boolean trigger = true;
        System.out.println((resolved)?"Resolved Complaints:":"Pending Complaints:");
        for (Complaint complaint : complaint_db.values()) {
            if (complaint.get_status()==resolved){
                trigger = false;
                complaint.view_complaint();
            }
        }
        if (trigger){
            System.out.println("No Complaints Found!");
        }
    }
    public static void view_all_complaints(Student student){
        boolean trigger = true;
        System.out.println("Complaints by " + student.get_name() + ":");
        for (Complaint complaint : complaint_db.values()) {
            if (complaint.get_student().get_student_roll_number()==student.get_student_roll_number()){
                complaint.view_complaint();
                trigger = false;
            }
        }
        if (trigger){
            System.out.println("No Complaints Found!");
        }
    }
    public static void view_all_complaints(Admin admin){
        boolean trigger = true;
        System.out.println("Complaints resolved by " + admin.get_name() + ":");
        for (Complaint complaint : complaint_db.values()) {
            if (complaint.get_admin().get_admin_id()==admin.get_admin_id()){
                complaint.view_complaint();
                trigger = false;
            }
        }
        if (trigger){
            System.out.println("No Complaints Found!");
        }
    }
    public void show_complaint_status(){
        System.out.println("Complaint ID: " + this.complaint_id);
        System.out.println("Status: " + ((this.status)?"Resolved":"Pending"));
    }
    public void set_complaint(Student student,String Complaint){
        this.student = student;
        this.complaint = Complaint;
        this.status = false;
        this.complaint_id = current_complaint_id;
        current_complaint_id++;
        complaint_db.put(this.complaint_id,this);
    }
    public void resolve_complaint(String response){
        this.final_response = response;
        this.status = true;
    }
    public void view_complaint(){
        System.out.println("Complaint ID: " + this.complaint_id);
        System.out.println("Student: " + this.student.get_name());
        System.out.println("Complaint: " + this.complaint);
        if (this.admin == null){
            System.out.println("Admin: Not Assigned");
            
        } else {
            System.out.println("Admin: " + this.admin.get_name());
        }
        if (this.final_response != null){
            System.out.println("Response: " + this.final_response);
        }
        if (this.status){
            System.out.println("Status: Resolved");
        } else {
            System.out.println("Status: Pending");
        }
    }
    
}
