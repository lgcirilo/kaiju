package com.lgcirilo.kaiju.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore // used in conjunction with FetchType. Lazy so exception is not thrown
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @NotNull
    private String text;
    private boolean isComplete;
    private LocalDateTime createdAt;

    public Task() {
    }

    public Task(@NotNull User user, String text) {
        this.user = user;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    private void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (!user.equals(task.user)) return false;
        return text != null ? text.equals(task.text) : task.text == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + user.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", isComplete=" + isComplete +
                ", createdAt=" + createdAt +
                '}';
    }
}