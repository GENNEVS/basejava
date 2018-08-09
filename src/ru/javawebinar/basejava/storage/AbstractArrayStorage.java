package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertResume(resume, (Integer) key);
            size++;
        }
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage[(int) key] = resume;
    }

    @Override
    protected void deleteResume(String uuid, Object key) {
        deleteResume((Integer) key);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(String uuid, Object key) {
        return storage[(int) key];
    }

    protected boolean existResume(Object key) {
        return (int) key >= 0;
    }

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(Resume resume, int index);


}
