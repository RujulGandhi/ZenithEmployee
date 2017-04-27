package app.com.zenith.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaveMainDetails
{
    // TODO Create ***** Rujul *********
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("total_leave")
    @Expose
    private Integer totalLeave;
    @SerializedName("leave_data")
    @Expose
    private List<LeaveDatum> leaveData = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getTotalLeave() {
        return totalLeave;
    }

    public void setTotalLeave(Integer totalLeave) {
        this.totalLeave = totalLeave;
    }

    public List<LeaveDatum> getLeaveData() {
        return leaveData;
    }

    public void setLeaveData(List<LeaveDatum> leaveData) {
        this.leaveData = leaveData;
    }

}