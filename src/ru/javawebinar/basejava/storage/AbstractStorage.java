package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        Object key = getNotExistKey(resume.getUuid());
        saveResume(resume, key);
    }

    @Override
    public void update(Resume resume) {
        Object key = getExistKey(resume.getUuid());
        updateResume(resume, key);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getExistKey(uuid);
        return getResume(uuid, key);
    }

    @Override
    public void delete(String uuid) {
        Object key = getExistKey(uuid);
        deleteResume(uuid, key);
    }

    protected Object getExistKey(String uuid) {
        Object key = getKey(uuid);
        if (!existResume(key)) {
            throw new NotExistStorageException(uuid);
        }

        return key;
    }

    protected Object getNotExistKey(String uuid) {
        Object key = getKey(uuid);
        if (existResume(key)) {
            throw new ExistStorageException(uuid);
        }

        return key;
    }

    protected abstract Object getKey(String uuid);

    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract Resume getResume(String uuid, Object key);

    protected abstract void deleteResume(String uuid, Object key);

    protected abstract boolean existResume(Object key);
}
