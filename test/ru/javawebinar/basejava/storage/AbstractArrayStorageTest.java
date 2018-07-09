package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private int storageLimit;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(String fieldName, Storage obj) throws NoSuchFieldException, IllegalAccessException {
        Field storageField = this.getClass().getSuperclass().getDeclaredField(fieldName);
        storageField.setAccessible(true);

        /*
        Field fieldStorageLimit = obj.getClass().getSuperclass().getDeclaredField("STORAGE_LIMIT");
        fieldStorageLimit.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(fieldStorageLimit, fieldStorageLimit.getModifiers() & ~Modifier.FINAL);
        fieldStorageLimit.set(obj, new Integer(3));
        */

        storageField.set(this, obj);
        storageLimit = obj.getClass().getSuperclass().getDeclaredField("STORAGE_LIMIT").getInt(obj);
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clearTest() throws Exception {
        Assert.assertEquals(3,storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void saveTest() throws Exception {
        final String UUID_4 = "uuid4";

        Assert.assertEquals(new Resume(UUID_3), storage.get(UUID_3));
        storage.save(new Resume(UUID_4));
        Assert.assertEquals(4,storage.size());
        Assert.assertEquals(new Resume(UUID_4), storage.get(UUID_4));
    }

    @Test
    public void updateTest() throws Exception {
        Resume resume = new Resume(UUID_3);
        storage.update(resume);
        Assert.assertEquals(3,storage.size());
        Assert.assertEquals(resume, storage.get(UUID_3));
    }

    @Test
    public void deleteTest() throws Exception {
        storage.delete(UUID_3);
        Assert.assertEquals(2,storage.size());
    }

    @Test
    public void getTest() throws Exception {
        Assert.assertEquals(new Resume(UUID_1),storage.get(UUID_1));
    }

    @Test
    public void getAllTest() throws Exception {
        Resume[] resumes = storage.getAll();
        Assert.assertTrue(resumes.length == storage.size());
        Assert.assertEquals(resumes[0], storage.get(UUID_1));
    }

    @Test
    public void sizeTest() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void NotExistStorageExceptionTest() throws Exception {
        final String NOTEXIST_RESUME = "dummy";

        storage.get(NOTEXIST_RESUME);
        storage.delete(NOTEXIST_RESUME);
        storage.update(new Resume(NOTEXIST_RESUME));
    }

    @Test(expected = ExistStorageException.class)
    public void ExistStorageExceptionTest() throws Exception {
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void OverflowStorageExceptionTest() throws Exception {
        try {
            for (int i = storage.size(); i <= storageLimit; i++) {
                storage.save(new Resume());
            }
            Assert.fail("StorageException not thrown");

        } catch (Exception e) {
            Assert.assertTrue(e.getClass().getSimpleName().equals("StorageException"));
        }
    }
}