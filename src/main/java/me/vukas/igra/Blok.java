package me.vukas.igra;

import static me.vukas.Program.KOLONA;
import static me.vukas.igra.Igra.PRAZNIH_REDOVA;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.Collections;

public class Blok {

  private Terminal terminal;
  private int red;
  private int kolona;

  public Blok(Terminal terminal) {
    this.terminal = terminal;
  }

  public SveTacke daj(){
    return new SveTacke(Collections.singletonList(new Koordinate(red, kolona)));
  }

  public void nacrtajBlokBr(int br) throws IOException {
    red = PRAZNIH_REDOVA + 1 + Math.floorDiv(br , KOLONA);
    kolona = (br % KOLONA);

    TerminalPosition startPosition = terminal.getCursorPosition();
    terminal.setCursorPosition(startPosition.withColumn(kolona).withRow(red));

    switch (red){
      case 4:
        terminal.setBackgroundColor(TextColor.ANSI.RED);
        break;
      case 5:
        terminal.setBackgroundColor(TextColor.ANSI.BLUE);
        break;
      case 6:
        terminal.setBackgroundColor(TextColor.ANSI.WHITE);
        break;
    }

    terminal.putCharacter(' ');

  }
}
