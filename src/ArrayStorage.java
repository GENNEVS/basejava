import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (!isInStorage(r.uuid)) {
            if (size < storage.length) {
                storage[size++] = r;
            } else {
                System.out.println("Resume can't be saved: storage is full!");
            }
        } else {
            System.out.println("Resume is in the storage.");
        }

    }

    void update(Resume r) {
        int index = getIndex(r.uuid);
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Resume isn't in the storage.");
        }

    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Resume isn't in the storage.");
        }

        return null;
    }

    void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume isn't in the storage.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
    }

    /**
     *
     * @param uuid
     * @return index of the element with uuid that equals to param
     */
    private int getIndex(String uuid) {
        for(int i = 0; i < size; i++ ) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isInStorage(String uuid) {
        return getIndex(uuid) != -1;
    }
}
