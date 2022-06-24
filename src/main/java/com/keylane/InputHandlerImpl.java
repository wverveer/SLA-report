package com.keylane;

public class InputHandlerImpl implements InputHandler{
    public String getInputChoice(){
        return System.console().readLine();
    }

    public String getInput(){
        return System.console().readLine();
    }
}
