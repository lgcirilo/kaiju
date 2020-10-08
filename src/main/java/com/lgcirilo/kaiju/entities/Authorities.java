//package com.lgcirilo.kaiju.entities;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//public class Authorities {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private String id;
//
//    private String role;
//
//    @ManyToMany
//    private Set<User> users;
//
//    public Authorities(String role) {
//        this.role = role;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Authorities that = (Authorities) o;
//
//        if (!id.equals(that.id)) return false;
//        return role.equals(that.role);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id.hashCode();
//        result = 31 * result + role.hashCode();
//        return result;
//    }
//}
