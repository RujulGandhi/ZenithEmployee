package app.com.zenith.Model;

/**
 * Created by archi_info on 3/14/2017.
 */
public class AddleaveDetails
{
    // TODO Model Class for Add Leave page
    private String type_Id;
    private String from_Date;
    private String to_Date;
    private String leave_Notes;
    private int leave_Attasment;

    public String getType_Id()
    {
        return type_Id;
    }

    public void setType_Id(String type_Id) {
        this.type_Id = type_Id;
    }

    public String getFrom_Date() {
        return from_Date;
    }

    public void setFrom_Date(String from_Date) {
        this.from_Date = from_Date;
    }

    public String getTo_Date() {
        return to_Date;
    }

    public void setTo_Date(String to_Date) {
        this.to_Date = to_Date;
    }

    public String getLeave_Notes() {
        return leave_Notes;
    }

    public void setLeave_Notes(String leave_Notes) {
        this.leave_Notes = leave_Notes;
    }

    public int getLeave_Attasment() {
        return leave_Attasment;
    }

    public void setLeave_Attasment(int leave_Attasment) {
        this.leave_Attasment = leave_Attasment;
    }
}
