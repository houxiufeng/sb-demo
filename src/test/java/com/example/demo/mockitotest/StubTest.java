package com.example.demo.mockitotest;

import com.example.demo.service.ArticleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StubTest {
    @Mock
    private List<String> mockList;
    @Mock
    private ArticleService mockArticleService;
    @Spy
    private ArticleService spyArticleService;


    /**
     * 指定返回值
     */
    @Test
    public void test1() {
        //方法1
        doReturn("zero").when(mockList).get(0);
        //junit5 里面自带的断言，如果返回值不相等则单元测试失败。
        Assertions.assertEquals("zero", mockList.get(0));

        //方法2
        when(mockList.get(0)).thenReturn("one");
        Assertions.assertEquals("one", mockList.get(0));

    }

    /**
     * void返回值方法插桩
     */
    @Test
    public void test2() {
        doNothing().when(mockList).clear();
        mockList.clear();
        verify(mockList, times(1)).clear();

    }

    /**
     * 插桩的2种方式
     * 注意使用doXxx().when(obj).someMethod(); 可以防止spy方法执行原方法逻辑.适用于spy/mock, 而且还可以用于无返回值方法的情况.
     */
    @Test
    public void test3() {
        when(mockArticleService.getNumber(anyInt())).thenReturn(99);
        int number = mockArticleService.getNumber(1);
        //99,实际并未执行方法.
        System.out.println(number);

        //spy对象在没有插桩时是调用真实的方法的，写在when 中会导致先执行一次原方法，达不到mock的目的。
        when(spyArticleService.getNumber(anyInt())).thenReturn(98);
        int number1 = spyArticleService.getNumber(1);
        //98,而且实际上执行了原方法 cal getNumber
        System.out.println(number1);

        //使用doXxx().when(obj).someMethod(); 可以防止spy方法执行原方法逻辑。
        doReturn(1000).when(spyArticleService).getNumber(anyInt());
        int number2 = spyArticleService.getNumber(1);
        //1000, 没有执行原方法，不会打印 call getNumber
        System.out.println(number2);
    }

    /**
     * 抛出异常
     */
    @Test
    public void test4() {
        //方法1
        when(mockList.get(anyInt())).thenThrow(RuntimeException.class);
        try {
            mockList.get(3);
            //走到这里说明插桩失败
            Assertions.fail();
        } catch (Exception e) {
            //断言为真
            Assertions.assertTrue(e instanceof RuntimeException);
        }

        //方法2, 适用于无返回值的情况
        doThrow(RuntimeException.class).when(mockList).clear();
        try {
            mockList.clear();
            //走到这里说明插桩失败
            Assertions.fail();
        } catch (Exception e) {
            //断言为真
            Assertions.assertTrue(e instanceof RuntimeException);
        }

    }

    /**
     * 多次插桩
     */
    @Test
    public void test5() {
        //表示第一次调用返回1，第二次调用返回2，第三次及后面调用返回3
//        when(mockList.size()).thenReturn(1).thenReturn(2).thenReturn(3);
        //简写的方式
        when(mockList.size()).thenReturn(1,2,3);
        Assertions.assertEquals(1, mockList.size());
        Assertions.assertEquals(2, mockList.size());
        Assertions.assertEquals(3, mockList.size());
        Assertions.assertEquals(3, mockList.size());

    }

    /**
     * thenAnswer实现指定 逻辑 插桩, 返回的都是指定的固定值
     */
    @Test
    public void test6() {
        when(mockList.get(anyInt())).thenAnswer(new Answer<String>() {
            /**
             * 泛型表示插桩方法的返回值,这里就是String
             */
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                //getArgument表示获取插桩方法(此处就是list.get)的第几个参数
                Integer i = invocationOnMock.getArgument(0, Integer.class);
                return String.valueOf(i * 100);
            }
        });

        String s = mockList.get(3);
        Assertions.assertEquals("300", s);
        String s1 = mockList.get(4);
        Assertions.assertEquals("400", s1);

    }

    /**
     * 执行真正的原始方法
     */
    @Test
    public void test7() {
        //对mock对象进行插桩让它执行原始的方法逻辑。
        when(mockArticleService.getNumber(anyInt())).thenCallRealMethod();
        int number = mockArticleService.getNumber(10);
        Assertions.assertEquals(11, number);

        //spy对象默认就会执行真实方法，如果不想让它调用需要单独为它插桩
        int number1 = spyArticleService.getNumber(10);
        Assertions.assertEquals(11, number1);

        //spy插桩后不会执行真实方法
        doReturn(1000).when(spyArticleService).getNumber(anyInt());
        int number2 = spyArticleService.getNumber(10);
        Assertions.assertEquals(1000, number2);

    }

    /**
     * 测试verify
     */
    @Test
    public void test8() {
        mockList.add("one");
        //调用mock对象的写操作是没有效果的
        Assertions.assertEquals(0, mockList.size());
        mockList.clear();
        //指定要验证的方法和参数，注意这里并不是调用，也不会产生调用效果。
        //验证调用过1次add方法,且参数必须是one.
        verify(mockList).add("one");
        //verify(mockList, times(1)) 等价于 verify(mockList)
        verify(mockList, times(1)).clear();

        //校验没有调用的两种方式
        verify(mockList, times(0)).get(anyInt());
        verify(mockList, never()).get(anyInt());

        //校验最少或者最多调用了多少次
        verify(mockList, atLeast(1)).clear();
        verify(mockList, atMost(1)).clear();

    }

}
