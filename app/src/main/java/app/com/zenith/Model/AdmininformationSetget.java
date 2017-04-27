package app.com.zenith.Model;

/**
 * Created by archi_info on 3/9/2017.
 */

public class AdmininformationSetget
{
    // TODO Model Class For Admin Information Page
    private String jobinfo_eventname;
    private String jobinfo_hourerate;
    private String jobinfo_starttime;
    private String jobinfo_endtime;

    public String getJobinfo_starttime() {
        return jobinfo_starttime;
    }

    public void setJobinfo_starttime(String jobinfo_starttime) {
        this.jobinfo_starttime = jobinfo_starttime;
    }

    public String getJobinfo_endtime() {
        return jobinfo_endtime;
    }

    public void setJobinfo_endtime(String jobinfo_endtime) {
        this.jobinfo_endtime = jobinfo_endtime;
    }

    public String getJobinfo_eventname() {
        return jobinfo_eventname;
    }

    public void setJobinfo_eventname(String jobinfo_eventname) {
        this.jobinfo_eventname = jobinfo_eventname;
    }

    public String getJobinfo_hourerate() {
        return jobinfo_hourerate;
    }

    public void setJobinfo_hourerate(String jobinfo_hourerate) {
        this.jobinfo_hourerate = jobinfo_hourerate;
    }
}
