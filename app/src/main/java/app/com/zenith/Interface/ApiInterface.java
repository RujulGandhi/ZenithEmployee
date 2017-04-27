package app.com.zenith.Interface;

import java.util.HashMap;

import app.com.zenith.Model.EmployeeLeaveMainDetails;
import app.com.zenith.Model.LeaveMainDetails;
import app.com.zenith.Model.LoginMainStatus;
import app.com.zenith.Model.TraningMainDetails;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface
{
    // TODO Create ***** Rujul *********

    @FormUrlEncoded
    @POST("api/login_for_all.php")
    Call<LoginMainStatus> getLoginDetails(@FieldMap HashMap<String, String> params);


    @GET("api/get_traning_program.php")
    Call<TraningMainDetails> getTraningList();

    @FormUrlEncoded
    @POST("api/get_leave_application.php")
    Call<LeaveMainDetails> getLeaveApplicationList(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("api/get_leave_types.php")
    Call<EmployeeLeaveMainDetails> getEmployeeLeaveInfo(@FieldMap HashMap<String, String> params);
  }