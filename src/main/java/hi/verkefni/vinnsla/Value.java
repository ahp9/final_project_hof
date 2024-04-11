package hi.verkefni.vinnsla;

public enum Value {
    TVISTUR(2),
    THRISTUR(3),
    FJARKI(4),
    FIMMA(5),
    SEXA(6),
    SJOA(7),
    ATTA(8),
    NIA(9),
    TIA(10),
    GOSI(10),
    DROTTNING(10),
    KONGUR(10),
    AS(11);

    private final int VALUE;

    Value(int v) {
        this.VALUE = v;
    }

    int getVALUE() {
        return VALUE;
    }

}
