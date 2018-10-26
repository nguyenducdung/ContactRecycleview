package rikkeisoft.nguyenducdung.com.contactrecycleview.custom.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import rikkeisoft.nguyenducdung.com.contactrecycleview.R;
import rikkeisoft.nguyenducdung.com.contactrecycleview.model.ItemContact;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> implements RecyclerView.OnItemTouchListener {
    private ArrayList<ItemContact> itemContacts;
    private Context context;
    private ItemClick itemClick;

    public ContactAdapter(ArrayList<ItemContact> itemContacts, Context context, ItemClick itemClick) {
        this.itemContacts = itemContacts;
        this.context = context;
        this.itemClick = itemClick;
    }

    public ContactAdapter(ArrayList<ItemContact> itemContacts, Context context) {
        this.itemContacts = itemContacts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tvName.setText(itemContacts.get(position).getNameContact());
        holder.tvPhone.setText(itemContacts.get(position).getPhoneContact());

//        holder.itemView.OnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    holder.cbItem.setVisibility(View.GONE);
//                }
//                return false;
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onClickItem(position);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemContacts.size();
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvPhone;
        private CheckBox cbItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            cbItem = itemView.findViewById(R.id.cb_item);
        }
    }

    public interface ItemClick {
        void onClickItem(int i);
    }

    public void dataChange(int i) {
        notifyItemChanged(i);
    }

    public void dataRemove(int i) {
        notifyItemRemoved(i);
    }

    public void dataAdd(int i) {
        notifyItemInserted(i);
    }

}
