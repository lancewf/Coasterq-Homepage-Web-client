package com.coasterq.client.client.connection;

import java.util.ArrayList;
import java.util.List;

import com.coasterq.client.client.data.Park;
import com.finfrock.client.RetrieverAndroid15;
import com.google.gwt.core.client.JsArray;

public class ParksRetriever extends RetrieverAndroid15<List<Park>>
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public ParksRetriever(ServiceLocations serviceLocations)
   {
      super(serviceLocations.getParkInfoAddress());
   }
   
   // --------------------------------------------------------------------------
   // Retriever Members
   // --------------------------------------------------------------------------
   
   @Override
   protected List<Park> parseText(String rawText)
   {
      JsArray<Park> parkWaitDataJsArray = 
         asArrayOfParkData(rawText);
      
      List<Park> parks = new ArrayList<Park>();
      
      for(int index = 0; index < parkWaitDataJsArray.length(); index++)
      {
         Park park = parkWaitDataJsArray.get(index);
       
         parks.add(park);
      }

      return parks;
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private final native JsArray<Park> asArrayOfParkData(String json) 
   /*-{
      return eval(json);
   }-*/;
}
