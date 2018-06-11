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
        int index = this.getIndex(uuid);
        if (index < 0) {
            return null;
        }
        return this.storage[index];
    }

    /**
     *
     * @param uuid
     * @return index of the element with uuid that equals to param
     */
    int getIndex(String uuid) {
        int i = -1;
        boolean found = false;

        while(!found && ++i <= this.tail) {
            found = this.storage[i].uuid.equals(uuid);
        }
        return found? i:-1;
    }


    void delete(String uuid) {
        int index = this.getIndex(uuid);

        for(int i = index; i < this.tail; i++) {
            this.storage[i] = this.storage[i + 1];
            this.storage[i + 1] = null;
        }

        this.tail--;
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
