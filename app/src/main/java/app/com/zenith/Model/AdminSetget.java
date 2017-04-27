package app.com.zenith.Model;

import java.util.ArrayList;

/**
 * Created by archi_info on 3/7/2017.
 */
public class AdminSetget {
    // TODO Model Class for Joblist Page
    private String e_id;
    private String e_name;
    private String e_shift;
    private String e_date;
    private String e_img;
    private ArrayList<EventDetails> eventArray;

    public ArrayList<EventDetails> getEventArray() {
        return eventArray;
    }

    public void setEventArray(ArrayList<EventDetails> eventArray) {
        this.eventArray = eventArray;
    }

    public String getE_img() {
        return e_img;
    }

    public void setE_img(String e_img) {
        this.e_img = e_img;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_shift() {
        return e_shift;
    }

    public void setE_shift(String e_shift) {
        this.e_shift = e_shift;
    }

    public String getE_date() {
        return e_date;
    }

    public void setE_date(String e_date) {
        this.e_date = e_date;
    }

    @Override
    public String toString() {
        return "AdminSetget{" +
                "e_id='" + e_id + '\'' +
                ", e_name='" + e_name + '\'' +
                ", e_shift='" + e_shift + '\'' +
                ", e_date='" + e_date + '\'' +
                ", e_img='" + e_img + '\'' +
                ", eventArray=" + eventArray +
                '}';
    }
}