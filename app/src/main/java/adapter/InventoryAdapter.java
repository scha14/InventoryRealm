package adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import inventory.realm.app.jsmtech.inventoryrealm.R;
import io.realm.Realm;
import model.Inventory;


/**
 * Created by Sukriti on 6/22/16.
 */
public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.RV_ViewHolder> {

    private List<Inventory> listOfItems;
    private Context mContext;
    View itemView;
    private Realm r;


    public InventoryAdapter(Context context, ArrayList<Inventory> listOfItems) {
        this.mContext = context;
        this.listOfItems = listOfItems;

    }

    @Override
    public RV_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_inventory, parent, false); // link to xml
        return new RV_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RV_ViewHolder holder, int position) {
        final Inventory t = listOfItems.get(holder.getAdapterPosition());
        holder.mItemName.setText(t.getItemName());
        holder.mQuantity.setText(t.getQuantity()+"");
        r = Realm.getInstance(mContext);


        // onclick listeners

        holder.mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Edit Quantity");
// Set up the input
                final EditText input = new EditText(mContext);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        r.beginTransaction();
                        t.setQuantity(Integer.parseInt(input.getText().toString()));
                        r.commitTransaction();
                        holder.mQuantity.setText(input.getText().toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();


            }
        });


        holder.mMinusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.beginTransaction();
                t.setQuantity(t.getQuantity()-1);
                r.commitTransaction(); // This Transaction has nothing to do with the app

                holder.mQuantity.setText(t.getQuantity()+"");
            }
        });


        holder.mPlusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.beginTransaction();
                t.setQuantity(t.getQuantity()+1);
                r.commitTransaction();;
                holder.mQuantity.setText(t.getQuantity()+"");

            }
        });
    }


    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    public class RV_ViewHolder extends RecyclerView.ViewHolder {

        protected TextView mItemName;
        protected TextView mQuantity;
        protected ImageButton mEdit;
        protected ImageButton mPlusOne;
        protected ImageButton mMinusOne;

        public RV_ViewHolder(View itemView) {
            super(itemView);

            mItemName = (TextView) itemView.findViewById(R.id.item_name);
            mQuantity = (TextView) itemView.findViewById(R.id.item_quantity);
            mEdit = (ImageButton) itemView.findViewById(R.id.edit);
            mPlusOne = (ImageButton) itemView.findViewById(R.id.plus_one);
            mMinusOne = (ImageButton) itemView.findViewById(R.id.minus_one);


        }


    }

}



