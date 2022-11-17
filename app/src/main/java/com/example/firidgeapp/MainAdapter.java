package com.example.firidgeapp;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>
{
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    public MainAdapter(Activity context, List<MainData> dataList)
    {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.ViewHolder holder, int position)
    {
        final MainData data = dataList.get(position);
        database = RoomDB.getInstance(context);
        holder.textView.setText(data.getText());
        holder.textView2.setText(data.getText2());
        holder.textView3.setText(data.getText3());
        holder.textView4.setText(data.getText4());
        holder.btEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainData mainData = dataList.get(holder.getAdapterPosition());

                final int sID = mainData.getId();
                String sText = mainData.getText();
                String sText2 = mainData.getText3();
                String sText3 = mainData.getText2();
                String sText4 = mainData.getText4();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width, height);

                dialog.show();

                final EditText editText = dialog.findViewById(R.id.dialog_edit_text);
                final EditText editText2 = dialog.findViewById(R.id.dialog_edit_text2);
                final EditText editText3 = dialog.findViewById(R.id.dialog_edit_text3);
                final EditText editText4 = dialog.findViewById(R.id.dialog_edit_text4);
                Button bt_update = dialog.findViewById(R.id.bt_update);

                editText.setText(sText);
                editText2.setText(sText2);
                editText3.setText(sText3);
                editText4.setText(sText4);
                bt_update.setOnClickListener(new View.OnClickListener()
                {

                    @Override
                    public void onClick(View v)
                    {

                        dialog.dismiss();
                        String uText = editText.getText().toString().trim();
                        String uText2 = editText2.getText().toString().trim();
                        String uText3 = editText3.getText().toString().trim();
                        String uText4 = editText4.getText().toString().trim();
                        database.mainDao().update(sID, uText,uText2,uText3,uText4);

                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();

                    }

                });
            }

        });

        /* 삭제 클릭 */
        holder.btDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainData mainData = dataList.get(holder.getAdapterPosition());

                database.mainDao().delete(mainData);

                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, dataList.size());
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView,textView2,textView3,textView4;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View view)
        {
            super(view);
            textView = view.findViewById(R.id.text_view);
            textView2 = view.findViewById(R.id.tv_result);
            textView3 = view.findViewById(R.id.abcData);
            textView4 = view.findViewById(R.id.text_memo);
            btEdit = view.findViewById(R.id.bt_edit);
            btDelete = view.findViewById(R.id.bt_delete);
        }
    }

}