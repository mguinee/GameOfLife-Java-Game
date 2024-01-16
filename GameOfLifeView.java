package tp5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOfLifeView {
    private int width;
    private int height;
    private int cellSize;
    private GameOfLifeController controller;
    private JButton[][] buttons;
    private JButton startStopButton;
    

    public GameOfLifeView(int width, int height, int cellSize) {
        this.width = width;
        this.height = height;
        this.cellSize=cellSize;
        this.buttons = new JButton[width][height];
        this.startStopButton = new JButton("Start");
        createGrid();
    }
    
    private void createGrid() {
        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(width, height));

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                button.setOpaque(true);
                button.setPreferredSize(new Dimension(cellSize, cellSize));
                button.setBorderPainted(false);
                button.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                    	int xPos=0;
                    	int yPos=0;
                    	for (int i = 0; i < width; i++) {
                            for (int j = 0; j < height; j++) {
                            	if (buttons[i][j]==button) {
                            		xPos=i;
                            		yPos=j;
                            		break;
                            	}
                            }
                    	}
                        if (controller.isAlive(xPos, yPos)) {
                            controller.setDead(xPos, yPos);
                        } else {
                            controller.setAlive(xPos, yPos);
                        }
                        refreshGrid();
                    }
                });
                
                frame.add(button);
                buttons[i][j] = button;
            }
        }
        
        
        startStopButton.setText("Start");
        startStopButton.setPreferredSize(new Dimension(300, 300));
        startStopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (startStopButton.getText().equals("Start")) {
                    controller.start();
                    startStopButton.setText("Stop");
                } else {
                    controller.stop();
                    startStopButton.setText("Start");
                }
                refreshGrid();
            }
        });	
        
        frame.add(startStopButton);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void setController(GameOfLifeController controller) {
        this.controller = controller;
    }

    public void refreshGrid() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(controller.isAlive(i, j)) {
                	buttons[i][j].setBackground(Color.BLACK);
                }
                else {
                	buttons[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
}