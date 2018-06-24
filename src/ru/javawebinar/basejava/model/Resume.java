package ru.javawebinar.basejava.model;

/**
 * com.urise.webapp.model.Resume class
 */
public class Resume implements Comparable<Resume> {

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    // Unique identifier
    private String uuid;


    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Resume resume = (Resume) obj;

        return uuid.equals(resume.uuid);
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume r) {
        return uuid.compareTo(r.uuid);
    }
}
