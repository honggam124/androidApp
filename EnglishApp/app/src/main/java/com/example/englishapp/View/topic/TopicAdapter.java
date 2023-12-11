package com.example.englishapp.View.topic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.Model.Topic;
import com.example.englishapp.R;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private List<Topic> topics;

    public TopicAdapter(List<Topic> topics) {
        this.topics = topics;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_library, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        Topic topic = topics.get(position);

        //holder.img.setImageResource(Integer.parseInt(topic.getImg().toString()));
        holder.tv_tittleTopic.setText(topic.getTitleTopic());
        holder.tv_status.setText(topic.getStatus());
        holder.tv_process.setText(topic.getProcess());
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public static class TopicViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv_tittleTopic;
        private TextView tv_status;
        private TextView tv_process;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imv_topic);
            tv_tittleTopic = itemView.findViewById(R.id.tv_tittleTopic);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_process = itemView.findViewById(R.id.tv_process);
        }
    }
}
