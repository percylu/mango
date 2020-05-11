package com.weehai.mango.backup.controller;

import com.weehai.mango.backup.constants.BackupConstants;
import com.weehai.mango.backup.datasource.BackupDataSourceProperties;
import com.weehai.mango.backup.service.MySqlBackupService;
import com.weehai.mango.backup.util.HttpResult;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: PercyLu
 * @Company: WeeHai
 * @Date: 2020/1/7 3:24 下午
 * @Version: 1.0
 */
@RestController
@RequestMapping("/backup")
public class MySqlBackupController {
    @Autowired
    private BackupDataSourceProperties properties;
    @Autowired
    private MySqlBackupService mySqlBackupService;
    @GetMapping("/backup")
    public HttpResult backup(){
        String backupFolderName= BackupConstants.DEFAULT_BACKUP_NAME+"_"+
                (new SimpleDateFormat(BackupConstants.DATE_FORMAT)).format(new Date());
        return backup(backupFolderName);
    }
    private HttpResult backup(String backupFolderName){
        String host=properties.getHost();
        String userName=properties.getUserName();
        String password=properties.getPassword();
        String database=properties.getDatabase();
        String backupFolderPath=BackupConstants.BACKUP_FOLDER+backupFolderName+ File.separator;
        String fileName=BackupConstants.BACKUP_FILE_NAME;
        try{
            boolean success=mySqlBackupService.backup(host,userName,password,backupFolderPath,fileName,database);
            if(!success){
                HttpResult.error("备份失败！");
            }
        }catch(Exception e){
                return HttpResult.error(500,e.getMessage());
        }
        return HttpResult.ok();
    }

    @GetMapping("/restore")
    public HttpResult restore(@RequestParam String name)throws IOException {
        String host=properties.getHost();
        String userName=properties.getUserName();
        String password=properties.getPassword();
        String database=properties.getDatabase();
        String restoreFilePath=BackupConstants.RESTORE_FOLDER+name;
        try{
            mySqlBackupService.restore(restoreFilePath,host,userName,password,database);
        }catch (Exception e){
            return HttpResult.error(500,e.getMessage());
        }
        return HttpResult.ok();
    }

    @GetMapping("/findRecords")
    public HttpResult findRecords(){
        if(!new File(BackupConstants.DEFAULT_RESTORE_FILE).exists()){
            //初始化备份文件
            backup(BackupConstants.DEFAULT_BACKUP_NAME);
        }
        List<Map<String,String>> backupRecords=new ArrayList<>();
        File restoreFolder = new File(BackupConstants.RESTORE_FOLDER);
        if(restoreFolder.exists()){
            for(File file:restoreFolder.listFiles()){
                Map<String,String> backup=new HashMap<>();
                backup.put("name",file.getName());
                backup.put("title",file.getName());
                if(BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(file.getName())){
                    backup.put("title","系统默认备份");
                }
                backupRecords.add( backup);
            }
        }
        //排序，默认备份最前，然后按时间戳排序，新备份在前面、
        backupRecords.sort((o1,o2)->BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(o2.get("name"))?1:
                o2.get("name").compareTo(o1.get("name")));
        return HttpResult.ok(backupRecords);
    }

    @GetMapping("/delete")
    public HttpResult deleteBackupRecords(@RequestParam String name){
        if(BackupConstants.DEFAULT_BACKUP_NAME.equals(name)){
            return HttpResult.error("系统默认备份无法删除");
        }
        String restoreFilePath = BackupConstants.BACKUP_FOLDER+name;
        try{
            FileUtils.deleteDirectory(new File(restoreFilePath));
        }catch (Exception e){
            return HttpResult.error(500,e.getMessage());
        }
        return HttpResult.ok();
    }

}
