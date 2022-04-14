import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GameObject 
{
   //middle of square, if 25,25....
   protected int xPosition; //13
   protected int yPosition; //13
   private  Color color;
    
   public GameObject(int xPosition,int yPosition, Color color)
   {
      this.xPosition=xPosition+23;
      this.yPosition=yPosition+23;
      this.color=color;
   }
  
   
   public  boolean collides(GameObject GO) //collides method
   {
      if ((GO.getXPosition()==xPosition) &&
          (GO.getYPosition()==yPosition) &&
          (GO.getColor()==color))
         return false;
      else
      { 
         int topthis = yPosition-13;
         int bottomthis = yPosition+12;
         int leftthis = xPosition-13;
         int rightthis = xPosition+12;
         int topother = GO.getYPosition()-13;
         int bottomother = GO.getYPosition()+12;
         int leftother = GO.getXPosition()-13;
         int rightother = GO.getXPosition()+12;
         return !((bottomthis < topother) ||
                (topthis > bottomother) ||
                (leftthis > rightother) ||
                (rightthis < leftother));
      }
   }
   //getter methods for x,y,and color
   public int getXPosition()
   {
      return xPosition;
   }
   public int getYPosition()
   {
      return yPosition;
   }
   public Color getColor()
   {
      return color;
   }
   
   public void draw (Graphics g)
   {
      
      g.setColor(getColor());
      g.fillRect(xPosition-13,yPosition-13,25,25);
   }
   
   
   
}