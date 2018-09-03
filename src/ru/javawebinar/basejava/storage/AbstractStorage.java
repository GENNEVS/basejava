package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

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

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getAllResumes();
        Collections.sort(list);
        return list;
    }

    private Object getExistKey(String uuid) {
        Object key = getKey(uuid);
        if (!existResume(key)) {
            throw new NotExistStorageException(uuid);
        }

        return key;
    }

    private Object getNotExistKey(String uuid) {
        Object key = getKey(uuid);
        if (existResume(key)) {
            throw new ExistStorageException(uuid);
        }

        return key;
    }

    protected abstract List<Resume> getAllResumes();

    protected abstract Object getKey(String uuid);

    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract Resume getResume(String uuid, Object key);

    protected abstract void deleteResume(String uuid, Object key);

    protected abstract boolean existResume(Object key);
}
