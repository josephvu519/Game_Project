/**
 * Created by adina on 4/8/2018.
 */


/**
 * GameView
 * Probably the most important class for the game
 *
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quchen>
 */

package com.example.game_project;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Timer;
        import java.util.TimerTask;
        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.os.Build;
        import android.os.Message;
        import android.view.MotionEvent;
        import android.view.SurfaceHolder;
        import android.view.SurfaceView;

        import com.example.game_project.Accessories.Background;
        import com.example.game_project.Accessories.Bubble;

public class GameViewActivity extends SurfaceView {

    /** Milliseconds for game timer tick */
    public static final long UPDATE_INTERVAL = 50;        // = 20 FPS

    private Timer timer = new Timer();
    private TimerTask timerTask;

    /** The surfaceholder needed for the canvas drawing */
    private SurfaceHolder holder;

    private GameplayActivity game;
    private Character player;
    private Background background;
  //  private Frontground frontground;
  //  private List<Obstacle> obstacles = new ArrayList<Obstacle>();
  //  private List<PowerUp> powerUps = new ArrayList<PowerUp>();

    private PausedActivity pauseButton;
    volatile private boolean paused = true;

 //   private Tutorial tutorial;
 //   private boolean tutorialIsShown = true;

    public GameViewActivity(Context context) {
        super(context);
        this.game = (GameplayActivity) context;
        setFocusable(true);

        holder = getHolder();
     //   player = new Bubble(this, game);
        background = new Background(this, game);
      //  frontground = new Frontground(this, game);
     //   pauseButton = new PausedActivity(this, game);
     //   tutorial = new Tutorial(this, game);
    }

    private void startTimer() {
        setUpTimerTask();
        timer = new Timer();
        timer.schedule(timerTask, UPDATE_INTERVAL, UPDATE_INTERVAL);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        if (timerTask != null) {
            timerTask.cancel();
        }
    }

    private void setUpTimerTask() {
        stopTimer();
      /*  timerTask = new TimerTask() {
            @Override
            public void run() {
                GameViewActivity.this.run();
            }
        };*/
    }

    @Override
    public boolean performClick() {
        return super.performClick();
        // Just to remove the stupid warning
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
      /*  if(event.getAction() == MotionEvent.ACTION_DOWN  // Only for "touchdowns"
                && !this.player.isDead()){ // No support for dead players
            if(tutorialIsShown){
                // dismiss tutorial
                tutorialIsShown = false;
                resume();
                this.player.onTap();
            }else if(paused){
                resume();
            }else if(pauseButton.isTouching((int) event.getX(), (int) event.getY()) && !this.paused){
                pause();
            }else{
                this.player.onTap();
            }
        }*/
        return true;
    }

    /**
     * content of the timertask
     */
  /*  public void run() {
        checkPasses();
        checkOutOfRange();
        checkCollision();
        createObstacle();
        move();

        draw();
    }*/



    private Canvas getCanvas() {
        Canvas canvas;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            canvas = holder.lockHardwareCanvas();
        } else {
            canvas = holder.lockCanvas();
        }

        return canvas;
    }

    /**
     * Draw Tutorial
     */
  /*  public void showTutorial(){
        player.move();
        pauseButton.move();

        while(!holder.getSurface().isValid()){
            /*wait*/
   /*         try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        Canvas canvas = getCanvas();
        drawCanvas(canvas, true);
        tutorial.move();
        tutorial.draw(canvas);
        holder.unlockCanvasAndPost(canvas);
    } */

    public void pause(){
        stopTimer();
        paused = true;
    }

  /*  public void drawOnce(){
        (new Thread(new Runnable() {
            @Override
            public void run() {
                if(tutorialIsShown){
                    showTutorial();
                } else {
                    draw();
                }
            }
        })).start();
    }*/

    public void resume(){
        paused = false;
        startTimer();
    }

    /**
     * Draws all gameobjects on the surface
     */
    private void draw() {
        while(!holder.getSurface().isValid()){
            /*wait*/
            try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        Canvas canvas = getCanvas();

        drawCanvas(canvas, true);

        holder.unlockCanvasAndPost(canvas);
    }

    /**
     * Draws everything normal,
     * except the player will only be drawn, when the parameter is true
     * @param drawPlayer
     */
    private void drawCanvas(Canvas canvas, boolean drawPlayer){
        background.draw(canvas);
     /*   for(Obstacle r : obstacles){
            r.draw(canvas);
        }
        for(PowerUp p : powerUps){
            p.draw(canvas);
        }
        if(drawPlayer){
            player.draw(canvas);
        }
        frontground.draw(canvas); */
      //  pauseButton.draw(canvas);

        // Score Text
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(getScoreTextMetrics());
       // canvas.drawText(game.getResources().getString(R.string.onscreen_score_text) + " " + game.accomplishmentBox.points
       //                 + " / " + game.getResources().getString(R.string.onscreen_coin_text) + " " + game.coins,
       //         0, getScoreTextMetrics(), paint);
    }

    /**
     * Let the player fall to the ground
     */
  /*  private void playerDeadFall(){
        player.dead();
        do{
            player.move();
            draw();
            // sleep
            try { Thread.sleep(UPDATE_INTERVAL/4); } catch (InterruptedException e) { e.printStackTrace(); }
        }while(!player.isTouchingGround());
    } */

    /**
     * Checks whether an obstacle is passed.
     */
 /*   private void checkPasses(){
        for(Obstacle o : obstacles){
            if(o.isPassed()){
                if(!o.isAlreadyPassed){    // probably not needed
                    o.onPass();
                    createPowerUp();
                }
            }
        }
    }*/


    /**
     * Checks collisions and performs the action
     */
 /*   private void checkCollision(){
        for(Obstacle o : obstacles){
            if(o.isColliding(player)){
                o.onCollision();
                gameOver();
            }
        }
        for(int i=0; i<powerUps.size(); i++){
            if(this.powerUps.get(i).isColliding(player)){
                this.powerUps.get(i).onCollision();
                this.powerUps.remove(i);
                i--;
            }
        }
        if(player.isTouchingEdge()){
            gameOver();
        }
    }*/

    /**
     * if no obstacle is present a new one is created
     */
 /*   private void createObstacle(){
        if(obstacles.size() < 1){
            obstacles.add(new Obstacle(this, game));
        }
    }*/

    /**
     * return the speed of the obstacles/cow
     */
 /*   public int getSpeedX(){
        // 16 @ 720x1280 px
        int speedDefault = this.getWidth() / 45;

        // 1,2 every 4 points @ 720x1280 px
        int speedIncrease = (int) (this.getWidth() / 600f * (game.accomplishmentBox.points / 4));

        int speed = speedDefault + speedIncrease;

        return Math.min(speed, 2*speedDefault);
    }
*/
    /**
     * Let's the player fall down dead, makes sure the runcycle stops
     * and invokes the next method for the dialog and stuff.
     */
    public void gameOver(){
        pause();
     //   playerDeadFall();
     //   game.GameOverActivity();
    }

    /**
     * A value for the position and size of the onScreen score Text
     */
    public int getScoreTextMetrics(){
        return (int) (this.getHeight() / 21.0f);
        /*/ game.getResources().getDisplayMetrics().density)*/
    }

    public Character getPlayer(){
        return this.player;
    }

    public GameplayActivity getGame(){
        return this.game;
    }

}
