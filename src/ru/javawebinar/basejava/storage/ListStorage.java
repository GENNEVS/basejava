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
    protected boolean existResume(Resume resume) {
        return storage.contains(resume);
    }

    @Override
    protected boolean hasAvailablePlace() {
        return true;
    }

    @Override
    protected void insertResume(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void updateResume(Resume resume) {
        int index = storage.indexOf(resume);
        storage.set(index, resume);
    }

    @Override
    protected Resume getResume(String uuid) {
        int index = storage.indexOf(new Resume(uuid));
        return storage.get(index);
    }

    @Override
    protected void deleteResume(String uuid) {
        int index = storage.indexOf(new Resume(uuid));
        storage.remove(index);
    }

    @Override
    protected Resume[] toArray() {
        Resume[] resumes = new Resume[size()];
        return storage.toArray(resumes);
    }
}
