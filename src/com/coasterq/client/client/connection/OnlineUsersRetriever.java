package com.coasterq.client.client.connection;

import com.finfrock.client.RetrieverAndroid15;

public class OnlineUsersRetriever extends RetrieverAndroid15<Integer>
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public OnlineUsersRetriever(ServiceLocations serviceLocations)
   {
      super(serviceLocations.getOnlineUsersAddress());
   }
   
   // --------------------------------------------------------------------------
   // Retriever Members
   // --------------------------------------------------------------------------
   
   @Override
   protected Integer parseText(String rawText)
   {

      return (Integer)Integer.parseInt(rawText);
   }
}