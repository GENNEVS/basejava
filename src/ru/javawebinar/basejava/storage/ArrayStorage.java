package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
      public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Resume " + r.getUuid() + " can't be saved: storage is full!");
        } else if (getIndex(r.getUuid()) != -1){
            System.out.println("Resume " + r.getUuid() + " is in the storage.");
        } else {
            storage[size++] = r;
        }

    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Resume  " + r.getUuid() + " isn't in the storage.");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + uuid + " isn't in the storage.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    /**
     *
     * @param uuid
     * @return index of the element with uuid that equals to param
     */
    protected int getIndex(String uuid) {
        for(int i = 0; i < size; i++ ) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
