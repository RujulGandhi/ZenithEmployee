package app.com.zenith.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TraningMainDetails
{
    // TODO Create ***** Rujul *********
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("traning_detail")
    @Expose
    private List<TraningDetail> traningDetail = null;

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

    public List<TraningDetail> getTraningDetail() {
        return traningDetail;
    }

    public void setTraningDetail(List<TraningDetail> traningDetail) {
        this.traningDetail = traningDetail;
    }
}