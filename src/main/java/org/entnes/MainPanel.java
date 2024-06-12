package org.entnes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class MainPanel extends JPanel implements ActionListener {
	
	//Panel settings
	final int PANEL_WIDTH = 800;
    final int PANEL_HEIGHT = 800;
    
    //Creature settings
    int creatureWidth = 30;
    int creatureHeight = 30;
    double velocityCreature = 6;
    double angleCreature = 30;
    Creature creature;
    
    //Food settings
    int foodWidth = 10;
    int foodHeight = 10;
    final int MAX_FOOD_QUANTITY = 20;
    int foodQuantity = 20;
    Food food;
    
    //Timers settings
    final int TIMER5_MS = 5;
    final int TIMER1000_MS = 1000;
    Timer timer1;
    Timer timer2;

    MainPanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        timer1 = new Timer(TIMER5_MS,this);
        timer1.start();
        timer2 = new Timer(TIMER1000_MS,this);
        timer2.start();
        food = new Food(PANEL_WIDTH, PANEL_HEIGHT, foodQuantity, foodWidth, foodHeight);
        creature = new Creature(PANEL_WIDTH, PANEL_HEIGHT, creatureWidth, creatureHeight);
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
    		food.deleteFood(creature.collisionCheck(food.getXFoodCords(), food.getYFoodCords(), foodWidth, foodHeight));
            creature.moveCreatureFollowNearestFood(velocityCreature, food.getXFoodCords(), food.getYFoodCords());
//            creature.moveCreatureSimpleBounce(velocityCreature, angleCreature);
    		food.moveFood();
	        repaint();
        }
    	else if (e.getSource() == timer2) {
			food.addFood(1,MAX_FOOD_QUANTITY);
			repaint();
		}
    }
    

}
