package com.example.assitancedistrubutionmonitoringsystem;

public class coat {
    String id, Tittle, Message,DatePost,TimePost;
    public coat(){

    }

    public coat(String id, String Tittle,String Message,String DatePost,String TimePost){

        this.id = id;
        this.Tittle = Tittle;
        this.Message = Message;
        this.DatePost = DatePost;
        this.TimePost = TimePost;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDatePost() {
        return DatePost;
    }

    public void setDatePost(String datePost) {
        this.id =datePost;
    }

    public String getTimePost() {
        return TimePost;
    }

    public void setTimePost(String timePost) {
        this.TimePost = timePost;
    }
}
