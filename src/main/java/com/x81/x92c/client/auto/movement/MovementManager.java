package com.x81.x92c.client.auto.movement;

public class MovementManager {
    private static MovementManager single_instance = null;

    private MovementManager () { }

    public static MovementManager getInstance() {
        if (single_instance == null)
            single_instance = new MovementManager();

        return single_instance;
    }

    //---------------------------------------------------------

    //State
    public boolean keyWalk = false;
    public boolean keyJump = false;
    public boolean keySprint = false;

    //methods
    public void all () {
        keySprint = true;
        keyWalk = true;
        keyJump = true;
    }

    public void none () {
        keySprint = false;
        keyWalk = false;
        keyJump = false;
    }

    public boolean any () {
        return keySprint || keyWalk || keyJump;
    }

    //Update methods
    public boolean _switchFrame = false;

    public void updateOnNextTick () {
        _switchFrame = true;
    }

    public void onDidUpdate () {
        _switchFrame = false;
    }
}
