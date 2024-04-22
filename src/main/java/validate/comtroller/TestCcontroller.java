package validate.comtroller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import validate.params.PwdFindParams;

@RestController
public class TestCcontroller {

    @PostMapping("/api/test")
    public boolean test(@Validated @RequestBody PwdFindParams params){
        return params!=null;
    }
}
