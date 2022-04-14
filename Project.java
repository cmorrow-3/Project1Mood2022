import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Project extends JFrame
{
   public Project()
   {
      super("Project");
      Container contents = getContentPane();
      contents.add(new ProjectPanel());//add panel to frame
      
      setSize(820,645); //added 25 to y variable to see whole panel
      setVisible(true);
   }
   public static void main(String[] args)
   {
      Project theFrame = new Project();
      theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}
