package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
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
    protected void saveResume(Resume resume, Resume key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Resume resume, Resume key) {
        storage.replace((key).getUuid(), resume);
    }

    @Override
    protected Resume getResume(Resume key) {
        return storage.get((key).getUuid());
    }

    @Override
    protected void deleteResume(Resume key) {
        storage.remove((key).getUuid());
    }

    @Override
    protected Resume getKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
        protected boolean existResume(Resume key) {
        return key != null;
    }

    @Override
    protected List<Resume> getAllResumes() {
        return new ArrayList<>(storage.values());
    }
}
