package fpt.aptech.projectcard.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import fpt.aptech.projectcard.MainActivity;
import fpt.aptech.projectcard.R;
import fpt.aptech.projectcard.domain.UrlProduct;

public class GridViewURLAdapter extends ArrayAdapter<UrlProduct> {
    public GridViewURLAdapter(@NonNull Context context, ArrayList<UrlProduct> urlProductArrayList) {
        super(context,0, urlProductArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }
        UrlProduct urlProduct = getItem(position);
        ImageView img = listItemView.findViewById(R.id.imgGridItem);
        try {
            //avatar
            Bitmap bitmap1 = BitmapFactory.decodeStream((InputStream)new URL(urlProduct.getLinkType().getLinkImage()).getContent());
            img.setImageBitmap(bitmap1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (urlProduct.getLinkType().getId() == 1 || urlProduct.getLinkType().getId() == 2 || urlProduct.getLinkType().getId() == 3
                        || urlProduct.getLinkType().getId() == 4 || urlProduct.getLinkType().getId() == 5 || urlProduct.getLinkType().getId() == 8){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + urlProduct.getUrl()));
                }
                if (urlProduct.getLinkType().getId() == 6){
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + urlProduct.getUrl()));
                }
                if (urlProduct.getLinkType().getId() == 7){
                    intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                }
                getContext().startActivity(intent);
            }
        });
        img.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onLongClick(View v) {
                img.setTooltipText(urlProduct.getName());
                return false;
            }
        });
        return listItemView;
    }
}
