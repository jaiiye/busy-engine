



















package com.busy.engine.dao.base;

import com.busy.engine.domain.ImageType;

public interface ImageTypeDao extends IGenericDao<ImageType, Integer>
{
                    
      ImageType getRelatedSiteImageList(ImageType image_type);     
        
}
    

