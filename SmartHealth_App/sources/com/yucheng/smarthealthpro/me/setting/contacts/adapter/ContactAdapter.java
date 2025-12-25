package com.yucheng.smarthealthpro.me.setting.contacts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.me.setting.contacts.bean.MyContacts;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.ColorGenerator;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.TextDrawable;
import com.yucheng.ycbtsdk.YCBTClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ContactAdapter extends RecyclerView.Adapter<MyRecycleHolder> {
    private List<MyContacts> contactBeanList;
    private List<String> list;
    private Context mContext;
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private TextDrawable.IBuilder mDrawableBuilder = TextDrawable.builder().round();
    private int maxSize = 10;
    private int currSize = 0;

    public ContactAdapter(Context context, List<MyContacts> contactBeanList) {
        this.mContext = context;
        if (contactBeanList == null) {
            this.contactBeanList = new ArrayList();
        } else {
            this.contactBeanList = contactBeanList;
        }
        initMax();
    }

    private void initMax() {
        if (YCBTClient.getChipScheme() == 3) {
            this.maxSize = 10;
        } else {
            this.maxSize = 30;
        }
    }

    public void addAll(List<MyContacts> beans) {
        if (beans != null) {
            this.contactBeanList = beans;
        }
        this.currSize = 0;
        Iterator<MyContacts> it2 = this.contactBeanList.iterator();
        while (it2.hasNext()) {
            if (it2.next().isChecked) {
                this.currSize++;
            }
        }
        notifyDataSetChanged();
    }

    public void add(MyContacts bean, int position) {
        this.contactBeanList.add(position, bean);
        notifyItemInserted(position);
    }

    public void add(MyContacts bean) {
        this.contactBeanList.add(bean);
        notifyItemChanged(this.contactBeanList.size() - 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyRecycleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts_layout, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final MyRecycleHolder holder, final int position) {
        final MyContacts myContacts;
        List<MyContacts> list = this.contactBeanList;
        if (list == null || list.size() == 0 || this.contactBeanList.size() <= position || (myContacts = this.contactBeanList.get(position)) == null) {
            return;
        }
        holder.tv_name.setText(myContacts.getName());
        holder.iv_img.setImageDrawable(this.mDrawableBuilder.build(String.valueOf(myContacts.getName().charAt(0)), this.mColorGenerator.getColor(myContacts.getName())));
        holder.check_box.setChecked(myContacts.isChecked);
        holder.check_box.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.adapter.ContactAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (myContacts.isChecked) {
                    myContacts.isChecked = false;
                    ContactAdapter.this.currSize--;
                } else if (ContactAdapter.this.currSize >= ContactAdapter.this.maxSize) {
                    holder.check_box.setChecked(false);
                    ToastUtil.getInstance(ContactAdapter.this.mContext).toast(ContactAdapter.this.mContext.getString(R.string.contacts_limit_reached));
                } else {
                    myContacts.isChecked = true;
                    ContactAdapter.this.currSize++;
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MyContacts> list = this.contactBeanList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public static class MyRecycleHolder extends RecyclerView.ViewHolder {
        public final CheckBox check_box;
        public final ImageView iv_img;
        public final TextView tv_name;

        public MyRecycleHolder(View itemView) {
            super(itemView);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            this.check_box = (CheckBox) itemView.findViewById(R.id.check_box);
        }
    }
}
