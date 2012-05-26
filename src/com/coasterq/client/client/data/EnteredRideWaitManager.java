package com.coasterq.client.client.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.finfrock.client.DataChangeListener;

public class EnteredRideWaitManager 
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------

   private List<EnteredRideWait> enteredRideWaits = new ArrayList<EnteredRideWait>();
   private List<DataChangeListener> dataChangeListeners = 
      new ArrayList<DataChangeListener>();
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public EnteredRideWaitManager()
   {
      //
      // Do nothing
      //
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------

   public List<EnteredRideWait> getEnteredRideWaits()
   {
      return enteredRideWaits;
   }

   public void setEnteredRideWaits(List<EnteredRideWait> enteredRideWaits)
   {
      this.enteredRideWaits = enteredRideWaits;
      
      Collections.sort(enteredRideWaits, new Comparator<EnteredRideWait>()
      {
         @Override
         public int compare(EnteredRideWait enteredRideWait1, 
                            EnteredRideWait enteredRideWait2)
         {
            return (-1)*enteredRideWait1.getDateTime().compareTo(
               enteredRideWait2.getDateTime());
         }

      });
      
      dataChanged();
   }

   /* (non-Javadoc)
    * @see com.coasterq.client.data.RideManagerable#addDataChangeListener(com.coasterq.client.common.DataChangeListener)
    */
   public void addDataChangeListener(DataChangeListener dataChangeListener)
   {
      dataChangeListeners.add(dataChangeListener);
   }
   
   /* (non-Javadoc)
    * @see com.coasterq.client.data.RideManagerable#removeDataChangeListener(com.coasterq.client.common.DataChangeListener)
    */
   public void removeDataChangeListener(DataChangeListener dataChangeListener)
   {
      dataChangeListeners.remove(dataChangeListener);
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private void dataChanged()
   {
      for(DataChangeListener dataChangeListener : dataChangeListeners)
      {
         dataChangeListener.onDataChange();
      }
   }

}
