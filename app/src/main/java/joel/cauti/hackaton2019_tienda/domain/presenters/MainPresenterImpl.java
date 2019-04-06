package joel.cauti.hackaton2019_tienda.domain.presenters;

import android.content.Context;

import joel.cauti.hackaton2019_tienda.data.dtos.BaseResponseDto;
import joel.cauti.hackaton2019_tienda.domain.interactors.MainInteractor;
import joel.cauti.hackaton2019_tienda.domain.interactors.MainInteractorImpl;

public class MainPresenterImpl implements MainPresenter, MainInteractor.Callbacks{

    private View view;
    private Context context;
    private MainInteractor interactor;

    public MainPresenterImpl(View view) {
        this.view = view;
        this.context = (Context) view;
        this.interactor = new MainInteractorImpl(this,context);
    }

    @Override
    public void getTokenQR() {
        //view.showLoading();
        interactor.getTokenQR();
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void successTokenQR(BaseResponseDto baseResponseDto) {
        //view.hideLoading();
        view.deployQR(baseResponseDto.getData().getUrl());
    }

    @Override
    public void errorTokenQR(int i, String string) {

    }

    @Override
    public void errorUnauthorized() {

    }
}
