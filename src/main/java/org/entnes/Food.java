package org.entnes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Food {
	
	private int foodQuantity = 0;
	private final int PANEL_WIDTH;
	private final int PANEL_HEIGHT;
	private int foodWidth;
	private int foodHeight;

	private int foodMaxSpeed = 4;

	private int foodMinSpeed = 3;
	Random random = new Random();
	private ArrayList<Integer> xFoodCords = new ArrayList<>();
	private ArrayList<Integer> yFoodCords = new ArrayList<>();
	private ArrayList<Integer> xFoodVelocity = new ArrayList<>();
	private ArrayList<Integer> yFoodVelocity = new ArrayList<>();
	
    Food(int PANEL_WIDTH, int PANEL_HEIGHT, int foodQuantity, int foodWidth, int foodHeight) {
    	this.foodQuantity = foodQuantity;
    	this.PANEL_WIDTH = PANEL_WIDTH;
    	this.PANEL_HEIGHT = PANEL_HEIGHT;
    	this.foodWidth = foodWidth;
    	this.foodHeight = foodHeight;
    	for (int i = 0; i < foodQuantity; i++) {
    		xFoodCords.add(random.nextInt(PANEL_WIDTH- foodWidth));
    		yFoodCords.add(random.nextInt(PANEL_HEIGHT- foodHeight));
    		xFoodVelocity.add(random.nextInt(foodMaxSpeed-foodMinSpeed)+foodMinSpeed);
    		yFoodVelocity.add(random.nextInt(foodMaxSpeed-foodMinSpeed)+foodMinSpeed);
    	}
    }
    
    public void drawFood(Graphics2D g2D) {    	
    	for (int i = 0; i < foodQuantity; i++) {
	        g2D.setPaint(Color.green);
	        g2D.fillRect(xFoodCords.get(i), yFoodCords.get(i), foodWidth, foodHeight);
    	}
    }
    
    public void addFood(int toAddFood, int maxFood) {    	
    	if(foodQuantity <maxFood) {
    		int currentFoodQuantity = foodQuantity;
	    	for (int i = currentFoodQuantity; i < currentFoodQuantity + toAddFood; i++) {
	    		xFoodCords.add(random.nextInt(PANEL_WIDTH- foodWidth));
	    		yFoodCords.add(random.nextInt(PANEL_HEIGHT- foodHeight));
				xFoodVelocity.add(random.nextInt(foodMaxSpeed-foodMinSpeed)+foodMinSpeed);
				yFoodVelocity.add(random.nextInt(foodMaxSpeed-foodMinSpeed)+foodMinSpeed);
	    		foodQuantity++;	    		
	    	}	
    	}
    	
    }
    
    public void deleteSomeFood(int toDeleteFood) {    	
    	if(foodQuantity > 0) {
    		int currentFoodQuantity = foodQuantity;
	    	for (int i = currentFoodQuantity; i > currentFoodQuantity - toDeleteFood; i--) {
	    		xFoodCords.remove(foodQuantity-1);
	    		yFoodCords.remove(foodQuantity-1);
	    		xFoodVelocity.remove(foodQuantity-1);
	    		yFoodVelocity.remove(foodQuantity-1);
	    		foodQuantity--;	    		
	    	}	
    	}
    	
    }
    
    public void deleteFood(int id) {    	
    	if(id >= 0) {
    		xFoodCords.remove(id);
    		yFoodCords.remove(id);
    		xFoodVelocity.remove(id);
    		yFoodVelocity.remove(id);
    		foodQuantity--;
    	}
    	
    }
    
    public void moveFood() {
    	for (int i = 0; i < foodQuantity; i++) {
	        if(xFoodCords.get(i) >= PANEL_WIDTH- foodWidth || xFoodCords.get(i) < 0){
	        	xFoodVelocity.set(i, -xFoodVelocity.get(i));
	        }
	
	        if(yFoodCords.get(i) >= PANEL_HEIGHT- foodHeight || yFoodCords.get(i) < 0){
	        	yFoodVelocity.set(i, -yFoodVelocity.get(i));
	        }
	
	        xFoodCords.set(i, (xFoodCords.get(i) + xFoodVelocity.get(i)));
	        yFoodCords.set(i, (yFoodCords.get(i) + yFoodVelocity.get(i)));
    	}
    }
    
    public int getFoodQuantity() {
    	return foodQuantity;
    }
    
    public ArrayList<Integer> getxFoodCords(){
    	return xFoodCords;
    }
    
    public ArrayList<Integer> getyFoodCords(){
    	return yFoodCords;
    }
    
    public ArrayList<Integer> getxFoodVeliocity(){
    	return xFoodVelocity;
    }
    
    public ArrayList<Integer> getyFoodVeliocity(){
    	return yFoodVelocity;
    }
    

}
