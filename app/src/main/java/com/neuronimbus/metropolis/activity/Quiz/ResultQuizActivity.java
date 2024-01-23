package com.neuronimbus.metropolis.activity.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.Utils.LocaleHelper;
import com.neuronimbus.metropolis.activity.Home.HomeActivity;
import com.neuronimbus.metropolis.AuthModels.QuizAnswerAuthModel;
import com.neuronimbus.metropolis.Fragment.Quiz.QuestionFragment;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.QuizAnswerResponse;
import com.neuronimbus.metropolis.Models.QuizData;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ResultQuizActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    RelativeLayout rel_back;
    Button btn_home,btn_next_quiz;
    TextView txt_percentage,txt_title;
    int anwer,incorrect_answer,total;
    double percentage;
    ArrayList<QuizData> quizdata;
    CustomProgressDialog dialog;
    SPManager spManager;
    String quiz_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_quiz);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context=ResultQuizActivity.this;
        dialog=new CustomProgressDialog(context);
        spManager=new SPManager(context);
        quizdata=new ArrayList<>();
        rel_back=(RelativeLayout)findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);

        btn_home=(Button)findViewById(R.id.btn_home);
        btn_home.setOnClickListener(this);
        btn_next_quiz=(Button)findViewById(R.id.btn_next_quiz);
        btn_next_quiz.setOnClickListener(this);

        txt_percentage=(TextView)findViewById(R.id.txt_percentage);
        txt_title=(TextView)findViewById(R.id.txt_title);


        anwer = QuestionFragment.answer;
        incorrect_answer= QuestionFragment.incorect_answer;
        QuestionFragment.answer=0;
        QuestionFragment.incorect_answer=0;
        total=anwer+incorrect_answer;

        percentage=anwer * 100 / total;


        txt_percentage.setText(Double.toString(percentage)+"%"+" "+ getString(R.string.score));
        //txt_title.setText(getString(R.string.you_ans)+" "+anwer+" "+getString(R.string.out_of)+" "+total+" "+getString(R.string.que_correct));


        String lang = spManager.getLanguage();

        if (lang.equals("en")){
            //txt_title.setText("You answered"+" "+anwer+" "+"out of"+" "+total+" "+"question correctly.To continue click on next quiz or click on home to go back");

        }
         if (lang.equals("hi")){
            //txt_title.setText("आपने उत्तर दिया"+" "+anwer+" "+"में से"+" "+total+" "+"सही प्रश्न करो. जारी रखने के लिए अगले क्विज़ पर क्लिक करें या वापस जाने के लिए होम पर क्लिक करें");

        }
         if (lang.equals("mr")){
            //txt_title.setText("तुम्ही उत्तर दिले"+" "+anwer+" "+"पैकी"+" "+total+" "+"योग्य प्रश्न. सुरू ठेवण्यासाठी पुढील क्विझवर क्लिक करा किंवा परत जाण्यासाठी होम वर क्लिक करा");

        }
         if (lang.equals("gu")){
            //txt_title.setText("તમે જવાબ આપ્યો"+" "+anwer+" "+"માંથી"+" "+total+" "+"યોગ્ય રીતે પ્રશ્ન કરો. ચાલુ રાખવા માટે આગલી ક્વિઝ પર ક્લિક કરો અથવા પાછા જવા માટે હોમ પર ક્લિક કરો");

        }
         if (lang.equals("ta")){
            //txt_title.setText("நீங்கள் பதிலளித்தீர்கள்"+" "+anwer+" "+"இல்"+" "+total+" "+"சரியாக கேள்வி. தொடர, அடுத்த வினாடி வினாவைக் கிளிக் செய்யவும் அல்லது திரும்பிச் செல்ல முகப்பு என்பதைக் கிளிக் செய்யவும்");

        }


        quizdata=QuestionFragment.quizdata;


        quiz_id=QuestionFragment.quiz_id;
        QuestionFragment.quiz_id="";
        sendReply();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    public void sendReply()
    {
        dialog.show("");
        //hi,mr,gu,ta

        QuizAnswerAuthModel model=new QuizAnswerAuthModel();
        model.setQuiz_id(quiz_id);
        model.setUser_id(spManager.getUserId());
        model.setQuizData(quizdata);

        WebServiceModel.getRestApi().getQuizAns(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<QuizAnswerResponse>() {
                    @Override
                    public void onNext(QuizAnswerResponse quizAnswerResponse) {
                        dialog.dismiss("");
                        String msg = quizAnswerResponse.getMsg();

                        if (msg.equals("success")) {


                            Toast.makeText(context,"Data saved successfully",Toast.LENGTH_SHORT).show();
                            QuestionFragment.quizdata.clear();

                        } else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();
                        QuestionFragment.quizdata.clear();
                        dialog.dismiss("");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        if(id==btn_home.getId())
        {
            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if(id==btn_next_quiz.getId())
        {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else if(id==rel_back.getId())
        {
            finish();
        }
    }
}