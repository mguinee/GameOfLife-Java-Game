package tp5;

import java.util.Random;



public class GameOfLifeModel {
	private int width;
	private int height;
	private boolean autofill;
	private boolean[][] grid;
	private GameOfLifeController controller;
	
	public GameOfLifeModel(int width,int height, boolean autofill) {
		this.width = width;
		this.height=height;
		this.autofill=autofill;
		this.grid = new boolean[width][height];
        if (autofill) {
        	initializeRandGrid();
        }

	}
	

	public void initializeRandGrid() {
		Random rand = new Random();
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length; j++) {
				this.grid[i][j] = ((rand.nextInt(100) + 1) <= 25 );
			}
		}
	}
	public void setController(GameOfLifeController controller) {
        this.controller = controller;
    }
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public boolean[][] getGrid(){
		return grid;
	}
	
	public int countNeighbours(int x, int y) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < width && j >= 0 && j < height && !(i == x && j == y)) {
                    if(grid[i][j] & (i!=x || j!=y)) {
                    	count+=1;
                    }
                }
            }
        }
        return count;
    }
	public boolean isAlive(int x, int y) {
		return grid[x][y];
	}
	
	public void setAlive(int x, int y) {
		grid[x][y]=true;
	}
	
	public void setDead(int x, int y) {
		grid[x][y]=false;
	}
	
	
	public boolean isAliveNextGen(int x,int y) {	
		int neighbourCount = countNeighbours(x, y);
        if (grid[x][y] && (neighbourCount == 2 || neighbourCount == 3)) {
            return true;
        } else if (!grid[x][y] && neighbourCount == 3) {
            return true;
        } else {
            return false;
        }
	}
	
	public void nextGeneration() {
		boolean[][] nextGrid = new boolean[width][height];
	    for (int i = 0; i < width; i++) {
	    	for (int j = 0; j < height; j++) {
	        	nextGrid[i][j] = isAliveNextGen(i, j);
	            
	    	}
	    }
	    grid = nextGrid;
	}

	

	
	

}