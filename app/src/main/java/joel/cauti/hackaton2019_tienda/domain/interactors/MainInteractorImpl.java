package joel.cauti.hackaton2019_tienda.domain.interactors;

import android.content.Context;

import joel.cauti.hackaton2019_tienda.R;
import joel.cauti.hackaton2019_tienda.data.dtos.BaseResponseDto;
import joel.cauti.hackaton2019_tienda.data.services.TokenServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractorImpl implements MainInteractor {

    private Callbacks callbacks;
    private Context context;

    public MainInteractorImpl(Callbacks callbacks, Context context) {
        this.callbacks = callbacks;
        this.context = context;
    }

    @Override
    public void getTokenQR() {
        TokenServiceManager.getApiService().getTokenQR().enqueue(new Callback<BaseResponseDto>() {
            @Override
            public void onResponse(Call<BaseResponseDto> call, Response<BaseResponseDto> response) {
                switch (response.code()){
                    case 200:
                        if(response.body() != null){
                            BaseResponseDto baseResponseDto = response.body();
                            callbacks.successTokenQR(baseResponseDto);
                        }else{
                            callbacks.errorTokenQR(2,context.getString(R.string.requestMessageError2));
                        }
                        break;
                    case 401:
                        callbacks.errorUnauthorized();
                        break;
                    default:
                        callbacks.errorTokenQR(1,context.getString(R.string.requestMessageError1)+String.valueOf(response.code()));
                        break;
                }
            }

            @Override
            public void onFailure(Call<BaseResponseDto> call, Throwable t) {
                callbacks.errorTokenQR(0,context.getString(R.string.requestMessageError0)+t.getMessage());
            }
        });
    }
}
