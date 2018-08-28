package ru.javawebinar.basejava.model;

public class Resume implements Comparable<Resume> {

    private final String email;
    private String fullName;

    public Resume(String email) {
        this.email = email;
    }

    public Resume(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Resume resume = (Resume) obj;

        return email.equals(resume.email);
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public int compareTo(Resume resume) {
        return email.compareTo(resume.email);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
