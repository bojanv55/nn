package me.vukas.igra;

public class PredSled {
    private Koordinate pred;
    private Koordinate sled;

    public PredSled(Koordinate pred, Koordinate sled) {
        this.pred = pred;
        this.sled = sled;
    }

    public Koordinate getPred() {
        return pred;
    }

    public Koordinate getSled() {
        return sled;
    }
}
