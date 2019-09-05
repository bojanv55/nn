package me.vukas.igra;

import static me.vukas.Program.KOLONA;
import static me.vukas.Program.REDOVA;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Loptica {

  private Terminal terminal;
  private enum Smjer{
    DOLE,
    GORE
  }
  private Smjer smjer = Smjer.DOLE;
  private int kolona;
  private int red;

  public Loptica(Terminal terminal) {
    this.terminal = terminal;
    kolona = Math.floorDiv(KOLONA, 2);
    red = REDOVA - Math.floorDiv((REDOVA-7),2);
  }

  public void nacrtajLopticu() throws IOException {
    TerminalPosition startPosition = terminal.getCursorPosition();
    terminal.setCursorPosition(startPosition.withColumn(kolona).withRow(red));
    terminal.setBackgroundColor(ANSI.YELLOW);
    terminal.putCharacter(' ');
  }

  private void pomjeriDolje() throws IOException {

    //ako smo dosli do reketa odozgo ili do nekog bloka odozgo, smjer ka gore
    if(red == REDOVA - 1){
      smjer = Smjer.GORE;
      pomjeriGore();
      return;
    }

    brisiLoptu();

    red++;

    crtajLoptu();

    terminal.flush();
  }

  private void pomjeriGore() throws IOException {

    //ako smo dosli do nekog od blokova odozdo ili do gornjeg zida, smjer ka dolje

    brisiLoptu();

    red--;

    crtajLoptu();

    terminal.flush();
  }

  private void crtajLoptu() throws IOException {
    terminal.setCursorPosition(new TerminalPosition(kolona, red));
    terminal.setBackgroundColor(ANSI.YELLOW);
    terminal.putCharacter(' ');
  }

  private void brisiLoptu() throws IOException {
    terminal.setCursorPosition(new TerminalPosition(kolona, red));
    terminal.setBackgroundColor(ANSI.BLACK);
    terminal.putCharacter(' ');
  }

  public void osvjezi() throws IOException {
    switch (smjer){
      case DOLE:
        pomjeriDolje();
        break;
      case GORE:
        pomjeriGore();
        break;
    }
  }

}
