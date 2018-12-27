
package com.game;

import com.game.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * <p>
 * 使用rest-assured框架，测试controller
 * </p>
 *
 * @author yinhang
 * @date 2018/3/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JunitTest {

    @Test
    public void test() {
    }

    public static void main(String[] args) {
    }
}
