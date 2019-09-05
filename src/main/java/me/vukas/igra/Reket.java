package me.vukas.igra;

import static me.vukas.Program.KOLONA;
import static me.vukas.Program.REDOVA;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Reket {
  private Terminal terminal;
  private static final int DUZINA_REKETA = 12;

  private int pocetak;

  public Reket(Terminal terminal) {
    this.terminal = terminal;
    pocetak = (Math.floorDiv(KOLONA, 2)-Math.floorDiv(
        DUZINA_REKETA,2));
  }

  public void nacrtajReket() throws IOException {

    TerminalPosition startPosition = terminal.getCursorPosition();


    terminal.setBackgroundColor(ANSI.GREEN);

    for(int i=1; i<= DUZINA_REKETA; i++) {
      terminal.setCursorPosition(startPosition.withColumn(pocetak + i).withRow(REDOVA));
      terminal.putCharacter(' ');
    }

  }

  public void pomjeriLijevo() throws IOException {
    terminal.setBackgroundColor(ANSI.BLACK);

    for(int i=1; i<= DUZINA_REKETA; i++) {
      terminal.setCursorPosition(new TerminalPosition(pocetak + i, REDOVA));
      terminal.putCharacter(' ');
    }

    if(pocetak>-1) {
      pocetak--;
    }

    terminal.setBackgroundColor(ANSI.GREEN);

    for(int i=1; i<= DUZINA_REKETA; i++) {
      terminal.setCursorPosition(new TerminalPosition(pocetak + i, REDOVA));
      terminal.putCharacter(' ');
    }

    terminal.flush();
  }

  public void pomjeriDesno() throws IOException {
    terminal.setBackgroundColor(ANSI.BLACK);

    for(int i=1; i<= DUZINA_REKETA; i++) {
      terminal.setCursorPosition(new TerminalPosition(pocetak + i, REDOVA));
      terminal.putCharacter(' ');
    }

    if(pocetak<KOLONA-DUZINA_REKETA-1) {
      pocetak++;
    }

    terminal.setBackgroundColor(ANSI.GREEN);

    for(int i=1; i<= DUZINA_REKETA; i++) {
      terminal.setCursorPosition(new TerminalPosition(pocetak + i, REDOVA));
      terminal.putCharacter(' ');
    }

    terminal.flush();
  }
}
