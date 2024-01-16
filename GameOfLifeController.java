package tp5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLifeController implements ActionListener {
    private GameOfLifeView view;
    private GameOfLifeModel model;
    private boolean running;
    private Thread gameThread;

    public GameOfLifeController(GameOfLifeView view, GameOfLifeModel model) {
        this.view = view;
        this.view.setController(this);
        this.model = model;
        this.model.setController(this);
        this.running = false;
    }

    public int getWidth() {
        return model.getWidth();
    }

    public int getHeight() {
        return model.getHeight();
    }

    public boolean isAlive(int x, int y) {
        return model.isAlive(x, y);
    }

    public void setAlive(int x, int y) {
        model.setAlive(x, y);
    }

    public void setDead(int x, int y) {
        model.setDead(x, y);
    }

    public void start() {
        if (!running) {
            running = true;
            gameThread = new Thread(new GameOfLifeRunnable());
            gameThread.start();
        }
    }

    public void stop() {
        running = false;
        if (gameThread != null) {
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        model.nextGeneration();
    }

    private class GameOfLifeRunnable implements Runnable {
        public void run() {
            while (running) {
                model.nextGeneration();
                view.refreshGrid();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}