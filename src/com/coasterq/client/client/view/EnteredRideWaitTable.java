package com.coasterq.client.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.coasterq.client.client.data.EnteredRideWait;
import com.coasterq.client.client.data.EnteredRideWaitManager;
import com.finfrock.client.DataChangeListener;
import com.finfrock.client.FacebookConnectionStatus;
import com.finfrock.client.Row;
import com.finfrock.client.Table;

public class EnteredRideWaitTable extends Table
{
//
  // --------------------------------------------------------------------------
  // Private Instance Data
  // --------------------------------------------------------------------------
  
  private HashMap<EnteredRideWait, EnteredRideWaitRow> enteredRideWaitRowTable = 
     new HashMap<EnteredRideWait, EnteredRideWaitRow>();
  
  private EnteredRideWaitManager enteredRideWaitManager;
  
  private FacebookConnectionStatus facebookConnectionStatus;
  
  // --------------------------------------------------------------------------
  // Constructor
  // --------------------------------------------------------------------------

  public EnteredRideWaitTable(EnteredRideWaitManager enteredRideWaitManager, 
          FacebookConnectionStatus facebookConnectionStatus)
  {
     this.enteredRideWaitManager = enteredRideWaitManager;
     this.facebookConnectionStatus = facebookConnectionStatus;
     
     enteredRideWaitManager.addDataChangeListener(new DataChangeListener()
     {
        @Override
        public void onDataChange()
        {
           updateTable();
        }
     });
     
     updateTable();
  }

  // --------------------------------------------------------------------------
  // Table Members
  // --------------------------------------------------------------------------
  
  @Override
  protected Row getHeaderRow()
  {
     return new EnteredRideWaitHeaderRow();
  }

  @Override
  protected List<Row> getRows()
  {
     List<Row> enteredRideWaitRows = new ArrayList<Row>();
     
     for(EnteredRideWait enteredRideWait : 
       enteredRideWaitManager.getEnteredRideWaits())
     {
       EnteredRideWaitRow enteredRideWaitRow = getRow(enteredRideWait);
        
       enteredRideWaitRows.add(enteredRideWaitRow);
     }
     
     return enteredRideWaitRows;
  }

  // --------------------------------------------------------------------------
  // Private Members
  // --------------------------------------------------------------------------

  private EnteredRideWaitRow getRow(EnteredRideWait enteredRideWait)
  {
    EnteredRideWaitRow enteredRideWaitRow = 
      enteredRideWaitRowTable.get(enteredRideWait);
     
     if(enteredRideWaitRow == null)
     {
       enteredRideWaitRow = new EnteredRideWaitRow(enteredRideWait, 
           facebookConnectionStatus);
        
        enteredRideWaitRowTable.put(enteredRideWait, enteredRideWaitRow);
     }
     
     return enteredRideWaitRow;
  }

}
