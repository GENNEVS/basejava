package ru.javawebinar.basejava.model;

public class Resume implements Comparable<Resume> {

    private final String uuid;

    private String fullName;

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
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
        int compareByName = fullName.compareTo(resume.fullName);
        return compareByName == 0 ? uuid.compareTo(resume.uuid) : compareByName;
    }
}
