package com.dragon.admin.modules.sys.form;

import lombok.Data;

/**
 * 密码表单
 *
 * @author Dragon Wen
 */
@Data
public class PasswordForm {
    /**
     * 原密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;

}
