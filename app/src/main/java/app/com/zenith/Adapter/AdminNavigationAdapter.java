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
 * Created by archi_info on 3/3/2017.
 */

public class AdminNavigationAdapter extends RecyclerView.Adapter<AdminNavigationAdapter.NavigationHolder>
{
    public Context context;

    // TODO: 15-Feb-17 Rujul Constructor
    public AdminNavigationAdapter(Context context) {
        this.context = context;
    }

    @Override
    public AdminNavigationAdapter.NavigationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_navigationdrawer, parent,false);
        NavigationHolder viewHolder = new NavigationHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NavigationHolder holder, int position)
    {
        holder.nameTv.setText(context.getResources().getStringArray(R.array.adminnavigationitem_admintext)[position]);
        TypedArray imgs = context.getResources().obtainTypedArray(R.array.adminnavigationitems_adminimage);

        // get resource ID by index
        imgs.getResourceId(position, -1);

        // or set you ImageView's resource to the id
        holder.iconIV.setImageResource(imgs.getResourceId(position, -1));

        // recycle the array
        imgs.recycle();
    }




    @Override
    public int getItemCount() {
        return context.getResources().getStringArray(R.array.adminnavigationitem_admintext).length;
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
