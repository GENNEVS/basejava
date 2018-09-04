package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

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
        storage.add(resume);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage.set((Integer) key, resume);
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove((int) key);
    }

    @Override
    protected Object getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    protected boolean existResume(Object key) {
        return (int) key >= 0;
    }

    @Override
    protected List<Resume> getAllResumes() {
        return new ArrayList<>(storage);
    }
}
