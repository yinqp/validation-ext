package validate.params;

import jakarta.validation.constraints.Max;
import lombok.Data;
import validate.DateTimeString;
import validate.LeastOne;
import validate.Same;
import validate.SameNo;

import java.util.Date;


@Data
@LeastOne(fields = {"id","phone"})
//@SameNo(fields = {"password","passwordConfirm","passwordOld"})
public class PwdFindParams {

    private  PwdFindParams id;
    private String phone;

    @DateTimeString
    private Date date;

    private String password;

    private String passwordConfirm;

    private String passwordOld;

}
