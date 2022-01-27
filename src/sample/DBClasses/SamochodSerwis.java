package sample.DBClasses;

public class SamochodSerwis {

    private String nr_rej;
    private String marka;
    private String model;

    public String getNr_rej() {
        return nr_rej;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public SamochodSerwis(String nr_rej, String marka, String model) {
        this.nr_rej = nr_rej;
        this.marka = marka;
        this.model = model;
    }
}
