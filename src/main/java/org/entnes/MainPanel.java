package org.entnes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainPanel extends JPanel implements ActionListener {
	
	//Panel settings
	final int PANEL_WIDTH = 500;
    final int PANEL_HEIGHT = 500;
    
    //Creature settings
    final int CREATURE_WIDTH = 30;
    final int CREATURE_HEIGHT = 30;
    double VelocityCreatue = 4;
    double angleCreatue = 30;
    double angleCreatueDinamic = 0.0;
    Creature creature;
    
    //Food settings
    final int FOOD_WIDTH = 10;
    final int FOOD_HEIGHT = 10;
    final int MAX_FOOD_QUANTITY = 2;
    int foodQuantity = 2;
    Food food;
    
    //Timers settings
    final int TIMER5_MS = 5;
    final int TIMER1000_MS = 1000;
    Timer timer1;
    Timer timer2;
     
//    Image enemy;
//    Image backgroundImage;  
    
	Random random = new Random();	

    MainPanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        timer1 = new Timer(TIMER5_MS,this);
        timer1.start();
        timer2 = new Timer(TIMER1000_MS,this);
        timer2.start();
        food = new Food(foodQuantity, PANEL_WIDTH, PANEL_HEIGHT, FOOD_WIDTH, FOOD_HEIGHT);
        creature = new Creature(CREATURE_WIDTH, CREATURE_HEIGHT, PANEL_WIDTH, PANEL_HEIGHT);
    }

    public void paint(Graphics g){
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(Color.black);
        g2D.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        
        creature.drawCreature(g2D);        
        food.drawFood(g2D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == timer1) {
    		food.deleteFood(creature.collsionCheck(food.getxFoodCords(), food.getyFoodCords(), FOOD_WIDTH, FOOD_HEIGHT));
            if(creature.searchForFood(food.getxFoodCords(), food.getyFoodCords()) != null) {
                angleCreatueDinamic = creature.searchForFood(food.getxFoodCords(), food.getyFoodCords()).get(5);
                creature.moveCreature(VelocityCreatue,angleCreatueDinamic);
            }
//            System.out.println(angleCreatueDinamic);

    		food.moveFood();
	        repaint();
        }
    	else if (e.getSource() == timer2) {
			food.addFood(1,MAX_FOOD_QUANTITY);
			repaint();
		}
    }
    

}
