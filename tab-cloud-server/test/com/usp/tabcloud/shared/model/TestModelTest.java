package com.usp.tabcloud.shared.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestModelTest extends AppEngineTestCase {

    private TestModel model = new TestModel();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
