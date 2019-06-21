import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by mafx on 2019/5/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent",classes = ApplicationConfig.class),
        @ContextConfiguration(name = "child", classes = SpringMvcConfig.class)
         })
public class NewsTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void test1() throws Exception {
        String url;
        url="/news/getAllNews";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    public void test2() throws Exception {
        String url;
        url="/news/getNewsType";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    public void test3() throws Exception {
        String url;
        url="/news/sortNewsByAccessNum/0";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
