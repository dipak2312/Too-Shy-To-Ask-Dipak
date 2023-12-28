package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.neuronimbus.metropolis.Models.QRCode.question;
import com.neuronimbus.metropolis.Models.faqcontent;
import com.neuronimbus.metropolis.R;

import java.util.ArrayList;

public class QRCodeAdapter extends RecyclerView.Adapter<QRCodeAdapter.ViewHolder> {
    Context context;
    ArrayList<com.neuronimbus.metropolis.Models.QRCode.question> question;

    public QRCodeAdapter(Context context, ArrayList<com.neuronimbus.metropolis.Models.QRCode.question> question) {
        this.context = context;
        this.question = question;
    }

    @NonNull
    @Override
    public QRCodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_list_item_layout_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QRCodeAdapter.ViewHolder holder, int position) {
        holder.qsnView.setText(Html.fromHtml(question.get(position).getTitle()));
        holder.ansView.setText(Html.fromHtml(question.get(position).getDescription()));


        boolean isExpandable = question.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable? View.VISIBLE : View.GONE);

        holder.expandableLayout.setVisibility(isExpandable? View.VISIBLE : View.GONE);

        Typeface tf_regular = Typeface.createFromAsset(context.getAssets(), "font/worksansmedium.ttf");
        if (question.get(position).isExpandable()){
            holder.qsnView.setTypeface(tf_regular,Typeface.NORMAL);
            holder.ansView.setTypeface(tf_regular);
            holder.showAnswer.setImageResource(R.drawable.down_arrow);

           // holder.cardViewMain.setBackground(context.getResources().getDrawable(R.drawable.rectangle_pink_border));
            //holder.qsnView.setTextColor(context.getResources().getColor(R.color.purple));
            holder.qsnView.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        }else {
            holder.qsnView.setTypeface(tf_regular,Typeface.NORMAL);
            //holder.ansView.setTypeface(tf_regular);
            holder.showAnswer.setImageResource(R.drawable.update_arrow);
           // holder.cardViewMain.setBackground(context.getResources().getDrawable(R.drawable.reactangle_with_corner_radius_8_white));

           // holder.qsnView.setTextColor(context.getResources().getColor(R.color.black));

            holder.qsnView.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        }
    }

    @Override
    public int getItemCount() {
        return question.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView qsnView, ansView;
        ImageView showAnswer;
        OnQuestionClickListener onQuestionClickListener;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;
        CardView cardView, cardViewMain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.onQuestionClickListener = onQuestionClickListener;
            qsnView = itemView.findViewById(R.id.question_tv);
            ansView = itemView.findViewById(R.id.answer_tv);
            showAnswer =  itemView.findViewById(R.id.show_answer);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            expandableLayout =itemView.findViewById(R.id.expandable);
            cardViewMain = itemView.findViewById(R.id.main_card_at_faq_item);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   question model = question.get(getAdapterPosition());
                    model.setExpandable(!model.isExpandable());



                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            onQuestionClickListener.OnAskedQuestionClick(getAdapterPosition());
        }
    }

    public interface OnQuestionClickListener {

        void OnAskedQuestionClick(int position);


    }
}
