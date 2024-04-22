import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.ObjectUtils;
import validate.App;
import validate.params.PwdFindParams;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class SptringBootTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testParams()throws Exception{
        String test ="{\"id\":{},\"phone\":\"\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/api/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(test)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }


    @Test
    public void testParamsSame()throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/api/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"12345\",\"passwordConfirm\":\"12345\",\"passwordOld\":\"12345\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testParamsSameNo()throws Exception{
//        PwdFindParams p = new PwdFindParams();
//        p.setId(null);
//        p.setPhone("18910964043");
//        PwdFindParams p2 = new PwdFindParams();
//        p2.setId(null);
//        p2.setPhone("18910964044");
//        List<PwdFindParams> list1 = new ArrayList<>();
//        list1.add(p);
//        List<PwdFindParams> list2 = new ArrayList<>();
//        list2.add(p2);
//
//                ;
//        boolean b = ObjectUtils.nullSafeEquals(list1, list2);
//        System.out.println(b);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"12341\",\"passwordConfirm\":\"12349\",\"passwordOld\":\"12341\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
