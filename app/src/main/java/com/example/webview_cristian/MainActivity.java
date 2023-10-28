package com.example.webview_cristian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtenemos la referencia del Id definido en el XML
        webView= findViewById(R.id.webView);
        //Obtiene las configuraciones del WebView para modificar sus propiedades
        WebSettings webSettings = webView.getSettings();
        //Habilita el JavaScript
        webSettings.setJavaScriptEnabled(true);
        //Agrega una interfaz de JavaScript, permite comunicar el codigo de la aplicacion
        webView.addJavascriptInterface(new JavaScriptInterface(), "appInterface");
    }

    //El metodo loadURL se llama cuando el usuario hace click en el boton
    public void loadURL(View view){
        //obtiene las referencias del EditText definido en el XML
        EditText urlEditText = findViewById(R.id.urlEditText);
        //Obtiene el texto ingresado como una cadena
        String url = urlEditText.getText().toString();
        //Comprueba si la cadena esta vacia
        if (url.isEmpty()){
            //Muestra el toast indicado que se debe ingresar una URL valida
            Toast.makeText(this, "Ingresa una URL valida",Toast.LENGTH_SHORT).show();
        }else {//Si la cadena no esta vacia carga la URL ingresada
            webView.loadUrl(url);
        }
    }

    //define la clase interna que se utiliza para comunicar JavaScript y Java
    private class JavaScriptInterface{
        //clase interna que contiene un metodo llamado showToast
        @android.webkit.JavascriptInterface //Permite a JavaScript llamarlo desde una pagiba Web cargada
        public void showToast(String message){
        }
    }

}