package me.vukas.igra;

import static me.vukas.Program.*;
import static me.vukas.Program.KVADRAT;
import static me.vukas.igra.Igra.PRAZNIH_REDOVA;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reket {
  private Terminal terminal;
  private static final int DUZINA_REKETA = 12;
  private static final int DUZINA_ZONE = Math.floorDiv(DUZINA_REKETA, 3);

  public Igra.UdarU udaraU(PredSled sledPoz) {
    if (REKET_ZONE_L.uZoni(sledPoz.getSled())) {
      return Igra.UdarU.REKET_L;
    }
    if(REKET_ZONE_C.uZoni(sledPoz.getSled())){
      return Igra.UdarU.REKET_C;
    }
    if(REKET_ZONE_D.uZoni(sledPoz.getSled())){
      return Igra.UdarU.REKET_D;
    }
    return null;
  }

  public class Zona{
    int zonaOd;
    int zonaDo;

    public Zona(int zonaOd, int zonaDo) {
      this.zonaOd = zonaOd;
      this.zonaDo = zonaDo;
    }

    public boolean uZoni(Koordinate koordinate){
      return koordinate.getRed()==REDOVA-1 && koordinate.getKolona()>= pocetak+zonaOd && koordinate.getKolona()<=zonaDo+pocetak;
    }
  }

  private Zona REKET_ZONE_L = new Zona(1 ,DUZINA_ZONE);
  private Zona REKET_ZONE_C = new Zona(DUZINA_ZONE+1 ,DUZINA_ZONE*2);
  private Zona REKET_ZONE_D = new Zona(DUZINA_ZONE*2+1 ,DUZINA_ZONE*3);

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

  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.GREEN);

    g2d.fillRect(pocetak * KVADRAT, (REDOVA * KVADRAT)-3*KVADRAT, DUZINA_REKETA * KVADRAT, KVADRAT);

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
