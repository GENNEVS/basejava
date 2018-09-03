package ru.javawebinar.basejava.model;

import java.util.UUID;

public class Resume implements Comparable<Resume> {

    private final String uuid;

    private String fullName;

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public Resume(String uuid) {
        this.uuid = uuid;
        this.fullName = "Anonymous";
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public int hashCode() {
        return (uuid + fullName).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Resume resume = (Resume) obj;

        return uuid.equals(resume.uuid) &&
                fullName.equals(resume.fullName);
    }

    @Override
    public String toString() {
        return "uuid=" + uuid + ", fullName=" + fullName;
    }

    @Override
    public int compareTo(Resume resume) {
        if (fullName.compareTo(resume.fullName) == 0) {
            return uuid.compareTo(resume.uuid);
        }

        return fullName.compareTo(resume.fullName);
    }
}
