package com.example.kanadesk.samplewebsocketclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity {
    private Button wsConncetButton, wsSendMessageButton;
    private TextView wsStateTextView, wsRecvMessageTextView;

    private static String ServerIP = "192.168.0.5";
    private static String ServerPORT = "8000";


    private myWsClientListener ws;

    //WS Lister
    private class myWsClientListener extends WebSocketClient {
        public myWsClientListener(URI serverUri) {
            super(serverUri);
        }

        @Override
        //接続
        public void onOpen(ServerHandshake handshakedata) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    wsStateTextView.setText("Conneted!!");
                }
            });
        }

        @Override
        //Serverからのメッセージの受信
        public void onMessage(final String message) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    wsRecvMessageTextView.setText(message);
                }
            });
        }

        @Override
        //Serverの切断
        public void onClose(int code, String reason, boolean remote) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    wsStateTextView.setText("DisConneted..");
                }
            });
        }

        @Override
        //エラー
        public void onError(Exception ex) {
            Log.e("ERROR", ex.getMessage());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //サーバーの接続準備
        WebSocketImpl.DEBUG = true;
        try {
            ws = new myWsClientListener(new URI("ws://" + ServerIP + ":" + ServerPORT +"/"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        wsStateTextView = findViewById(R.id.StateTxtView);
        wsRecvMessageTextView = findViewById(R.id.RecvTxtView);


        wsConncetButton = findViewById(R.id.ConnectBtn);
        wsSendMessageButton = findViewById(R.id.SendMsgBtn);

        //Server接続Buttonイベント
        wsConncetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!ws.isOpen()) {
                    ws.connect();
                }

            }
        });

        //送信Buttonイベント
        wsSendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ws.isOpen()){
                    ws.send("hello");
                }
            }
        });
    }
}
