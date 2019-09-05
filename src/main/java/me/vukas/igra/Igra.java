package me.vukas.igra;

import static me.vukas.Program.KOLONA;
import static me.vukas.Program.REDOVA;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
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

  boolean kensel = false;
  public boolean stop = false;

  public void ponovo() throws IOException {
    r = new Reket(terminal);
    l = new Loptica(terminal);
    kensel = false;
    terminal.clearScreen();
  }

  public void stop() {
    kensel = true;
    stop = true;
    ses.shutdown();
  }

  public static enum UdarU{
    BLOK_DOLE,
    BLOK_G,
    BLOK_L,
    BLOK_DESNO,
    REKET_L,
    REKET_C,
    REKET_D,
    ZID_L,
    ZID_D,
    ZID_G
  }

  public ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(1);
  ScheduledFuture<?> sf;

  public Igra(Terminal terminal) {
    this.terminal = terminal;
  }

  public void kreni() throws IOException, InterruptedException {
    nacrtajSve();
    sf = ses.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        try {
          osvjezi();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }, 100, 100, TimeUnit.MILLISECONDS);

    while(!kensel){
      Thread.sleep(1);
    }
  }

  private void osvjezi() throws IOException {

    if(kensel){
      return;
    }

    PredSled sledPoz = l.sledecaPozicija();

    if(sledPoz.getSled().getRed() == REDOVA + 1){
      //propali smo
      sf.cancel(true);
      kensel = true;
      return;
    }

    UdarU ud;

    if((ud = r.udaraU(sledPoz))!=null){
      //udara u reket
      l.osvjezi(ud);
      return;
    }

    Iterator i = blokovi.iterator();
    while (i.hasNext()){
      Blok b = (Blok) i.next();
      if((ud = b.udaraU(sledPoz)) != null){
        b.ukloni();
        l.osvjezi(ud);
        i.remove();
        return;
      }
    }

    if(sledPoz.getSled().getRed() == -1){
      l.osvjezi(UdarU.ZID_G);
      return;
    }

    if(sledPoz.getSled().getKolona() == -1){
      l.osvjezi(UdarU.ZID_L);
      return;
    }

    if(sledPoz.getSled().getKolona() == KOLONA){
      l.osvjezi(UdarU.ZID_D);
      return;
    }

    l.osvjezi(null);

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
