package hw2.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity   {
    public ArrayList<Item> checkBox = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.btn1);
        for(int i =0 ; i<100 ; i++){
            Item item = new Item();
            item.setText(Integer.toString(i+1));
            item.setCheck(false);
            this.checkBox.add(item);
        }
        // 將select的東西變成字串 再將它印在toast裡
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this.checkBox);
        RecyclerView recyclerView =(RecyclerView) findViewById(R.id.rv1);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void onClick(View view) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this.checkBox);
        RecyclerView recyclerView =(RecyclerView) findViewById(R.id.rv1);
        String selectedItem =adapter.getSelect();
        Toast.makeText(this, "selected:"+selectedItem, Toast.LENGTH_SHORT).show();
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        public ArrayList<Item> checkBoxItems ;
        public RecyclerViewAdapter(ArrayList<Item> checkBoxItem) {
            this.checkBoxItems = checkBoxItem;
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            
            public CheckBox checkBox1 ;

            //constructor
            public ViewHolder(View itemView) {
                super(itemView);
                checkBox1 = (CheckBox) itemView.findViewById(R.id.cb1);
            }
        }
        public String getSelect(){
            StringBuilder stringBuilder = new StringBuilder();
            for (Item n : checkBoxItems){
                if (n.isCheck()){
                    stringBuilder.append(",");
                    stringBuilder.append(n.getText());
                }
            }
            return stringBuilder.toString();
        }

        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items,parent,false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Item item = checkBoxItems.get(position);
            holder.checkBox1.setText(item.getText());
            holder.checkBox1.setChecked(item.isCheck());
            holder.checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    holder.checkBox1.setChecked(b);
                    checkBoxItems.get(position).setCheck(b);
                }
            });
        }
        @Override
        public int getItemCount() {
            Log.d("msg", String.valueOf(checkBoxItems.size()));
            return checkBoxItems.size();
        }
    }

    public static class Item{
        String text;
        boolean check;
        public String getText(){
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }
        public boolean isCheck(){
            return check;
        }
        public void setCheck(boolean check){
            this.check = check;
        }
    }

}

