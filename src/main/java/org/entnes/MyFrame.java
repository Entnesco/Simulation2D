package org.entnes;


import javax.swing.*;

public class MyFrame extends JFrame{
	
	MainPanel mainPanel;
	
	MyFrame(){
		
		mainPanel = new MainPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);   

	}

	


}
