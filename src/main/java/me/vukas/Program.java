package me.vukas;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.vukas.igra.Blok;
import me.vukas.igra.Igra;
import me.vukas.igra.Reket;

import javax.swing.*;

public class Program {
  public final static int KOLONA = 50;
  public final static int REDOVA = 50;

  public final static int KVADRAT = 10;

  public static class TestPane extends JPanel {

    List<Blok> blokovi = new ArrayList<>();
    Reket r;

    public void dodajBlok(Blok b){
      blokovi.add(b);
    }

    public void dodajReket(Reket r){
      this.r = r;
    }

    @Override
    public Dimension getPreferredSize() {
      return new Dimension(KOLONA*KVADRAT, REDOVA*KVADRAT);
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);


      for(Blok b : blokovi){
        b.paintComponent(g);
      }

      r.paintComponent(g);

//      g.setColor(Color.GREEN);
//      g.fillRect(0, 0, WIDTH, HEIGHT);
//      g.setColor(Color.BLACK);
//      g.fillOval(100, 100, 30, 30);
    }
  }

  public static void main(String[] args) throws InterruptedException {

    JFrame j = new JFrame("Igra");
    j.setSize(KOLONA*KVADRAT, REDOVA*KVADRAT);
    j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    j.setLayout(new BorderLayout());
    TestPane p = new TestPane();
    j.add(p);
    j.setVisible(true);


    DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

    Terminal terminal = null;
    try {
      terminal = defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(KOLONA, REDOVA)).createTerminal();
      terminal.setCursorVisible(false);

      Igra i = new Igra(terminal, p);

      Terminal finalTerminal = terminal;
      Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
          KeyStroke key = null;
          try {
            key = finalTerminal.pollInput();
            while(key==null || !key.equals(new KeyStroke(KeyType.Escape))) {
              if(key!=null) {
                i.primiKomandu(key);
                //Thread.sleep(1);
              }
              key = finalTerminal.pollInput();
            }
            i.stop();
          } catch (IOException e) {
            e.printStackTrace();
//          } catch (InterruptedException e) {
//            e.printStackTrace();
          }
        }
      });
      t.setDaemon(true);
      t.start();

      while(!i.stop) {
        i.ponovo();
        i.kreni();
      }


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
