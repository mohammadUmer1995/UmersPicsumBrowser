package com.example.umerspicsumbrowser.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umerspicsumbrowser.ImageViewSingleActivity;
import com.example.umerspicsumbrowser.R;
import com.example.umerspicsumbrowser.datamodel.AutharPojo;
import com.example.umerspicsumbrowser.url.URLs;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolderAthor> {

    private ArrayList<AutharPojo> mArrayList;
    private Context mContext;


    public RecyclerAdapter(ArrayList<AutharPojo> arrayList, Context context) {
        this.mArrayList = arrayList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolderAthor onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, null);

        return new ViewHolderAthor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderAthor holder, int position) {

        AutharPojo autharPojo = mArrayList.get(position);

        holder.tv_name.setText(autharPojo.getAuthor());
        String name=autharPojo.getAuthor();
        String path = autharPojo.getId();
        final String both = URLs.IMAGE_URL+path;
        Log.d("image_author_name","name:"+name);

        Picasso.with(mContext).load(both).into( holder.img_author);
        holder.img_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("image_author_name","name:"+name);

                Intent intent = new Intent(mContext, ImageViewSingleActivity.class);
                intent.putExtra("imagekey", both);
                intent.putExtra("name",name);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class ViewHolderAthor extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private ImageView img_author;

        private ViewHolderAthor(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            img_author = itemView.findViewById(R.id.img_author);

        }
    }

}
