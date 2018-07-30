package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected boolean existResume(Resume resume) {
        return false;
    }

    @Override
    protected boolean hasAvailablePlace() {
        return false;
    }

    @Override
    protected void insertResume(Resume resume) {

    }

    @Override
    protected void updateResume(Resume resume) {

    }

    @Override
    protected Resume getResume(String uuid) {
        return null;
    }

    @Override
    protected void deleteResume(String uuid) {

    }

    @Override
    protected Resume[] toArray() {
        return new Resume[0];
    }
}
