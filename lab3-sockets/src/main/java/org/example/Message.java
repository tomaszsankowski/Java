package org.example;
import java.io.Serializable;

public class Message implements Serializable {
    private int number;
    private String content;
    private String username;

    public Message(int number, String content, String username) {
        this.number = number;
        this.content = content;
        this.username = username;
    }

    public int getNumber() { return number; }

    public String getContent() {
        return content;
    }

    public String getUsername() { return username; }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUsername(String username) { this.username = username; }
}
