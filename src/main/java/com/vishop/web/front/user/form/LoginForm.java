package com.vishop.web.front.user.form;

import lombok.Data;

/**
 * Created with vishop.
 * User : Homiss
 * Date : 2016/1/11
 * Time : 21:26
 */
@Data
public class LoginForm {

    private String username;
    private String password;
    private boolean remeber;
    /* 防止后台暴力破解的字段 */
    private String guard;
    private String redirectURL;

}
