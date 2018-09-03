package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapHashStorage extends AbstractStorage {
    protected Map<Integer, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        storage.put(key.hashCode(), resume);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage.replace(key.hashCode(), resume);
    }

    @Override
    protected Resume getResume(String uuid, Object key) {
        return storage.get(key.hashCode());
    }

    @Override
    protected void deleteResume(String uuid, Object key) {
        storage.remove(key.hashCode());
    }

    @Override
    protected Object getKey(String uuid) {
        return uuid.hashCode();
    }

    @Override
    protected boolean existResume(Object key) {
        return storage.containsKey(key.hashCode());
    }

    @Override
    protected List<Resume> getAllResumes() {
        return new ArrayList<>(storage.values());
    }
}
