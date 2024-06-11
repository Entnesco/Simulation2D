package org.entnes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Creature {
	
	private int PANEL_WIDTH;
	private int PANEL_HEIGHT;
    private int creatureWidth;
	private int creatureHeight;
    private double x = 0 ,y = 0;
    private double  xVelocity = 0;
    private double  yVelocity = 0;
    private double velocity = 0;
    private Double angle = 0.0;
    private int foodConsumed = 0;
	
	Creature(int CREATURE_WIDTH, int CREATURE_HEIGHT, int PANEL_WIDTH, int PANEL_HEIGHT) {
		this.creatureWidth = CREATURE_WIDTH;
		this.creatureHeight = CREATURE_HEIGHT;
		this.PANEL_WIDTH = PANEL_WIDTH;
		this.PANEL_HEIGHT = PANEL_HEIGHT;
	}
	
    public void moveCreature(double  velocityInit, Double angleInit) {
    	if(velocity == 0) velocity = velocityInit;
    	angle = angleInit;
    	
//        if(x > PANEL_WIDTH-creatureWidth || x < 0){
//            angle = 180-angle;
//        }
//
//        if(y > PANEL_HEIGHT-creatureHeight || y < 0){
//        	angle = -angle;
//        }


        if(angle >= 360) angle = angle - 360;
        if(angle <= -360) angle = angle + 360;
    	
    	xVelocity = Math.round(Math.cos(Math.toRadians(angle))*velocity*1000)/1000d;
    	yVelocity = Math.round(Math.sin(Math.toRadians(angle))*velocity*1000)/1000d;

        x = x + xVelocity;
        y = y + yVelocity;

		if(x > PANEL_WIDTH-creatureWidth || x < 0){
			x = x - xVelocity;
		}

		if(y > PANEL_HEIGHT-creatureHeight || y < 0){
			y = y - yVelocity;
		}
        

    }
    
    public void drawCreature(Graphics2D g2D) {
        g2D.setPaint(Color.gray);
        g2D.fillRect((int)x, (int)y, creatureWidth, creatureHeight);
    }
    
    public int collsionCheck(ArrayList<Integer> xFoodCords, ArrayList<Integer> yFoodCords, int foodWidth, int foodHeight) {
    	for(int id = 0; id < xFoodCords.size(); id++) {
    		if(!((xFoodCords.get(id) + foodWidth < x) || (xFoodCords.get(id) > x + creatureWidth))) {
        		if(!((yFoodCords.get(id) + foodHeight < y) || (yFoodCords.get(id) > y + creatureHeight))) {
//        			creatureWidth++;
//        			creatureHeight++;
//        			if(creatureWidth >= PANEL_WIDTH) creatureWidth = PANEL_WIDTH;
//        			if(creatureHeight >= PANEL_HEIGHT) creatureHeight = PANEL_HEIGHT;
        			foodConsumed++;
//        			System.out.println("Food consumed: " + foodConsumed);
        			return id;
        		}
    		}
    	}
    	return -1;
    }
    
    public ArrayList<Double> searchForFood(ArrayList<Integer> xFoodCords, ArrayList<Integer> yFoodCords)
    {
    	if(xFoodCords.size() > 1) {
	    	double xFood = xFoodCords.get(0);
	    	double yFood = yFoodCords.get(0);
	    	double xDist = xFood-x;
	    	double yDist = yFood-y;
	    	double distCreatureFood = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
	    	angle = Math.toDegrees(Math.acos(xDist/distCreatureFood));
			if(yDist<0) angle = -angle;
	    	
	    	ArrayList<Double> nerestFood = new ArrayList<>();
	    	nerestFood.add(xFood);
	    	nerestFood.add(yFood);
	    	nerestFood.add(xDist);
	    	nerestFood.add(yDist);
	    	nerestFood.add(distCreatureFood);
	    	nerestFood.add(angle);
	    	
	    	for(int id = 1; id < xFoodCords.size(); id++) {
		    	xFood = xFoodCords.get(id);
		    	yFood = yFoodCords.get(id);
		    	xDist = xFood-x;
		    	yDist = yFood-y;
		    	distCreatureFood = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
				angle = Math.toDegrees(Math.acos(xDist/distCreatureFood));
				if(yDist<0) angle = -angle;
		    	
		    	if(Math.abs(distCreatureFood) < Math.abs(nerestFood.get(4))) {
			    	nerestFood.set(0, xFood);
			    	nerestFood.set(1, yFood);
			    	nerestFood.set(2, xDist);
			    	nerestFood.set(3, yDist);
			    	nerestFood.set(4, distCreatureFood);
			    	nerestFood.set(5, angle);
		    	}
	    	}
	    	
	    	return nerestFood;
	    	
    	}    	
    	else if(xFoodCords.size() == 1){
	    	double xFood = xFoodCords.get(0);
	    	double yFood = yFoodCords.get(0);
	    	double xDist = xFood-x;
	    	double yDist = yFood-y;
	    	double distCreatureFood = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
			angle = Math.toDegrees(Math.acos(xDist/distCreatureFood));
			if(yDist<0) angle = -angle;

	    	
	    	ArrayList<Double> nerestFood = new ArrayList<>();
			nerestFood.add(xFood);
			nerestFood.add(yFood);
			nerestFood.add(xDist);
			nerestFood.add(yDist);
			nerestFood.add(distCreatureFood);
			nerestFood.add(angle);
	    	
	    	return nerestFood;
    	}

		return null;
    }

	public int getFoodConsumed() {
		return foodConsumed;
	}

	public void setFoodConsumed(int foodConsumed) {
		this.foodConsumed = foodConsumed;
	}
	
    public int getxVelocity(int xVelocity) {
    	return xVelocity;
    }
	
    public void setxVelocity(int xVelocity) {
    	this.xVelocity = xVelocity;
    }

    public int getyVelocity(int yVelocity) {
    	return yVelocity;
    }
    
    public void setyVelocity(int yVelocity) {
    	this.yVelocity = yVelocity;
    }
    
    public int getCreatureWidth() {
		return creatureWidth;
	}

	public void setCreatureWidth(int creatureWidth) {
		this.creatureWidth = creatureWidth;
	}

	public int getCreatureHeight() {
		return creatureHeight;
	}

	public void setCreatureHeight(int creatureHeight) {
		this.creatureHeight = creatureHeight;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
}
