package hr.rma.sl.tvc_zadatak2;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public  class MyTask extends AsyncTask<Integer, String, String> {
   // private Context mContext;
    private  View rootView;

    MyTask(View rootView){
      //  this.mContext=context.getApplicationContext();
        this.rootView=rootView;
    }

    @Override
    protected String doInBackground(Integer... params) {
        for(int i = 1; i<=100; i++)
        {
            if(isCancelled())
            {
                break;
            }
            try {
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
                Thread.sleep(100);
                publishProgress(fizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // input.append(fizzBuzz + "\n");
        }

          /*  for (int i=1; i <= params[0]; i++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
        return "Task Completed.";
    }
    @Override
    protected void onPostExecute(String result) {
        //  progressBar.setVisibility(View.GONE);

        //btn.setText("Restart");
    }
    @Override
    protected void onPreExecute() { ;
        // txt.setText("Task Starting...");
        TextView input = rootView.findViewById(R.id.editText);
        input.setText("");
    }
    @Override
    protected void onProgressUpdate(String... values) {
        TextView input = rootView.findViewById(R.id.editText);
        ScrollView scrollView = rootView.findViewById(R.id.horizontalScrollView1);
        scrollView.fullScroll(View.FOCUS_DOWN);
        input.append(values[0]);
        //    txt.setText("Running..."+ values[0]);
        // progressBar.setProgress(values[0]);
    }
}
