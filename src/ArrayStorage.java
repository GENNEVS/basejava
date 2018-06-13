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
        storage[size++] = r;
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            return null;
        }
        return storage[index];
    }

    /**
     *
     * @param uuid
     * @return index of the element with uuid that equals to param
     */
    int getIndex(String uuid) {
        for(int i = 0; i < size; i++ ) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }


    void delete(String uuid) {
        int index = getIndex(uuid);
        System.arraycopy(storage,index + 1,storage,index,size - index - 1);
        storage[size - 1] = null;
        size--;
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
}
