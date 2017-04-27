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
 * Created by archirayan on 13-Feb-17.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationHolder>
{
    // TODO: 15-Feb-17 Rujul Constructor
    public Context context;
    public NavigationAdapter(Context context) {
        this.context = context;
    }

    @Override
    public NavigationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_adapter, parent, false);
        NavigationHolder viewHolder = new NavigationHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NavigationHolder holder, int position) {
        holder.nameTv.setText(context.getResources().getStringArray(R.array.navigationitems)[position]);
        TypedArray imgs = context.getResources().obtainTypedArray(R.array.navigationitems_image);
        // get resource ID by index
        imgs.getResourceId(position, -1);
        // or set you ImageView's resource to the id
        holder.iconIV.setImageResource(imgs.getResourceId(position, -1));
        // recycle the array
        imgs.recycle();
    }

    @Override
    public int getItemCount() {
        return context.getResources().getStringArray(R.array.navigationitems).length;
    }

    // TODO: 15-Feb-17 Rujul View Holder
    public class NavigationHolder extends RecyclerView.ViewHolder {
        public TextView nameTv;
        public ImageView iconIV;

        public NavigationHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.navigation_view_name);
            iconIV = (ImageView) itemView.findViewById(R.id.navigation_adapter_icon);
        }
    }
}
