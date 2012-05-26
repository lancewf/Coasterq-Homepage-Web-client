package com.coasterq.client.client.connection;

public class ReleaseServiceLocations implements ServiceLocations
{
   
   @Override
   public String getFriendStatusDataAddress()
   {
      return getNativeBaseServicesUrl() + "getAllEnteredRideWaits";
   }
   
   @Override
   public String getParkInfoAddress()
   {
      return getNativeBaseServicesUrl() + "getParkInfoAddress";
   }
   
   @Override
   public String getOnlineUsersAddress()
   {
      return getNativeBaseServicesUrl() + "getNumberOfUsersOnline";
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private static native String getNativeHomeUrl()
   /*-{
      return $wnd.home_url;
   }-*/;
   
   private static native String getNativeBaseUrl() 
   /*-{
      return $wnd.base_url;
   }-*/;
   
   private static native String getNativeBaseServicesUrl() 
   /*-{
      return $wnd.base_services_url;
   }-*/;
}
