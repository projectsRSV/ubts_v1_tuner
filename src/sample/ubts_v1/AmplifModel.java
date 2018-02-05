package sample.ubts_v1;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AmplifModel {
    private StringProperty band = new SimpleStringProperty();
    private StringProperty addressI2C = new SimpleStringProperty();
    private StringProperty fanPin = new SimpleStringProperty();
    private StringProperty valid = new SimpleStringProperty();

    public String getBand() {
        return band.get();
    }

    public StringProperty bandProperty() {
        return band;
    }

    public void setBand(String band) {
        this.band.set(band);
    }

    public String getAddressI2C() {
        return addressI2C.get();
    }

    public StringProperty addressI2CProperty() {
        return addressI2C;
    }

    public void setAddressI2C(String addressI2C) {
        this.addressI2C.set(addressI2C);
    }

    public String getFanPin() {
        return fanPin.get();
    }

    public StringProperty fanPinProperty() {
        return fanPin;
    }

    public void setFanPin(String fanPin) {
        this.fanPin.set(fanPin);
    }

    public String getValid() {
        return valid.get();
    }

    public StringProperty validProperty() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid.set(valid);
    }

    @Override
    public String toString() {
        return "PaModel{" +
                "band=" + band +
                ", addressI2C=" + addressI2C +
                ", fanPin=" + fanPin +
                ", valid=" + valid +
                '}';
    }
}
