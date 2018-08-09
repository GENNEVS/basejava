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
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    protected void saveResume(Resume resume, Object key) {

    }

    @Override
    protected void updateResume(Resume resume, Object key) {

    }

    @Override
    protected Resume getResume(String uuid, Object key) {
        return null;
    }

    @Override
    protected void deleteResume(String uuid, Object key) {

    }

    @Override
    protected Object getKey(String uuid) {
        return null;
    }

    @Override
    protected boolean existResume(Object key) {
        return false;
    }
}
