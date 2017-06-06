package com.example.ishiiaya.flyingbottle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Created by ishiiaya on 2017/05/20.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockTestClass {

    @Test
    public void test_test(){

        StringUtil list = mock(StringUtil.class);
        list.toString();
    }
}
