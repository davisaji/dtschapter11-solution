package id.ac.polinema.dtsfit.services;

import java.util.List;

import id.ac.polinema.dtsfit.models.Calory;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CaloryService {

    @GET("/dhanifudin/fake-fit/calories")
    Call<List<Calory>> getCalories();

    @POST("/dhanifudin/fake-fit/calories")
    Call<Calory> addCalory(@Body Calory calory);

    @PUT("/dhanifudin/fake-fit/calories/{id}")
    Call<Calory> editCalory(@Path("id") int id, @Body Calory calory);
}
