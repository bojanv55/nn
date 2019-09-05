package me.vukas.igra;

import java.util.ArrayList;
import java.util.List;

public class SveTacke {

  List<Koordinate> koordinate = new ArrayList<>();

  public SveTacke(List<Koordinate> koordinate) {
    this.koordinate = koordinate;
  }

  public void dodajKord(Koordinate k){
    koordinate.add(k);
  }

  public boolean poklapaSeSa(Koordinate k){
    return koordinate.contains(k);
  }

}
