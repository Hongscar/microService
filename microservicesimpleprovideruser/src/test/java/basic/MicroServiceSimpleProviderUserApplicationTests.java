//package basic;
//
//import basic.controller.UserController;
//import org.json.JSONObject;
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * @Author: Seth
// * @Description:
// * @Date: Created in 10:46 2019/11/27
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MicroServiceSimpleProviderUserApplication.class)
//class MicroServiceSimpleProviderUserApplicationTests {
//
//    private MockMvc mvc;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
//    }
//
//    @Test
//    public void getHello() throws Exception {
//        // mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
////		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON)).
////				andExpect(status().isOk()).
////				andExpect(content().string(equalTo
////						("{\"id\":2,\"username\":\"account2\",\"name\":\"洪\",\"age\":28,\"balance\":180}")));
//        MvcResult result = null;
//        result = mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON).param("id", "2"))
//                .andExpect(status().isOk()).andReturn();
//        JSONObject object = new JSONObject(result.getResponse().getContentAsString());
//        System.out.println(object);
//    }
//
//    @Test
//    public void contextLoads() {
//    }
//
//}
