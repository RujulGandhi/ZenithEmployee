package app.com.zenith.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import app.com.zenith.Model.TraningDetail;
import app.com.zenith.R;
import de.hdodenhof.circleimageview.CircleImageView;
/**
 * Created by ravi-archi on 2/15/2017.
 */
public class TrainingProgramAdapter extends RecyclerView.Adapter<TrainingProgramAdapter.TrainProgramHolder>
{
    // TODO  Adapter Class For Admin Training Program Page
    public Context context;
    public List<TraningDetail> trainingInfoList;

    public TrainingProgramAdapter(Context context, List<TraningDetail> trainingInfoList)
    {
        this.context = context;
        this.trainingInfoList = trainingInfoList;
        Log.d("Size", " -- " + trainingInfoList.size());
    }

    @Override
    public TrainProgramHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_training_program, parent, false);
        TrainProgramHolder viewHolder = new TrainProgramHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrainProgramHolder holder, int position)
    {
        holder.trainingTitle.setText(trainingInfoList.get(position).getTitle());
        holder.trainingSubTitle.setText(trainingInfoList.get(position).getSubtitle());
        holder.trainingSubject.setText(trainingInfoList.get(position).getSubject());
        if (trainingInfoList.get(position).getImage().length() > 0)
            Glide.with(context).load(trainingInfoList.get(position).getImage()).placeholder(R.drawable.ic_placeholder).into(holder.trainingImage);
    }

    @Override
    public int getItemCount() {
        return trainingInfoList.size();
    }

    // TODO: 15-Feb-17 nidhi View Holder
    public class TrainProgramHolder extends RecyclerView.ViewHolder
    {
        private TextView trainingTitle;
        private TextView trainingSubTitle;
        private TextView trainingSubject;
        private CircleImageView trainingImage;
        public TrainProgramHolder(View itemView)
        {
            super(itemView);
            trainingTitle = (TextView) itemView.findViewById(R.id.adapter_training_program_txt_traing_title);
            trainingSubTitle = (TextView) itemView.findViewById(R.id.adapter_training_program_txt_traing_subtitle);
            trainingSubject = (TextView) itemView.findViewById(R.id.adapter_training_program_txt_traing_subject);
            trainingImage = (CircleImageView) itemView.findViewById(R.id.adapter_training_program_image);
        }
    }
}
