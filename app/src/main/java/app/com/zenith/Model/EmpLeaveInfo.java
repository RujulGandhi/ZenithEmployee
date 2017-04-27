package app.com.zenith.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmpLeaveInfo
{
    // TODO Create ***** Rujul *********
    @SerializedName("leave_type_id")
    @Expose
    private String leaveTypeId;
    @SerializedName("leave_name")
    @Expose
    private String leaveName;
    @SerializedName("leave_days")
    @Expose
    private String leaveDays;
    @SerializedName("used_leave")
    @Expose
    private Integer usedLeave;

    public String getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(String leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public String getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(String leaveDays) {
        this.leaveDays = leaveDays;
    }

    public Integer getUsedLeave() {
        return usedLeave;
    }

    public void setUsedLeave(Integer usedLeave) {
        this.usedLeave = usedLeave;
    }

}