package com.vishop.web.front.user.form.validator;

import com.vishop.core.util.CommRegular;
import com.vishop.core.util.StringUtils;
import com.vishop.entity.common.MapContainer;
import com.vishop.web.front.user.form.LoginForm;
import com.vishop.web.front.user.form.RegistForm;

/**
 * Created with vishop.
 * User : Homiss
 * Date : 2016/1/11
 * Time : 20:17
 */
public class UserFormValidator {

    public static MapContainer validateLogin(LoginForm form) {
        MapContainer result = new MapContainer();
        if(StringUtils.isBlank(form.getUsername()) || !form.getUsername().matches(CommRegular.USERNAME)){
            result.put("username", "请输入正确的用户名");
        }
        if(StringUtils.isBlank(form.getPassword()) || !form.getPassword().matches(CommRegular.PASSWD)){
            result.put("password", "密码输入有误");
        }
        return result;
    }

    public static MapContainer validateRegist(RegistForm form) {
        MapContainer result = new MapContainer();
        if(StringUtils.isBlank(form.getUsername()) || !form.getUsername().matches(CommRegular.USERNAME)){
            result.put("username", "请输入正确的用户名");
        }
        if(StringUtils.isBlank(form.getPassword()) || !form.getPassword().matches(CommRegular.PASSWD)){
            result.put("password", "密码输入有误");
        }
        if(!form.getPassword().equals(form.getRepassword())){
            result.put("repassword", "两次密码输入不同");
        }
        return result;
    }
}
