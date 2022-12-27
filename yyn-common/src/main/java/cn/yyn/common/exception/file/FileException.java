package cn.yyn.common.exception.file;

import cn.yyn.common.exception.base.BaseException;

/**
 * 文件信息异常类
 * 
 * @author yyncode
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
