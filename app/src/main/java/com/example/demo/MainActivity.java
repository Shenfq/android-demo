package com.example.demo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

public class MainActivity extends AppCompatActivity {
    public String TAG = "log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String returnData = data.getStringExtra("data_return");
            Log.d(TAG, "onActivityResult: " + returnData);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.baidu_item:
                Intent baidu = new Intent(Intent.ACTION_VIEW);
                baidu.setData(Uri.parse("http://www.baidu.com"));
                startActivity(baidu);
                break;
            case R.id.about_item:
                // 显示 Intent
                Toast.makeText(this, "You click About", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("extra_data", "Hello SecondActivity");
                startActivityForResult(intent, 1);
                break;
            case R.id.exit_item:
                finish();
                break;
        }
        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_div:
            case R.id.btn_dot: {
                Button btn = (Button) view;
                String strAdded = btn.getText().toString();
                TextView formula = findViewById(R.id.formula_area);
                String strContent = formula.getText().toString();
                String strNewContent = strContent + strAdded;
                formula.setText(strNewContent);
            }
            break;

            case R.id.btn_c: {
                TextView formula = findViewById(R.id.formula_area);
                formula.setText("");

                TextView result = findViewById(R.id.result_area);
                result.setText("");
            }
            break;

            case R.id.btn_del: {
                TextView formula = findViewById(R.id.formula_area);
                String strContent = formula.getText().toString();
                if (strContent.length() > 0) {
                    strContent = strContent.substring(0, strContent.length() - 1);
                    formula.setText(strContent);
                }
            }
            break;

            case R.id.btn_equ: {
                TextView formula = findViewById(R.id.formula_area);
                String strContent = formula.getText().toString();

                try {
                    Symbols s = new Symbols();
                    double res = s.eval(strContent);

                    TextView result = findViewById(R.id.result_area);
                    result.setText(String.valueOf(res));

                    formula.setText("");

                } catch (SyntaxException e) {
                    Toast.makeText(MainActivity.this, "错误！", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }

        // Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show();
    }
}
