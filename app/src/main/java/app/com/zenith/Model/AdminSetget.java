package app.com.zenith.Model;

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

    // TODO Nested Looping...
    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_colorcode() {
        return event_colorcode;
    }

    public void setEvent_colorcode(String event_colorcode) {
        this.event_colorcode = event_colorcode;
    }

    private String event_id;
    private String event_colorcode;
    private String event_name;
    private String event_start_time;
    private String event_end_time;
    private String event_hourly_rate;


    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getE_img() {
        return e_img;
    }

    public void setE_img(String e_img) {
        this.e_img = e_img;
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

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_end_time() {
        return event_end_time;
    }

    public void setEvent_end_time(String event_end_time) {
        this.event_end_time = event_end_time;
    }

    public String getEvent_hourly_rate() {
        return event_hourly_rate;
    }

    public void setEvent_hourly_rate(String event_hourly_rate) {
        this.event_hourly_rate = event_hourly_rate;
    }

    public String getEvent_start_time() {
        return event_start_time;
    }

    public void setEvent_start_time(String event_start_time) {
        this.event_start_time = event_start_time;
    }
}