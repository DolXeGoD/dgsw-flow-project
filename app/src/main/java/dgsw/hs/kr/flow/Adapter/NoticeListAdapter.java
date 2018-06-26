package dgsw.hs.kr.flow.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dgsw.hs.kr.flow.Model.NoticeListItem;
import dgsw.hs.kr.flow.R;

/**
 * Created by 조현재 on 2018-06-21.
 */

public class NoticeListAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private ArrayList<NoticeListItem> data;
    private int layout;

    public NoticeListAdapter(Context context, int layout, ArrayList<NoticeListItem> data){
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data=data;
        this.layout=layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        NoticeListItem noticeListItem = data.get(position);
        TextView title = convertView.findViewById(R.id.tv_notice_title);
        title.setText(noticeListItem.getTitle());
        return convertView;
    }
}
