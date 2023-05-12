package com.google.mlkit.vision.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Calorie extends AppCompatActivity {
    TextView outtext;
    EditText age;
    EditText height;
    EditText weight;
    RadioGroup selectgender;
    TextView bmiout;
    TextView statusout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);
        outtext = findViewById(R.id.outtext);
        age = findViewById(R.id.age);
        height = findViewById(R.id.height1);
        weight = findViewById(R.id.weight1);
        selectgender = findViewById(R.id.gender);
        bmiout = findViewById(R.id.bmiout);
        statusout = findViewById(R.id.statusout);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
//        if(id==R.id.menu_contact){
//            Toast.makeText(this, "Opening Contact Us.", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(this,MainActivity6.class);
//            startActivity(i);
//        }
//        if(id==R.id.menu_about){
//            Toast.makeText(this, "Opening About Us.", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(this,MainActivity8.class);
//            startActivity(i);
//        }
        if(id==R.id.menu_home){
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
    public void calc(View view){
        int selected = selectgender.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selected);
        String gender = radioButton.getText().toString();
        double weight_val = Integer.parseInt(weight.getText().toString());
        double height_val = Integer.parseInt(height.getText().toString());
        double age_val = Integer.parseInt(age.getText().toString());
        double height_in_meters= height_val/100;
        double bmi_val= weight_val/(height_in_meters*height_in_meters);
        if(gender.equals("Male")){
            double bmr_val = 10*weight_val+6.25*height_val-5*age_val+5;
            String bmr = String.format("%.2f",bmr_val);
            outtext.setText("Your Recomended Daily Calorie Input is : "+bmr+"Kcal");
//            bmiout.setText(String.format("%.2f",bmi_val));
            bmiout.setText("Your BMI is: "+String.format("%.2f",bmi_val));
            if(bmi_val<=18.5){
                statusout.setText("You Are Underweight");
            }
            else if(bmi_val>18.5 && bmi_val<25){
                statusout.setText("You have Normal BMI");
            }
            else if(bmi_val>=25 && bmi_val<30){
                statusout.setText("You are Overweight");
            }
            else if(bmi_val>=30 && bmi_val<35){
                statusout.setText("You are Obese");
            }
            else if(bmi_val>=35 && bmi_val<40){
                statusout.setText("You are Highly Obese");
            }
            else{
                statusout.setText("You are Extremely Obese");
            }
        }
        else if(gender.equals("Female")){
            double bmr = 10*weight_val+6.25*height_val-5*age_val-161;
            outtext.setText("Your Recomended Daily Calorie Input is : "+bmr+"Kcal");
            bmiout.setText(String.format("%.2f",bmi_val));
            if(bmi_val<=18.5){
                statusout.setText("You Are Underweight");
            }
            else if(bmi_val>18.5 && bmi_val<25){
                statusout.setText("You have Normal BMI");
            }
            else if(bmi_val>=25 && bmi_val<30){
                statusout.setText("You are Overweight");
            }
            else if(bmi_val>=30 && bmi_val<35){
                statusout.setText("You are Obese");
            }
            else if(bmi_val>=35 && bmi_val<40){
                statusout.setText("You are Highly Obese");
            }
            else{
                statusout.setText("You are Extremely Obese");
            }

        }
        else{
            Toast.makeText(this, "no gender found", Toast.LENGTH_SHORT).show();
        }

    }
}