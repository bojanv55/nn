package me.vukas.igra;

import static me.vukas.Program.KOLONA;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Igra {

  public static final int PRAZNIH_REDOVA = 3;
  public static final int REDOVA_KOCKI = 3;

  private static final KeyStroke LIJEVO = new KeyStroke(KeyType.ArrowLeft);
  private static final KeyStroke DESNO = new KeyStroke(KeyType.ArrowRight);

  private Terminal terminal;
  List<Blok> blokovi = new ArrayList<>();
  Reket r;
  Loptica l;

  ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(1);

  public Igra(Terminal terminal) {
    this.terminal = terminal;
    r = new Reket(terminal);
    l = new Loptica(terminal);
  }

  public void kreni() throws IOException {
    nacrtajSve();
    ses.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        try {
          osvjezi();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }, 1, 1, TimeUnit.SECONDS);
  }

  private void osvjezi() throws IOException {
    l.osvjezi();
  }

  public void primiKomandu(KeyStroke key) throws IOException {

    if(key.equals(LIJEVO)){
      r.pomjeriLijevo();
    }

    if(key.equals(DESNO)){
      r.pomjeriDesno();
    }

  }

  private void nacrtajSve() throws IOException {
    for(int i=0; i<(KOLONA * REDOVA_KOCKI); i++){
      Blok b = new Blok(terminal);
      blokovi.add(b);
      b.nacrtajBlokBr(i);
    }

    r.nacrtajReket();
    l.nacrtajLopticu();

    terminal.flush();
  }

}
