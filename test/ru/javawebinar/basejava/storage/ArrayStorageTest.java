package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import static org.junit.Assert.*;

public class ArrayStorageTest extends AbstractArrayStorageTest {
    public ArrayStorageTest() throws IllegalAccessException, NoSuchFieldException {
        super("storage", new ArrayStorage());
    }

}