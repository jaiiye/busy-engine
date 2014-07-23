











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class RelatedItem implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_RELATED_ITEM_ID = "RelatedItemId";
        public static final String PROP_ITEM1 = "Item1";
        public static final String PROP_ITEM2 = "Item2";
        

        private Integer relatedItemId;
                
        private Integer item1;
                
        private Integer item2;
                
                 
        
        

        public RelatedItem()
        {
            this.relatedItemId = 0; 
       this.item1 = 0; 
       this.item2 = 0; 
        
       
       }
        
        public RelatedItem(Integer RelatedItemId, Integer Item1, Integer Item2)
        {
            this.relatedItemId = RelatedItemId;
       this.item1 = Item1;
       this.item2 = Item2;
              
       
       } 
        
             
        
            public Integer getRelatedItemId()
            {
                return this.relatedItemId;
            }
            
            public void setRelatedItemId(Integer RelatedItemId)
            {
                this.relatedItemId = RelatedItemId;
            }
            
            
        
            public Integer getItem1()
            {
                return this.item1;
            }
            
            public void setItem1(Integer Item1)
            {
                this.item1 = Item1;
            }
            
            
        
            public Integer getItem2()
            {
                return this.item2;
            }
            
            public void setItem2(Integer Item2)
            {
                this.item2 = Item2;
            }
            
            
         
        
        
            
    }

