package joel.cauti.hackaton2019_tienda.data.services;

import joel.cauti.hackaton2019_tienda.data.dtos.BaseResponseDto;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TokenService {

    @GET("get_token")
    Call<BaseResponseDto> getTokenQR();
}
