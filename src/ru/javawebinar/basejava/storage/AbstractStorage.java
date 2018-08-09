package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        Object key = getKey(resume.getUuid());
        if (existResume(key)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume, key);
        }
    }

    @Override
    public void update(Resume resume) {
        Object key = getKey(resume.getUuid());
        if (!existResume(key)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResume(resume, key);
        }
    }

    @Override
    public Resume get(String uuid) {
        Object key = getKey(uuid);
        if (!existResume(key)) {
            throw new NotExistStorageException(uuid);
        } else {
            return getResume(uuid, key);
        }
    }

    @Override
    public void delete(String uuid) {
        Object key = getKey(uuid);
        if (!existResume(key)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(uuid, key);
        }
    }

    protected abstract Object getKey(String uuid);

    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract Resume getResume(String uuid, Object key);

    protected abstract void deleteResume(String uuid, Object key);

    protected abstract boolean existResume(Object key);
}
