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
    protected void saveResume(Resume resume, int index) {

    }

    @Override
    protected void updateResume(Resume resume, int index) {

    }

    @Override
    protected Resume getResume(String uuid, int index) {
        return null;
    }

    @Override
    protected void deleteResume(String uuid, int index) {

    }

    @Override
    protected Resume[] toArray() {
        return new Resume[0];
    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }
}
