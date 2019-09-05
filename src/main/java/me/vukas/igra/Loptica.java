package me.vukas.igra;

import static me.vukas.Program.KOLONA;
import static me.vukas.Program.REDOVA;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.Map;

public class Loptica {

  private Terminal terminal;
  private enum Smjer{
    DOLE,
    DOLE_LIJEVO,
    DOLE_DESNO,
    GORE,
    GORE_LIJEVO,
    GORE_DESNO
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

  public PredSled sledecaPozicija(){
    Koordinate k = null;
    switch (smjer){
      case GORE:
        k = new Koordinate(red-1, kolona);
        break;
      case GORE_DESNO:
        k = new Koordinate(red-1, kolona+1);
        break;
      case GORE_LIJEVO:
        k = new Koordinate(red-1, kolona-1);
        break;
      case DOLE:
        k = new Koordinate(red+1, kolona);
        break;
      case DOLE_DESNO:
        k = new Koordinate(red+1, kolona+1);
        break;
      case DOLE_LIJEVO:
        k = new Koordinate(red+1, kolona-1);
        break;
    }

    Koordinate pred = new Koordinate(red, kolona);
    return new PredSled(pred, k);
  }

  private void pomjeriDolje() throws IOException {
    brisiLoptu();

    red++;

    crtajLoptu();

    terminal.flush();
  }

  private void pomjeriGore() throws IOException {
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

  public void osvjezi(Igra.UdarU udarU) throws IOException {

    if(udarU!=null){
      switch (udarU){
        case REKET_D:
          smjer = Smjer.GORE_DESNO;
          break;
        case REKET_C:
          smjer = Smjer.GORE;
          break;
        case REKET_L:
          smjer = Smjer.GORE_LIJEVO;
          break;
        case BLOK_G:
          if(smjer == Smjer.DOLE) {
            smjer = Smjer.GORE;
          }
          else if(smjer == Smjer.DOLE_DESNO){
            smjer = Smjer.GORE_DESNO;
          }
          else if(smjer == Smjer.DOLE_LIJEVO){
            smjer = Smjer.GORE_LIJEVO;
          }
          break;
//        case BLOK_DESNO:
//          if(smjer == Smjer.GORE_LIJEVO){
//            smjer = Smjer.GORE_DESNO;
//          }
//          else if(smjer == Smjer.DOLE_LIJEVO){
//            smjer = Smjer.DOLE_DESNO;
//          }
//          break;
//        case BLOK_L:
//          if(smjer == Smjer.GORE_DESNO){
//            smjer = Smjer.GORE_LIJEVO;
//          }
//          else if(smjer == Smjer.DOLE_DESNO){
//            smjer = Smjer.DOLE_LIJEVO;
//          }
//          break;
        case BLOK_DOLE:
          if(smjer == Smjer.GORE) {
            smjer = Smjer.DOLE;
          }
          else if(smjer == Smjer.GORE_LIJEVO){
            smjer = Smjer.DOLE_LIJEVO;
          }
          else if(smjer == Smjer.GORE_DESNO){
            smjer = Smjer.DOLE_DESNO;
          }
          break;
        case ZID_G:
          if(smjer == Smjer.GORE) {
            smjer = Smjer.DOLE;
          }
          else if(smjer == Smjer.GORE_LIJEVO){
            smjer = Smjer.DOLE_LIJEVO;
          }
          else if(smjer == Smjer.GORE_DESNO){
            smjer = Smjer.DOLE_DESNO;
          }
          break;
        case ZID_L:
          if(smjer == Smjer.GORE_LIJEVO){
            smjer = Smjer.GORE_DESNO;
          }
          else if(smjer == Smjer.DOLE_LIJEVO){
            smjer = Smjer.DOLE_DESNO;
          }
          break;
        case ZID_D:
          if(smjer == Smjer.GORE_DESNO){
            smjer = Smjer.GORE_LIJEVO;
          }
          else if(smjer == Smjer.DOLE_DESNO){
            smjer = Smjer.DOLE_LIJEVO;
          }
          break;
      }
    }

    switch (smjer){
      case DOLE:
        pomjeriDolje();
        break;
      case GORE:
        pomjeriGore();
        break;
      case DOLE_DESNO:
        pomjeriDoleDesno();
        break;
      case DOLE_LIJEVO:
        pomjeriDoleLijevo();
        break;
      case GORE_DESNO:
        pomjeriGoreDesno();
        break;
      case GORE_LIJEVO:
        pomjeriGoreLijevo();
        break;
    }
  }

  private void pomjeriDoleLijevo() throws IOException {
    brisiLoptu();

    red++;
    kolona--;

    crtajLoptu();

    terminal.flush();
  }

  private void pomjeriDoleDesno() throws IOException {
    brisiLoptu();

    red++;
    kolona++;

    crtajLoptu();

    terminal.flush();
  }

  private void pomjeriGoreLijevo() throws IOException {
    brisiLoptu();

    red--;
    kolona--;

    crtajLoptu();

    terminal.flush();
  }

  private void pomjeriGoreDesno() throws IOException {
    brisiLoptu();

    red--;
    kolona++;

    crtajLoptu();

    terminal.flush();
  }

}
