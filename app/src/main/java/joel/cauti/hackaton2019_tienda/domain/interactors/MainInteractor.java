package joel.cauti.hackaton2019_tienda.domain.interactors;

import joel.cauti.hackaton2019_tienda.data.dtos.BaseResponseDto;

public interface MainInteractor {

    void getTokenQR();

    interface Callbacks{
        void successTokenQR(BaseResponseDto tokenResponseDto);
        void errorTokenQR(int i, String string);
        void errorUnauthorized();
    }
}
