package com.coasterq.client.client.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.coasterq.client.client.connection.ParksRetriever;
import com.coasterq.client.client.connection.ServiceLocations;
import com.coasterq.client.client.data.CollectionNode;
import com.coasterq.client.client.data.LeafNode;
import com.coasterq.client.client.data.Node;
import com.coasterq.client.client.data.Park;
import com.finfrock.client.Loadable;
import com.finfrock.client.LocationManager;
import com.finfrock.client.Returnable;
import com.google.gwt.user.client.Timer;

public class LocalParksPanel extends CollectionNode
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private LocationManager locationManager;
   private ServiceLocations serviceLocations;
   private List<Park> parks;
   private static double FAR_DISTANCE = 290;
   private static double CLOSE_DISTANCE = 130;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public LocalParksPanel(ServiceLocations serviceLocations)
   {
      super("");
      
      this.serviceLocations = serviceLocations;
      locationManager = new LocationManager();
      
      initialize();
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private void initialize()
   {
      if(locationManager.isSupported())
      {
         ParksRetriever retriever = new ParksRetriever(serviceLocations);
         
         retriever.startLoad(new Returnable<Loadable>()
         {
            @Override
            public void returned(Loadable object)
            {
               ParksRetriever parksRetriever = (ParksRetriever) object;
               
               parks = parksRetriever.getObject();
               
               startDisplayOfLocalParks();
            }
         });
      }
   }

   private void startDisplayOfLocalParks()
   {
      showLocalParks();
      
      showChildren();
      
      Timer t = new Timer()
      {
         public void run()
         {
            showLocalParks();
         }
      };

      // Schedule the timer to run every 5 minutes.
      t.scheduleRepeating(300000);
      //t.scheduleRepeating(5000);
   }
   //130 km = 80 miles
   private List<Park> findLocalParks()
   {
      List<Park> localParks = findParksAtDistance(CLOSE_DISTANCE);
      
      if(localParks.size() == 0)
      {
         localParks = findParksAtDistance(FAR_DISTANCE);
      }

      List<Park> closesThree = getClosesThreeParks(localParks);
      
      return closesThree;
   }
   
   private List<Park> getClosesThreeParks(List<Park> parks)
   {
      List<Park> sortedParks = sortParkByDistance(parks);
      
      if(sortedParks.size() > 3)
      {
         return sortedParks.subList(0, 3);
      }
      else
      {
         return sortedParks;
      }
   }
   
   private List<Park> sortParkByDistance(List<Park> parks)
   {
      Collections.sort(parks, new Comparator<Park>()
      {
         @Override
         public int compare(Park park1, Park park2)
         {
            int park1Distance = (int)(1000 * locationManager.findDistanceFrom(park1.getLatitude(), 
               park1.getLongitude()));
            
            int park2Distance = (int)(1000 * locationManager.findDistanceFrom(park2.getLatitude(), 
               park2.getLongitude()));
            
            return park1Distance - park2Distance;
         }
      });
      
      return parks;
   }
   
   private List<Park> findParksAtDistance(double maxDistance)
   {
      List<Park> localParks = new ArrayList<Park>();
      
      for(Park park : parks)
      {
         double distance = locationManager.findDistanceFrom(park.getLatitude(), 
            park.getLongitude());
         
         if(distance < maxDistance)
         {
            localParks.add(park);
         }
      }
      
      return localParks;
   }

   private void showLocalParks()
   {
      List<Park> closeParks = findLocalParks();
      
      removeAllChildren();
      
      if(closeParks.size() > 0)
      {
         setTitle("Local Parks");
  
         for (Park park : closeParks)
         {
            Node child = new LeafNode(park.getName(), park
               .getUrl());
            addChildren(child);
         }
      }
      else
      {
         setTitle("");
      }
   }
   
}
