package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        saveResume(resume, index);
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        updateResume(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        return getResume(uuid, index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        deleteResume(uuid, index);
    }

    @Override
    public Resume[] getAll() {
        return toArray();
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract Resume getResume(String uuid, int index);

    protected abstract void deleteResume(String uuid, int index);

    protected abstract Resume[] toArray();
}
