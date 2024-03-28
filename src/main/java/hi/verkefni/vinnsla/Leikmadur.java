package hi.verkefni.vinnsla;

public class Leikmadur  implements LeikmadurInterface{
    private int samatalsAHendi;
    private int spilAHendi;

    public Leikmadur(){
        this.samatalsAHendi = 0;
        this.spilAHendi = 0;
    }

    @Override
    public int getSamtals() {
        System.out.println(samatalsAHendi);
        return samatalsAHendi;
    }

    @Override
    public String getNafn() {
        return null;
    }

    @Override
    public void setNafn(String nafn) {

    }

    @Override
    public void gefaSpil(SpilV s) {
        if (spilAHendi < 6){
            samatalsAHendi += s.getVirdi();
            spilAHendi += 1;
            System.out.println(s);
        }
    }

    @Override
    public boolean vinnurDealer(Leikmadur d) {
        if ( this.samatalsAHendi == 21 || d.samatalsAHendi > 21){
            return false;
        } else if (d.samatalsAHendi == 21 || this.samatalsAHendi > 21){
            return true;
        } else if ( d.samatalsAHendi > this.samatalsAHendi){
            return true;
        } else if (d.samatalsAHendi < this.samatalsAHendi){
            return false;
        }
        return false;
    }

    @Override
    public Leikmadur hvorVann(Leikmadur d) {
        if (this.samatalsAHendi == 21 || d.samatalsAHendi > 21){
            return this;
        } else if ( d.samatalsAHendi == 21 || this.samatalsAHendi > 21){
            return d;
        }
        return null;
    }

    @Override
    public void nyrLeikur() {
        this.samatalsAHendi = 0;
        this.spilAHendi = 0;
    }
}
