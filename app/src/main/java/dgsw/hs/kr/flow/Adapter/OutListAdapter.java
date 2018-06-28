package dgsw.hs.kr.flow.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dgsw.hs.kr.flow.Model.OutListItem;
import dgsw.hs.kr.flow.R;

/**
 * Created by 조현재 on 2018-06-25.
 */

public class OutListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<OutListItem> data;
    private int layout;

    public OutListAdapter(Context context, int layout, ArrayList<OutListItem> data){
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
        if(convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }
        OutListItem outListItem = data.get(position);
        TextView start_time = convertView.findViewById(R.id.tv_start_time);
        TextView end_time = convertView.findViewById(R.id.tv_end_time);
        TextView reason = convertView.findViewById(R.id.tv_reason);
        TextView isAccept = convertView.findViewById(R.id.tv_isAccept);

        start_time.setText(outListItem.getStart_date());
        end_time.setText(outListItem.getEnd_date());
        reason.setText(outListItem.getReason());

        if(outListItem.getAccept() == 0){
            isAccept.setText("승인 대기중.");
        }
        else if(outListItem.getAccept() == 1){
            isAccept.setText("승인됨.");
        }

        return convertView;
    }
}
