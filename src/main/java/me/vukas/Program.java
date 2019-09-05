package me.vukas;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import me.vukas.igra.Igra;

public class Program {
  public final static int KOLONA = 50;
  public final static int REDOVA = 25;

  public static void main(String[] args) throws InterruptedException {
    DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

    Terminal terminal = null;
    try {
      terminal = defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(KOLONA, REDOVA)).createTerminal();
      terminal.setCursorVisible(false);

      Igra i = new Igra(terminal);
      i.kreni();

//      terminal.putCharacter('H');
//      terminal.putCharacter('e');
//      terminal.putCharacter('l');
//      terminal.putCharacter('l');
//      terminal.putCharacter('o');
//      terminal.putCharacter('\n');
//      terminal.flush();
//
//      Thread.sleep(2000);
//
//      TerminalPosition startPosition = terminal.getCursorPosition();
//      terminal.setCursorPosition(startPosition.withRelativeColumn(3).withRelativeRow(2));
//      terminal.flush();
//      Thread.sleep(2000);
//
//      terminal.setBackgroundColor(TextColor.ANSI.BLUE);
//      terminal.setForegroundColor(TextColor.ANSI.YELLOW);
//
//      terminal.putCharacter('Y');
//      terminal.putCharacter('e');
//      terminal.putCharacter('l');
//      terminal.putCharacter('l');
//      terminal.putCharacter('o');
//      terminal.putCharacter('w');
//      terminal.putCharacter(' ');
//      terminal.putCharacter('o');
//      terminal.putCharacter('n');
//      terminal.putCharacter(' ');
//      terminal.putCharacter('b');
//      terminal.putCharacter('l');
//      terminal.putCharacter('u');
//      terminal.putCharacter('e');
//      terminal.flush();
//      Thread.sleep(2000);
//
//      terminal.setCursorPosition(startPosition.withRelativeColumn(3).withRelativeRow(3));
//      terminal.flush();
//      Thread.sleep(2000);
//      terminal.enableSGR(SGR.BOLD);
//      terminal.putCharacter('Y');
//      terminal.putCharacter('e');
//      terminal.putCharacter('l');
//      terminal.putCharacter('l');
//      terminal.putCharacter('o');
//      terminal.putCharacter('w');
//      terminal.putCharacter(' ');
//      terminal.putCharacter('o');
//      terminal.putCharacter('n');
//      terminal.putCharacter(' ');
//      terminal.putCharacter('b');
//      terminal.putCharacter('l');
//      terminal.putCharacter('u');
//      terminal.putCharacter('e');
//      terminal.flush();
//      Thread.sleep(2000);
//
//      terminal.resetColorAndSGR();
//      terminal.setCursorPosition(terminal.getCursorPosition().withColumn(0).withRelativeRow(1));
//      terminal.putCharacter('D');
//      terminal.putCharacter('o');
//      terminal.putCharacter('n');
//      terminal.putCharacter('e');
//      terminal.putCharacter('\n');
//      terminal.flush();
//
//      Thread.sleep(2000);
//      terminal.bell();
//      terminal.flush();
//      Thread.sleep(2000);

      KeyStroke key = terminal.readInput();
      while(!key.equals(new KeyStroke(KeyType.Escape))) {
        i.primiKomandu(key);
        Thread.sleep(5);
        key = terminal.readInput();
      }

    }
    catch(IOException e) {
      e.printStackTrace();
    }
    finally {
      if (terminal != null) {
        try {
          terminal.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
