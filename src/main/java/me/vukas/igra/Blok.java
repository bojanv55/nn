package me.vukas.igra;

import static me.vukas.Program.KOLONA;
import static me.vukas.igra.Igra.PRAZNIH_REDOVA;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Blok {

  private Terminal terminal;

  public Blok(Terminal terminal) {
    this.terminal = terminal;
  }

  public void nacrtajBlokBr(int br) throws IOException {
    int red = PRAZNIH_REDOVA + 1 + Math.floorDiv(br , KOLONA);
    int kolona = (br % KOLONA);

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
