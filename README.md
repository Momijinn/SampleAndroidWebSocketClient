SampleAndroidWebSocketClient
===
AndroidでWebSocket通信をクライアントとして実行するサンプルプログラム

## Description
Java-WebSocketを利用したAndroidでWebSocket通信をクライアントとして実行するためのアプリケーション

## Demo
![screenshot_20180623-173447](https://user-images.githubusercontent.com/13119897/41809104-a025771c-7722-11e8-9c75-1744346f53ea.png)

## Requirement
* 動作確認環境
    * Android 8.0.0
    * Android 7.1.1

## Usage
MainActivity.javaにあるServerIPとServerPORTに接続したいServerのIPとPORTに入れる
```java
private static String ServerIP = "192.168.0.5";
private static String ServerPORT = "8000";
```

書き換え後、アプリをインストールしCONNECTボタンを押すと接続できる

接続後、SENDTEXTボタンを押せばServerへ"hello"という文字列を送信する

ws.send()の中身を書き換えれば"hello"という文字列を変えれば違う文字列を送信することができる
```java
ws.send("hello");
```

## Install
本プログラムはTooTallNate氏のJava-WebSocketにて作成している

[Java-WebSocket](https://github.com/TooTallNate/Java-WebSocket)

### ライブラリのインストール
build.gradleに以下を書き込み、Sync Nowをする
```
repositories {
    mavenCentral()
}

dependencies {
    compile "org.java-websocket:Java-WebSocket:1.3.8"
}
```

## Licence
This software is released under the MIT License, see LICENSE.

## Author
[Twitter](https://twitter.com/momijinn_aka)

[Blog](http://www.autumn-color.com/)