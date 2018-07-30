package ru.javawebinar.basejava.storage;

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
    protected boolean existResume(Resume resume) {
        int index = getIndex(resume.getUuid());

        return index >= 0;
    }

    @Override
    protected boolean hasAvailablePlace() {
        return size < STORAGE_LIMIT;
    }

    @Override
    protected void insertResume(Resume resume) {
        insertResume(resume, getIndex(resume.getUuid()));
        size++;
    }

    @Override
    protected void updateResume(Resume resume) {
        int index = getIndex(resume.getUuid());
        storage[index] = resume;
    }

    @Override
    protected void deleteResume(String uuid) {
        int index = getIndex(uuid);
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(String uuid) {
        int index = getIndex(uuid);
        return storage[index];
    }

    @Override
    protected Resume[] toArray() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void deleteResume(int index);
}
