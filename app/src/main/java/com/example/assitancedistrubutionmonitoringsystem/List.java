package com.example.assitancedistrubutionmonitoringsystem;

public class List {


    String IDnumber;
    String FirstName;
    String MiddleName;
    String LastName;
    String Contact;
    String Email;
    String Gender;
    String Status;
    String TypeofAssitance;
    String Amount;
    String Date;
    String SponsoredBy;
    String NotedBy;
    String NoteDate;
    String Time;

    List(){


    }


    public List(String IDnumber,String firstName, String middleName, String lastName, String contact, String email, String gender, String status, String type_of_Assitance, String amount, String date, String sponsored_by,String notedBy,
               String noteDate, String time) {

        this.IDnumber = IDnumber;
        FirstName = firstName;
        MiddleName = middleName;
        LastName = lastName;
        Contact = contact;
        Email = email;
        Gender = gender;
        Status = status;
        TypeofAssitance = type_of_Assitance;
        Amount = amount;
        Date = date;
        SponsoredBy = sponsored_by;
        NotedBy = notedBy;
        NoteDate = noteDate;
        Time = time;
    }

    public  String getNoteDate(){
        return NoteDate;
    }
    public void setNoteDate(String noteDate){
        Time = noteDate;
    }

    public  String getTime(){
        return Time;
    }
    public void setTime(String time){
        Time = time;
    }

    public  String getNotedBy(){
        return NotedBy;
    }
    public void setNotedBy(String notedBy){
        NotedBy = notedBy;
    }


    public  String getIDnumber(){
        return  IDnumber;
    }
   public  void  setIDnumber(String iDnumber){
        IDnumber = iDnumber;

   }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTypeofAssitance() {
        return TypeofAssitance;
    }

    public void setTypeofAssitance(String type_of_Assitance) {
        TypeofAssitance = type_of_Assitance;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSponsoredBy() {
        return SponsoredBy;
    }

    public void setSponsoredBy(String sponsored_by) {
        SponsoredBy = sponsored_by;
    }
}


