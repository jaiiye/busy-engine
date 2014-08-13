














package com.busy.engine.service;

import com.busy.engine.entity.FileFolder;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface FileFolderService
{
      public Result<FileFolder> find(String userName, Integer id);
      public Result<List<FileFolder>> findAll(String userName); 
      public Result<FileFolder> store(String userName, Integer fileFolderId, Integer siteFileId, Integer siteFolderId);
      public Result<FileFolder> remove(String userName, Integer id);
}    








