package ggikko.me.ggikkoapp.di.mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Map;

import ggikko.me.ggikkoapp.ui.img.adapter.SearchAdapter;

/**
 * Created by ggikko on 2016. 8. 17..
 */
public class TestInjectorCreatorTest {
    @Test
    public void testMockHolder() throws Exception {

        MockHolder holder = new MockHolder();
        SearchAdapter searchAdapter = mock(SearchAdapter.class);
        holder.add(SearchAdapter.class, searchAdapter);

        Map<Type, Object> map = holder.getMockMap();
        for (Map.Entry<Type, Object> entry : map.entrySet()) {
            System.out.println("Class - " + entry.getKey().toString() + "\n Object - " + entry.getValue().toString() + "\n");
        }

        assertThat("Exist", holder.isExist(SearchAdapter.class));
        SearchAdapter mainPageParserFromHolder = holder.get(SearchAdapter.class);
        assertThat("Same?", searchAdapter, is(mainPageParserFromHolder));
    }
}
