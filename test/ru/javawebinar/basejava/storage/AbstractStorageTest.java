package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private Resume resume_1 = new Resume(UUID_1);
    private Resume resume_2 = new Resume(UUID_2);
    private Resume resume_3 = new Resume(UUID_3);
    private Resume resume_4 = new Resume(UUID_4);

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
        storage.update(resume_4);
    }

    @Test
    public void testDelete() {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void testDeleteNotExistStorageException() {
        storage.delete(UUID_4);
    }

    @Test
    public void testGet() {
        Assert.assertEquals(resume_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void testGetNotExistStorageException() {
        storage.get(UUID_4);
    }

    @Test
    public void getAllTest() {
        List<Resume> expected = Arrays.asList(resume_1, resume_2, resume_3);
        Assert.assertArrayEquals(expected.toArray(), storage.getAllSorted().toArray());
    }

    @Test
    public void testSize() {
        Assert.assertEquals(3, storage.size());
    }
}
