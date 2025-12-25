package com.yucheng.smarthealthpro.me.file;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yucheng.smarthealthpro.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class FileAdapter extends BaseAdapter {
    private Context context;
    private List<FileBean> datas;

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public FileAdapter(Context context, List<FileBean> datas) {
        this.datas = datas == null ? new ArrayList<>() : datas;
        this.context = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<FileBean> list = this.datas;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.datas.get(position);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_ui_url, parent, false);
            holder = new Holder();
            holder.iv_head = (ImageView) convertView.findViewById(R.id.item_ui_url_image);
            holder.tv_name = (TextView) convertView.findViewById(R.id.item_ui_url_name);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        FileBean fileBean = this.datas.get(position);
        if (fileBean.type == 1) {
            holder.iv_head.setImageResource(R.mipmap.bin_file);
        } else if (fileBean.type == 2) {
            holder.iv_head.setImageResource(R.mipmap.zip_file);
        } else {
            holder.iv_head.setImageResource(R.mipmap.folder);
        }
        holder.tv_name.setText(fileBean.name);
        return convertView;
    }

    private static class Holder {
        private ImageView iv_head;
        private TextView tv_name;

        private Holder() {
        }
    }

    public void setDataChanged(List<FileBean> datas) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        this.datas = datas;
        notifyDataSetChanged();
    }
}
