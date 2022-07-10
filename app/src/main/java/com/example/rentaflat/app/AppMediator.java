package com.example.rentaflat.app;

import com.example.rentaflat.flat.FlatState;
import com.example.rentaflat.flats.FlatsState;
import com.example.rentaflat.login.LoginState;
import com.example.rentaflat.register.RegisterState;

@SuppressWarnings("unused")
public class AppMediator {

    private static AppMediator INSTANCE;

    private FlatsState flatsState;
    private FlatState flatState;
    private RegisterState registerState;
    private LoginState loginState;


    private FlatsToFlatState flatsToFlatState;
    private LoginToFlatsState loginToFlatsState;
    private RegisterToFlatsState registerToFlatsState;


    private AppMediator() {
        flatsState = new FlatsState();
        flatsToFlatState = new FlatsToFlatState();
        flatState = new FlatState();
        registerState = new RegisterState();
        loginState = new LoginState();
        loginToFlatsState = new LoginToFlatsState();
        registerToFlatsState = new RegisterToFlatsState();

    }


    public static AppMediator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppMediator();
        }

        return INSTANCE;
    }

    // to reset the state when testing
    public static void resetInstance() { INSTANCE = null; }

    public FlatsState getFlatsState() { return flatsState;}
    public FlatState getFlatState() { return flatState;}
    public RegisterState getRegisterState() {return registerState;}
    public LoginState getLoginState() {return loginState;}

    public FlatsToFlatState getFlatsToFlatState() {
        FlatsToFlatState state = flatsToFlatState;
        flatsToFlatState = null;
        return state;
    }
    public void setFlatsToFlatState(FlatsToFlatState state) {
        this.flatsToFlatState = state;
    }

    public RegisterToFlatsState getRegisterToFlatState() {
        RegisterToFlatsState state = registerToFlatsState;
        registerToFlatsState = null;
        return state;
    }
    public void setRegisterToFlatState(RegisterToFlatsState state) {this.registerToFlatsState = state;}

    public LoginToFlatsState getLoginToFlatsState() {
        LoginToFlatsState state = loginToFlatsState;
        loginToFlatsState = null;
        return state;
    }
    public void setLoginToFlatsState(LoginToFlatsState loginToFlatsState) {this.loginToFlatsState = loginToFlatsState; }
}

