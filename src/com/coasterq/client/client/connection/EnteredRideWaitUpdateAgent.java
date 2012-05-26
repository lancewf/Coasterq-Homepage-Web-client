package com.coasterq.client.client.connection;

import java.util.List;

import com.coasterq.client.client.data.EnteredRideWait;
import com.coasterq.client.client.data.EnteredRideWaitManager;
import com.finfrock.client.Loadable;
import com.finfrock.client.Returnable;
import com.google.gwt.user.client.Timer;

public class EnteredRideWaitUpdateAgent
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private EnteredRideWaitRetriever enteredRideWaitRetriever;
   private EnteredRideWaitManager enteredRideWaitManager;

   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public EnteredRideWaitUpdateAgent(
                                EnteredRideWaitRetriever enteredRideWaitRetriever, 
                                EnteredRideWaitManager enteredRideWaitManager)
   {
      this.enteredRideWaitManager = enteredRideWaitManager;
      this.enteredRideWaitRetriever = enteredRideWaitRetriever;
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public void start()
   {
      enteredRideWaitRetriever.startLoad(new Returnable<Loadable>()
      {
         @Override
         public void returned(Loadable loadable)
         {
            List<EnteredRideWait> enteredRideWaits = enteredRideWaitRetriever
               .getObject();

            enteredRideWaitManager.setEnteredRideWaits(enteredRideWaits);
         }
      });
      
      Timer t = new Timer() 
      {
        public void run() 
        {
           enteredRideWaitRetriever.startLoad(new Returnable<Loadable>()
           {
              @Override
              public void returned(Loadable loadable)
              {
                 List<EnteredRideWait> enteredRideWaits = 
                    enteredRideWaitRetriever.getObject();
                 
                 enteredRideWaitManager.setEnteredRideWaits(enteredRideWaits);
              }
           });
        }
      };

      // Schedule the timer to run every 5 minutes.
      t.scheduleRepeating(300000);
   }
}
