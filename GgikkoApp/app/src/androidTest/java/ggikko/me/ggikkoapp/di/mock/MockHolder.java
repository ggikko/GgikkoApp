package ggikko.me.ggikkoapp.di.mock;

import static org.mockito.Mockito.mock;

import android.support.annotation.Nullable;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

/**
 * Created by ggikko on 2016. 8. 17..
 */
public class MockHolder {

    @Getter private Map<Type, Object> mockMap = new HashMap<>();

    private static MockHolder staticMockHolder;

    static void init(MockHolder mockHolder) {
        staticMockHolder = mockHolder;
    }

    public static void addMock(Type type, Object object) {
        staticMockHolder.add(type, object);
    }

    public static <T> T addMock(Type type) {
        T t = (T) mock(type.getClass());
        staticMockHolder.add(type, t);
        return t;
    }

    public static <T> T returnMockIfExist(Object object, Type type) {
        if (staticMockHolder.isExist(type)) {
            T t = staticMockHolder.get(type);
            if (t != null) {
                return t;
            }
        }

        return (T) object;
    }

    public void add(Type type, Object object) {
        if (object != null) {
            mockMap.put(type, object);
        }
    }

    public boolean isExist(Type type) {
        return mockMap.containsKey(type);
    }

    @Nullable
    public <T> T get(Type type) {
        if (mockMap.containsKey(type)) {
            return (T) mockMap.get(type);
        }

        return null;
    }
}
