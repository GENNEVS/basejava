package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
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
    protected void saveResume(Resume resume, Integer key) {
        storage.add(resume);
    }

    @Override
    protected void updateResume(Resume resume, Integer key) {
        storage.set(key, resume);
    }

    @Override
    protected Resume getResume(Integer key) {
        return storage.get(key);
    }

    @Override
    protected void deleteResume(Integer key) {
        storage.remove((int) key);
    }

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    protected boolean existResume(Integer key) {
        return key >= 0;
    }

    @Override
    protected List<Resume> getAllResumes() {
        return new ArrayList<>(storage);
    }
}
