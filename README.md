Android Programing
----------------------------------------------------
### 2017.11.23 38일차

#### 예제
____________________________________________________

#### 공부정리
____________________________________________________

##### __BroadCasts__

- BroadCasts 란?

  > 4대 Component (Activity, Service, BroadcastReceiver, ContentProvider) 중 하나로 안드로이드 단말기에서 발생하는 다양한 이벤트/정보를 받고 반응하는 컴포넌트이다. 예를 들면 시스템부팅, 배터리 부족, 전화/문자 수신, 네트워크 끊김을 알려주는 것이 BroadCast 이다. 이렇게 BroadCast 된 이벤트는 각 APP에서 필요한 BroadCast 이벤트를 받아들이고, 이벤트에 대한 처리를 BroadCastReciver를 통해 할 수 있게 해준다.

- BroadCastReciver 사용법

  1. App 단위로 BroadcastReceiver 사용하기

      - Manifest.xml 만 설정하면 Android 에서 발생하는 BroadCast 를 수신받을 수 있다.

      - Manifest.xml 설정

      ```
      <!-- Permission 설정 -->
      <!-- Permission 권한을 설정해야 한다. -->
      <uses-permission android:name="android.permission.READ_SMS" />
      <uses-permission android:name="android.permission.RECEIVE_SMS" />

      <!-- Receiver 등록 -->
      <!-- receiver -->
      <!-- name : BroadcastReceiver 를 상속받은 Class 명 작성 -->
      <!-- intent-filter -->
      <!-- action name : 수신받을 행동에 대한 action name 을 작성 -->
      <receiver
          android:name=".MyReceiver"
          android:enabled="true"
          android:exported="true">

          <intent-filter>
              <action android:name="android.provider.Telephony.SMS_RECEIVED" />
          </intent-filter>
      </receiver>
      ```

  2. Component 단위로 BroadcastReceiver 사용하기

      - BroadcastReceiver 를 상속받은 class 를 사용하는 측에서만 Android 에서 발생하는 BroadCast 를 수신받을 수 있다.

      - Component 에서 설정 ( Code 는 Activity 로 작성 )

      ```java
      public class MainActivity extends BaseActivity {

          public MainActivity() {
              // Permission 설정
              super(new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS});
          }

          @Override
          public void init() {
              setContentView(R.layout.activity_main);
          }

          @Override
          protected void onNewIntent(Intent intent) {
              super.onNewIntent(intent);
          }

          // Component 단위로 Receiver 를 관리하려면
          // registerReceiver 로 Receiver 를 등록해주고,
          // unregisterReceiver 로 Receiver 를 해제한다.
          @Override
          protected void onStart() {
              super.onStart();
              registerReceiver(new MyReceiver(), new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
          }
      }
      ```

- BroadCastReciver 작성

  - BroadcastReceiver 를 상속받아 작성한다.

  - `onReceive()` 메소드를 재정의하여 사용한다.

  ```java
  public class MyReceiver extends BroadcastReceiver {

      private static final String TAG = "MyReceiver";

      @Override
      public void onReceive(Context context, Intent intent) {
          // 1. 등록된 Receiver 를 통해 BroadCast Message 가 Intent 에 담겨 들어온다.
          // Manifest.xml 에 설정한 action name 과 동일한지 검사한다.
          if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) {
              // 로직 작성
          }
      }
  }
  ```
