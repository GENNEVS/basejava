package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    private Comparator<Resume> ResumeComparator = Comparator.comparing(Resume::getUuid);

    @Override
    protected Integer getKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, ResumeComparator);
    }

    @Override
    protected void insertResume(Resume resume, int index) {
        int indexOfInsert = -(index + 1);
        System.arraycopy(storage, indexOfInsert, storage, indexOfInsert + 1, size - indexOfInsert);
        storage[indexOfInsert] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}
