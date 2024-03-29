package me.vukas.igra;

import static me.vukas.Program.KOLONA;
import static me.vukas.Program.KVADRAT;
import static me.vukas.igra.Igra.PRAZNIH_REDOVA;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

public class Blok {

  private Terminal terminal;
  private int red;
  private int kolona;

  int br;

  public Blok(Terminal terminal, int i) {
    this.terminal = terminal;
    br = i;
  }


  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    red = PRAZNIH_REDOVA + 1 + Math.floorDiv(br , KOLONA);
    kolona = (br % KOLONA);

    switch (red){
      case 4:
        g2d.setColor(Color.RED);
        break;
      case 5:
        g2d.setColor(Color.BLUE);
        break;
      case 6:
        g2d.setColor(Color.WHITE);
        break;
    }

    g2d.fillRect(kolona * KVADRAT, red * KVADRAT, KVADRAT, KVADRAT);

  }

  public SveTacke daj(){
    return new SveTacke(Collections.singletonList(new Koordinate(red, kolona)));
  }

  public void nacrtajBlokBr() throws IOException {
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

  public void ukloni() throws IOException {
    terminal.setCursorPosition(new TerminalPosition(kolona, red));
    terminal.setBackgroundColor(TextColor.ANSI.BLACK);
    terminal.putCharacter(' ');
  }

  public Igra.UdarU udaraU(PredSled sledPoz) {
    if (sledPoz.getSled().equals(new Koordinate(red, kolona))){
      //udara samo da vidimo smjer
      if(sledPoz.getPred().getRed() == red-1){
        return Igra.UdarU.BLOK_G;
      }
      if(sledPoz.getPred().getRed() == red+1){
        return Igra.UdarU.BLOK_DOLE;
      }
//      if(sledPoz.getPred().getKolona() == kolona-1){
//        return Igra.UdarU.BLOK_L;
//      }
//      if(sledPoz.getPred().getKolona() == kolona+1){
//        return Igra.UdarU.BLOK_DESNO;
//      }
    }
    return null;
  }

  public Igra.UdarU udaraU(Koordinate sledPoz) {
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Blok blok = (Blok) o;
    return red == blok.red &&
            kolona == blok.kolona;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, kolona);
  }
}
