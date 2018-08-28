package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Resume> getAllSorted() {
        return storage.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
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
    protected Resume getResume(String uuid, Object key) {
        return storage.get((Integer) key);
    }

    @Override
    protected void deleteResume(String uuid, Object key) {
        storage.remove((int) key);
    }

    @Override
    protected Object getKey(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected boolean existResume(Object key) {
        return (int) key >= 0;
    }
}
