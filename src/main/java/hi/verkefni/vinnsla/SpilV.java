package hi.verkefni.vinnsla;

public class SpilV {
    private final Tegund tegund;
    private final Gildi gildi;


    public SpilV(Tegund t, Gildi g) {
        tegund = t;
        gildi = g;
    }

    public Gildi getGildi() {
        return gildi;
    }

    /**
     * Heiltölu gildi á bak við spilið
     * @return heiltölugildi spils
     */
    public int getVirdi() {
        return gildi.getGildi();
    }

    public Tegund getTegund() {
        return tegund;
    }

    public String toString() {
        return gildi+" "+tegund.name();
    }



}
