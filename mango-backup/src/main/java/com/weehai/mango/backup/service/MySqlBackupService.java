package com.weehai.mango.backup.service;

/**
 * @Author: PercyLu
 * @Company: WeeHai
 * @Date: 2020/1/7 2:05 下午
 * @Version: 1.0
 */
public interface MySqlBackupService {
    /**
     * 备份数据库
     * @param host host地址，可以是本机或者远程
     * @param userName 数据库的用户名
     * @param password 数据库的密码
     * @param savePath 备份的路径
     * @param fileName 备份的文件名
     * @param databaseName 需要备份的数据库名称
     * @return
     * @throws java.io.IOException
     */
    boolean backup(String host,String userName,String password,String savePath, String fileName,
                   String databaseName) throws Exception;

    /**
     * 恢复数据库
     * @param restoreFilePath 数据库备份的脚本路径
     * @param host IP地址
     * @param database 数据库名称
     * @param userName 用户名
     * @param password 密码
     * @return
     * @throws Exception
     */
    boolean restore(String restoreFilePath, String host, String database,String userName,String password)
    throws Exception;
}
