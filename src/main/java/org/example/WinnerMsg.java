package org.example;

public class WinnerMsg {
    WinEnum winner;
    String message;

    public WinEnum getWinner() {return winner;}
    public String getMessage() {return message;}

    public void setWinner(WinEnum winner){
        this.winner = winner;
    }
    public void setMessage(String message){
        this.message = message;
    }
}
