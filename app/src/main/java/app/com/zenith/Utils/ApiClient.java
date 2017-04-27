package app.com.zenith.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by archirayan on 13-Feb-17.
 */

public class ApiClient {
    // TODO Api Client Create ***** Rujul ******

    private static final String BASE_URL = "http://hire-people.com/host2/zenith_coach/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}