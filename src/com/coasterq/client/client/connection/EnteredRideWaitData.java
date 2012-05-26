package com.coasterq.client.client.connection;

import com.google.gwt.core.client.JavaScriptObject;

public class EnteredRideWaitData extends JavaScriptObject
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   protected EnteredRideWaitData() 
   {
      //
      // Do nothing
      //
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   public final native String getParkName()
   /*-{
      return this.parkname;
   }-*/;
   
   public final native String getParkUrl()
   /*-{
      return this.parkurl;
   }-*/;
   
   public final native String getRideName()
   /*-{
      return this.ridename;
   }-*/;
   
   public final native int getFacebookUid()
   /*-{
      return this.facebookuid;
   }-*/;
   
   public final native int getWaitTime()
   /*-{
      return this.waittime;
   }-*/;
   
   public final native int get24Hour()
   /*-{
      return this.hour;
   }-*/;
   
   public final native int getMinutes()
   /*-{
      return this.minutes;
   }-*/;
   
   public final native int getDayOfMonth()
   /*-{
      return this.dayOfMonth;
   }-*/;
   
   public final native int getYear()
   /*-{
      return this.year;
   }-*/;
   
   public final native int getMonth()
   /*-{
      return this.month;
   }-*/;
}
