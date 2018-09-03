package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.fill;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertResume(resume, (Integer) index);
            size++;
        }
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    @Override
    protected void deleteResume(String uuid, Object index) {
        deleteResume((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(String uuid, Object index) {
        return storage[(int) index];
    }

    @Override
    protected List<Resume> getAllResumes() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    protected boolean existResume(Object index) {
        return (int) index >= 0;
    }

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(Resume resume, int index);
}
