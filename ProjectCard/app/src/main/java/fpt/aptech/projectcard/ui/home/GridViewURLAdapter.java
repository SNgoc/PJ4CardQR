package fpt.aptech.projectcard.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import fpt.aptech.projectcard.MainActivity;
import fpt.aptech.projectcard.R;
import fpt.aptech.projectcard.domain.UrlProduct;
import fpt.aptech.projectcard.session.SessionManager;
import fpt.aptech.projectcard.ui.profile.ProfileFragment;
import fpt.aptech.projectcard.ui.social.SocialFragment;
import fpt.aptech.projectcard.ui.social.UpdateUrlFragment;

public class GridViewURLAdapter extends ArrayAdapter<UrlProduct> {
    FragmentManager fragmentManager;

    public GridViewURLAdapter(@NonNull Context context, ArrayList<UrlProduct> urlProductArrayList, FragmentManager fragmentManager) {
        super(context,0, urlProductArrayList);
        this.fragmentManager = fragmentManager;
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
                if (urlProduct.getLinkType().getId() != 6 && urlProduct.getLinkType().getId() != 7 ){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + urlProduct.getUrl()));
                }
                if (urlProduct.getLinkType().getId() == 6){
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + urlProduct.getUrl()));
                }
                if (urlProduct.getLinkType().getId() == 7){
                    intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + urlProduct.getUrl()));
                }
                getContext().startActivity(intent);
            }
        });
        img.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(),img);
                popup.inflate(R.menu.popup_menu_item);

                //set title item to url name
                Menu menuOpts = popup.getMenu();
                menuOpts.getItem(0).setTitle("⭐" + urlProduct.getName());

                // Register Menu Item Click event.
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return menuItemClicked(item);
                    }
                });

                // Show the PopupMenu.
                popup.show();

//                img.setTooltipText(urlProduct.getName());
                return false;
            }

            // When user click on Menu Item.
            // @return true if event was handled.
            private boolean menuItemClicked(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuItem_Name:
                        Toast.makeText(getContext(), urlProduct.getName(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuItem_edit:
                        Toast.makeText(getContext(), "Update Url layout", Toast.LENGTH_SHORT).show();
                        SessionManager.setSaveUrlProduct(urlProduct);
                        //change to update layout fragment
                        fragmentManager.beginTransaction().replace(R.id.fl_content_home, new UpdateUrlFragment())
                                .addToBackStack(null).commit();
                        break;
                    case R.id.menuItem_drop:
                        Toast.makeText(getContext(), R.string.popup_delete_url, Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }

        });
        return listItemView;
    }
}
