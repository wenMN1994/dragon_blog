package com.dragon.common.exception.file;

import com.dragon.common.exception.base.BaseException;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/17 16:27
 * @description： 文件信息异常类
 * @modified By：
 * @version: 1.0.0
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
