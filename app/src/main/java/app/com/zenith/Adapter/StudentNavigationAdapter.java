package app.com.zenith.Adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.com.zenith.R;

/**
 * Created by archi_info on 3/17/2017.
 */

public class StudentNavigationAdapter extends RecyclerView.Adapter<StudentNavigationAdapter.NavigationHolder>
{
    // TODO Adapter Class for Student Navigation page

    public Context context;
    // TODO: 15-Feb-17 Rujul Constructor
    public StudentNavigationAdapter(Context context) {
        this.context = context;
    }

    @Override
    public StudentNavigationAdapter.NavigationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_navigationdrawer, parent,false);
        StudentNavigationAdapter.NavigationHolder viewHolder = new StudentNavigationAdapter.NavigationHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentNavigationAdapter.NavigationHolder holder, int position)
    {
        holder.nameTv.setText(context.getResources().getStringArray(R.array.studentnavigationitem_admintext)[position]);
        TypedArray imgs = context.getResources().obtainTypedArray(R.array.studentnavigationitems_adminimage);

        // get resource ID by index
        imgs.getResourceId(position, -1);

        // or set you ImageView's resource to the id
        holder.iconIV.setImageResource(imgs.getResourceId(position, -1));

        // recycle the array
        imgs.recycle();
    }




    @Override
    public int getItemCount() {
        return context.getResources().getStringArray(R.array.studentnavigationitem_admintext).length;
    }

    // TODO: 15-Feb-17 Rujul View Holder
    public class NavigationHolder extends RecyclerView.ViewHolder {
        public TextView nameTv;
        public ImageView iconIV;

        public NavigationHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.navigation_view_name1);
            iconIV = (ImageView) itemView.findViewById(R.id.navigation_adapter_icon1);
        }
    }
}
