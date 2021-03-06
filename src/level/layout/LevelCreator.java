package layout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LevelCreator extends JFrame {
  
    final static int maxGap = 20;
    JComboBox horGapComboBox;
    JComboBox verGapComboBox;
    JButton doneButton = new JButton("Done");
    
    Color[] colors = new Color[12];
   
    GridLayout experimentLayout = new GridLayout(5,11);
    
    JButton[] buttons = new JButton[55];
    int[] arr = {1,1,1,2,2,2,3,4,4,4,4,5,5,1,1,6,2,3,3,4,7,7,5,5,6,6,6,2,8,8,9,9,7,10,5,10,6,11,11,12,8,8,9,7,10,10,10,11,11,12,12,12,8,9,7};
    
    
    public LevelCreator(String name) 
    {
        super(name);
        setResizable(true);
    }
    
    public void addComponentsToPane(final Container pane) 
    {
       // initGaps();
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2,3));
        
        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        
        //Sets preffered size for the panel
        compsToExperiment.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,
                (int)(buttonSize.getHeight() * 3.5)+maxGap * 2));
        
        //SET COLORS FOR BUTTONS
        setButtonColors(arr);
        
        for(int i = 0; i < 55; i++)
          buttons[i].addActionListener(listen);
        
         doneButton.addActionListener(listenDone);
        
        for(int i = 0 ; i < 55; i++)
          buttons[i].setEnabled(true);
        
         for(int i = 0; i  < 55; i++)
         {
                compsToExperiment.add(buttons[i]);
         }
 
        controls.add(doneButton);

                experimentLayout.setHgap(5);
      
                experimentLayout.setVgap(5);
                experimentLayout.layoutContainer(compsToExperiment);

        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }
    
        ActionListener listen = new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
          
            JButton btn = (JButton) e.getSource();
            
            int indexOfColor = Integer.parseInt(btn.getText());
            Color currentBackground = btn.getBackground();
            
            if(colors[indexOfColor-1] == currentBackground)
            {
               for(int i = 0 ; i < 55; i ++)
               {
                 if(buttons[i].getText().equals(btn.getText()))
                   buttons[i].setBackground(Color.gray);
               }
            }
            else
            { 
               for(int i = 0 ; i < 55; i ++)
               {
                 if(buttons[i].getText().equals(btn.getText()))
                   buttons[i].setBackground(colors[indexOfColor-1]);
               }
            }

            }
        
    };
       

         ActionListener listenDone = new ActionListener() 
    {
        @Override
        public void actionPerformed(ActionEvent e) {
          
          boolean exist = false;
          
        
          boolean[] indexes = new boolean[12];

          for(int i = 0 ; i < 55; i++)
          {
             if(buttons[i].getBackground() == Color.gray) 
             {
                  if(indexes[Integer.parseInt(buttons[i].getText()) -1] == false)
                  {
                     indexes[Integer.parseInt(buttons[i].getText()) -1] = true;
                  }
             }
          }
          
          
          for(int i = 0 ; i < 12; i++)
          {
            System.out.println(indexes[i]);
          }
 
         }};
         
     /* Assign colors according to texts on the buttons 
      * void function
      */
    public void setButtonColors(int [] integers) 
   {
      
      colors[0] = Color.black;
      colors[1] = Color.red;
      colors[2] = Color.LIGHT_GRAY;
      colors[3] = Color.blue;
      colors[4] = Color.yellow;
      colors[5] = Color.green;
      colors[6] = Color.pink;
      colors[7] = Color.cyan;
      colors[8] = Color.orange;
      colors[9] = Color.magenta;
      colors[10] = Color.white;
      colors[11] = Color.DARK_GRAY;
      
      int colorIndex = 0;
      
      for(int i = 0; i < 55; i++)
        buttons[i] = new JButton(String.valueOf(integers[i])); 

      for(int i = 0; i < 55; i++)
      {
        for(int j = i; j < 55; j++)
        {
          if((buttons[i].getText() == buttons[j].getText()) && (buttons[j].isEnabled()))
          {
        
            buttons[j].setBackground(colors[integers[colorIndex]-1]);
            buttons[j].setEnabled(false);
            colorIndex++;
          } 
      } 

      }
      
   }
    
 
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              
        LevelCreator frame = new LevelCreator("GridLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
            }
        });
    }
}