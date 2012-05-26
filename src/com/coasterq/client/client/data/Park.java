package com.coasterq.client.client.data;

import com.google.gwt.core.client.JavaScriptObject;

public class Park extends JavaScriptObject
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   protected Park() 
   {
      //
      // Do nothing
      //
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   public final native String getName()
   /*-{
      return this.name;
   }-*/;

   public final native String getUrl()
   /*-{
      return this.url;
   }-*/;

   public final native double getLatitude()
   /*-{
      return this.latitude;
   }-*/;
   
   public final native double getLongitude()
   /*-{
      return this.longitude;
   }-*/;
}
