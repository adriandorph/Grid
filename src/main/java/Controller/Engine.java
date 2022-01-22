package Controller;

import View.Renderable;
import javafx.application.Platform;

public abstract class Engine<RenderObject> implements Runnable {
    protected volatile boolean running;
    protected volatile boolean stopPaused;
    private final double secondsPerFrame;
    protected final Renderable<RenderObject> renderable;
    protected final Updatable<RenderObject> updatable;
    protected int actualFPS;
    private boolean dispose = false;

    public Engine(Renderable<RenderObject> renderable, Updatable<RenderObject> updatable, int FPS){
        this.renderable = renderable;
        this.updatable = updatable;
        this.secondsPerFrame = 1.0/FPS;
    }

    public void start(){
        Thread thread = new Thread(this); // Demands that this is Runnable
        thread.start();
    }

    public void stop(){
        running = false;
    }

    public void dispose(){
        stopPaused = true;
        dispose = true;
        running = false;
    }

    protected abstract void stopped();
    protected abstract boolean pauseCondition();

    @Override
    public void run(){

        running = true;
        stopPaused = false;
        double lastTime = System.nanoTime() / 1000000000.0; // 1 = 1 second
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;

        while (running){
            while(!stopPaused && pauseCondition()){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lastTime = System.nanoTime() / 1000000000.0;
            }
            boolean render = false;
            double firstTime = System.nanoTime() / 1000000000.0;
            double passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while(unprocessedTime >= secondsPerFrame){
                unprocessedTime -= secondsPerFrame;
                render = true;
                //update
                updatable.update(secondsPerFrame);//In seconds
                 if (updatable.stopCondition()) running = false;

                if (frameTime >= 1){
                    frameTime = 0;
                    actualFPS = frames;
                    System.out.println("FPS: "+actualFPS);
                    frames = 0;
                }
            }

            if (render){
                //render game
                Platform.runLater(() -> renderable.render(updatable.getRenderObject()));
                frames++;

            } else { //Nothing to do, so wait a bit before running through the running loop again.
                //Decreases CPU-load by around 85%
                try {
                    Thread.sleep(0,500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Engine stopped");
        if(!dispose) stopped();
    }
}
