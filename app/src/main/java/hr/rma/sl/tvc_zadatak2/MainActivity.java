package hr.rma.sl.tvc_zadatak2;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static hr.rma.sl.tvc_zadatak2.UpdateLocale.changeLang;

import static hr.rma.sl.tvc_zadatak2.UpdateLocale.getLocale;
import static hr.rma.sl.tvc_zadatak2.UpdateLocale.isLocaleSet;
import static hr.rma.sl.tvc_zadatak2.UpdateLocale.loadLocale;

//import static com.example.tvc_zadatak1.UpdateLocale.changeLang;

public class MainActivity extends AppCompatActivity{
    Locale myLocale;
    String currentLanguage;
    private View customLayout;
    private String m_Text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadLocale(MainActivity.this);
        currentLanguage = getLocale(MainActivity.this);
        setContentView(R.layout.activity_main);
        // Toast.makeText(MainActivity.this, "Selected language: " +getLocale(MainActivity.this), Toast.LENGTH_SHORT).show();

        final Button btn = (Button) findViewById(R.id.language_button);
        Button messageBtn = (Button) findViewById(R.id.message_button);
        Button textBtn = (Button) findViewById(R.id.text_button);

        if (!isLocaleSet(getApplicationContext())) {
            btn.setText("-");
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, btn);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        Intent intent = intent = new Intent(getApplicationContext(), MainActivity.class);
                        switch (id) {
                            case R.id.croatian:
                                //if(!getLocale(getApplicationContext()).equals(getApplicationContext().getResources().getString(R.string.locale)))
                                if (!getLocale(getApplicationContext()).equals("cro")) {
                                    changeLang("cro", MainActivity.this);
                                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                                }
                                //setNewLocale(MainActivity.this, LocaleManager.CROATIAN);
                                break;
                            case R.id.english:
                                //if(!getLocale(getApplicationContext()).equals(getApplicationContext().getResources().getString(R.string.locale)))
                                if (!getLocale(getApplicationContext()).equals("en")) {
                                    changeLang("en", MainActivity.this);
                                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                                }
                                //setNewLocale(MainActivity.this, LocaleManager.ENGLISH);
                                break;
                            default:
                                break;
                        }
                        //Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();//showing popup menu
            }
        });

        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set up the input
                // set the custom layout
                customLayout = getLayoutInflater().inflate(R.layout.edit_text_dialog, null);
                final AlertDialog builder = new AlertDialog.Builder(MainActivity.this).setNegativeButton(R.string.negativeButton, null).setTitle(R.string.fizzBuzz).setView(customLayout).show();
                builder.setCanceledOnTouchOutside(false);
                final TextView input = customLayout.findViewById(R.id.editText);
                Button negativeButton = builder.getButton(AlertDialog.BUTTON_NEGATIVE);
                final MyTask1 myTask = new MyTask1();
                myTask.execute(100);
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myTask.cancel(true);
                        builder.dismiss();
                    }
                });
            }
        });

    }

    public  class MyTask1 extends AsyncTask<Integer, String, String> {
        @Override
        protected String doInBackground(Integer... params) {
            for(int i = 1; i<=100; i++)
            {
                if(isCancelled())
                {
                    break;
                }
                String fizzBuzz = "";
                if (i % 3 == 0) {
                    fizzBuzz += "Fizz";
                }
                if (i % 5 == 0) {
                    fizzBuzz += "Buzz";
                }
                if (fizzBuzz.isEmpty()) {
                    fizzBuzz += i;
                }
                fizzBuzz += "\n";
                try {

                    Thread.sleep(100);
                    publishProgress(fizzBuzz);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // input.append(fizzBuzz + "\n");
            }
            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {

        }
        @Override
        protected void onPreExecute() { ;
            TextView input = customLayout.findViewById(R.id.editText);
            input.setText("");
        }
        @Override
        protected void onProgressUpdate(String... values) {
            TextView input = customLayout.findViewById(R.id.editText);
            ScrollView scrollView = customLayout.findViewById(R.id.horizontalScrollView1);
            scrollView.fullScroll(View.FOCUS_DOWN);
            input.append(values[0]);
        }
    }


}
