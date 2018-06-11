import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int tail = -1;

    void clear() {
        Arrays.fill(this.storage, 0, this.size(), null);
        this.tail = -1;
    }

    void save(Resume r) {
        try {
            this.storage[++this.tail] = r;
        } catch (Exception e) {
            e.getMessage();
        }
    }

    Resume get(String uuid) {
        return null;
    }

    void delete(String uuid) {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] list = null;
        try {
            list = Arrays.copyOfRange(this.storage, 0, this.size());
        } catch (Exception e) {
            e.getMessage();
        }
        return  list;
    }

    int size() {
        return this.tail + 1;
    }
}
