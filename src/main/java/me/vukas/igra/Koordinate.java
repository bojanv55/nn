package me.vukas.igra;

import java.util.Objects;

public class Koordinate {

  private int red;
  private int kolona;

  public Koordinate(int red, int kolona) {
    this.red = red;
    this.kolona = kolona;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Koordinate that = (Koordinate) o;
    return red == that.red &&
        kolona == that.kolona;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, kolona);
  }
}
