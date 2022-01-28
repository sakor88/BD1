package sample.DBClasses;

public class Naped {

    public static Integer id = 6;

    private Integer id_naped;
    private String kod_silnika;
    private Integer moc;
    private String skrzynia_biegow;
    private Integer pojemnosc;
    private Integer moment_obrotowy;

    public Integer getId_naped() {
        return id_naped;
    }

    public String getKod_silnika() {
        return kod_silnika;
    }

    public Integer getMoc() {
        return moc;
    }

    public String getSkrzynia_biegow() {
        return skrzynia_biegow;
    }

    public Integer getPojemnosc() {
        return pojemnosc;
    }

    public Integer getMoment_obrotowy() {
        return moment_obrotowy;
    }

    public Naped(Integer id_naped, String kod_silnika, Integer moc, String skrzynia_biegow, Integer pojemnosc, Integer moment_obrotowy) {
        this.id_naped = id_naped;
        this.kod_silnika = kod_silnika;
        this.moc = moc;
        this.skrzynia_biegow = skrzynia_biegow;
        this.pojemnosc = pojemnosc;
        this.moment_obrotowy = moment_obrotowy;
    }
}
