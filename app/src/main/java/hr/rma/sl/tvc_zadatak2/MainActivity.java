package hr.rma.sl.tvc_zadatak2;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import static hr.rma.sl.tvc_zadatak2.UpdateLocale.changeLang;
import static hr.rma.sl.tvc_zadatak2.UpdateLocale.getLocale;
import static hr.rma.sl.tvc_zadatak2.UpdateLocale.isLocaleSet;
import static hr.rma.sl.tvc_zadatak2.UpdateLocale.loadLocale;

public class MainActivity extends AppCompatActivity{
    private String currentLanguage;
    private View customLayout;
    private Button languageBtn, messageBtn, fizzBuzzBtn, positiveButton;
    private int languageIndex = 0;
    private String[] languagesArray;
    private FizzBuzz fizzBuzz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadLocale(MainActivity.this);
        currentLanguage = getLocale(MainActivity.this);
        setContentView(R.layout.activity_main);

        languageBtn = findViewById(R.id.language_button);
        messageBtn = findViewById(R.id.message_button);
        fizzBuzzBtn = findViewById(R.id.fizz_buzz_button);
        languagesArray = new String[]{getResources().getString(R.string.english), getResources().getString(R.string.croatian)};

        //if language isn't set show "-"
        if (!isLocaleSet(getApplicationContext())) {
            languageBtn.setText("-");
        }

        languageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get languageIndex of chosen language
                if(!getLocale(getApplicationContext()).equals("en"))
                {
                    languageIndex = 1;
                }

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getResources().getString(R.string.chooseLanguage)).setSingleChoiceItems(languagesArray, languageIndex, new DialogInterface.OnClickListener() {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which)
                        {
                            case 0:
                                //if user clicks on other language, change language and restart activity
                                //else dissmis dialog
                                if (!getLocale(getApplicationContext()).equals("en")) {
                                    changeLang("en", MainActivity.this);
                                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                                }else{
                                    dialog.dismiss();
                                }
                                break;
                            case 1:
                                if (!getLocale(getApplicationContext()).equals("cro")) {
                                    changeLang("cro", MainActivity.this);
                                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                                }else{
                                    dialog.dismiss();
                                }
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.create();
                builder.show();
            }
        });

        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if language isn't set show Toast
                //else create and show an alertDialog
                if (!isLocaleSet(getApplicationContext())) {
                    Toast.makeText(MainActivity.this, R.string.no_language, Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage(R.string.hello_world);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        fizzBuzzBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set the custom layout
                customLayout = getLayoutInflater().inflate(R.layout.fizz_buzz_dialog, null);
                final AlertDialog builder = new AlertDialog.Builder(MainActivity.this).setPositiveButton(R.string.positive_button, null).setNegativeButton(R.string.negative_button, null).setView(customLayout).show();
                builder.setCanceledOnTouchOutside(false);
                positiveButton = builder.getButton(AlertDialog.BUTTON_POSITIVE);
                Button negativeButton = builder.getButton(AlertDialog.BUTTON_NEGATIVE);

                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //create new process
                        fizzBuzz = new FizzBuzz(MainActivity.this);
                        //start process with sleep interval of 100ms
                        fizzBuzz.execute(100);
                    }
                });

                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //make process cancellable
                        fizzBuzz.cancel(true);
                        builder.dismiss();
                    }
                });
            }
        });

    }

    public class FizzBuzz extends AsyncTask<Integer, String, String> {

        private WeakReference<MainActivity> activityReference;

        FizzBuzz(MainActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected String doInBackground(Integer... params) {
            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }

            for(int i = 1; i<=100; i++)
            {
                //if process is cancelled break the loop
                if(isCancelled())
                {
                    break;
                }
                String fizzBuzzStr = "";
                if (i % 3 == 0) {
                    fizzBuzzStr += getResources().getString(R.string.fizz);
                }
                if (i % 5 == 0) {
                    fizzBuzzStr += getResources().getString(R.string.buzz);
                }
                if (fizzBuzzStr.isEmpty()) {
                    fizzBuzzStr += i;
                }
                fizzBuzzStr += "\n";
                try {
                    Thread.sleep(params[0]);
                    publishProgress(fizzBuzzStr);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // get refference to activity if it exists
            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            positiveButton.setEnabled(true);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // get refference to activity if it exists
            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            //clear text before start
            TextView input = customLayout.findViewById(R.id.editText);
            input.setMovementMethod(ScrollingMovementMethod.getInstance());
            input.setText("");
            positiveButton.setEnabled(false);
        }
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            // get reference to activity if it exists
            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            //update text and scroll to bottom
            TextView input = customLayout.findViewById(R.id.editText);
     //       ScrollView scrollView = customLayout.findViewById(R.id.horizontalScrollView1);
       //     scrollView.fullScroll(View.FOCUS_DOWN);
            input.append(values[0]);
        }
    }
}
