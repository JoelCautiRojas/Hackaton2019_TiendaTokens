package joel.cauti.hackaton2019_tienda.presentation.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

import joel.cauti.hackaton2019_tienda.R;
import joel.cauti.hackaton2019_tienda.domain.presenters.MainPresenter;
import joel.cauti.hackaton2019_tienda.domain.presenters.MainPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainPresenter.View{

    private TextView textView;
    private ImageView imageView;
    private RelativeLayout progressBar;

    private Integer conteo = 5;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenterImpl(MainActivity.this);
        ui();
        initUi();
        events();
        initTimer();
    }

    private void ui() {
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.image);
        progressBar = findViewById(R.id.loading);
    }

    private void initUi() {

    }

    private void events() {

    }

    @Override
    public void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(){
        progressBar.setVisibility(View.GONE);
    }

    private void initTimer() {
        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                presenter.updateUI();
                if (conteo == 0){
                    presenter.getTokenQR();
                    conteo = 5;
                }
                conteo--;
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask1,0,1000);
    }

    @Override
    public void deployQR(String url) {
        Picasso.get().load(url).into(imageView);
    }
}
