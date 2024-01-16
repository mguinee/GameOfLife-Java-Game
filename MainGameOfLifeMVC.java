package tp5;

public class MainGameOfLifeMVC {
	private GameOfLifeView view;
	private GameOfLifeModel model;
	private GameOfLifeController controller;
	// Give randomly the dead / alive status to each cell at the
	// initialisation or make each cell dead
	private static boolean DEFAULT_AUTOFILL = false;
	// Number of cells
	private static int DEFAULT_WIDTH = 50;
	private static int DEFAULT_HEIGHT = 50;
	// Cell size in pixels
	private static int DEFAULT_CELL_SIZE = 15;
	public MainGameOfLifeMVC(int width, int height, int cellSize, boolean autofill) {
		model = new GameOfLifeModel(width, height, true);
		view = new GameOfLifeView(width, height, cellSize);
		controller = new GameOfLifeController(view, model);
	}
	public GameOfLifeView getView() {
		return this.view;
	}
	public GameOfLifeController getController() {
		return this.controller;
	}
	public GameOfLifeModel getModel() {
		return this.model;
	}
	public static void main(String[] args) {
		new MainGameOfLifeMVC(
				MainGameOfLifeMVC.DEFAULT_WIDTH,
				MainGameOfLifeMVC.DEFAULT_HEIGHT,
				MainGameOfLifeMVC.DEFAULT_CELL_SIZE,
				MainGameOfLifeMVC.DEFAULT_AUTOFILL
				);
	}
}
