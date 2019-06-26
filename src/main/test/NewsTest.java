import com.bluemsun.news.entity.NewsType;
import com.bluemsun.news.service.SearchEngineService;
import net.sf.json.JSONObject;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
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
 * @author mafx
 * <p>单元测试类</p>
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
    @Test
    public void test4() throws Exception {
        String url;
        url="/news/keywordSearch?keyword=中国留学生在澳失踪已9个月 警方呼吁公众协助寻人";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Autowired
    TransportClient client;

    @Autowired
    SearchEngineService searchEngineService;
    @Test
    public void test5(){
        String index="news";
        String type="news";
        NewsType newsType=new NewsType();
        newsType.setId(1);
        newsType.setParam_name("政治");
        String jsonStr= JSONObject.fromObject(newsType).toString();
        IndexResponse response=client.prepareIndex(index,type,"1").setSource(jsonStr, XContentType.JSON).get();
        System.out.println(response.toString());
    }
    @Test
    public void test6(){
        System.out.println(searchEngineService.searchEng("一国两制"));
    }
}
