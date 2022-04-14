import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


//alllll the imports 

public class ProjectPanel extends JPanel 
{
   
   ArrayList<ArrayList<GameObject>> levelMap = new ArrayList<ArrayList<GameObject>>();// create arraylist of array list to hold data map
   ArrayList<GameObject> innerList = new ArrayList<GameObject>();
  /* Block me1=new Block(100,100, Color.GREEN); test for collides 
   GameObject me2=new GameObject(74,100, Color.GREEN);*/ 
   
   //instantiations
   PlayerSQ player;
   VictoryBlock victory;
   Timer t;
   //new TimerTask();
   int N=-1;
   int levelRows;
   int levelColumns;
   
   public ProjectPanel()//take in file name 
   {
      //super();
      
      addKeyListener(new keyEventDemo());//add keylistener
      setFocusable(true);
      t = new Timer(10, new TimeListener());//add timer for smooth movement of square
   
      try{
         Scanner read= new Scanner(new File("BlockFile.txt")); //create scanner for file
         int playerX=read.nextInt();//read in first set of data for player 
         int playerY=read.nextInt();
      
         levelRows=read.nextInt();//read in second set of data 
         levelColumns=read.nextInt();
         
         //make each inner list  individually,
         for(int i=0;i<levelRows;i++)
         {
            levelMap.add(innerList);
         }
         
         for(int i=0;i<levelRows;i++)//read in info into array of array
         {
            for(int j=0;j<levelColumns;j++)
            {
               int check=read.nextInt();
               if (i==playerX && j==playerY)
               {
                  player =new PlayerSQ(playerX*25,playerY*25, Color.RED);//create player
                  
               }
               
               else if (check==2)
               {
                  victory =new VictoryBlock(j*25,i*25, Color.GREEN);//create and add vicotry block if 2
                  levelMap.get(i).add(victory);
               }
               else if (check==1)
               {
                  Block block =new Block(j*25,i*25, Color.BLUE);//create and normal block if 1
                  levelMap.get(i).add(block);
               }
               else 
               {
                  levelMap.get(i).add(null);
               }
               
            }
            
         }
         
         
      }
      catch(FileNotFoundException e)
      {
         System.out.println("Unable to load file");
      }
      
      //System.out.println(levelMap.size());
      
      if (player.isOnGround(levelMap))
      {
         System.out.println("ground");
      }     //tests for collide^^^s*/
      else
      {
         while(N>=7)
         {
            player.move(0,N,levelMap);//gravity in effect when player is not on ground
            N--;
         }
      }
      
      if (player.collides(victory))
      {
         add(new JOptionPane("Victory"));
        
      }
      
      
      
      
   
      t.start();      
      
      setPreferredSize(new Dimension(820,620));
      
   }
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.fillRect(0,0,820,620);
      g.setColor(Color.GRAY);
      g.fillRect(10,10,800,600);
      //me1.draw(g);
      //me2.draw(g);
      for(int i=0;i<innerList.size();i++) //draw map with double for loop
      {
         for(int j=0;j<levelMap.size();j++)
         {
            if (levelMap.get(j).get(i)==null)
            {
            }
            else
            {
               levelMap.get(j).get(i).draw(g);
            }
         }
      }
      if (player.isOnGround(levelMap))
      {
         System.out.println("ground");
      }
      
      player.draw(g);
      
      
      
   
   }
   
   public class keyEventDemo implements KeyListener
   {
      public void keyTyped(KeyEvent e) {}
      public void keyReleased(KeyEvent e) //when released stop direction
      //code for smoothness
      {
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
            up=false;
         }
         if(e.getKeyCode() == KeyEvent.VK_S)
         {
            down =false;
         }
         if(e.getKeyCode() == KeyEvent.VK_A)
         {
            left = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_D)
         {
            right = false;
         }
         repaint();
      }
      public void keyPressed(KeyEvent e) //when pressed go direction 
      {
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
            up=true;
         }
               
         if(e.getKeyCode() == KeyEvent.VK_S)
         {
            down =true;
                  
         }
         if(e.getKeyCode() == KeyEvent.VK_A)
         {
            left = true;
                  
         }
         if(e.getKeyCode() == KeyEvent.VK_D)
         {
            right = true;
                  
         }
               
         repaint();
      }
   }
   
   boolean up;
   boolean down;
   boolean left;
   boolean right;
   
   public class TimeListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(up)//y vaule decreases while "up"
         {
            //if square hits a black tile, make sure it doesnt go off the screen
            /*if(squarePositionY<0 && (levelClass.nextUp().equals("NONE")))
            {
               squarePositionY=squarePositionY;
            }
            else if (squarePositionY<0)
            {
               levelClass=new Level7(new File(levelClass.nextUp()));//create new panel if it does go off screen.
               squarePositionY=399;
            }
            else*/
            {
               player.move(0,-7, levelMap);
            }
         }
         if(down)//y value increases while "down"
         {
            /*if(squarePositionY>400 && (levelClass.nextDown().equals("NONE")))
            {
               squarePositionY=squarePositionY;
            }
            else if (squarePositionY>400)
            {
               levelClass=new Level7(new File(levelClass.nextDown()));//create new panel if it does go off screen.
               squarePositionY=1;
            }
            else*/
            {
               player.move(0,1, levelMap);
            }
            
         }
         if(left)//x value decreases while "left"
         {
            /*if(squarePositionX<0 && (levelClass.nextLeft().equals("NONE")))
            {
               squarePositionX=squarePositionX;
            }
            else if (squarePositionX<0)
            {
               levelClass=new Level7(new File(levelClass.nextLeft()));//create new panel if it does go off screen. 
               squarePositionX=399;
            }
            else*/
            {
               player.move(-1,0, levelMap);
            }
         }
         if(right)//x value increases while "right"
         {
            /*if(squarePositionX>400 && (levelClass.nextRight().equals("NONE")))
            {
               squarePositionX=squarePositionX;
            }
            else if (squarePositionX>400)
            {
               levelClass=new Level7(new File(levelClass.nextRight()));//create new panel if it does go off screen.
               squarePositionX=1;
            }
            else*/
            {
               player.move(1,0, levelMap);
            }
         }
         repaint();
      }
      
      
   } 

   
      
}


