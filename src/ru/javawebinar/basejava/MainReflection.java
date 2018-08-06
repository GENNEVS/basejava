package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume();
        System.out.println(resume);
        // Invoke resume.toString via Reflection
        Method toString = resume.getClass().getDeclaredMethod("toString");
        System.out.println(toString.invoke(resume));
    }
}
