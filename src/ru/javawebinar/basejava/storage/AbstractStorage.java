package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK key = getNotExistKey(resume.getUuid());
        saveResume(resume, key);
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK key = getExistKey(resume.getUuid());
        updateResume(resume, key);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK key = getExistKey(uuid);
        return getResume(key);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK key = getExistKey(uuid);
        deleteResume(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = getAllResumes();
        Collections.sort(list);
        return list;
    }

    private SK getExistKey(String uuid) {
        SK key = getKey(uuid);
        if (!existResume(key)) {
            throw new NotExistStorageException(uuid);
        }

        return key;
    }

    private SK getNotExistKey(String uuid) {
        SK key = getKey(uuid);
        if (existResume(key)) {
            throw new ExistStorageException(uuid);
        }

        return key;
    }

    protected abstract List<Resume> getAllResumes();

    protected abstract SK getKey(String uuid);

    protected abstract void saveResume(Resume resume, SK key);

    protected abstract void updateResume(Resume resume, SK key);

    protected abstract Resume getResume(SK key);

    protected abstract void deleteResume(SK key);

    protected abstract boolean existResume(SK key);
}
