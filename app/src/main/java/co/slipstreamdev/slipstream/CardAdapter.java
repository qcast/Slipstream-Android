package co.slipstreamdev.slipstream;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jcarr on 9/6/2015.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.SubViewHolder> {

    public static final String TAG = "Slipstream";

    List<Subscription> channels;
    private Context mContext;

    CardAdapter(List<Subscription> channels, Context context) {
        mContext = context;
        this.channels = channels;
    }

    public class SubViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name;
        ImageView image;
        TextView latestBuildInfo;

        SubViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            name = (TextView) view.findViewById(R.id.card_name);
            image = (ImageView) view.findViewById(R.id.card_image);
            latestBuildInfo = (TextView) view.findViewById(R.id.card_build_info);
        }
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    @Override
    public SubViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new SubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubViewHolder subViewHolder, int i) {
        subViewHolder.name.setText(channels.get(i).name);
        subViewHolder.latestBuildInfo.setText(channels.get(i).latestBuildInfo);
        Log.d(TAG, "picasso rn");
        Picasso.with(subViewHolder.image.getContext()).load(channels.get(i).imageURL).into(subViewHolder.image);
    }
}
