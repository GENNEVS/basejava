package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        Object key = getNotExistKey(resume.getEmail());
        saveResume(resume, key);
    }

    @Override
    public void update(Resume resume) {
        Object key = getExistKey(resume.getEmail());
        updateResume(resume, key);
    }

    @Override
    public Resume get(String email) {
        Object key = getExistKey(email);
        return getResume(email, key);
    }

    @Override
    public void delete(String email) {
        Object key = getExistKey(email);
        deleteResume(email, key);
    }

    protected Object getExistKey(String email) {
        Object key = getKey(email);
        if (!existResume(key)) {
            throw new NotExistStorageException(email);
        }

        return key;
    }

    protected Object getNotExistKey(String email) {
        Object key = getKey(email);
        if (existResume(key)) {
            throw new ExistStorageException(email);
        }

        return key;
    }

    protected abstract Object getKey(String email);

    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract Resume getResume(String email, Object key);

    protected abstract void deleteResume(String email, Object key);

    protected abstract boolean existResume(Object key);
}
