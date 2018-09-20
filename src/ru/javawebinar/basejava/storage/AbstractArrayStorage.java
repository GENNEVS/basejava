package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.fill;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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
    protected void saveResume(Resume resume, Integer index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertResume(resume, index);
            size++;
        }
    }

    @Override
    protected void updateResume(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    protected void deleteResume(Integer index) {
        delete(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage[index];
    }

    @Override
    protected List<Resume> getAllResumes() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    protected boolean existResume(Integer index) {
        return index >= 0;
    }

    protected abstract void delete(int index);

    protected abstract void insertResume(Resume resume, int index);
}
