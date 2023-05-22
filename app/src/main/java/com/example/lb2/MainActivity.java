package com.example.lb2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button ButtonOutput; //Кнопка вывода
    public int select_Item;
    private EditText input_StartData,input_StartRange,input_OilCoast,output_OilCoast,output_OneKm;

    String[] caldMode = { "Расход на 100 км", "Расход на 1000 км"};//Массив для режима калькулятора

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView selectionCalcMode = findViewById(R.id.textModeCalc); //Идентификатор выбора режима калькулятора
        Spinner spinner = findViewById(R.id.spinnerModeSelect);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, caldMode);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);


        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                selectionCalcMode.setText(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);


        // =========================================================================== //
        Spinner ct;

        ct = (Spinner) findViewById(R.id.spinnerModeSelect);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.calcMode);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ваш выбор: " + choose[selectedItemPosition], Toast.LENGTH_SHORT);
                toast.show();
                select_Item = selectedItemPosition;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//        ///Элементы
        input_StartData = findViewById(R.id.input_StartData);
        input_StartRange = findViewById(R.id.input_StartRange);

        input_OilCoast = findViewById(R.id.input_OilCoast);
        output_OilCoast = findViewById(R.id.output_OilCoast);
        output_OneKm = findViewById(R.id.output_OneKm);

        ButtonOutput = findViewById(R.id.ButtonOutput);
//
//
//        //Подсчет
             ButtonOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (select_Item == 0)
                {
                    float oilUsed = Float.parseFloat(input_StartData.getText().toString()) ; //Израсходовано топлива
                    float rangeUsed = Float.parseFloat(input_StartRange.getText().toString()) ; //Пройденное расстояние
                    float oilCoast = Float.parseFloat(input_OilCoast.getText().toString()) ; // Стоимость топлива
                    float result_per100 = oilUsed/rangeUsed * 100;
                    float result_per1 = result_per100 * oilCoast / 100;

                    output_OilCoast.setText(String.valueOf(result_per100));
                    output_OneKm.setText(String.valueOf(result_per1));

                }
                if (select_Item == 1)
                {
                    float oilUsed = Float.parseFloat(input_StartData.getText().toString()) ; //Израсходовано топлива
                    float rangeUsed = Float.parseFloat(input_StartRange.getText().toString()) ; //Пройденное расстояние
                    float oilCoast = Float.parseFloat(input_OilCoast.getText().toString()) ; // Стоимость топлива
                    float result_per100 = oilUsed/rangeUsed * 1000;
                    float result_per1 = result_per100 * oilCoast / 1000;

                    output_OilCoast.setText(String.valueOf(result_per100));
                    output_OneKm.setText(String.valueOf(result_per1));
                }

            }
        });





    }
}