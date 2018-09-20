package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void saveResume(Resume resume, String key) {
        storage.put(key, resume);
    }

    @Override
    protected void updateResume(Resume resume, String key) {
        storage.replace(key, resume);
    }

    @Override
    protected Resume getResume(String key) {
        return storage.get(key);
    }

    @Override
    protected void deleteResume(String key) {
        storage.remove(key);
    }

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean existResume(String key) {
        return storage.containsKey(key);
    }

    @Override
    protected List<Resume> getAllResumes() {
        return new ArrayList<>(storage.values());
    }
}
