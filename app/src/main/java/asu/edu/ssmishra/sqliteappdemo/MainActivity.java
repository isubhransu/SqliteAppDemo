package asu.edu.ssmishra.sqliteappdemo;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBhelper mydb;
    EditText editName, editSurname, editMarks, editID;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBhelper(this);

        editName = (EditText) findViewById(R.id.editText);
        editSurname = (EditText) findViewById(R.id.editText2);
        editMarks = (EditText) findViewById(R.id.editText3);
        editID = (EditText) findViewById(R.id.editText4);
        btnAddData = (Button) findViewById(R.id.button);
        btnViewAll = (Button) findViewById(R.id.viewAll);
        btnUpdate = (Button) findViewById(R.id.updatebtn);
    }

    public void AddData(View view){

        boolean isInserted =  mydb.insertData(editName.getText().toString(),
                                              editSurname.getText().toString(),
                                              editMarks.getText().toString());
        if(isInserted == true){
            Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void viewAll(View view){
        Cursor res = mydb.getData();
        if(res.getCount() == 0){
            showMessage("Error", "Nothing Found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("id: "+ res.getString(0)+"\n");
            buffer.append("Name: "+ res.getString(1)+"\n");
            buffer.append("Surname: "+ res.getString(2)+"\n");
            buffer.append("Marks: "+ res.getString(3)+"\n");
        }
        showMessage("Data", buffer.toString());
    }

    public void updateVal(View view){
        boolean isUpdated = mydb.updateData(editID.getText().toString(), editName.getText().toString(), editSurname.getText().toString(), editMarks.getText().toString());
        if(isUpdated==true){
            Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Data not updated", Toast.LENGTH_LONG).show();
        }

    }

    public  void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
