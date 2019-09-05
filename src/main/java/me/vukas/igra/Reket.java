package me.vukas.igra;

import static me.vukas.Program.KOLONA;
import static me.vukas.Program.REDOVA;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reket {
  private Terminal terminal;
  private static final int DUZINA_REKETA = 12;
  private static final int DUZINA_ZONE = Math.floorDiv(DUZINA_REKETA, 3);

  private static class Zona{
    int zonaOd;
    int zonaDo;

    public Zona(int zonaOd, int zonaDo) {
      this.zonaOd = zonaOd;
      this.zonaDo = zonaDo;
    }

    public boolean uZoni(int n){
      return n>=zonaOd && n<=zonaDo;
    }
  }

  private static final Zona REKET_ZONE_L = new Zona(0 ,DUZINA_ZONE);
  private static final Zona REKOT_ZONE_C = new Zona(DUZINA_ZONE+1 ,DUZINA_ZONE*2);
  private static final Zona REKOT_ZONE_D = new Zona(DUZINA_ZONE*2+1 ,DUZINA_ZONE*3);

  private int pocetak;

  public SveTacke daj(){
    List<Koordinate> koordinates = new ArrayList<>();
    for(int i=1; i<= DUZINA_REKETA; i++) {
      koordinates.add(new Koordinate(pocetak + i, REDOVA));
    }
    return new SveTacke(koordinates);
  }

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
