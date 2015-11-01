package blackeyes.com.br;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecognitionListener, TextToSpeech.OnInitListener{

    ImageButton fala;
    boolean falaCon = true;
    private TextView returnedText,tuto, title;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private ProgressBar progressTuto;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    private TextToSpeech tts;
    private String tela = "";
    Handler h =new Handler();
    private boolean verifica = true;
    String text = "", control = "segunda", volt = "pular";
    Button avancar, regresso;

    public static int ENABLE_BLUETOOTH = 1;
    public static int SELECT_PAIRED_DEVICE = 2;
    public static int SELECT_DISCOVERED_DEVICE = 3;

    static TextView statusMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Subir();
        inicio();
        tts = new TextToSpeech(this, this);
        qualTela();
        IsSpeak();
        fala = (ImageButton)findViewById(R.id.imageButton);
        falar();


        Avancar();
        Regresso();
        comandos();
    }

    public void Avancar(){

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (control){


                    case "segunda":
                        segundaTela();
                        break;
                    case "terceira":
                        terceiraTela();
                        break;
                    case "quarta":
                        quartaTela();
                        break;
                    case "quinta":
                        quintaTela();
                        break;
                    case "sexta":
                        sextaTela();
                        break;
                }

            }
        });
    }

    public void Regresso(){
        regresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (volt) {

                    case "apresentacao":
                        apresentacao();
                        break;
                    case "segunda":
                        segundaTela();
                        break;
                    case "terceira":
                        terceiraTela();
                        break;
                    case "quarta":
                        quartaTela();
                        break;
                    case "quinta":
                        quintaTela();
                        break;
                    case "sexta":
                        sextaTela();
                        break;

                    case "pular":
                        Intent naveIntent = new Intent(MainActivity.this, Nave.class);
                        startActivity(naveIntent);
                        break;
                }
            }
        });
    }

    public void apresentacao(){

        tuto.setText(getString(R.string.apresentacao));
        title.setText("tela 1");
        tela = "tela 1";
        regresso.setText("Pular");
        control = "segunda";
        volt = "pular";
        progressTuto.setProgress(1);
        tts.speak(getString(R.string.apresentacao), tts.QUEUE_ADD, null);
    }

    public void segundaTela(){

        tuto.setText(getString(R.string.segundaTela));
        title.setText("tela 2");
        tela = "tela 2";
        regresso.setText("Voltar");
        control = "terceira";
        volt = "apresentacao";
        progressTuto.setProgress(2);
        tts.speak(getString(R.string.segundaTela), tts.QUEUE_ADD, null);
    }

    public void terceiraTela(){
        tuto.setText(getString(R.string.terceiraTela));
        title.setText("tela 3");
        tela = "tela 3";
        control = "quarta";
        volt = "segunda";
        progressTuto.setProgress(3);
        tts.speak(getString(R.string.terceiraTela), tts.QUEUE_ADD, null);
    }

    public void quartaTela(){
        tuto.setText(getString(R.string.quartaTela));
        title.setText("tela 4");
        tela = "tela 4";
        control = "quinta";
        volt = "terceira";
        progressTuto.setProgress(4);
        tts.speak(getString(R.string.quartaTela), tts.QUEUE_ADD, null);
    }

    public void quintaTela(){
        tuto.setText(getString(R.string.quintaTela));
        title.setText("tela 5");
        tela = "tela 5";
        control = "sexta";
        volt = "quarta";
        progressTuto.setProgress(5);
        tts.speak(getString(R.string.quintaTela), tts.QUEUE_ADD, null);
    }
    public void sextaTela(){
        tuto.setText(getString(R.string.sextaTela));
        title.setText("tela 6");
        tela = "tela 6";
        volt = "quinta";
        progressTuto.setProgress(6);
        tts.speak(getString(R.string.sextaTela), tts.QUEUE_ADD, null);
    }

    public void qualTela(){

        switch (tela){

            case "tela 1":
                tts.speak(getString(R.string.apresentacao), tts.QUEUE_ADD, null);
                break;
            case "tela 2":
                tts.speak(getString(R.string.segundaTela), tts.QUEUE_ADD, null);
                break;
            case "tela 3":
                tts.speak(getString(R.string.terceiraTela), tts.QUEUE_ADD, null);
                break;
            case "tela 4":
                tts.speak(getString(R.string.quartaTela), tts.QUEUE_ADD, null);
                break;
            case "tela 5":
                tts.speak(getString(R.string.quintaTela), tts.QUEUE_ADD, null);
                break;
            case "tela 6":
                tts.speak(getString(R.string.sextaTela), tts.QUEUE_ADD, null);

        }
    }

    public void falar(){
        fala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (falaCon) {
                    fala.setImageResource(R.drawable.play);
                    tts.stop();
                    falaCon = false;
                } else {
                    fala.setImageResource(R.drawable.stop);
                    falaCon = true;
                    verifica = true;
                    qualTela();

                }

            }
        });
    }

    public void inicio(){

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    toggleButton.setBackgroundResource(R.drawable.mic);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                    speech.startListening(recognizerIntent);
                    comandos();
                } else {
                    toggleButton.setBackgroundResource(R.drawable.micoff);
                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.INVISIBLE);
                    speech.stopListening();
                    comandos();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (speech != null) {
            speech.destroy();
            Log.i(LOG_TAG, "destroy");
        }

    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
        progressBar.setIndeterminate(false);
        progressBar.setMax(10);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech");
        progressBar.setIndeterminate(true);
        toggleButton.setChecked(false);
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        returnedText.setText(errorMessage);
        toggleButton.setChecked(false);
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        Log.i(LOG_TAG, "onEvent");

    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.i(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(LOG_TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        text = matches.get(0);
        returnedText.setText(text);
        comandos();
        text = "";
    }
    public void discoverDevices(View view){

        Intent searchPairedDevicesIntent = new Intent(this, DiscoveredDevices.class);
        startActivityForResult(searchPairedDevicesIntent, SELECT_PAIRED_DEVICE);
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS){
            tts.speak(getString(R.string.apresentacao), tts.QUEUE_ADD, null);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        tts.stop();
        speech.stopListening();

    }

    public void comandos(){

        if(text.equals("dispositivos")){

            //discoverDevices();

        }

        switch (text){

            case "próximo":
                switch (control){

                    case "apresentacao":
                        apresentacao();
                        break;
                    case "segunda":
                        segundaTela();
                        break;
                    case "terceira":
                        terceiraTela();
                        break;
                    case "quarta":
                        quartaTela();
                        break;
                    case "quinta":
                        quintaTela();
                        break;
                    case "sexta":
                        sextaTela();
                        break;
                }
                break;

            case "voltar":
                switch (volt){

                    case "apresentacao":
                        apresentacao();
                        break;
                    case "segunda":
                        segundaTela();
                        break;
                    case "terceira":
                        terceiraTela();
                        break;
                    case "quarta":
                        quartaTela();
                        break;
                    case "quinta":
                        quintaTela();
                        break;
                    case "sexta":
                        sextaTela();
                        break;
                }
                break;

            case "pular":

                Intent naveIntent = new Intent(MainActivity.this, Nave.class);
                startActivity(naveIntent);
                finish();
                break;




        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode == ENABLE_BLUETOOTH) {
            if (requestCode == RESULT_OK) {
                statusMessage.setText("Bluetooth ativado :D");
            } else {
                statusMessage.setText("Bluetooth não ativado :( ");
            }
        }
        else if (requestCode == SELECT_PAIRED_DEVICE || requestCode == SELECT_DISCOVERED_DEVICE){
            if(resultCode == RESULT_OK){
                statusMessage.setText("Você selecionou " + data.getStringExtra("btnDevName") + "\n"
                        + data.getStringExtra("btDevAddress"));
            }
            else {
                statusMessage.setText("Nenhum dispositivo selecionado :(");
            }
        }
    }

    public void Subir(){

        returnedText = (TextView) findViewById(R.id.txtReturn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        avancar = (Button)findViewById(R.id.btnAv);
        tuto = (TextView)findViewById(R.id.tuto);
        regresso = (Button)findViewById(R.id.btnVo);
        title = (TextView)findViewById(R.id.Titulo);
        progressTuto = (ProgressBar)findViewById(R.id.tuoProgress);

        progressBar.setVisibility(View.INVISIBLE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,"en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        statusMessage = (TextView)findViewById(R.id.statusMessage);

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter().getDefaultAdapter();
        if(btAdapter == null){
            statusMessage.setText("Que pena! Hardware Bluetooth não está funcionando :( ");
        }else{
            statusMessage.setText("Ótimo! Hardware Bluetooth está funcionando :) ");
        }

        if(!btAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, ENABLE_BLUETOOTH);
            statusMessage.setText("Solicitando ativação do Bluetooth... ");
        }else {
            statusMessage.setText("Bluetooth já ativado :) ");
        }

    }

    public void IsSpeak(){
        final Runnable r = new Runnable()
        {
            public void run()
            {
                if(tts.isSpeaking()){
                    falar();
                }
                if(!tts.isSpeaking()){
                    falar();
                }
                h.postDelayed(this, 2000);
            }

        };

        h.postDelayed(r, 1000); }
}
