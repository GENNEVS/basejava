package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapHashStorage extends AbstractStorage {
    protected Map<Resume, Resume> storage = new HashMap<>();

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
        storage.put((Resume) key, resume);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage.replace((Resume) key, resume);
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get(key);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove(key);
    }

    @Override
    protected Object getKey(String uuid) {
        for (Resume key : storage.keySet()) {
            if (key.getUuid().equals(uuid)) {
                return key;
            }
        }
        return new Resume(uuid, "Key Resume");
    }

    @Override
    protected boolean existResume(Object key) {
        return storage.containsKey(key);
    }

    @Override
    protected List<Resume> getAllResumes() {
        return new ArrayList<>(storage.values());
    }
}
