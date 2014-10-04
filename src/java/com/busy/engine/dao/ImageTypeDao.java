


































package com.busy.engine.dao;

import com.busy.engine.entity.ImageType;

public interface ImageTypeDao extends IGenericDao<ImageType, Integer>
{
                    
      void getRelatedSiteImageList(ImageType image_type);     
        
}
    

