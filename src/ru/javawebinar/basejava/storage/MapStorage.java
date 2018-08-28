package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;
import java.util.stream.Collectors;

public class MapStorage extends AbstractStorage {
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
    public List<Resume> getAllSorted() {
        List<Resume> list = storage.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey, Comparator.naturalOrder()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        return list;
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        storage.put((String) key, resume);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage.replace((String) key, resume);
    }

    @Override
    protected Resume getResume(String uuid, Object key) {
        return storage.get(key);
    }

    @Override
    protected void deleteResume(String uuid, Object key) {
        storage.remove(key);
    }

    @Override
    protected Object getKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean existResume(Object key) {
        return storage.containsKey(key);
    }
}
