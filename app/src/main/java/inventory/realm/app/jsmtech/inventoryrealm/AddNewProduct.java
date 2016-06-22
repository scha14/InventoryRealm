package inventory.realm.app.jsmtech.inventoryrealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import model.Inventory;

/**
 * Created by Sukriti on 6/22/16.
 */
public class AddNewProduct extends AppCompatActivity {

    private TextView mProductName;
    private TextView mProductQuantity;
    private Button mAddProduct;
    private Realm r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        r = Realm.getInstance(this);

        mProductName = (TextView) findViewById(R.id.item_name);
        mProductQuantity = (TextView) findViewById(R.id.item_quantity);
        mAddProduct = (Button) findViewById(R.id.add_product);


        mAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(AddNewProduct.this, "Clicked", Toast.LENGTH_SHORT).show();

                String tN = mProductName.getText().toString();
                String temp = mProductQuantity.getText().toString();

                if (tN.isEmpty() || temp.isEmpty()) {
                    Toast.makeText(AddNewProduct.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Add new Transaction and save to database using Realm.
             //       Toast.makeText(AddNewProduct.this, "Else!", Toast.LENGTH_LONG).show();
                    int tA = Integer.parseInt(mProductQuantity.getText().toString());
             //       Toast.makeText(AddNewProduct.this, "Int Value " + tA , Toast.LENGTH_LONG).show();

                    r.beginTransaction();

                    Inventory t = r.createObject(Inventory.class);
                    t.setItemName(tN);
                    t.setQuantity(tA);

                    r.commitTransaction(); // This Transaction has nothing to do with the app

                    Toast.makeText(AddNewProduct.this, "Product Added!", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(AddNewProduct.this, MainActivity.class);
                        startActivity(i);
                        finish();

                }
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent i = new Intent(AddNewTransaction.this, MainActivity.class);
//        startActivity(i);
//        finish();
//    }
}

