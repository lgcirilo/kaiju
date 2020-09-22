package com.lgcirilo.auxiliaryclasses;

public class TaskText {
    String text;

    public TaskText() {
    }

    public TaskText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "taskText{" +
                "text='" + text + '\'' +
                '}';
    }
}
