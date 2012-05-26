package com.coasterq.client.client.data;

import java.util.ArrayList;
import java.util.List;

import com.finfrock.client.Returnable;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;

public class CollectionNode extends Node
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private boolean isChildrenVisable = false;
   private List<Node> childrenNodes = new ArrayList<Node>();
   private Label label;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public CollectionNode(String title)
   {
      setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      
      label = new Label(title);
      label.addStyleName("collectionnodelabel");
      label.addClickHandler(new ClickHandler()
      {
         @Override
         public void onClick(ClickEvent event)
         {
            onSelected();
         }
      });

      if(title == null || title.length() == 0)
      {
         
      }
      else
      {
         add(label);
      }
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   public List<Node> getChildrenNodes()
   {
      return childrenNodes;
   }
   
   public void addChildren(Node child)
   {
      childrenNodes.add(child);
      
      child.addListener(new Returnable<Node>()
      {
         @Override
         public void returned(Node object)
         {
            notifyOfClick(CollectionNode.this);
         }  
      });
      
      if(isChildrenVisable)
      {
         add(child);
      }
   }
   
   @Override
   public String getTitle()
   {
      return label.getText();
   }

   @Override
   public boolean isLeaf()
   {
      return false;
   }

   @Override
   public void setTitle(String title)
   {
      if(title == null || title.length() == 0)
      {
         remove(label);
      }
      else
      {
         add(label);
         
         label.setText(title);
      }
   }
   
   public void showChildren()
   {
      for(Node node : childrenNodes)
      {
         add(node);
      }
      
      isChildrenVisable = true;
   }
   
   public void hideChildren()
   {
      for(Node node : childrenNodes)
      {
         remove(node);
      }
      
      isChildrenVisable = false;
   }
   
   protected void removeAllChildren()
   {
      clear();
      
      childrenNodes.clear();
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private void onSelected()
   {
      if (isChildrenVisable)
      {
         hideChildren();
      }
      else
      {
         showChildren();
      }

      notifyOfClick(this);
   }
}
