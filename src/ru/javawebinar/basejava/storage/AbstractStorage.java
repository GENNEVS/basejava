package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        if (existResume(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else if (!hasAvailablePlace()) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertResume(resume);
        }
    }

    @Override
    public void update(Resume resume) {
        if (!existResume(resume)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResume(resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        if (!existResume(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        }

        return getResume(uuid);
    }

    @Override
    public void delete(String uuid) {
        if (!existResume(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        return toArray();
    }


    protected abstract boolean existResume(Resume resume);

    protected abstract boolean hasAvailablePlace();

    protected abstract void insertResume(Resume resume);

    protected abstract void updateResume(Resume resume);

    protected abstract Resume getResume(String uuid);

    protected abstract void deleteResume(String uuid);

    protected abstract Resume[] toArray();
}
