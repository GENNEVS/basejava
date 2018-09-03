package ru.javawebinar.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses( {
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapHashStorageTest.class
})
@RunWith(Suite.class)
public class AllTestsRunner {
}
