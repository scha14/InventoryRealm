package inventory.realm.app.jsmtech.inventoryrealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.InventoryAdapter;
import io.realm.Realm;
import io.realm.RealmResults;
import model.Inventory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recList;
    private InventoryAdapter mAdapter;
    private ImageButton mAddButton;
    private ArrayList<Inventory> mInventoryList = new ArrayList<>();
    private Realm r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = Realm.getInstance(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        recList = (RecyclerView) findViewById(R.id.inventory_recycler_view);
        mAddButton = (ImageButton) findViewById(R.id.addButton);

        final LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        mAdapter = new InventoryAdapter(MainActivity.this, mInventoryList);
        recList.setAdapter(mAdapter);



       mAddButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(MainActivity.this, AddNewProduct.class);
               startActivity(i);

           }
       });


        RealmResults<Inventory> results1 =
                r.where(Inventory.class).findAll();


        for(Inventory c : results1) {
            mInventoryList.add(c);
            Toast.makeText(MainActivity.this, "value added " + c.getItemName() + " " + c.getQuantity(), Toast.LENGTH_LONG).show();
        }

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
