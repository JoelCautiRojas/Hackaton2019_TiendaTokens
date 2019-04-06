package joel.cauti.hackaton2019_tienda.domain.presenters;

public interface MainPresenter {

    void getTokenQR();
    void updateUI();

    interface View{

        void showLoading();
        void hideLoading();
        void deployQR(String url);
    }
}
