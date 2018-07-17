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
    private int storageLimit = AbstractArrayStorage.STORAGE_LIMIT;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String NOTEXIST_RESUME = "dummy";

    private Resume resume_1 = new Resume(UUID_1);
    private Resume resume_2 = new Resume(UUID_2);
    private Resume resume_3 = new Resume(UUID_3);
    private Resume resume_4 = new Resume(UUID_4);
    private Resume notExistResume = new Resume(NOTEXIST_RESUME);

    public AbstractArrayStorageTest(Storage obj) throws NoSuchFieldException, IllegalAccessException {
        Field storageField = this.getClass().getSuperclass().getDeclaredField("storage");
        storageField.setAccessible(true);
        storageField.set(this, obj);
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume_1);
        storage.save(resume_2);
        storage.save(resume_3);
    }

    @Test
    public void testCleart() throws Exception {
        Assert.assertEquals(3,storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void testSave() throws Exception {
        Assert.assertEquals(3,storage.size());
        storage.save(new Resume(UUID_4));
        Assert.assertEquals(4,storage.size());
    }

    @Test
    public void testSaveOverflowStorageException() throws Exception {
        try {
            for (int i = storage.size(); i <= storageLimit; i++) {
                storage.save(new Resume());
            }
            Assert.fail("StorageException not thrown");
        } catch (Exception e) {
            Assert.assertTrue(e.getClass().getSimpleName().equals("StorageException"));
        }
    }

    @Test(expected = ExistStorageException.class)
    public void testSaveExistStorageException() throws Exception {
        storage.save(resume_3);
    }

    @Test
    public void testUpdate() throws Exception {
        Resume resume = new Resume(UUID_3);
        storage.update(resume);

        Assert.assertEquals(3,storage.size());
        Assert.assertEquals(resume, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void testUpdateNotExistStorageException() {
        storage.update(notExistResume);
    }

    @Test
    public void testDelete() throws Exception {
        Assert.assertEquals(3,storage.size());
        storage.delete(UUID_3);
        Assert.assertEquals(2,storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void testDeleteNotExistStorageException() {
        storage.delete(NOTEXIST_RESUME);
    }

    @Test
    public void testGet() throws Exception {
        Assert.assertEquals(resume_1,storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void testGetNotExistStorageException() {
        storage.get(NOTEXIST_RESUME);
    }

    @Test
    public void getAllTest() throws Exception {
        Resume[] expected = new Resume[] {resume_1, resume_2, resume_3};

        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.save(resume_4);
        Assert.assertEquals(4, storage.size());
    }
}