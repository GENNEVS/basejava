package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String NOT_EXIST_RESUME = "dummy";

    private Resume resume_1 = new Resume(UUID_1);
    private Resume resume_2 = new Resume(UUID_2);
    private Resume resume_3 = new Resume(UUID_3);
    private Resume resume_4 = new Resume(UUID_4);
    private Resume notExistResume = new Resume(NOT_EXIST_RESUME);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume_1);
        storage.save(resume_2);
        storage.save(resume_3);
    }

    @Test
    public void testClear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void testSave() {
        storage.save(resume_4);
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void testSaveExistStorageException() {
        storage.save(resume_3);
    }

    @Test
    public void testUpdate() {
        storage.update(resume_3);
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(resume_3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void testUpdateNotExistStorageException() {
        storage.update(notExistResume);
    }

    @Test
    public void testDelete() {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void testDeleteNotExistStorageException() {
        storage.delete(NOT_EXIST_RESUME);
    }

    @Test
    public void testGet() {
        Assert.assertEquals(resume_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void testGetNotExistStorageException() {
        storage.get(NOT_EXIST_RESUME);
    }

    @Test
    public void getAllTest() {
        Resume[] expected = {resume_1, resume_2, resume_3};
        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test
    public void testSize() {
        Assert.assertEquals(3, storage.size());
    }
}
