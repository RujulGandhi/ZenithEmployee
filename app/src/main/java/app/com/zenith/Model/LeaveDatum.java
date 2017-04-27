package app.com.zenith.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeaveDatum
{
    // TODO Create ***** Rujul *********
@SerializedName("leave_id")
@Expose
private String leaveId;
@SerializedName("leave_name")
@Expose
private String leaveName;
@SerializedName("applied_date")
@Expose
private String appliedDate;
@SerializedName("totaldays")
@Expose
private String totaldays;
@SerializedName("from_date")
@Expose
private String fromDate;
@SerializedName("to_date")
@Expose
private String toDate;
@SerializedName("leave_notes")
@Expose
private String leaveNotes;
@SerializedName("leave_attachment")
@Expose
private String leaveAttachment;
@SerializedName("leave_status")
@Expose
private String leaveStatus;

public String getLeaveId() {
return leaveId;
}

public void setLeaveId(String leaveId) {
this.leaveId = leaveId;
}

public String getLeaveName() {
return leaveName;
}

public void setLeaveName(String leaveName) {
this.leaveName = leaveName;
}

public String getAppliedDate() {
return appliedDate;
}

public void setAppliedDate(String appliedDate) {
this.appliedDate = appliedDate;
}

public String getTotaldays() {
return totaldays;
}

public void setTotaldays(String totaldays) {
this.totaldays = totaldays;
}

public String getFromDate() {
return fromDate;
}

public void setFromDate(String fromDate) {
this.fromDate = fromDate;
}

public String getToDate() {
return toDate;
}

public void setToDate(String toDate) {
this.toDate = toDate;
}

public String getLeaveNotes() {
return leaveNotes;
}

public void setLeaveNotes(String leaveNotes) {
this.leaveNotes = leaveNotes;
}

public String getLeaveAttachment() {
return leaveAttachment;
}

public void setLeaveAttachment(String leaveAttachment) {
this.leaveAttachment = leaveAttachment;
}

public String getLeaveStatus() {
return leaveStatus;
}

public void setLeaveStatus(String leaveStatus) {
this.leaveStatus = leaveStatus;
}

}