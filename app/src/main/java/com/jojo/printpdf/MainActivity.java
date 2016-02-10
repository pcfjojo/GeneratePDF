package com.jojo.printpdf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    PDDocument fattura = null;
    EditText editText_details;
    Button btn_create_fattuta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_details = (EditText) findViewById(R.id.editText_details);
        btn_create_fattuta = (Button) findViewById(R.id.btn_create_fattura);
        btn_create_fattuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    makeFattura(editText_details.getText().toString());
                } catch (COSVisitorException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    void makeFattura(String clientDetails) throws COSVisitorException {
        try {
            fattura = new PDDocument();
            PDPage page = new PDPage();
            fattura.addPage( page );


            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDPageContentStream contentStream = new PDPageContentStream(fattura, page);
            contentStream.beginText();
            contentStream.setFont(font, 12);
            contentStream.moveTextPositionByAmount(100, 700);
            contentStream.drawString("Naviger srl. \n Via Degani, \n nr.10 \n Reggio Emilia. Italia." + clientDetails);
            contentStream.endText();
            contentStream.close();
            fattura.save("fattuta.pdf");
            fattura.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
