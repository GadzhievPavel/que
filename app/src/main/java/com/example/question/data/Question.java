package com.example.question.data;

public class Question {
    private int id;
    private String text;
    private int rate;
    private boolean done;

    public Question(){

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    public void setDone(int i){
        Integer integer = i;
        this.done=integer.equals(1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
