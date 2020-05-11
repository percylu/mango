package com.weehai.mango.backup.service.impl;

import com.weehai.mango.backup.service.MySqlBackupService;
import com.weehai.mango.backup.util.MySqlBackupRestoreUtils;
import org.springframework.stereotype.Service;

/**
 * @Author: PercyLu
 * @Company: WeeHai
 * @Date: 2020/1/7 2:16 下午
 * @Version: 1.0
 */
@Service
public class MySqlBackupServiceImpl implements MySqlBackupService {
    @Override
    public boolean backup(String host, String userName, String password, String savePath, String fileName, String databaseName) throws Exception {
        return MySqlBackupRestoreUtils.backup(host,userName,password,savePath,fileName,databaseName);
    }

    @Override
    public boolean restore(String restoreFilePath, String host, String database, String userName, String password) throws Exception {
        return MySqlBackupRestoreUtils.restore(restoreFilePath,host,userName,password,database);
    }
}
