package com.yucheng.smarthealthpro.me.setting.dial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.me.setting.dial.bean.DialResultBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class DialAdapter extends BaseAdapter {
    private Context context;
    private List<DialResultBean.Data> datas;
    private RequestOptions requestOptions;
    private SetDialListener setDialListener;

    public interface SetDialListener {
        void callback(int position);

        void delete(int position);

        void update(int position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public DialAdapter(Context context, List<DialResultBean.Data> datas) {
        this.datas = datas == null ? new ArrayList<>() : datas;
        this.context = context;
        this.requestOptions = new RequestOptions().error(R.mipmap.dial_default_icon).placeholder(R.mipmap.dial_default_icon);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<DialResultBean.Data> list = this.datas;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_dial, parent, false);
            holder = new Holder();
            holder.iv_head = (ImageView) convertView.findViewById(R.id.dial_item_iv_head);
            holder.iv_done = (ImageView) convertView.findViewById(R.id.dial_item_iv_down_done);
            holder.iv_delete = (ImageView) convertView.findViewById(R.id.dial_item_iv_delete);
            holder.tv_name = (TextView) convertView.findViewById(R.id.dial_item_tv_name);
            holder.tv_progress = (TextView) convertView.findViewById(R.id.dial_item_tv_progress_down);
            holder.tv_update = (TextView) convertView.findViewById(R.id.dial_item_tv_update);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.dial_item_progress_down);
            holder.tv_state = (TextView) convertView.findViewById(R.id.dial_item_tv_state);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        List<DialResultBean.Data> list = this.datas;
        if (list == null || position >= list.size()) {
            return convertView;
        }
        DialResultBean.Data data = this.datas.get(position);
        if (data.fileName != null) {
            Glide.with(this.context).applyDefaultRequestOptions(this.requestOptions).load(data.imgName).into(holder.iv_head);
        }
        holder.tv_name.setText(data.name);
        if (data.progress > 0 && data.progress < 100) {
            holder.progressBar.setVisibility(0);
            holder.tv_progress.setVisibility(0);
            holder.tv_progress.setText(data.progress + "%");
            holder.progressBar.setProgress(data.progress);
        } else {
            holder.progressBar.setVisibility(8);
            holder.tv_progress.setVisibility(8);
            holder.progressBar.setProgress(0);
        }
        holder.progressBar.setVisibility(0);
        holder.tv_progress.setVisibility(8);
        holder.iv_done.setVisibility(8);
        if (data.state == 1) {
            holder.tv_state.setText(this.context.getString(R.string.dial_state_install));
            data.enable = true;
        } else if (data.state == 2) {
            holder.tv_state.setText(this.context.getString(R.string.upgrade_downing));
            data.enable = false;
        } else if (data.state == 3) {
            holder.tv_state.setText(this.context.getString(R.string.dial_state_set_curr_dial));
            data.enable = true;
        } else if (data.state == 4) {
            holder.iv_done.setVisibility(0);
            holder.tv_state.setText(this.context.getString(R.string.dial_state_curr_dial));
            data.enable = false;
        } else if (data.state == 5) {
            holder.tv_state.setText(this.context.getString(R.string.dial_state_installing));
            data.enable = true;
        } else if (data.state == 6) {
            holder.tv_state.setText(this.context.getString(R.string.dial_state_continue_install));
            data.enable = true;
        } else {
            holder.tv_state.setText(this.context.getString(R.string.dial_state_install));
            data.enable = true;
        }
        holder.tv_state.setEnabled(data.enable);
        holder.tv_state.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.adapter.DialAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (DialAdapter.this.setDialListener != null) {
                    DialAdapter.this.setDialListener.callback(position);
                }
            }
        });
        if (data.isDelete) {
            holder.tv_state.setVisibility(8);
            holder.tv_update.setVisibility(8);
            holder.tv_progress.setVisibility(8);
            holder.progressBar.setVisibility(8);
        } else {
            holder.tv_state.setVisibility(0);
        }
        if (data.isCanDelete && data.isDelete && (data.state == 3 || data.state == 4 || data.state == 5 || data.state == 6)) {
            holder.iv_delete.setVisibility(0);
        } else {
            holder.iv_delete.setVisibility(8);
        }
        holder.iv_delete.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.dial.adapter.DialAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (DialAdapter.this.setDialListener != null) {
                    DialAdapter.this.setDialListener.delete(position);
                }
            }
        });
        return convertView;
    }

    private static class Holder {
        private ImageView iv_delete;
        private ImageView iv_done;
        private ImageView iv_head;
        private ProgressBar progressBar;
        private TextView tv_name;
        private TextView tv_progress;
        private TextView tv_state;
        private TextView tv_update;

        private Holder() {
        }
    }

    public void setDataChanged(List<DialResultBean.Data> datas) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setListener(SetDialListener setDialListener) {
        this.setDialListener = setDialListener;
    }
}
